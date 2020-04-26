
import com.fasterxml.jackson.databind.ObjectMapper;
import de.vandermeer.asciitable.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class RedesPetri {

		/*NESSA CLASSE SERÁ LIDO O ARQUIVO JSON QUE ESPECIFICA UMA REDE,
		* MONTADA A REDE E EXECUTADA*/

	private final static ObjectMapper mapper = new ObjectMapper();
	private final static List<Lugar> lugares = new ArrayList<>();
	private static List<Estado> estadoLugares = new ArrayList<>();
	private final static List<Transicao> transicoes = new ArrayList<>();
	private static List<Estado> estadoTransicoes = new ArrayList<>();
	private final static List<Conexao> conexoes = new ArrayList<>();
	private final static Map<Long, Nodo> mapa = new HashMap<>();
	private static Rede rede = new Rede();
	private static boolean verificado;

	public static void main(String... args) {
		leArquivo(testFile()); // Deverá ser passsado o arg[0] na versão final
		menu();
	}

	private static void menu() { // menu principal
		Scanner in = new Scanner(System.in);
		int opcao;
		do {
			System.out.println("0 - Finalizar o programa");
			System.out.println("1 - Verifica o estado atual da rede");
			System.out.println("2 - Executa um ciclo e exibe o resultado");
			System.out.println("3 - Executa x ciclos e exibe o resultado");
			opcao = in.nextInt();
			System.out.print("\n");
			switch (opcao) {
				case 0:
					break;
				case 1:
					verifica();
					break;
				case 2:
					executa(true);
					break;
				case 3:
					System.out.println("Quantos ciclos deseja executar:");
					int total = in.nextInt();
					rodaNciclos(total);
					break;
				default:
					System.out.println("Opção Inválida!");
					break;
			}
		} while (opcao != 0);
	}

	/**
	 * Método temporário, utilizado para testes, deve ser removido, passa o endereço do arquivo input
	 * @return - o local do arquivo rede.json
	 */
	public static String testFile() {
		String root = Paths.get("").toAbsolutePath().toString();
		String path = "\\files\\rede.json";
		return root + path;
	}

	public static void leArquivo(final String arquivo) {
		try {
			processaArquivo(arquivo);
			montaRede();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Ocorreu um problema ao ler o arquivo: " + arquivo);
		}
	}

	public static void processaArquivo(final String arquivo) throws IOException {
		File json = new File(arquivo).getCanonicalFile();
		rede = mapper.readValue(json, Rede.class);
	}

	/**
	 * Metódo que monta a rede após a leitura do arquivo de entrada
	 */
	public static void montaRede() {
		populaMapa(rede.getNos());
		geraConexoes(rede.getArcos());
		montaConexoes();
	}

	/**
	 * Popula o mapa da rede com os nos, e também cria os lugares e transições e os colocam em suas devidas listas
	 * @param nos - a lista de nos a partir da qual o mapa será populado
	 */
	private static void populaMapa(final List<Nodo> nos) {
		for (Nodo no : nos) {
			if (no.getTipo() == NodeType.LUGAR.ordinal()) {
				final Lugar lugar = criaLugar(no);
				lugares.add(lugar);
				mapa.put(no.getUid(), lugar);
			} else if (no.getTipo() == NodeType.TRANSICAO.ordinal()) {
				final Transicao transicao = criaTransicao(no);
				transicoes.add(transicao);
				mapa.put(no.getUid(), transicao);
			}
		}
	}

	/**
	 * Gera as conexões da rede a partir da lista de arcos do input
	 * @param arcos - a lista de arcos
	 */
	private static void geraConexoes(List<Arco> arcos) {
		for (Arco arco : arcos) {
			final Nodo origem = mapa.get(arco.getOrigem());
			final Nodo destino = mapa.get(arco.getDestino());
			final Conexao conexao = new Conexao(origem, destino, arco.getTotal());
			conexoes.add(conexao);
		}
	}

	/**
	 * Percorre a lista de conexões geradas e faz a vinculação das conexões com os nós
	 */
	private static void montaConexoes() {
		for (Conexao conexao : conexoes) {
			final Lugar lugar = conexao.getLugar();
			final Transicao transicao = conexao.getTransicao();
			if (conexao.isSaida()) {
				lugar.addDestino(conexao);
				transicao.addOrigem(conexao);
			} else {
				transicao.addDestino((conexao));
			}
		}
	}

	/**
	 * Função utilizada na montagem da rede, cria um lugar
	 * @param nodo - O nó a partir do qual o lugar será criado
	 * @return - O lugar criado
	 */
	private static Lugar criaLugar(Nodo nodo) {
		return new Lugar(nodo.getId(), nodo.getNome(), nodo.getTotal());
	}

	/**
	 * Função utilizada na montagem da rede, cria uma transição
	 * @param nodo - O nó a partir do qual a transição será criada
	 * @return - A transição criada
	 */
	private static Transicao criaTransicao(Nodo nodo) {
		return new Transicao(nodo.getId(), nodo.getNome(), nodo.getTotal());
	}


	/**
	 * Não deve ser chamada pelo menu, serve de interface
	 */
	private static void preparaCiclo() {
		for (Lugar lugar : lugares) {
			lugar.escolheTransicao();
		}
		for (Transicao transicao : transicoes) {
			transicao.checaTransicao();
		}
	}

	/**
	 * Não deve ser chamada pelo menu, serve de interface
	 */
	private static void executaCiclo() {
		for (Transicao transicao : transicoes) {
			transicao.executaTransicao();
		}
	}

	/**
	 * Função a ser chamada pelo menu para verificar estado atual da rede
	 */
	private static void verifica() {
		preparaCiclo();
		getEstados();
		renderTable();
		verificado = true;
	}

	/**
	 * Função a ser chamada pelo menu para executar 1 ciclo da rede
	 */
	private static void executa(final boolean first) {
		if (!verificado) {
			preparaCiclo();
		}
		if (first) {
			getEstados();
			renderTable();
		}
		executaCiclo();
		preparaCiclo();
		getEstados();
		renderTable();
		// chamar preparar ciclo para verificar o estado das transições após o primeiro ciclo
	}

	/**
	 * Função a ser chamada pelo menu para executar n ciclos da rede
	 * @param total - o número de ciclos a serem executados
	 */
	private static void rodaNciclos(int total) {
		int cnt = total;
		while (cnt > 0) {
			executa(cnt == total);
			cnt--;
		}
	}

	/**
	 * Função chamada para exibir os resultados da rede.
	 * Exibe as alterações ocorridas para Lugares e Transições.
	 */

	public static void renderTable() {
		renderLugares();
		renderTransicoes();
	}

	/**
	 * Renderiza a tabela de lugares
	 * nomeLugar instancia os nomes para cada um dos lugares presentes na rede.
	 * marcasLugar representa as marcas disponíveis em cada lugar da rede.
	 */

	public static void renderLugares() {
		AsciiTable lt = new AsciiTable();

		List<String> nomes = new ArrayList<>();
		nomes.add("Lugares");
		List<String> status = new ArrayList<>();
		status.add("Marcas");

		for (Estado estado : estadoLugares) {
			nomes.add(estado.getNome());
			status.add(String.valueOf(estado.getMarcas()));
		}

		// Adiciona o cabeçalho na LugaresTable
		lt.addRule();
		lt.addRow(nomes);
		lt.addRule();
		// Adiciona a quantidade de marcas disponíveis na LugaresTable
		lt.addRow(status);
		lt.addRule();
		// Setta a largura da LugaresTable
		lt.getContext().setWidth(30);

		String rend = lt.render();
		System.out.println(rend);
	}

	/**
	 * Renderiza a tabela de transições
	 * nomeTransição instancia os nomes para cada uma das transições presentes na rede.
	 * habilitadaTransição representa se dada transição foi habilitada.
	 */
	public static void renderTransicoes() {
		AsciiTable tt = new AsciiTable();

		List<String> nomes = new ArrayList<>();
		nomes.add("Transições");
		List<String> status = new ArrayList<>();
		status.add("Habilitada");

		// Instancia o nome da categoria na primeira posição da linha

		for (Estado estado : estadoTransicoes) {
			nomes.add(estado.getNome());
			status.add(estado.isHabilitado() ? "S" : "N");
		}

		// Adiciona o cabeçalho na TransicaoTable
		tt.addRule();
		tt.addRow(nomes);
		tt.addRule();
		// Adiciona o estado da transição na TransicaoTable
		tt.addRow(status);
		tt.addRule();
		// Setta a largura da TransicaoTable
		tt.getContext().setWidth(30);

		String rend = tt.render();
		System.out.println(rend);
	}

	private static void getEstados() {
		final List<Estado> el = new ArrayList<>();
		final List<Estado> et = new ArrayList<>();
		lugares.forEach(lugar -> el.add(lugar.getEstado()));
		transicoes.forEach(transicao -> et.add(transicao.getEstado()));
		estadoLugares = el;
		estadoTransicoes = et;
	}

}

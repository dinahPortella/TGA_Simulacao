
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class RedesPetri {

		/*NESSA CLASSE SERÁ LIDO O ARQUIVO JSON QUE ESPECIFICA UMA REDE,
		* MONTADA A REDE E EXECUTADA*/

		/*ALGUSN MÉTODOS QUE USO AQUI AINDA NÃO EXISTEM, PRECISAM SER CRIADOS*/

	final static ObjectMapper mapper = new ObjectMapper();
	private static List<Lugar> lugares = new ArrayList<>();
	private static List<Transicao> transicoes = new ArrayList<>();
	private static List<Conexao> conexoes = new ArrayList<>();
	private static Map<Long, Nodo> mapa = new HashMap<>();
	private static Rede rede = new Rede();
	private static List<Nodo> nodos;
	private static String nome;
	private static boolean preparado;
	/*Arquivo JSON  = Objeto hipotético que seria a rede lida de um JSON*/
	
//	public RedesPetri(String nome, Arquivo JSON) {
//		super();
//		this.nome = nome;
//		montaRede(JSON);
//	}

	/*NESSE MÉTODO, APLICA O JACKSON PRA LER O JSON E DESSERIALIZAR OS OBJETOS*/
//	private void montaRede(Arquivo JSON){
//	}

	public static void main(String... args) {
		System.out.println("olá");
		leArquivo(testFile()); // Deverá ser passsado o arg[0] na versão final
		System.out.println("ok");
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
	 * Popula o mapa da rede com os nos, e também cria os lugares e transições e os poem em suas devidas listas
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
		preparado = true;
	}

	/**
	 * Função a ser chamada pelo menu para executar 1 ciclo da rede
	 */
	private static void executa() {
		if (!preparado) {
			preparaCiclo();
		}
		executaCiclo();
		preparado = false;
	}

	/**
	 * Função a ser chamada pelo menu para executar n ciclos da rede
	 * @param total - o número de ciclos a serem executados
	 */
	private static void rodaNciclos(int total) {
		while (total > 0) {
			executa();
			total--;
		}
	}


	
	public List<Lugar> getLugares() {
		return lugares;
	}
	public List<Transicao> getTransicoes() {
		return transicoes;
	}
	public List<Conexao> getConexaos() {
		return conexoes;
	}
	
	/*
	@Override
	public String toString() {
		return null;
		
	}*/
}

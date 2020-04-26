import java.util.*;

public class RedesPetri {

		/*NESSA CLASSE SERÁ LIDO O ARQUIVO JSON QUE ESPECIFICA UMA REDE,
		* MONTADA A REDE E EXECUTADA*/

		/*ALGUSN MÉTODOS QUE USO AQUI AINDA NÃO EXISTEM, PRECISAM SER CRIADOS*/

	private List<Lugar> lugares = new ArrayList<Lugar>();
	private List<Transicao> transicoes = new ArrayList<Transicao>();
	private List<Conexao> conexoes = new ArrayList<Conexao>();
	private Map<Long, Nodo> mapa = new HashMap<>();
	private Rede rede = new Rede();
	private List<Nodo> nodos;
	private String nome;
	private boolean preparado;
	/*Arquivo JSON  = Objeto hipotético que seria a rede lida de um JSON*/
	
//	public RedesPetri(String nome, Arquivo JSON) {
//		super();
//		this.nome = nome;
//		montaRede(JSON);
//	}

	/*NESSE MÉTODO, APLICA O JACKSON PRA LER O JSON E DESSERIALIZAR OS OBJETOS*/
//	private void montaRede(Arquivo JSON){
//	}


	public void processaArquivo(final String arquivo) {

	}

	/**
	 * Metódo que monta a rede após a leitura do arquivo de entrada
	 */
	public void MontaRede() {
		populaMapa(rede.getNos());
		geraConexoes(rede.getArcos());
		montaConexoes();
	}

	/**
	 * Popula o mapa da rede com os nos, e também cria os lugares e transições e os poem em suas devidas listas
	 * @param nos - a lista de nos a partir da qual o mapa será populado
	 */
	private void populaMapa(final List<Nodo> nos) {
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
	private void geraConexoes(List<Arco> arcos) {
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
	private void montaConexoes() {
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
	private Lugar criaLugar(Nodo nodo) {
		final Lugar out = new Lugar(nodo.getId(), nodo.getNome(), nodo.getTotal());
		return out;
	}

	/**
	 * Função utilizada na montagem da rede, cria uma transição
	 * @param nodo - O nó a partir do qual a transição será criada
	 * @return - A transição criada
	 */
	private Transicao criaTransicao(Nodo nodo) {
		final Transicao out = new Transicao(nodo.getId(), nodo.getNome(), nodo.getTotal());
		return out;
	}

	/**
	 * Não deve ser chamada pelo menu, serve de interface
	 */
	private void preparaCiclo() {
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
	private void executaCiclo() {
		for (Transicao transicao : transicoes) {
			transicao.executaTransicao();
		}
	}

	/**
	 * Função a ser chamada pelo menu para verificar estado atual da rede
	 */
	private void verifica() {
		preparaCiclo();
		preparado = true;
	}

	/**
	 * Função a ser chamada pelo menu para executar 1 ciclo da rede
	 */
	private void executa() {
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
	private void rodaNciclos(int total) {
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

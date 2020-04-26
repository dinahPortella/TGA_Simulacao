import java.util.ArrayList;
import java.util.List;

public class Transicao extends Nodo {

	private final List<Conexao> entradas;
	private final List<Conexao> saidas;
	private int estado;
	private int escolhida;

	public Transicao (int id, String nome, int rec) {
		super(id, nome, NodeType.TRANSICAO.ordinal(), rec);
		entradas = new ArrayList<>();
		saidas = new ArrayList<>();
	}

	/**
	 * Adiciona uma entrada na lista de entradas da transição
	 * @param origem - A entrada a ser inserida
	 */
	public void addOrigem(Conexao origem) {
		this.entradas.add(origem);
	}

	/**
	 * Adiciona uma saída na lsita de saídas da transição
	 * @param destino - A saída a ser adicionada
	 */
	public void addDestino(Conexao destino) {
		this.saidas.add(destino);
	}

	/**
	 * Diz se a transição está habilitada ou não de acordo com o seu estado
	 * @return - Um boolean indicando se a transição está ou não habilitada
	 */
	private boolean habilitada() {

		return estado == TransitionState.HABILITADA_ESCOLHIDA.ordinal() || estado == TransitionState.HABILITADA_N_ESCOLHIDA.ordinal();
	}

	/**
	 * Chama os métodos que fazem a checagem da transição
	 */
	public void checaTransicao() {
		checaEscolha();
		boolean escolhida = estado == TransitionState.ESCOLHIDA.ordinal();
		if (checkOrigins(escolhida)) {
			if (escolhida) {
				estado = TransitionState.HABILITADA_ESCOLHIDA.ordinal();
			} else {
				estado = TransitionState.HABILITADA_N_ESCOLHIDA.ordinal();
			}
		} else {
			estado = TransitionState.VERIFICADA.ordinal();
		}
	}

	/**
	 * Faz a checagem para ver se uma transição foi escolhida por todas as suas entradas
	 * A checagem só é feita se a transição está no estado ociosa, ou seja não foi verificada
	 * Isso é feito para previnir a checagem desnecessária
	 */
	private void checaEscolha() {
		if (estado == TransitionState.OCIOSA.ordinal()) {
			if (escolhida == entradas.size()) {
				estado = TransitionState.ESCOLHIDA.ordinal();
			} else {
				estado = TransitionState.VERIFICADA.ordinal();
			}
		}
	}

	/**
	 * Checa todas as entradas verificando se elas tem as marcas necessárias para executar a transição
	 * Se todos as entradas possuem marcas suficientes, também é feita a reserva das marcas em preparação para execução
	 * @return - Um boolean indicando se todas as entradas possuem marcas suficientes ou não
	 */
	public boolean checkOrigins(boolean escolhida) {
		boolean out = true;
		for (Conexao conexao : entradas) {
			if (conexao.getLugar().getTotal() < conexao.getTotal()) {
				out = false;
				break;
			}
		}
		if (escolhida) {
			if (out) {
				for (Conexao conexao : entradas) {
					int necessario = conexao.getTotal();
					conexao.getLugar().preparaTransicao(necessario);
				}
			}
		}
		return out;
	}

	/**
	 * Executa a transição, consumindo marcas e resetando o estado em preparação para o próximo ciclo
	 */
	public void executaTransicao() {
		if(estado == TransitionState.HABILITADA_ESCOLHIDA.ordinal()) {
			consomeMarcas();
			passaMarcas();
		}
		this.resetaTransicao();
	}

	/**
	 * Método chamado após executar uma transição, independente de estar habilitada ou não, reseta a transição preparando para o próximo ciclo
	 */
	public void resetaTransicao() {
		for (Conexao origem : entradas) {
			origem.getLugar().resetaReserva();
		}
		estado = TransitionState.OCIOSA.ordinal();
		escolhida = 0;
	}

	/**
	 * Consome as marcas do lugar de origem
	 */
	public void consomeMarcas() {
		for (Conexao origem : entradas) {
			origem.getLugar().consomeMarcas();
		}
	}

	/**
	 * Passa as marcas do lugar de origem para os lugares de destino
	 */
	public void passaMarcas() {
		for (Conexao destino : saidas) {
			int increase = destino.getTotal();
			destino.getLugar().ganhaMarcas(increase);
		}
	}

	/**
	 * Método chamado pelo lugar para escolher uma transição, conta quantos lugares escolheram a transição
	 */
	public void escolhe() {
		escolhida++;
	}

	/**
	 * Gera um objeto que contém o estado atual da transição
	 * @return - O objeto contendo informações do estado atual
	 */
	public Estado getEstado() {
		return new Estado(getTipo(), getNome(), habilitada(), 0);
	}

}

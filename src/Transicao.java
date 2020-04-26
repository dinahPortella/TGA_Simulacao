import java.util.ArrayList;
import java.util.List;

public class Transicao extends Nodo {

	private List<Conexao> entradas;
	private List<Conexao> saidas;
	private int estado;
	private int escolhida;

	public Transicao (int id, String nome, int rec) {
		super(id, nome, NodeType.TRANSICAO.ordinal(), rec);
		entradas = new ArrayList<>();
		saidas = new ArrayList<>();
	}

	public List<Conexao> getEntradas() {
		return entradas;
	}

	public void setEntradas(List<Conexao> entradas) {
		this.entradas = entradas;
	}

	public void addOrigem(Conexao origem) {
		this.entradas.add(origem);
	}

	public List<Conexao> getSaidas() {
		return saidas;
	}

	public void setSaidas(List<Conexao> saidas) {
		this.saidas = saidas;
	}

	public void addDestino(Conexao destino) {
		this.saidas.add(destino);
	}

	public boolean habilitada() {
		return estado == TransitionState.HABILITADA.ordinal();
	}

	public void checaTransicao() {
		checaEscolha();
		if (estado == TransitionState.ESCOLHIDA.ordinal()) {
			if (checkOrigins()) {
				estado = TransitionState.HABILITADA.ordinal();
			} else {
				estado = TransitionState.VERIFICADA.ordinal();
			}
		}
	}

	private void checaEscolha() {
		if (estado == TransitionState.OCIOSA.ordinal()) {
			if (escolhida == entradas.size()) {
				estado = TransitionState.ESCOLHIDA.ordinal();
			}
		}
	}

	public boolean checkOrigins() {
		boolean out = true;
		for (Conexao conexao : entradas) {
			if (conexao.getLugar().getTotal() < conexao.getTotal()) {
				out = false;
				break;
			}
		}
		if (out) {
			for (Conexao conexao : entradas) {
				int necessario = conexao.getTotal();
				conexao.getLugar().preparaTransicao(necessario);
			}
		}
		return out;
	}

	public void executaTransicao() {
		if(habilitada()) {
			consomeMarcas();
			passaMarcas();
		}
		this.resetaTransicao();
		estado = TransitionState.OCIOSA.ordinal();
	}

	public void resetaTransicao() {
		for (Conexao origem : entradas) {
			origem.getLugar().resetaReserva();
		}
		estado = TransitionState.OCIOSA.ordinal();
		escolhida = 0;
	}

	public void consomeMarcas() {
		for (Conexao origem : entradas) {
			origem.getLugar().consomeMarcas();
		}
	}

	public void passaMarcas() {
		for (Conexao destino : saidas) {
			int increase = destino.getTotal();
			destino.getLugar().ganhaMarcas(increase);
		}
	}

	public void escolhe() {
		escolhida++;
	}

}

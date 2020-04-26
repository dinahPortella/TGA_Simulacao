
public class Conexao {

	private int total;
	private Lugar lugar;
	private Transicao transicao;
	private boolean saida;

	/*A EXECUÇÃO DA REDE SE DARÁ EM CIMA DOS ARCOS, ELES SERÃO LIDOS E AJUSTAÇÃO AS MARCAS DOS LUGARES*/
	public Conexao(Nodo origem, Nodo destino, int total) {
		if (total < 1) {
			total = 1;
		}
		this.total = total;
		this.defineDirecao(origem, destino);
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public Transicao getTransicao() {
		return transicao;
	}

	public boolean isSaida() {
		return saida;
	}

	private void defineDirecao(Nodo origem, Nodo destino){

		if(origem.getTipo() == NodeType.LUGAR.ordinal() && destino.getTipo() == NodeType.TRANSICAO.ordinal()){
			this.lugar = (Lugar)origem;
			this.transicao = (Transicao)destino;
			saida = true;
		}

		else if(origem.getTipo() == NodeType.TRANSICAO.ordinal() && destino.getTipo() == NodeType.LUGAR.ordinal()){
			this.lugar = (Lugar)destino;
			this.transicao = (Transicao)origem;
			saida = false;
		}
		else {
			throw new IllegalArgumentException("Parâmetro(s) de arco inválido(s)");
		}
	}

}


public class Arco extends Nodo{

	private Lugar lugar;
	private Transicao transicao;

	/*A EXECUÇÃO DA REDE SE DARÁ EM CIMA DOS ARCOS, ELES SERÃO LIDOS E AJUSTAÇÃO AS MARCAS DOS LUGARES*/
	private Arco(String name, Nodo origem, Nodo destino, int total) {
		super(name, NodeType.ARCO.ordinal(), total);
		this.defineDirecao(origem, destino);
	}

	public Lugar getLugar() {
		return lugar;
	}

	public Transicao getTransicao() {
		return transicao;
	}

	private void defineDirecao(Nodo origem, Nodo destino){

		if(origem.getTipo() == NodeType.LUGAR.ordinal() && destino.getTipo() == NodeType.TRANSICAO.ordinal()){
			this.lugar = (Lugar)origem;
			this.transicao = (Transicao)destino;
		}

		else if(origem.getTipo() == NodeType.TRANSICAO.ordinal() && destino.getTipo() == NodeType.LUGAR.ordinal()){
			this.lugar = (Lugar)destino;
			this.transicao = (Transicao)origem;
		}
		else {
			throw new IllegalArgumentException("Parâmetro(s) de arco inválido(s)");
		}
	}

}

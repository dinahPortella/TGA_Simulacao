
public class Arco extends Nodo{

	private Lugar lugar;
	private Transicao transicao;
	private int peso;

	/*A EXECUÇÃO DA REDE SE DARÁ EM CIMA DOS ARCOS, ELES SERÃO LIDOS E AJUSTAÇÃO AS MARCAS DOS LUGARES*/
	private Arco(String name, Nodo origem, Nodo destino, int peso) {
		super();
		this.peso = peso;
		this.defineDirecao(origem, destino);
	}

	public Lugar getLugar() {
		return lugar;
	}

	public Transicao getTransicao() {
		return transicao;
	}

	private void defineDirecao(Nodo origem, Nodo destino){
		if(origem.getClass().getName() == "Lugar" && destino.getClass().getName() == "Transicao"){
			this.lugar = (Lugar)origem;
			this.transicao = (Transicao)destino;
		}
		else if(origem.getClass().getName() == "Transicao" && destino.getClass().getName() == "Lugar"){
			this.lugar = (Lugar)destino;
			this.transicao = (Transicao)origem;
		}
		else {
			throw new IllegalArgumentException("Parâmetro(s) de arco inválido(s)");
		}
	}

}

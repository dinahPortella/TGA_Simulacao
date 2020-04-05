
public class Teste {
	private String pai;
	private String filho;
	private boolean raiz;
	private String transicao;

	public Teste(String pai, String filho, String transicao) {
		super();
		this.setPai(pai);
		this.setFilho(filho);
		setRaiz(false);
		this.setTransicao(transicao);
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getFilho() {
		return filho;
	}

	public void setFilho(String filho) {
		this.filho = filho;
	}

	public boolean isRaiz() {
		return raiz;
	}

	public void setRaiz(boolean raiz) {
		this.raiz = raiz;
	}

	public String getTransicao() {
		return transicao;
	}

	public void setTransicao(String transicao) {
		this.transicao = transicao;
	}
	
	@Override
	public String toString() {
		return pai + "  " + transicao +  "  " + filho + (raiz ? "raiz " : "") + "\n";
	}

}

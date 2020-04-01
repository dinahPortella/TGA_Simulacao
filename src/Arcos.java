
public class Arcos {

	private Lugares lugares;
	private Transicoes transicoes;
	private Direcao direcao;
	
	private Arcos(String name, Direcao d, Lugares p, Transicoes t) {
		super();
		this.setDirecao(d);
		this.setLugares(p);
		this.setTransicoes(t);
	}

	public Lugares getLugares() {
		return lugares;
	}

	public void setLugares(Lugares lugares) {
		this.lugares = lugares;
	}

	public Transicoes getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(Transicoes transicoes) {
		this.transicoes = transicoes;
	}

	public Direcao getDirecao() {
		return direcao;
	}

	public void setDirecao(Direcao direcao) {
		this.direcao = direcao;
	}
}

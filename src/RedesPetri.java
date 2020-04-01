import java.util.ArrayList;
import java.util.List;

public class RedesPetri {


	private List<Lugares> lugares = new ArrayList<Lugares>();
	private List<Transicoes> transicoes = new ArrayList<Transicoes>();
	private List<Arcos> arcos = new ArrayList<Arcos>();
	
	public RedesPetri(String nome) {
		super();
	}
	
	public ArrayList<Arcos> getArcos(Transicoes t, Direcao d) {
		ArrayList<Arcos> list = new ArrayList<>();
		for (Arcos arcos : arcos) {
			if (arcos.getDirecao() == d) {
				if (arcos.getTransicoes().equals(t)) {
					list.add(arcos);
				}
			}
		}
		return list;
	}
	
	public List<Lugares> getLugares() {
		return lugares;
	}
	public void setLugares(List<Lugares> lugares) {
		this.lugares = lugares;
	}
	public List<Transicoes> getTransicoes() {
		return transicoes;
	}
	public void setTransicoes(List<Transicoes> transicoes) {
		this.transicoes = transicoes;
	}
	public List<Arcos> getArcos() {
		return arcos;
	}
	public void setArcos(List<Arcos> arcos) {
		this.arcos = arcos;
	}
	
	/*
	@Override
	public String toString() {
		return null;
		
	}*/
}

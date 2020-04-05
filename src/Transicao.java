import java.util.ArrayList;
import java.util.List;

public class Transicao extends Node {

	private String nome;
	private List<Arco> origem = new ArrayList<Arco>();
	private List<Arco> destino  = new ArrayList<Arco>();
	
	public Transicao (String nome) {
		super();
	}
	
	public void addOrigem(Arco arco) {
	this.origem.add(arco);
}

	public void addDestino(Arco arco) {
	this.destino.add(arco);
}

	public void dispara(){
		/*Escolhe aleatoriamente entre os arcos que this tranicao tem pra disparar, casoo seja mais de um*/
	}


}

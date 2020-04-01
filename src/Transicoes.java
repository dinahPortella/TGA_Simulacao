import java.util.ArrayList;
import java.util.List;

public class Transicoes {
	
	private List<Arcos> origem = new ArrayList<Arcos>();
	private List<Arcos> destino  = new ArrayList<Arcos>();
	
	public Transicoes (String nome) {
		super();
	}
	
public void addOrigem(Arcos arco) {
	this.origem.add(arco);
}

public void addDestino(Arcos arco) {
	this.destino.add(arco);
}


}

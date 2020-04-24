import java.util.ArrayList;
import java.util.List;

public class Transicao extends Nodo {

	private List<Arco> origem;
	private Arco destino;
	private int utilizado;
	private int disponivel;
	private boolean end = false;
	private Transicao proxima;

	public Transicao (String nome, int rec) {
		super(nome, NodeType.TRANSICAO.ordinal(), rec);
	}

	public List<Arco> getOrigem() {
		return origem;
	}

	public void setOrigem(List<Arco> origem) {
		this.origem = origem;
	}

	public void addOrigem(Arco origem) {
		this.origem.add(origem);
	}

	public Arco getDestino() {
		return destino;
	}

	public void setDestino(Arco destino) {
		this.destino = destino;
	}

	public Transicao getProxima() {
		return proxima;
	}

	public void setProxima(Transicao proxima) {
		this.proxima = proxima;
	}

	public void dispara(){
		/*Escolhe aleatoriamente entre os arcos que this tranicao tem pra disparar, casoo seja mais de um*/
	}

	public boolean habilitada() {
		boolean out = true;

		return out;
	}

	public boolean checkOrigins() {
		boolean out = true;
		for (Arco arco : origem) {

		}
		return out;
	}


}

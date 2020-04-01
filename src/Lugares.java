
public class Lugares {
	
	private String nome;
	private int marcas;

	public Lugares(String nome, int marcas) {
		this(nome);
		this.setMarcas(marcas);
	}

	public Lugares(String nome) {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getMarcas() {
		return marcas;
	}

	public void setMarcas(int marcas) {
		this.marcas = marcas;
	}
}

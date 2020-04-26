public class Nodo {

    private int id = 0;
    private String nome;
    private int tipo;
    protected int total;

    public Nodo() {
        super();
    }

    public Nodo(int id, String nome, int tipo, int total) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUid() {
        return Long.parseLong(String.valueOf(tipo) + String.valueOf(id));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

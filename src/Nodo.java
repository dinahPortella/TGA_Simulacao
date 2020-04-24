public class Nodo {

    private String nome;
    private int tipo;
    private int total;

    public Nodo(String nome, int tipo, int total) {
        this.nome = nome;
        this.tipo = tipo;
        this.total = total;
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

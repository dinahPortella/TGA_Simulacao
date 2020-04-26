public class Estado {

    private int tipo;
    private String nome;
    private boolean habilitado;
    private int marcas;

    public Estado(int tipo, String nome, boolean habilitado, int marcas) {
        this.tipo = tipo;
        this.nome = nome;
        this.habilitado = habilitado;
        this.marcas = marcas;
    }

    public String getNomeTipo() {
        return NodeType.values()[tipo].getNome();
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public int getMarcas() {
        return marcas;
    }

    public void setMarcas(int marcas) {
        this.marcas = marcas;
    }
}

public enum NodeType {

    UNDEFIDED("", null), // 0
    LUGAR("Lugar", Lugar.class),
    TRANSICAO("Transição", Transicao.class);

    private final String nome;
    private final Class<? extends Nodo> classe;

    NodeType(String nome, Class<? extends Nodo> classe) {
        this.nome = nome;
        this.classe = classe;
    }

    public String getNome() {
        return nome;
    }

    public Class<? extends Nodo> getClasse() {
        return classe;
    }
}

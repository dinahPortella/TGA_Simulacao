public enum NodeType {

    UNDEFIDED("", null), // 0
    ARCO("Arco", Arco.class),
    LUGAR("Lugar", Lugar.class),
    TRANSICAO("Transição", Transicao.class);

    private final String nome;
    private final Class<? extends Nodo> classe;

    private NodeType(String nome, Class<? extends Nodo> classe) {
        this.nome = nome;
        this.classe = classe;
    }

    private String getNome() {
        return nome;
    }

    private Class<? extends Nodo> getClasse() {
        return classe;
    }
}

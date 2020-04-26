public enum TransitionState {
    UNDEFINED(""),
    OCIOSA("Ociosa"),
    ESCOLHIDA("Escolhida"),
    VERIFICADA("Verificada"),
    HABILITADA("Habilitada");

    private final String nome;

    TransitionState(String nome) {
        this.nome = nome;
    }
}

public enum TransitionState {
    UNDEFINED(""),
    OCIOSA("Ociosa"),
    ESCOLHIDA("Escolhida"),
    VERIFICADA("Verificada"),
    HABILITADA_ESCOLHIDA("Habilitada e escolhida"),
    HABILITADA_N_ESCOLHIDA("Habilitada e não escolhida");

    private final String nome;

    TransitionState(String nome) {
        this.nome = nome;
    }
}

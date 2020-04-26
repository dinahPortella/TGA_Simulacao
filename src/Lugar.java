import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lugar extends Nodo {

    private List<Conexao> saidas;
    private int reservado = 0;

    public Lugar(int id, String nome, int total) {
        super(id, nome, NodeType.LUGAR.ordinal(), total);
        saidas = new ArrayList<>();
    }

    /**
     * Adiciona uma saída para o lugar
     * @param destino - A saída a ser adicionada
     */
    public void addDestino(Conexao destino) {
        saidas.add(destino);
    }

    /**
     * Consome as marcas reservadas para a execução da transição
     */
    public void consomeMarcas() {
        total -= reservado;
    }

    /**
     * Adiciona marcas ao lugar, é chamado pela transição durante a execução da mesma
     * @param quantidade - A quantidade de marcas ganhas
     */
    public void ganhaMarcas(int quantidade) {
        total += quantidade;
    }

    /**
     * Zera a quantidade de marcas de reserva, chamado após a conclusão da transição
     */
    public void resetaReserva() {
        reservado = 0;
    }

    /**
     * Prepara o lugar para transição reservando as marcas necessárias para execução
     * @param quantidade - A quantidade de marcas que serão consumidas pela transição
     */
    public void preparaTransicao(int quantidade) {
        reservado = quantidade;
    }

    /**
     * Escolhe qual transição vai ser disparada
     */
    public void escolheTransicao() {
        Transicao escolhida;
        if (saidas.size() > 1) {
            escolhida = sorteiaTransicao();
        } else {
            escolhida = saidas.get(0).getTransicao();
        }
        escolhida.escolhe();
    }

    /**
     * Escolhe aleatoriamente uma das transições do lugar quando o mesmo possui mais de uma saída
     * @return - A transição escolhida
     */
    private Transicao sorteiaTransicao() {
        final Random rng = new Random();
        int escolhido = rng.nextInt(saidas.size());
        return saidas.get(escolhido).getTransicao();
    }

    /**
     * Gera um objeto que contém o estado atual do lugar
     * @return - O objeto contendo informações do estado atual
     */
    public Estado getEstado() {
        return new Estado(getTipo(), getNome(), false, total);
    }

}

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

    public void addDestino(Conexao destino) {
        saidas.add(destino);
    }

    public void consomeMarcas() {
        total -= reservado;
    }

    public void ganhaMarcas(int quantidade) {
        total += quantidade;
    }

    public void resetaReserva() {
        reservado = 0;
    }

    public void preparaTransicao(int quantidade) {
        reservado = quantidade;
    }

    public void escolheTransicao() {
        Transicao escolhida = null;
        if (saidas.size() > 1) {
            escolhida = sorteiaTransicao();
        } else {
            escolhida = saidas.get(0).getTransicao();
        }
        escolhida.escolhe();
    }

    private Transicao sorteiaTransicao() {
        final Random rng = new Random();
        int escolhido = rng.nextInt(saidas.size());
        return saidas.get(escolhido).getTransicao();
    }

}

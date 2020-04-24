
public class Lugar extends Nodo {

    private String nome;

    public Lugar(String nome, int total) {
        super(nome, NodeType.LUGAR.ordinal(), total);
    }
    
}

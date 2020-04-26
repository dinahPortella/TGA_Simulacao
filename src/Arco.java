public class Arco {

    private int total;
    private long origem;
    private long destino;

    public Arco() {
        super();
    }

    public Arco(int total, long origem, long destino) {
        this.total = total;
        this.origem = origem;
        this.destino = destino;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getOrigem() {
        return origem;
    }

    public void setOrigem(long origem) {
        this.origem = origem;
    }

    public long getDestino() {
        return destino;
    }

    public void setDestino(long destino) {
        this.destino = destino;
    }

}

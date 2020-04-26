import java.util.List;

public class Rede {

    private List<Nodo> nos;
    private List<Arco> arcos;

    public Rede() {
        super();
    }

    public Rede(List<Nodo> nos, List<Arco> arcos) {
        this.nos = nos;
        this.arcos = arcos;
    }

    public List<Nodo> getNos() {
        return nos;
    }

    public void setNos(List<Nodo> nos) {
        this.nos = nos;
    }

    public List<Arco> getArcos() {
        return arcos;
    }

    public void setArcos(List<Arco> arcos) {
        this.arcos = arcos;
    }

}

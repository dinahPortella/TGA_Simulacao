import java.util.*;

public class RedesPetri {

		/*NESSA CLASSE SERÁ LIDO O ARQUIVO JSON QUE ESPECIFICA UMA REDE,
		* MONTADA A REDE E EXECUTADA*/

		/*ALGUSN MÉTODOS QUE USO AQUI AINDA NÃO EXISTEM, PRECISAM SER CRIADOS*/

	private List<Lugar> lugares = new ArrayList<Lugar>();
	private List<Transicao> transicoes = new ArrayList<Transicao>();
	private List<Arco> arcos = new ArrayList<Arco>();
	private Map<String, Nodo> rede = new HashMap<>();
	private String nome;
	/*Arquivo JSON  = Objeto hipotético que seria a rede lida de um JSON*/
	
	public RedesPetri(String nome, Arquivo JSON) {
		super();
		this.nome = nome;
		montaRede(JSON);
	}

	/*NESSE MÉTODO, APLICA O JACKSON PRA LER O JSON E DESSERIALIZAR OS OBJETOS*/
	private void montaRede(Arquivo JSON){
	}



	public void processaRede() {
		ArrayList<Arco> list = new ArrayList<>();
		boolean redeAcabou = false;

		while(redeAcabou == false) {
			int contAlteracoes = 0;
			for (Arco arco : arcos){
				/*Testa se tem algum arco que pode disparar uma transação*/
				Lugar lugarAtual;
				if( lugares.get(arco.getOrigem()) != null) {
					lugarAtual = lugares.get(arco.getOrigem());
					if(lugarAtual.getTotal()>=arco.getTotal()){
						/*Dispara o arco da transicao alterando as marcas do Lugar de destino do mesmo*/
						lugares.update(transicoes.get(arco.getDestino()).dispara());
						contAlteracoes++;

					}
				}
			}
		}


	}


	
	public List<Lugar> getLugares() {
		return lugares;
	}
	public List<Transicao> getTransicoes() {
		return transicoes;
	}
	public List<Arco> getArcos() {
		return arcos;
	}
	
	/*
	@Override
	public String toString() {
		return null;
		
	}*/
}

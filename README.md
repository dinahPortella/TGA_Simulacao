TGA_Simulacao

Uso básico do programa java -jar RedesPetri.jar path/input.json

O arquivo json deve ser um objeto Rede que tem 2 listas como campo de atributos, o primeiro é uma lista de nodos, e o segundo uma lista de arcos


Arco:
    - total: int - campo que indica a multiplicidade do arco
    - origem: long - campo que indica o id do nodo de origem
    - destino: long - campo que indica o id do nodo de destino
    - o formato dos Ids dos nodos é TN, onde T é o número que indica o tipo de nodo, 1 lugar, 2 transição, e N é o campo id do nodo
    - Arcos devem obrigatoriamente ser formados entre lugar e transição, ambos podem ser origem e destino, mas um arco com nodos do mesmo tipo para origem e destino é inválido
    
Nodo:
    - id: int - O id do nodo, ele é juntado com o tipo para gerar um id universal para lugares e transições
    - nome: String - O nome do nodo, utilizado na hora de exibir as informações da rede
    - tipo: int - O tipo de nodo, 1 para lugar, 2 para transição
    - total: int - A quantidade de marcas do lugar, não utilizado para transições
    
O projeto possui 3 jsons como exemplo básico de redes
# Simulador de Redes de Petri
Este projeto permite a montagem e execução de uma Rede de Petri de através de um arquivo.

## Modo de uso
O programa pode ser executado diretamente em console através do comando:
` -jar RedesPetri.jar path/input.json`
- Recomenda-se a utilização de Java 1.8.

### Arquivos necessários
O arquivo `RedesPetri.jar ` por ser encontrado na pasta [/releases](https://github.com/dinahPortella/TGA_Simulacao/tree/master/release "/releases") deste diretório.

O arquivo `input.json` representa uma Rede de Petri.
- A pasta [/files](https://github.com/dinahPortella/TGA_Simulacao/tree/master/files "/files") deste diretório apresenta 3 exemplos de Redes de Petri.

#### Rede de Petri
A rede representada através do arquivo apresenta duas listas como campo de atributos. O primeiro é uma lista de nodos e o segundo é uma lista de arcos.

##### Nodo
| Nome  | Tipo  | Utilização |
| :------------ | :------------ | :------------ |
| id  | `int`  | Junto ao `tipo` gera um id universal lugares e transições. |
| nome  | `String`  | Utilizado para exibir as informações da rede. |
| tipo  | `int`  | 1 - Representa lugar;  2 - Representa transição.  |
| total  | `int`  | Quantidade de marcas em um lugar (não utilizado em transições).  |
- Na rede, o formato dos Ids do nodos é TN. T indica o `tipo` de nodo e N o campo `id` do nodo.

##### Arco
| Nome  | Tipo  | Utilização |
| :------------ | :------------ | :------------ |
| total  | `int`  | Indica multiplicidade do arco  |
| origem  | `long`  | Indicica o `id` do nodo de origem  |
| destino  | `long`  | Indica o `id` do nodo de destino  |
- Arcos devem obrigatoriamente ser formados entre lugar e transição.
- Tanto lugar quando transição podem ser `origem` ou `destino`.
- Um arco com nodos do mesmo `tipo` para `origem` e `destino` é inválido.

## Autores
- **Bruno Schubert Medeiros**
- **Dinah Denovaro Portella Neta**
- **Lucas dos Santos Pletsch**
- **Samuel Weber**

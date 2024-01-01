# Séries de Dados

Este projeto é um código inicial de um exercício usado para praticar os conceitos de Classes Abstratas e Interfaces.

## Passo 1 - Contexto e Definição do Tema

As atividades de hoje têm por objetivo exemplificar a importância do uso dos conceitos de **Classes Abstratas e Interfaces**.

Este projeto tem um pasta (pacote) chamada `series` com duas interfaces e uma classe.
Uma Série de Dados é um conjunto de dados que tem um dia inicial, um dia final e um valor inteiro correspondente para cada dia.
Séries de dados podem ser, por exemplo:

- Das temperaturas de uma cidade, do dia 1 ao dia 5.
- Das vendas de uma loja, do dia 4 ao dia 10.

As interfaces fornecidas são genéricas e servem para qualquer tipo de série de dados diários.
A ideia é que você trate algum tipo de série de dados usando essas interfaces.

Neste passo você deve definir quais séries de dados vai tratar.
Você precisa definir necessariamente duas séries de dados que estejam de certa forma relacionados.
Abaixo estão alguns exemplos, mas você pode escolher outros.
Ao escolher valide com o professor, pois a ideia é cada aluno tenha temas diferentes.

1. Dados de clima: temperatura e umidade relativa do ar.
2. Dados de acidentes: fatais e não fatais
3. Dados da balança comercial de um país: importações e exportações.
4. Dados de ações de duas empresas: valores da ação da Vale e da Petrobrás.

Neste passo, você deve escrever abaixo quais são as séries de dados que você vai tratar e, em seguida, fazer commit com essa alteração.

> Dados de ações de duas empresas diferentes: valores da ação da Vale e da Petrobrás.

## Passo 2 - Criação do Pacote

Este projeto utiliza o conceito de **Pacotes**.
Para saber mais sobre o uso de Pacotes em Java, você pode acessar o material disponibilizado na seção de Estudos Autônomos.

Crie um pacote para colocar as classes das suas séries de dados.
As classes dos três passos seguintes devem pertencer a esse pacote.
Escolha um nome para o pacote que tenha relação com o tema que escolheu.

Para criar um pacote no VS Code, basta criar uma pasta dentro da pasta `src`.

Obs: para facilitar o entendimento, os passos seguintes citarão séries de dados de clima, lembre-se de adaptar as instruções para o seu tema escolhido.

## Passo 3 - Criação de classe para série de dados

Crie, dentro do pacote do Passo 2, **uma classe para representar as suas séries de dados**.

No caso dos dados de clima, por exemplo, a classe poderia se chamar `SerieDadosClima`.
A ideia é que ela seja usada depois para criar dois objetos para representar os dois tipos diferentes de dados de clima (temperatura e umidade relativa do ar, por exemplo).

A classe deverá implementar a interface fornecida `SeriesDados`, sobrescrevendo todos os seus métodos.

O construtor da classe deverá receber por parâmetro a identificação da série, o dia inicial e o dia final e deve criar uma estrutura de dados que guardará os dados da série.

Por fim, a classe deverá ter um método para adicionar um dado para um determinado dia. (Obs.: lembre-se de tratar o caso do método seja chamado para um dia fora do intervalo de dias definido para a série no construtor).

Obs.: lembre-se que como a classe `SerieDados` está em outro pacote, você precisará importá-la usando: `import series.SerieDados`;

Ao terminar, faça um commit.

## Passo 4 - Classe de controle

Crie, dentro do pacote do Passo 2, **uma classe de controle** que tenha dois atributos da classe de Séries de Dados que você criou no passo anterior.

Na implementação da classe do construtor, crie os dados das suas séries diretamente no código (*hard coded*).

Obs: *num programa normal poderíamos ter um menu para o usuário informar os dados ou poderíamos obter essas informações de um arquivo. Mas vamos fazer “na mão” para não gastar muito tempo.*

Faça o commit de suas alterações.

## Passo 5 - Classe principal

**Altere o código do método `executar` classe `Principal`** que foi fornecida neste projeto.

Você deve instanciar a classe controladora, criada no passo anterior, e obter a lista de séries guardando em uma variável com o tipo `List<SerieDados>`.

Para testar se está tudo certo, implemente um loop que mostre os dados das séries conforme o exemplo abaixo:

```text
Dados da Serie Temperaturas em Lavras
Dia 1: 23
Dia 2: 25
...
Dados da Serie Umidade relativa do ar em Lavras
Dia 1: 76
Dia 2: 82
...
```

Teste o seu programa e, estando tudo certo, faça um novo commit.

## Passo 6 - Visualização dos dados

Altere o método `executar` da classe `Principal`, **criando um objeto da classe `VisualizadorSeries`** passando a lista de séries como parâmetro.

Em seguida, **chame o método `exibir`** do objeto criado.

Teste seu programa e veja o que acontece.

Obs.: a classe `VisualizadorSeries` utiliza bibliotecas que não fazem parte do Java Padrão.
Elas se encontram na pasta `lib` desse projeto.

Advinha o que precisa fazer agora? Isso mesmo, um novo commit.

## Passo 7 - Reflexão sobre a atividade

Você chegou a **refletir sobre o que acabamos de fazer**?
Se não, vamos pensar nisso agora respondendo às perguntas abaixo.

1. Quando a classe de `VisualizadorSeries` foi implementada, quem a criou conhecia algum código que você desenvolveu?

> Não

2. Como então a classe `VisualizadorSeries` conseguiu mostrar os dados das suas séries mesmo sem conhecer a sua implementação?

> Ela recebeu os dados como parametro em uma de suas funções

3. O que o polimorfismo tem a ver com o que foi feito?

> A partir do polimorfismo foi possivel implementar as series de dados sem que a VisualizarSeries conhecesse os dados, ao invés de criar os objetos das series dentro da classe  VisualizarSeries para passar as informações.

4. Para que então servem as interfaces (como tipo de dado)?

> Elas ajdam a manter o incapsulamento, pois voce pode colocar nela somente metodos que exibem informação ao invés de retornar o objeto todo possibilitando que a classe o recebesse pudesse alterar o se comportamento, alem de evitar reolicação de codigo.

Ao terminar, faça um novo commit e sincronize suas alterações com o GitHub Classroom.

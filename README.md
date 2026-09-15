# Sistema de pedidos em Java

Projeto desenvolvido durante meus estudos de Java para praticar enumerações, composição de objetos e listas.

O programa recebe os dados de um cliente e de um pedido, adiciona os produtos e mostra um resumo com o subtotal de cada item e o valor total da compra.

## O que pratiquei

- orientação a objetos;
- composição entre classes;
- enumerações;
- listas com `ArrayList`;
- entrada de dados com `Scanner`;
- formatação de datas e valores;
- sobrescrita do método `toString()`;
- cálculo de subtotal e total;
- teste automático do programa.

## Estrutura

```text
Client
  └── Order
        ├── OrderStatus
        └── OrderItem
              └── Product
```

- `Client`: armazena os dados do cliente;
- `Order`: representa o pedido e calcula o valor total;
- `OrderItem`: representa um item e calcula seu subtotal;
- `Product`: armazena o nome e o preço do produto;
- `OrderStatus`: define os possíveis status do pedido;
- `Main`: recebe os dados e executa o programa.

## Exemplo utilizado

```text
Cliente: Alex Green
E-mail: alex@gmail.com
Data de nascimento: 15/03/1985
Status: PROCESSING

TV: 1 x $1000.00
Mouse: 2 x $40.00

Total: $1080.00
```

## Exemplo de saída

```text
ORDER SUMMARY:
Order moment: 15/09/2026 18:30:00
Order status: PROCESSING
Client: Alex Green (15/03/1985) - alex@gmail.com
Order items:
TV, $1000.00, Quantity: 1, Subtotal: $1000.00
Mouse, $40.00, Quantity: 2, Subtotal: $80.00
Total price: $1080.00
```

## Como executar

1. Abra o projeto no IntelliJ IDEA.
2. Acesse a classe `Main`.
3. Execute o método `main`.
4. Digite os dados solicitados no console.

## Teste automático

O projeto possui um teste que simula a entrada dos dados e verifica:

- os subtotais dos produtos;
- o valor total do pedido;
- a quantidade de itens;
- o cliente e o status do pedido;
- as informações exibidas no resumo.

Para executar, abra a classe `ClientOrderAutomaticTest` e rode o método `main`.

Resultado esperado:

```text
Todos os testes passaram!
```

## Aprendizado

Com esta atividade, consegui entender melhor como as classes podem se relacionar. Um pedido possui um cliente, um status e uma lista de itens. Cada item possui um produto, uma quantidade e um subtotal.

package M06_Enumeracao_e_Composicao.ClientOrder.application;

import M06_Enumeracao_e_Composicao.ClientOrder.entities.Client;
import M06_Enumeracao_e_Composicao.ClientOrder.entities.Order;
import M06_Enumeracao_e_Composicao.ClientOrder.entities.OrderItem;
import M06_Enumeracao_e_Composicao.ClientOrder.entities.OrderStatus;
import M06_Enumeracao_e_Composicao.ClientOrder.entities.Product;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

public class ClientOrderAutomaticTest {

    public static void main(String[] args) throws Exception {
        testOrderCalculations();
        testCompleteMainScenario();

        System.out.println("Todos os testes passaram!");
    }

    private static void testOrderCalculations() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Client client = new Client(
                "Alex Green",
                "alex@gmail.com",
                dateFormat.parse("15/03/1985")
        );

        Order order = new Order(client, OrderStatus.PROCESSING);
        OrderItem tv = new OrderItem(new Product("TV", 1000.00), 1);
        OrderItem mouse = new OrderItem(new Product("Mouse", 40.00), 2);

        order.addItem(tv);
        order.addItem(mouse);

        checkDoubleEquals(1000.00, tv.subTotal(), "subtotal da TV");
        checkDoubleEquals(80.00, mouse.subTotal(), "subtotal do Mouse");
        checkDoubleEquals(1080.00, order.total(), "total do pedido");
        checkEquals(2, order.getOrderItemList().size(), "quantidade de itens");
        checkEquals(client, order.getClient(), "cliente associado ao pedido");
        checkEquals(OrderStatus.PROCESSING, order.getStatus(), "status do pedido");
    }

    private static void testCompleteMainScenario() throws Exception {
        String simulatedInput = String.join(System.lineSeparator(),
                "Alex Green",
                "alex@gmail.com",
                "15/03/1985",
                "PROCESSING",
                "2",
                "TV",
                "1000.00",
                "1",
                "Mouse",
                "40.00",
                "2"
        ) + System.lineSeparator();

        InputStream originalInput = System.in;
        PrintStream originalOutput = System.out;
        ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();

        try {
            System.setIn(new ByteArrayInputStream(
                    simulatedInput.getBytes(StandardCharsets.UTF_8)
            ));
            System.setOut(new PrintStream(capturedOutput, true, StandardCharsets.UTF_8));

            Main.main(new String[0]);
        } finally {
            System.setIn(originalInput);
            System.setOut(originalOutput);
        }

        String output = capturedOutput.toString(StandardCharsets.UTF_8);

        checkContains(output, "ORDER SUMMARY:", "cabeçalho do resumo");
        checkContains(output, "PROCESSING", "status impresso");
        checkContains(output, "Alex Green", "nome do cliente");
        checkContains(output, "alex@gmail.com", "e-mail do cliente");
        checkContains(output, "15/03/1985", "data de nascimento");
        checkContains(output, "TV, $1000.00, Quantity: 1, Subtotal: $1000.00", "item TV");
        checkContains(output, "Mouse, $40.00, Quantity: 2, Subtotal: $80.00", "item Mouse");
    }

    private static void checkContains(String actual, String expected, String description) {
        if (!actual.contains(expected)) {
            throw new AssertionError(
                    "Falha em " + description + ". Não foi encontrado: " + expected
                            + System.lineSeparator() + "Saída obtida:" + System.lineSeparator() + actual
            );
        }
    }

    private static void checkDoubleEquals(double expected, double actual, String description) {
        double tolerance = 0.0001;
        if (Math.abs(expected - actual) > tolerance) {
            throw new AssertionError(
                    "Falha em " + description + ". Esperado: " + expected + ", obtido: " + actual
            );
        }
    }

    private static void checkEquals(int expected, int actual, String description) {
        if (expected != actual) {
            throw new AssertionError(
                    "Falha em " + description + ". Esperado: " + expected + ", obtido: " + actual
            );
        }
    }

    private static void checkEquals(Object expected, Object actual, String description) {
        if (!expected.equals(actual)) {
            throw new AssertionError(
                    "Falha em " + description + ". Esperado: " + expected + ", obtido: " + actual
            );
        }
    }
}

package M06_Enumeracao_e_Composicao.ClientOrder.application;

import M06_Enumeracao_e_Composicao.ClientOrder.entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");


        System.out.println("Enter client data:");

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Birth date (DD/MM/YYYY): ");
        Date birthDate = sdf1.parse(sc.nextLine());

        Client client = new Client(name, email, birthDate);

        System.out.println("Enter order data: ");

        System.out.print("Status: ");
        OrderStatus status = OrderStatus.valueOf(sc.next());

        Order order = new Order(client, status);

        System.out.print("How many items to this order? ");
        int numberItens = sc.nextInt();

        for (int i = 1; i <= numberItens; i++) {
            sc.nextLine();
            System.out.println("Enter #" + i + " item data:");

            System.out.print("Product name: ");
            String productName = sc.nextLine();

            System.out.print("Product price: ");
            double productPrice = sc.nextDouble();

            Product product = new Product(productName, productPrice);

            System.out.print("Quantity: ");
            int productQty = sc.nextInt();

            OrderItem orderItem = new OrderItem(product, productQty);
            order.addItem(orderItem);
        }

        System.out.println();

        System.out.println("ORDER SUMMARY: ");
        System.out.println(order);

        sc.close();

    }

}

package M06_Enumeracao_e_Composicao.ClientOrder.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static SimpleDateFormat sdfBirth = new SimpleDateFormat("dd/MM/yyyy");

    private Date moment;
    private OrderStatus status;
    private List<OrderItem> orderItemList = new ArrayList<>();
    private Client client;

    public Order() {
    }

    public Order(Client client, OrderStatus status) {
        this.client = client;
        this.status = status;
        moment = new Date();
    }

    public Date getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void addItem(OrderItem item) {
        orderItemList.add(item);
    }

    public void removeItem(OrderItem item) {
        orderItemList.remove(item);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double total() {
        double sum = 0.0;
        for (OrderItem orderItem : orderItemList) {
            sum += orderItem.subTotal();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order moment: "
                + sdf.format(moment)
                + "\n");
        stringBuilder.append("Order status: "
                + status
                + "\n");
        stringBuilder.append("Client: "
                + client.getName()
                + " ("
                + sdfBirth.format(client.getBirthDate())
                + ") - "
                + client.getEmail()
                + "\n");
        stringBuilder.append("Order items:\n");
        for (OrderItem orderItem : orderItemList) {
            stringBuilder.append(orderItem.toString() + "\n");
        }
        stringBuilder.append("Total price: $" + String.format("%.2f", total()));

        return stringBuilder.toString();
    }
}

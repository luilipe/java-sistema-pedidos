package com.luilipe.orders.domain;

public class OrderItem {

    private Product product;
    private Integer quantity;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        price = product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Double subTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return product.getName()
                + ", $"
                + String.format("%.2f", price)
                + ", "
                + "Quantity: "
                + quantity
                + ", "
                + "Subtotal: $"
                + String.format("%.2f", subTotal());

    }
}

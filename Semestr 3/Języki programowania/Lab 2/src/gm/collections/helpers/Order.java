package gm.collections.helpers;

import java.time.LocalDate;

public class Order implements Comparable<Order> {
    private final String orderNumber;
    private final LocalDate orderDate;
    private final double price;

    public Order(String orderNumber, LocalDate orderDate, double price) {
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Order o) {
        return this.orderDate.compareTo(o.orderDate);
    }

    @Override
    public String toString() {
        return "Order [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", price=" + price + "]";
    }
}

package gm.collections.exercises;

import gm.collections.helpers.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class Exercise5 {
    private final List<Order> orders = new ArrayList<>();

    private final Order order1 = new Order("ORD001", LocalDate.of(2024, 10, 15), 250.00);
    private final Order order2 = new Order("ORD002", LocalDate.of(2024, 9, 10), 180.50);
    private final Order order3 = new Order("ORD003", LocalDate.of(2024, 11, 20), 120.75);

    public Exercise5() {
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        sortComparision();
    }

    private void sortComparision() {
        System.out.println("Before sorting: ");
        printOrders();

        Collections.sort(orders);
        System.out.println("\nAfter sorting by date: ");
        printOrders();

        Collections.sort(orders, new OrderPriceComparator());
        System.out.println("\nAfter sorting by price: ");
        printOrders();
    }

    private void printOrders() {
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private static class OrderPriceComparator implements Comparator<Order> {
        @Override
        public int compare(Order o1, Order o2) {
            return Double.compare(o1.getPrice(), o2.getPrice());
        }
    }
}

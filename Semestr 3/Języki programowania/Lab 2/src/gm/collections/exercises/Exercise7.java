package gm.collections.exercises;

import gm.collections.helpers.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Exercise7 {
    private final List<Product> products = new ArrayList<>();

    private final Product product1 = new Product("Laptop", 1500.00);
    private final Product product2 = new Product("Smartphone", 800.00);
    private final Product product3 = new Product("Tablet", 600.00);
    private final Product product4 = new Product("Monitor", 400.00);

    public Exercise7() {
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        sortComparison();
    }

    private void sortComparison() {
        System.out.println("Before sorting: ");
        printProducts();

        Collections.sort(products);
        System.out.println("\nAfter sorting by name: ");
        printProducts();

        Collections.sort(products, new ProductPriceComparator());
        System.out.println("\nAfter sorting by price: ");
        printProducts();
    }

    private void printProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static class ProductPriceComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getPrice(), p2.getPrice());
        }
    }
}

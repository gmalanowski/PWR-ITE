package gm.collections.exercises;

import gm.collections.helpers.Car;

import java.util.HashSet;
import java.util.Set;

public class Exercise6 {
    private final Set<Car> cars = new HashSet<>();

    private final Car car1 = new Car("Toyota", "Corolla", "ABC123");
    private final Car car2 = new Car("Honda", "Civic", "XYZ456");
    private final Car car3 = new Car("Toyota", "Corolla", "ABC123");
    private final Car car4 = new Car("Ford", "Fiesta", "LMN789");
    private final Car car5 = new Car("Mazda", "3", "LMN789");

    public Exercise6() {
        listCars();
        addCars();
        printHashSet();
    }

    private void listCars() {
        System.out.println("Before adding cars to HashSet: ");
        System.out.println(car1 + "\n");
        System.out.println(car2 + "\n");
        System.out.println(car3 + "\n");
        System.out.println(car4 + "\n");
        System.out.println(car5 + "\n");
    }

    private void addCars() {
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
    }

    private void printHashSet() {
        System.out.println("Cars in HashSet: ");
        for (Car car : cars) {
            System.out.println(car.toString() + "\n");
        }
    }
}

package org.example;

interface Vehicle {
    double calculateCost(double distance);
}

class Airplane implements Vehicle {
    @Override
    public double calculateCost(double distance) {
        return (distance * distance)/100;
    }
}

class Train implements Vehicle {
    @Override
    public double calculateCost(double distance) {
        return distance/20;
    }
}

class ProfitCalculator {

    public double calculate_profit(double distance, double reward, Vehicle vehicle) {
        return reward - vehicle.calculateCost(distance);
    }
}

public class Transport {
    public static void main(String[] args) {
        ProfitCalculator calculator = new ProfitCalculator();

        Airplane plane = new Airplane();
        Train train = new Train();

        System.out.println(calculator.calculate_profit(200, 10, train));

        System.out.println(calculator.calculate_profit(50, 50, plane));
        System.out.println(calculator.calculate_profit(30, 40, plane));

        System.out.println(calculator.calculate_profit(350, 45, train));
        System.out.println(calculator.calculate_profit(350, 45, plane));

        /* Odpowiedzi na pytania w poleceniu

            1) Zysk wynosi 0
            2) Większy zysk da transport samolotem na dystans 30 przy wynagrodzeniu 40
            3) Lepsze zyski da pociąg.

         */
    }
}

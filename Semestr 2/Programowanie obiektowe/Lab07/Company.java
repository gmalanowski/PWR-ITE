package org.example;

import java.util.ArrayList;
import java.util.List;

abstract class Employee {
    String name;
    String surname;
    double salary;

    public abstract double calculatePayment();

    public Employee(String name, String surname, double salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }
}

class Accountant extends Employee {
    double TAX;
    double bonus;
    public Accountant(String name, String surname, double salary) {
        super(name,surname,salary);
        bonus = 0.10;
        TAX = 0.17;
    }

    @Override
    public double calculatePayment() {
        double payment = salary + salary*bonus;
        payment = payment - payment*TAX;
        return payment;
    }
}

class ITEmployee extends Employee {

    int experience;
    double TAX;
    public ITEmployee(String name, String surname, double salary, int experience) {
        super(name,surname,salary);
        this.experience = experience;
        TAX = 0.17;
    }

    @Override
    public double calculatePayment() {
        double bonus;
        if(experience < 2) bonus = 500;
        else if(experience >= 2 && experience < 5) bonus = 2000;
        else bonus = 5000;

        double payment = salary + bonus;
        payment = payment - payment* TAX;
        return payment;
    }
}

class Programmer extends ITEmployee {
    public Programmer(String name, String surname, double salary, int experience) {
        super(name,surname,salary,experience);
        TAX /= 2;
    }
}

public class Company {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Accountant("Adam", "Wiśniewski", 6000));
        employees.add(new ITEmployee("Agata", "Wróbel", 7500, 3));
        employees.add(new ITEmployee("Paweł", "Kowalski", 5000, 1));
        employees.add(new Programmer("Ada", "Nowak", 8000, 6));

        for (var e : employees) {
            System.out.println(String.format("First name: %s, last name: %s, salary after taxation: %f", e.name,
                    e.surname, e.calculatePayment()));
        }
    }
}
package gm.collections;

import gm.collections.exercise10.ConsoleUserDialog;
import gm.collections.exercises.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
            selectExercise();
    }

    public static void selectExercise() {
        Scanner scanner = new Scanner(System.in);

        boolean programRunning = true;
        System.out.println("Enter 0 for exit: ");

        while (programRunning) {
            System.out.println("\nEnter exercise number (1-10): ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number between 0 and 10: ");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 0 -> {programRunning = false;}
                case 1 -> {new Exercise1();}
                case 2 -> {new Exercise2();}
                case 3 -> {new Exercise3();}
                case 4 -> {new Exercise4();}
                case 5 -> {new Exercise5();}
                case 6 -> {new Exercise6();}
                case 7 -> {new Exercise7();}
                case 8 -> {new Exercise8();}
                case 9 -> {new Exercise9();}
                case 10 -> {ConsoleUserDialog app = new ConsoleUserDialog(); app.start();}
                default -> System.out.println("Please enter a number between 0 and 10: ");
            }
        }
        scanner.close();
    }
}

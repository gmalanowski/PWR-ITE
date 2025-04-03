package gm.collections.exercise10;

import java.util.Scanner;

public class ConsoleUserDialog {
    private final Scanner scanner;
    private final RiversManagement manager;

    public ConsoleUserDialog() {
        scanner = new Scanner(System.in);
        manager = new RiversManagement();
    }

    public void start() {
        while (true) {
            printMenu();
            try {
                int choice = getUserChoice();
                handleMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Add a new river");
        System.out.println("2. Display rivers in Polish");
        System.out.println("3. Display rivers in German");
        System.out.println("4. Display rivers in Czech");
        System.out.println("5. Display the watershed of a specific river");
        System.out.println("6. Load example data");
        System.out.println("7. Exit");
        System.out.print("Your choice: ");
    }

    private int getUserChoice() {
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> addRiver();
            case 2 -> displayRivers("PL");
            case 3 -> displayRivers("DE");
            case 4 -> displayRivers("CZ");
            case 5 -> displayWatershed();
            case 6 -> loadExampleData();
            case 7 -> exitProgram();
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void addRiver() {
        try {
            manager.addRiverFromUserInput();
        } catch (Exception e) {
            System.out.println("Failed to add the river. Please try again.");
        }
    }

    private void displayRivers(String language) {
        try {
            manager.displayRivers(language);
        } catch (IllegalArgumentException e) {
            System.out.println("Unsupported language: " + language);
        }
    }

    private void displayWatershed() {
        System.out.print("Enter the name of the river to display its watershed: ");
        String riverName = scanner.nextLine();
        try {
            manager.displayWatershed(riverName);
        } catch (Exception e) {
            System.out.println("Error displaying watershed: " + e.getMessage());
        }
    }

    private void loadExampleData() {
        try {
            manager.addExampleData();
            System.out.println("Example data has been loaded successfully.");
        } catch (Exception e) {
            System.out.println("Failed to load example data: " + e.getMessage());
        }
    }

    private void exitProgram() {
        System.out.println("Exiting the program.");
        System.exit(0);
    }

    public static void main(String[] args) {
        ConsoleUserDialog app = new ConsoleUserDialog();
        app.start();
    }
}

package gm.collections.exercise10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class RiversManagement {
    private final List<River> rivers;
    private final Map<String, List<River>> sortedRiversByLanguage;

    public RiversManagement() {
        rivers = new ArrayList<>();
        sortedRiversByLanguage = new HashMap<>();
    }

    public void addRiver(River river) {
        rivers.add(river);
    }

    private void sortRivers(String language) {
        Comparator<River> comparator;
        switch (language) {
            case "PL" -> {comparator =  new RiverComparators.ByNamePL();}
            case "DE" -> {comparator =  new RiverComparators.ByNameDE();}
            case "CZ" -> {comparator =  new RiverComparators.ByNameCZ();}
            default -> {throw new IllegalArgumentException("Unsupported language: " + language);}
        }
        List<River> sortedRivers = new ArrayList<>(rivers);
        Collections.sort(sortedRivers, comparator);
        sortedRiversByLanguage.put(language, sortedRivers);
    }

    public void displayRivers(String language) {
        if (!sortedRiversByLanguage.containsKey(language)) {
            sortRivers(language);
        }

        List<River> riversToDisplay = sortedRiversByLanguage.get(language);

        for (River river : riversToDisplay) {
            switch (language) {
                case "PL":
                    System.out.println(river.getNamePL());
                    break;
                case "DE":
                    System.out.println(river.getNameDE());
                    break;
                case "CZ":
                    System.out.println(river.getNameCZ());
                    break;
                default:
                    System.out.println("Unsupported language.");
                    break;
            }
        }
    }

    public void displayWatershed(String riverName) {
        River selectedRiver = null;
        for (River river : rivers) {
            if (river.getNamePL().equals(riverName) || river.getNameDE().equals(riverName) || river.getNameCZ().equals(riverName)) {
                selectedRiver = river;
                break;
            }
        }

        if (selectedRiver != null) {
            String watershed = selectedRiver.getFlowsInto();
            System.out.println("Rivers flowing into " + watershed + ":");

            List<River> riversInWatershed = new ArrayList<>();
            for (River river : rivers) {
                if (river.getFlowsInto().equals(watershed)) {
                    riversInWatershed.add(river);
                }
            }

            for (River river : riversInWatershed) {
                System.out.println(river);
            }
        } else {
            System.out.println("No river found with that name.");
        }
    }

    public void addRiverFromUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input details about river: ");

        System.out.print("River name in Polish: ");
        String namePL = scanner.nextLine();

        System.out.print("River name in German: ");
        String nameDE = scanner.nextLine();

        System.out.print("River name in Czech: ");
        String nameCZ = scanner.nextLine();

        System.out.print("River where it flows into: ");
        String flowsInto = scanner.nextLine();

        System.out.println("Enter the coordinates of the river course points:");
        List<River.Point> course = new ArrayList<>();
        boolean addingPoints = true;

        while (addingPoints) {
            System.out.print("Enter latitude (or 'exit' to finish): ");
            String latInput = scanner.nextLine();
            if (latInput.equals("exit")) {
                addingPoints = false;
                continue;
            }

            System.out.print("Enter longitude: ");

            try {
                double lat = Double.parseDouble(latInput);
                double lon = Double.parseDouble(scanner.nextLine());
                course.add(new River.Point(lat, lon));
            } catch (NumberFormatException e) {
                System.out.println("Invalid latitude or longitude.");
                addingPoints = true;
            }
        }

        River river = new River(namePL, nameDE, nameCZ, flowsInto, course);
        rivers.add(river);
        System.out.println("River added successfully.");
    }

    public void addExampleData() {
        addRiver(new River("Wisła", "Weichsel", "Visla", "Bałtyk", List.of(new River.Point(52.0, 21.0), new River.Point(54.0, 18.0))));
        addRiver(new River("Odra", "Oder", "Odra", "Bałtyk", List.of(new River.Point(50.0, 18.0), new River.Point(54.0, 15.0))));
        addRiver(new River("Dunaj", "Donau", "Dunaj", "Morze Czarne", List.of(new River.Point(48.0, 17.0), new River.Point(45.0, 28.0))));
    }
}

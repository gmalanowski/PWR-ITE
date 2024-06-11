package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FeaturesCalculator {

    private ArrayList<ArrayList<Integer>> binary_mask;
    private Integer[] mass_center;
    private String filename;

    public FeaturesCalculator(String filename) {
        this.filename = filename;
        this.mass_center = new Integer[2];
        this.binary_mask = new ArrayList<>();
    }

    public void read_data() {
        try{
            Scanner scanner = new Scanner(new File(filename));
            while(scanner.hasNextLine()) {
                ArrayList<Integer> row = new ArrayList<>();
                String[] line = scanner.nextLine().trim().split(" ");
                for(String digit : line) {
                    row.add(Integer.parseInt(digit));
                }
                binary_mask.add(row);
            }
        } catch (FileNotFoundException e) {
                System.out.println("Nie znaleziono pliku.");
        }
    }

    public void calculate_mass_center() {
        int sumX = 0, sumY = 0, count = 0;
        for(int x = 0; x < binary_mask.size(); x++) {
            for(int y = 0; y < binary_mask.get(x).size(); y++) {
                if(binary_mask.get(x).get(y) == 1) {
                    sumX += x;
                    sumY += y;
                    count++;
                }
            }
        }
        mass_center[0] = sumX / count;
        mass_center[1] = sumY / count;
    }

    public void save_results() {
        try(PrintWriter writer = new PrintWriter(filename.replace(".txt", "_out.txt"))) {
            for(int x = 0; x < binary_mask.size(); x++) {
                for(int y = 0; y < binary_mask.get(x).size(); y++) {
                    if(x == mass_center[0] && y == mass_center[1]) {
                        writer.print("P ");
                    } else {
                        writer.print(binary_mask.get(x).get(y) + " ");
                    }
                }
                writer.print("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie mozna zapisac do pliku. ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename;
        System.out.println("Podaj nazwe pliku: ");
        filename = scanner.nextLine();

        FeaturesCalculator calculator = new FeaturesCalculator(filename);
        calculator.read_data();
        calculator.calculate_mass_center();
        calculator.save_results();
    }
}

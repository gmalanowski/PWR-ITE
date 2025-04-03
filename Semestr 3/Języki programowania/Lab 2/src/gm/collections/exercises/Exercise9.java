package gm.collections.exercises;

import gm.collections.helpers.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Exercise9 {
    private final List<Animal> animals = new ArrayList<>();

    private final Animal animal1 = new Animal("Lion", 5, 190.5);
    private final Animal animal2 = new Animal("Elephant", 10, 1200.0);
    private final Animal animal3 = new Animal("Tiger", 3, 220.0);
    private final Animal animal4 = new Animal("Lion", 4, 180.0);
    private final Animal animal5 = new Animal("Elephant", 8, 1100.0);

    public Exercise9() {
        animals.add(animal1);
        animals.add(animal2);
        animals.add(animal3);
        animals.add(animal4);
        animals.add(animal5);

        sortComparison();
    }

    private void sortComparison() {
        System.out.println("Before sorting: ");
        printAnimals();

        Collections.sort(animals, new AnimalSpeciesAgeWeightComparator());
        System.out.println("\nAfter sorting by species, age, and weight: ");
        printAnimals();

        Collections.sort(animals, new AnimalWeightComparator());
        System.out.println("\nAfter sorting by weight (ascending): ");
        printAnimals();
    }

    private void printAnimals() {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private static class AnimalSpeciesAgeWeightComparator implements Comparator<Animal> {
        @Override
        public int compare(Animal a1, Animal a2) {
            int speciesComparison = a1.getSpecies().compareTo(a2.getSpecies());
            if (speciesComparison != 0) {
                return speciesComparison;
            }

            int ageComparison = Integer.compare(a1.getAge(), a2.getAge());
            if (ageComparison != 0) {
                return ageComparison;
            }

            return Double.compare(a1.getWeight(), a2.getWeight());
        }
    }

    private static class AnimalWeightComparator implements Comparator<Animal> {
        @Override
        public int compare(Animal a1, Animal a2) {
            return Double.compare(a1.getWeight(), a2.getWeight());
        }
    }

    public static void main(String[] args) {
        new Exercise8();
    }
}

package gm.collections.helpers;

public class Animal {
    private final String species;
    private final int age;
    private final double weight;

    public Animal(String species, int age, double weight) {
        this.species = species;
        this.age = age;
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Animal{species='" + species + "', age=" + age + ", weight=" + weight + '}';
    }
}

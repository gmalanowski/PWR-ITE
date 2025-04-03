package gm.collections.exercises;


import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Arrays;

public class Exercise1 {

    private final Set<String> hashSet;
    private final Set<String> treeSet;

    public Exercise1() {
        this.hashSet = new HashSet<>();
        this.treeSet = new TreeSet<>();

        String[] words = readData();

        addElements(words);
        displaySets();
        removeElements(words);
    }

    private String[] readData() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string of words (separated by comma)");
        String listOfWords = scanner.nextLine();

        return listOfWords.split(",");
    }

    private void measureTime(String operationName, Runnable operation) {
        long startTime = System.nanoTime();
        operation.run();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println(operationName + " took " + elapsedTime + " ns");
    }

    private void addElements(String[] words) {
        measureTime("\nAdding elements to HashSet", () -> hashSet.addAll(Arrays.asList(words)));

        measureTime("Adding elements to TreeSet", () -> treeSet.addAll(Arrays.asList(words)));
    }

    private void removeElements(String[] words) {
        measureTime("\nRemoving elements from HashSet", () -> {
            for (String word : words) {
                hashSet.remove(word);
            }
        });

        measureTime("Removing elements from TreeSet", () -> {
            for (String word : words) {
                treeSet.remove(word);
            }
        });
    }

    private void displaySets() {
        System.out.println("\nHashSet: " + hashSet);
        System.out.println("TreeSet: " + treeSet);
    }

}

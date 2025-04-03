package gm.collections.exercises;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Exercise2 {

    private final List<Integer> arrayList;
    private final List<Integer> linkedlist;

    public Exercise2() {
        this.arrayList = new ArrayList<>();
        this.linkedlist = new LinkedList<>();

        int[] randomNumbers = generateRandomNumbers(100000);

        addElements(randomNumbers);
        removeElements(randomNumbers);
        searchElements(randomNumbers);
    }

    private int[] generateRandomNumbers(int count) {
        Random rand = new Random();
        int[] numbers = new int[count];

        for (int i = 0; i < count; i++) {
            numbers[i] = rand.nextInt(100);
        }

        return numbers;
    }

    private void measureTime(String operationName, Runnable operation) {
        long startTime = System.nanoTime();
        operation.run();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println(operationName + " took " + elapsedTime + " ns");
    }

    private void addElements(int[] numbers) {
        measureTime("\nAdding elements to ArrayList", () -> {
            for (int number : numbers) {
                arrayList.add(number);
            }
        });

        measureTime("Adding elements to LinkedList", () -> {
            for (int number : numbers) {
                linkedlist.add(number);
            }
        });
    }

    private void removeElements(int[] numbers) {
        measureTime("\nRemoving elements from ArrayList", () -> {
            for (int number : numbers) {
                arrayList.remove(Integer.valueOf(number));
            }
        });

        measureTime("Removing elements from LinkedList", () -> {
            for (int number : numbers) {
                linkedlist.remove(Integer.valueOf(number));
            }
        });
    }

    private void searchElements(int[] numbers) {
        measureTime("\nSearching elements from ArrayList", () -> {
            for (int number : numbers) {
                arrayList.contains(number);
            }
        });

        measureTime("Searching elements from LinkedList", () -> {
            for (int number : numbers) {
                linkedlist.contains(number);
            }
        });
    }

}

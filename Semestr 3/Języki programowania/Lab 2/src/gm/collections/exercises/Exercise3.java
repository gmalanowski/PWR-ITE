package gm.collections.exercises;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Exercise3 {

    private final Map<String, String> hashMap;
    private final Map<String, String> treeMap;

    public Exercise3() {
        this.hashMap = new HashMap<>();
        this.treeMap = new TreeMap<>();

        addWordsToDictionary();
        displayDictionary();
    }

    private void addWordsToDictionary() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a word (or type 'exit' to stop): ");
            String word = scanner.nextLine();

            if (word.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Enter the definition of word: ");
            String definition = scanner.nextLine();

            hashMap.put(word, definition);
            treeMap.put(word, definition);
        }
    }

    private void displayDictionary() {
        System.out.println("\nHashMap (unordered):");
        for (String word : hashMap.keySet()) {
            System.out.println(word + ": " + hashMap.get(word));
        }

        System.out.println("\nTreeMap (ordered):");
        for (String word : treeMap.keySet()) {
            System.out.println(word + ": " + treeMap.get(word));
        }
    }
}

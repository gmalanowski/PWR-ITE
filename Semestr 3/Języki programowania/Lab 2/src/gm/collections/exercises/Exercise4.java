package gm.collections.exercises;

import gm.collections.helpers.User;

import java.util.HashSet;
import java.util.Set;

public class Exercise4 {

    private final Set<User> users = new HashSet<>();

    private final User user1 = new User("jkowalski", "jankowalski@example.com", 1);
    private final User user2 = new User("janzielony", "zielony@example.com", 2);
    private final User user3 = new User("aga1971", "aga1971@example.com", 3);
    private final User user4 = new User("aga1972", "aga1972@example.com", 3);
    private final User user5 = new User("jankowalski", "jkowalski@example.com", 1);

    public Exercise4() {
        listUsers();
        addUsers();
        printHashSet();
    }

    private void listUsers() {
        System.out.println("Before adding users to HashSet: ");
        System.out.println(user1 + "\n");
        System.out.println(user2 + "\n");
        System.out.println(user3 + "\n");
        System.out.println(user4 + "\n");
        System.out.println(user5 + "\n");
    }

    private void addUsers() {
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }

    private void printHashSet() {
        System.out.println("Users in HashSet: ");
        for (User user : users) {
            System.out.println(user.toString() + "\n");
        }
    }
}

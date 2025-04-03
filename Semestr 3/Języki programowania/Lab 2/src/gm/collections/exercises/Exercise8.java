package gm.collections.exercises;

import gm.collections.helpers.CustomDate;

import java.util.HashMap;
import java.util.Map;

public class Exercise8 {
    private final Map<CustomDate, String> dateMap = new HashMap<>();

    public Exercise8() {
        addDatesToMap();
        findDate();
        displayMap();
    }

    private void addDatesToMap() {
        CustomDate date1 = new CustomDate(2024, 10, 15);
        CustomDate date2 = new CustomDate(2024, 11, 1);
        CustomDate date3 = new CustomDate(2024, 10, 20);

        dateMap.put(date1, "Date 1");
        dateMap.put(date2, "Date 2");
        dateMap.put(date3, "Date 3");
    }

    private void findDate() {
        CustomDate dateToSearch = new CustomDate(2024, 10, 15);
        String event = dateMap.get(dateToSearch);

        if (event != null) {
            System.out.println("Found: " + event);
        } else {
            System.out.println("No event found");
        }
    }

    private void displayMap() {
        System.out.println("All events: ");
        for (Map.Entry<CustomDate, String> entry : dateMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

package gm.collections.helpers;

import java.util.Objects;

public class CustomDate {
    private final int year;
    private final int month;
    private final int day;

    public CustomDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomDate customDate = (CustomDate) o;
        return year == customDate.year && month == customDate.month && day == customDate.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }
}

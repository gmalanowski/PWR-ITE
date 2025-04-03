package gm.collections.exercise10;

import java.util.Comparator;

public class RiverComparators {

    public static class ByNamePL implements Comparator<River> {
        @Override
        public int compare(River r1, River r2) {
            return r1.getNamePL().compareTo(r2.getNamePL());
        }
    }

    public static class ByNameDE implements Comparator<River> {
        @Override
        public int compare(River r1, River r2) {
            return r1.getNamePL().compareTo(r2.getNamePL());
        }
    }

    public static class ByNameCZ implements Comparator<River> {
        @Override
        public int compare(River r1, River r2) {
            return r1.getNamePL().compareTo(r2.getNamePL());
        }
    }

}

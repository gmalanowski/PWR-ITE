package gm.collections.exercise10;

import java.util.ArrayList;
import java.util.List;

public class River {
    private final String namePL;
    private final String nameDE;
    private final String nameCZ;
    private final String flowsInto;
    private final List<Point> course;

    public River(String namePL, String nameDE, String nameCZ, String flowsInto, List<Point> course) {
        this.namePL = namePL;
        this.nameDE = nameDE;
        this.nameCZ = nameCZ;
        this.flowsInto = flowsInto;
        this.course = new ArrayList<>(course);
    }

    public String getNamePL() {
        return namePL;
    }

    public String getNameDE() {
        return nameDE;
    }

    public String getNameCZ() {
        return nameCZ;
    }

    public String getFlowsInto() {
        return flowsInto;
    }

    public List<Point> getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return "River{" +
                "namePL='" + namePL + '\'' + ", nameDE='" + nameDE + '\'' + ", nameCZ='" + nameCZ + '\'' +
                ", flowsInto='" + flowsInto + '\'' + ", course=" + course + '}';
    }

    public static class Point {
        private final double lat;
        private final double lon;

        public Point(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

        @Override
        public String toString() {
            return "(" + lat + ", " + lon + ")";
        }
    }
}

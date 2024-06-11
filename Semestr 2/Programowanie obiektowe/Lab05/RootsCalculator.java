package org.example;

import java.util.ArrayList;
import java.util.List;

public class RootsCalculator {
    public static List<Double> calculate_roots(double a, double b, double c) {
        List<Double> roots = new ArrayList<>();
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    roots.add(Double.POSITIVE_INFINITY);
                }
            } else {
                roots.add(-c / b);
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta > 0) {
                double sqrtDelta = Math.sqrt(delta);
                roots.add((-b + sqrtDelta) / (2 * a));
                roots.add((-b - sqrtDelta) / (2 * a));
            } else if (delta == 0) {
                roots.add(-b / (2 * a));
            }
        }
        return roots;
    }
}

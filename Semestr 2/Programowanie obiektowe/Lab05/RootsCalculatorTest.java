package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RootsCalculatorTest {

    @Test
    void runTests() {

        // 1. case
        List<Double> roots1 = new ArrayList<>();
        double inf = Double.POSITIVE_INFINITY;
        roots1.add(inf);
        assertEquals(roots1, RootsCalculator.calculate_roots(0, 0, 0));

        // 2. case
        List<Double> roots2 = new ArrayList<>();
        assertEquals(roots2, RootsCalculator.calculate_roots(0, 0, 7));

        // 3. case
        List<Double> roots3 = new ArrayList<>();
        roots3.add(-6.0);
        assertEquals(roots3, RootsCalculator.calculate_roots(0, 1, 6));

        // 4. case
        List<Double> roots4 = new ArrayList<>();
        assertEquals(roots4, RootsCalculator.calculate_roots(1, 1, 6));


        // 5. case
        List<Double> roots5 = new ArrayList<>();
        roots5.add(2.0);
        assertEquals(roots5, RootsCalculator.calculate_roots(1, -4, 4));


        // 6. case
        List<Double> roots6 = new ArrayList<>();
        roots6.add(6.0);
        roots6.add(-1.0);
        assertEquals(roots6, RootsCalculator.calculate_roots(1, -5, -6));

    }

}
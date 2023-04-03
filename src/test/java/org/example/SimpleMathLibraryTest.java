package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SimpleMathLibraryTest extends SimpleMathLibrary{
    @Test
    public void testAdd() {
        SimpleMathLibraryTest math = new SimpleMathLibraryTest();
        double result = math.add(2, 3);
        if (result == 5) {
            System.out.println("OK");
        } else {
            System.out.println("NOK");
        }
        Assertions.assertEquals(5, result);
    }

    @Test
    public void testMinus() {
        SimpleMathLibraryTest math = new SimpleMathLibraryTest();
        double result = math.minus(5, 2);
        if (result == 3) {
            System.out.println("OK");
        } else {
            System.out.println("NOK");
        }
        Assertions.assertEquals(3, result);
    }

    @Test
    public void testAddNegative() {
        SimpleMathLibraryTest math = new SimpleMathLibraryTest();
        double result = math.add(-2, 3);
        if (result == 1) {
            System.out.println("OK");
        } else {
            System.out.println("NOK");
        }
        Assertions.assertEquals(1, result, "Adding negative numbers should work");
    }

    @Test
    public void testMinusNegative() {
        SimpleMathLibraryTest math = new SimpleMathLibraryTest();
        double result = math.minus(2, -3);
        if (result == 5) {
            System.out.println("OK");
        } else {
            System.out.println("NOK");
        }
        Assertions.assertEquals(5, result, "Subtracting negative numbers should work");
    }

    public double add(double a, double b) {
        return a+b;
    }


    public double minus(double a, double b) {
        return a-b;
    }
}

package test.L0.java.org.example;

import org.testng.annotations.*;

import main.java.org.example.Calculator;

import static org.junit.Assert.assertEquals;

public class TestCalculator {

    private static final double DELTA = 1e-15;

    @Test
    public void test4() {
        Calculator c = new Calculator();
        assertEquals(4, c.addTwoNumbers(2, 2), DELTA);
    }

    @Test
    public void test5() {
        Calculator c = new Calculator();
        assertEquals(5, c.addTwoNumbers(3, 2), DELTA);
    }

    @Test
    public void test100() {
        Calculator c = new Calculator();
        assertEquals(100, c.addTwoNumbers(50, 50), DELTA);
    }

    @Test
    public void testS10() {
        Calculator c = new Calculator();
        assertEquals(10, c.subtractTwoNumbers(60, 50), DELTA);
    }

    @Test
    public void testS11() {
        Calculator c = new Calculator();
        assertEquals(3000, c.multiplyTwoNumbers(60, 50), DELTA);
    }

    @Test
    public void testS12() {
        Calculator c = new Calculator();
        assertEquals(10, c.divideTwoNumbers(60, 6), DELTA);
    }

    @Test
    public void testS13() {
        Calculator c = new Calculator();
        assertEquals(Double.POSITIVE_INFINITY, c.divideTwoNumbers(60, 0), DELTA);
    }
}

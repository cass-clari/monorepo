package test.java.org.example;

import org.testng.annotations.*;

import main.java.org.example.Calculator;

import static org.junit.Assert.assertEquals;

public class TestCalculator {

    @Test
    public void test4() {
        Calculator c = new Calculator();
        assertEquals(4, c.addTwoInts(2, 2));
    }

    @Test
    public void test100() {
        Calculator c = new Calculator();
        assertEquals(100, c.addTwoInts(50, 50));
    }

    @Test
    public void testS10() {
        Calculator c = new Calculator();
        assertEquals(10, c.subtractTwoInts(60, 50));
    }
}

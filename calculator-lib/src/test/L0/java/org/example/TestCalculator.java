package test.L0.java.org.example;

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

    @Test
    public void testS11() {
        Calculator c = new Calculator();
        assertEquals(3000, c.multiplyTwoInts(60, 50));
    }

    @Test
    public void testS12() {
        Calculator c = new Calculator();
        assertEquals(10, c.divideTwoInts(60, 6));
    }

//    @Test(expectedExceptions = RuntimeException.class)
//    public void testS13() {
//        Calculator c = new Calculator();
//        assertEquals(10, c.divideTwoInts(60, 0));
//    }
}

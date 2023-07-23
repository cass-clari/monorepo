package test.java.org.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import main.java.org.example.Adder;

public class TestAdder {

@Test
  public void testPositiveResult() throws Exception {
    Adder adder = new Adder();
    assertEquals(2, adder.add(1, 1));
    assertEquals(8, adder.add(6, 2));
  }

@Test
  public void testNegative() throws Exception {
    Adder adder = new Adder();
    assertEquals(-2, adder.add(-3, 1));
  }

  @Test
  public void testAdd2() throws Exception {
    Adder adder = new Adder();
    assertEquals(10, adder.add2(5, 5));
  }
 
}

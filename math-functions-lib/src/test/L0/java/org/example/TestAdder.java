package test.L0.java.org.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import main.java.org.example.Adder;

public class TestAdder {

  private static final double DELTA = 1e-15;

@Test
  public void testPositiveResult() throws Exception {
    Adder adder = new Adder();
    assertEquals(2, adder.add(1, 1), DELTA);
    assertEquals(8, adder.add(6, 2), DELTA);
  }

@Test
  public void testNegative() throws Exception {
    Adder adder = new Adder();
    assertEquals(-2, adder.add(-3, 1), DELTA);
  }

  @Test
  public void testAdd2() throws Exception {
    Adder adder = new Adder();
    assertEquals(10, adder.add2(5, 5), DELTA);
  }

  @Test
  public void testAdd3() throws Exception {
    Adder adder = new Adder();
    assertEquals(20, adder.add2(15, 5), DELTA);
  }
 
}

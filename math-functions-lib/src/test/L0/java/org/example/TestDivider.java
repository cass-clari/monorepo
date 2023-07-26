package test.L0.java.org.example;

import main.java.org.example.Divider;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDivider {

  @Test
  public void testDivide() throws Exception {
    Divider d = new Divider();
    assertEquals(2, d.divide(2, 1));
  }

  @Test(expected = ArithmeticException.class)
  public void testDivideByZero() throws Exception {
    Divider d = new Divider();
    assertEquals(0, d.divide(2, 0));
  }

  @Test(expected = RuntimeException.class)
  public void testDivideByZero2() throws Exception {
    Divider d = new Divider();
    assertEquals(0, d.divideByZero(2, 0));
  }

  @Test
  public void testDivideByZero3() throws Exception {
    Divider d = new Divider();
    assertEquals(2, d.divideByZero(2, 1));
  }
 
}

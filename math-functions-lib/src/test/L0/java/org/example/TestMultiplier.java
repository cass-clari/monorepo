package test.L0.java.org.example;

import main.java.org.example.Multiplier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMultiplier {

  private static final double DELTA = 1e-15;

@Test
  public void testMultiplier() throws Exception {
    Multiplier m = new Multiplier();
    assertEquals(2, m.multiply(2, 1), DELTA);
    assertEquals(9, m.multiply(3, 3), DELTA);
//    assertEquals(10, m.multiply(3, 3));
  }
 
}

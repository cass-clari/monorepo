package test.L0.java.org.example;

import main.java.org.example.Multiplier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMultiplier {

@Test
  public void testMultiplier() throws Exception {
    Multiplier m = new Multiplier();
    assertEquals(2, m.multiply(2, 1));
    assertEquals(9, m.multiply(3, 3));
//    assertEquals(10, m.multiply(3, 3));
  }
 
}

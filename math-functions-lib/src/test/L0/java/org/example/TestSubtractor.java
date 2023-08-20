package test.L0.java.org.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import main.java.org.example.Subtractor;

// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import java.nio.charset.StandardCharsets;

public class TestSubtractor {

  private static final double DELTA = 1e-15;

@Test
  public void testNoArgument() throws Exception {
    Subtractor subtractor = new Subtractor();
    assertEquals(1, subtractor.subtract(2, 1), DELTA);
    assertEquals(9, subtractor.subtract(18, 9), DELTA);
    assertEquals(0, subtractor.subtract(2, 2), DELTA);
  }
 
}

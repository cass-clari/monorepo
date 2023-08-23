package test.L0.java.org.example;

import main.java.org.example.Modder;
import main.java.org.example.Subtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import java.nio.charset.StandardCharsets;

public class TestModder {

  private static final double DELTA = 1e-15;

@Test
  public void testNoArgument() throws Exception {
    Modder modder = new Modder();
    assertEquals(4, modder.mod(8, 4), DELTA);
    assertEquals(10, modder.mod(30, 20), DELTA);
  }
 
}

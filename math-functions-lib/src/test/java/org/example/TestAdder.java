package test.java.org.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import main.java.org.example.Adder;

// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import java.nio.charset.StandardCharsets;

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
 
}

package test.java.org.example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestAdder.class,
        TestMultiplier.class,
        TestSubtractor.class
        })
public class TestSuite {
}

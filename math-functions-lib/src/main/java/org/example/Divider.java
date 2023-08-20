    package main.java.org.example;

public class Divider {
    
    public double divide( double i, double j ) {
        return i / j;
    }
    public double divideByZero( double i, double j ) {

        if (j == 0) {
            throw new RuntimeException("Cannot divide by zero");
        } else {
            return i / j;
        }
    }
}

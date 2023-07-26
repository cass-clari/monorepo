    package main.java.org.example;

public class Divider {
    
    public int divide( int i, int j ) {
        return i / j;
    }
    public int divideByZero( int i, int j ) {

        if (j == 0) {
            throw new RuntimeException("Cannot divide by zero");
        } else {
            return i / j;
        }
    }
}

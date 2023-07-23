package main.java.org.example;

public class Calculator {

    public int addTwoInts(int i, int j) {
        Adder adder = new Adder();
        return adder.add(i, j);
    }

    public int multiplyTwoInts(int i, int j) {
        Multiplier multi = new Multiplier();
        return multi.multiply(i, j);
    }

    public int divideTwoInts(int i, int j) {
        Divider divider = new Divider();
        return divider.divide(i, j);
    }

    public int subtractTwoInts(int i, int j) {
        Subtractor subtractor = new Subtractor();
        return subtractor.subtract(i, j);
    }
    
}

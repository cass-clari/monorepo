package main.java.org.example;

public class Calculator {

    public double addTwoNumbers(double i, double j) {
        Adder adder = new Adder();
        return adder.add(i, j);
    }

    public double multiplyTwoNumbers(double i, double j) {
        Multiplier multi = new Multiplier();
        return multi.multiply(i, j);
    }

    public double divideTwoNumbers(double i, double j) {
        Divider divider = new Divider();
        return divider.divide(i, j);
    }

    public double subtractTwoNumbers(double i, double j) {
        Subtractor subtractor = new Subtractor();
        return subtractor.subtract(i, j);
    }

    public double modTwoNumbers(double i, double j) {
        Modder modder = new Modder();
        return modder.mod(i, j);
    }
    
}

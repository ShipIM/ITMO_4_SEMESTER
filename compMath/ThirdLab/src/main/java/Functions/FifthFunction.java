package Functions;

public class FifthFunction implements Function {
    @Override
    public double compute(double x) {
        return -2 * Math.pow(x, 3) - 3 * Math.pow(x, 2) + x + 5;
    }

    @Override
    public String toString() {
        return "-2x^3 - 3x^2 + x + 5";
    }
}

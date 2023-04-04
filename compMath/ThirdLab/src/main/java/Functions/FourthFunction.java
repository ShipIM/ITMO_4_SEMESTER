package Functions;

public class FourthFunction implements Function {
    @Override
    public double compute(double x) {
        return -2 * Math.pow(x, 3) - 4 * Math.pow(x, 2) + 8 * x - 4;
    }

    @Override
    public String toString() {
        return "-2x^3 - 4x^2 + 8x - 4";
    }
}

package Functions;

public class FirstFunction implements Function {
    @Override
    public double compute(double x) {
        return -Math.pow(x, 3) - Math.pow(x, 2) - 2 * x + 1;
    }

    @Override
    public String toString() {
        return "-x^3 - x^2 - 2x + 1";
    }
}

package Functions;

public class SixthFunction implements Function {
    @Override
    public double compute(double x) {
        return 2 * x / Math.sqrt(1 - Math.pow(x, 4));
    }

    @Override
    public String toString() {
        return "2 * x / sqrt(1 - x^4)";
    }
}
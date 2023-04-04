package Functions;

public class SecondFunction implements Function {
    @Override
    public double compute(double x) {
        return 1 / Math.sqrt(x);
    }

    @Override
    public String toString() {
        return "1 / sqrt(x)";
    }
}
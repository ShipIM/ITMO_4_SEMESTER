package Functions;

public class ThirdFunction implements Function {
    @Override
    public double compute(double x) {
        return 1 / (1 - x);
    }

    @Override
    public String toString() {
        return "1 / (1 - x)";
    }
}
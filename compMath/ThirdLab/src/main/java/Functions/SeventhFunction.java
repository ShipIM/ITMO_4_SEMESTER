package Functions;

public class SeventhFunction implements Function {
    @Override
    public double compute(double x) {
        return 1 / x;
    }

    @Override
    public String toString() {
        return "1 / x";
    }
}

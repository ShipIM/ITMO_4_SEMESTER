package Math;

public class Inaccuracy {
    public static boolean rungeMethod(double previous, double next, int k, double accuracy) {
        return Math.abs((previous - next) / (Math.pow(2, k) - 1)) <= accuracy;
    }
}

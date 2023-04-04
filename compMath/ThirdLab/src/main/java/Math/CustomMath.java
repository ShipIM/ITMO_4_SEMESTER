package Math;

import Functions.Approximation;
import Functions.Discontinuity;
import Functions.Function;
import Methods.Method;
import Methods.OverflowException;
import javafx.util.Pair;

public class CustomMath {
    private static final double ACCURACY = 0.001;

    public static Pair<Discontinuity, Method.Result> integralResult(Method method, Function function, double lowerBound,
                                                                    double upperBound, double accuracy) {
        double h = (upperBound - lowerBound) / 10000, parameter, answer;
        Method.Result result;

        for (int i = 0; (parameter = (lowerBound + h * i)) <= upperBound; i++) {
            answer = function.compute(parameter);
            if (Double.isNaN(answer) || Double.isInfinite(answer)) {
                if (parameter == lowerBound) {
                    result = oneWayLimit(method, function, lowerBound, upperBound, accuracy,
                            k -> Math.pow(2, -k), false);

                    return new Pair<>(Discontinuity.A_POINT, !Double.isNaN(result.getValue()) ? result : null);
                } else if (parameter == upperBound) {
                    result = oneWayLimit(method, function, lowerBound, upperBound, accuracy,
                            k -> Math.pow(2, -k), true);

                    return new Pair<>(Discontinuity.B_POINT, !Double.isNaN(result.getValue()) ? result : null);
                } else {
                    Method.Result lower, upper;

                    lower = oneWayLimit(method, function, lowerBound, parameter, accuracy,
                            k -> Math.pow(2, -k), true);

                    upper = oneWayLimit(method, function, parameter, upperBound, accuracy,
                            k -> Math.pow(2, -k), false);

                    return new Pair<>(Discontinuity.INTERVAL,
                            !Double.isNaN(lower.getValue()) && !Double.isNaN(upper.getValue())
                                    ? new Method.Result(lower.getValue() + upper.getValue(),
                                    lower.getDivision() + upper.getDivision()) : null);
                }
            }
        }

        return new Pair<>(Discontinuity.NONE, method.compute(lowerBound, upperBound, accuracy, function));
    }

    private static Method.Result oneWayLimit(Method method, Function function, double lowerBound, double upperBound,
                                             double accuracy, Approximation approximation, boolean type) {
        int k = 0;
        double previous, current = 0;
        Method.Result result = new Method.Result(0, 0);

        do {
            previous = current;

            try {
                if (type) {
                    result = method.compute(lowerBound, upperBound - approximation.calculate(k),
                            accuracy, function);
                } else {
                    result = method.compute(lowerBound + approximation.calculate(k),
                            upperBound, accuracy, function);
                }
                current = result.getValue();
            } catch (OverflowException e) {
                result.setValue(Double.NaN);
                current = Double.NaN;
            }

            k++;
        } while (!Double.isNaN(current) && Math.abs(previous - current) > ACCURACY || k == 1);

        return result;
    }
}

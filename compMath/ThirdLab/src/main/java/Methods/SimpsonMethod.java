package Methods;

import Functions.Function;
import Math.BadFunctionException;
import Math.Inaccuracy;

public class SimpsonMethod implements Method {
    @Override
    public Result compute(double leftBound, double rightBound, double accuracy, Function function) {
        long n = 4;
        double h = (rightBound - leftBound) / n, previous = 0, next = 0;

        while (true) {
            if (n > 10000000)
                throw new OverflowException("Слишком большое количество разбиений");

            for (int i = 1; i <= n - 1; i += 2)
                next += 4 * function.compute((leftBound + h * i));

            for (int i = 2; i <= n - 2; i += 2)
                next += 2 * function.compute((leftBound + h * i));

            next = next + function.compute(leftBound) + function.compute(leftBound + h * n);
            next *= h / 3;

            if (Double.isNaN(next))
                throw new BadFunctionException("Не получается вычислить значение данной функции");

            if (n != 4 && Inaccuracy.rungeMethod(previous, next, 4, accuracy))
                break;

            previous = next;
            next = 0;
            n *= 2;
            h /= 2;
        }

        return new Result(next, n);
    }

    @Override
    public String toString() {
        return "Метод Симпсона.";
    }
}

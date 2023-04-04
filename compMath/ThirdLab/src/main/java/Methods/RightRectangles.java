package Methods;

import Functions.Function;
import Math.BadFunctionException;
import Math.Inaccuracy;

public class RightRectangles implements Method {
    @Override
    public Result compute(double leftBound, double rightBound, double accuracy, Function function) {
        long n = 4;
        double h = (rightBound - leftBound) / n, previous = 0, next = 0;

        while (true) {
            if (n > 10000000)
                throw new OverflowException("Слишком большое количество разбиений");

            for (int i = 1; i <= n; i++)
                next += h * function.compute(leftBound + h * i);

            if (Double.isNaN(next))
                throw new BadFunctionException("Не получается вычислить значение данной функции");

            if (n != 4 && Inaccuracy.rungeMethod(previous, next, 1, accuracy))
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
        return "Метод правых прямоугольников.";
    }
}

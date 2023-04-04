package Input;

import Functions.Function;
import Methods.Method;
import javafx.util.Pair;

import java.util.List;
import java.util.Scanner;

public class ConsoleSource extends AbstractSource {
    public ConsoleSource(Scanner input) {
        super(input);
    }

    @Override
    public Pair<Double, Double> bounds() {
        double leftBound, rightBound;

        try {
            System.out.println("Введите нижнюю границу интегрирования:");
            leftBound = Double.parseDouble(input.nextLine());

            System.out.println("Введите верхнюю границу интегрирования:");
            rightBound = Double.parseDouble(input.nextLine());

            return new Pair<>(leftBound, rightBound);
        } catch (NumberFormatException exception) {
            throw new DataInputException("На ввод ожидалось число.");
        }
    }

    @Override
    public int expression(List<Function> functions) {
        System.out.println("Укажите номер подынтегральной функции:");

        return iterate(functions) - 1;
    }

    @Override
    public int method(List<Method> methods) {
        System.out.println("Укажите номер метода:");

        return iterate(methods) - 1;
    }

    private int iterate(List<?> elements) {
        int number;

        try {
            for (int i = 0; i < elements.size(); i++)
                System.out.println((i + 1) + ". " + elements.get(i));

            number = Integer.parseInt(input.nextLine());

            if (number < 1 || number > elements.size())
                throw new DataInputException("Номер должен быть в диапазоне от 1 до " + elements.size() + ".");

            return number;
        } catch (NumberFormatException exception) {
            throw new DataInputException("На ввод ожидалось число.");
        }
    }

    @Override
    public double precision() {
        System.out.println("Укажите желаемую точность:");

        try {
            return Double.parseDouble(input.nextLine());
        } catch (NumberFormatException exception) {
            throw new DataInputException("На ввод ожидалось число.");
        }
    }
}

package Main;

import Functions.Discontinuity;
import Functions.Function;
import Input.ConsoleSource;
import Input.DataInputException;
import Input.DataSource;
import Math.BadFunctionException;
import Math.CustomMath;
import Methods.Method;
import javafx.util.Pair;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataSource dataSource = new ConsoleSource(scanner);

        Reflections reflections = new Reflections();
        List<Function> functions = new ArrayList<>();
        List<Method> methods = new ArrayList<>();

        try {
            for (Class<? extends Function> function : reflections.getSubTypesOf(Function.class)) {
                functions.add(function.getConstructor().newInstance());
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            for (Class<? extends Method> method : reflections.getSubTypesOf(Method.class)) {
                methods.add(method.getConstructor().newInstance());
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            int expressionNumber = dataSource.expression(functions), methodNumber = dataSource.method(methods);
            Pair<Double, Double> bounds = dataSource.bounds();
            double precision = dataSource.precision();

            Pair<Discontinuity, Method.Result> resultPair = CustomMath.integralResult(methods.get(methodNumber),
                    functions.get(expressionNumber), bounds.getKey(), bounds.getValue(), precision);

            String builder = resultPair.getValue() != null ? resultPair.getKey().getDescription() + ".\n" +
                    "Значение интеграла: " + resultPair.getValue().getValue() + ".\n" + "Получено за " +
                    resultPair.getValue().getDivision() + " разбиений."
                    : "Не удалось посчитать значение интеграла: интеграл является расходящимся.";
            System.out.println(builder);
        } catch (DataInputException e) {
            System.out.println(e.getMessage() + "\nПерезапустите программу, чтобы попробовать ещё раз.");
        } catch (BadFunctionException e) {
            System.out.println("Не удалось посчитать значение интеграла.");
        }
    }
}

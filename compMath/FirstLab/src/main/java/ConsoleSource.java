import java.util.Scanner;

public class ConsoleSource extends AbstractSource {
    public ConsoleSource(Scanner input) {
        super(input);
    }

    @Override
    public double[][] data() {
        try {
            System.out.println("Введите количество неизвестных уравнения:");
            int counter = Integer.parseInt(input.nextLine());

            String[] params;
            double[][] result = new double[counter][counter + 1];

            System.out.println("Введите коэффициенты уравнения (для каждого уравнения с новой строки через пробел):");

            for (int i = 0; i < counter; i++) {
                System.out.println((i + 1) + "-ое уравнение: ");
                params = input.nextLine().split(" ");

                for (int j = 0; j < counter + 1; j++)
                    result[i][j] = Double.parseDouble(params[j]);
            }

            return result;
        } catch (NumberFormatException exception) {
            throw new DataInputException("На ввод ожидалось число.");
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new DataInputException("Введено неверное количество коэффициентов.");
        }
    }

    @Override
    public double precision() {
        System.out.println("Укажите желаемую точность:");

        try {
            return Double.parseDouble(input.nextLine());
        } catch (NumberFormatException exception) {
            throw new DataInputException("Точность должна быть числом.");
        }
    }
}

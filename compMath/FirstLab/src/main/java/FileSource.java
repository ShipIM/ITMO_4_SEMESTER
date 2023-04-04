import java.util.Scanner;

public class FileSource extends AbstractSource {
    public FileSource(Scanner input) {
        super(input);
    }

    @Override
    public double[][] data() {
        try {
            int counter = Integer.parseInt(input.nextLine());

            String[] params;
            double[][] result = new double[counter][counter + 1];

            for (int i = 0; i < counter; i++) {
                params = input.nextLine().split(" ");

                for (int j = 0; j < counter + 1; j++)
                    result[i][j] = Double.parseDouble(params[j]);
            }

            return result;
        } catch (NumberFormatException exception) {
            throw new DataInputException("На ввод ожидалось число.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataInputException("Введено неверное количество коэффициентов.");
        }
    }
}
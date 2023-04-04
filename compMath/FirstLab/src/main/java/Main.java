import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner consoleInput = new Scanner(System.in);

        System.out.println("Укажите желаемую форму ввода данных [file, console]:");
        String input = consoleInput.nextLine();

        try {
            DataSource dataInput;

            if (input.equals("file")) {
                System.out.println("Укажите путь до файла из которого будет производиться чтение:");
                File file = new File(consoleInput.nextLine());

                try {
                    dataInput = new FileSource(new Scanner(file));
                } catch (IOException e) {
                    throw new DataInputException("Что-то не так с указанным файлом");
                }
            } else if (input.equals("console")) {
                dataInput = new ConsoleSource(consoleInput);
            } else {
                throw new DataInputException("Не выбран ни один из допустимых вариантов.");
            }

            SimpleIteration task = new SimpleIteration(dataInput);
            double[] result = task.process();

            System.out.println(result != null ? Arrays.toString(result) : "Определенного ответа нет");
        } catch (DataInputException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Перезапустите программу для продолжения работы.");
        }
    }
}

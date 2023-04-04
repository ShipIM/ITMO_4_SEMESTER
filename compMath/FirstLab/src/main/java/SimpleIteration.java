import java.util.Arrays;
import java.util.HashMap;

public class SimpleIteration {
    private final DataSource dataSource;

    public SimpleIteration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public double[] process() {
        double[][] tmp = dataSource.data();
        double precision = dataSource.precision();

        tmp = diagonal(tmp);

        for (int i = 0; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                System.out.print(tmp[i][j] + " ");
            }
            System.out.println("\n");
        }

        tmp = transform(tmp);

        if (condition(tmp)) System.out.println("Достаточное условие сходимости выполняется.");
        else System.out.println("Достаточное условие сходимости не выполняется.");

        return iterate(tmp, precision, 10000);
    }

    private double[][] diagonal(double[][] array) {
        HashMap<Integer, double[]> sorted = new HashMap<>();
        double sum, max, previous;
        int column = 0;

        for (double[] columns : array) {
            max = Float.MIN_VALUE;
            sum = 0;

            for (int i = 0; i < array.length; i++) {
                previous = max;
                max = Math.max(max, Math.abs(columns[i]));

                if (previous != max) column = i;

                sum += Math.abs(columns[i]);
            }

            sum -= max;
            if (max >= sum)
                if (!sorted.containsKey(column)) sorted.put(column, columns);
                else {
                    System.out.println("Приведение к диагональному преобладанию невозможно.");
                    return array;
                }
            else {
                System.out.println("Приведение к диагональному преобладанию невозможно.");
                return array;
            }
        }

        double[][] result = new double[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            if (sorted.containsKey(i)) result[i] = sorted.get(i);
            else {
                System.out.println("Приведение к диагональному преобладанию невозможно.");
                return array;
            }
        }

        return result;
    }

    private double[][] transform(double[][] array) {
        double[][] result = new double[array.length][array[0].length];
        double parameter;

        for (int i = 0; i < array.length; i++) {
            parameter = array[i][i];

            for (int j = 0; j < array[0].length; j++) {
                if (i == j) {
                    result[i][j] = 0;
                    continue;
                }

                result[i][j] = array[i][j] / parameter;
                if (j != array[0].length - 1) result[i][j] *= -1;
            }
        }

        return result;
    }

    private boolean condition(double[][] array) {
        double max = Double.MIN_VALUE, sum;

        for (double[] columns : array) {
            sum = 0;

            for (int i = 0; i < columns.length - 1; i++) {
                sum += Math.abs(columns[i]);
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);

        return max < 1;
    }

    private double[] iterate(double[][] array, double precision, int limit) {
        double[] previous, current = new double[array.length];
        for (int i = 0; i < array.length; i++)
            current[i] = array[i][array[0].length - 1];

        double criteria, max = Double.MIN_VALUE, tmp = 0;
        int counter = 0;

        do {
            previous = current.clone();

            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length - 1; j++) {
                    tmp += array[i][j] * previous[j];
                }
                tmp += array[i][array[0].length - 1];

                if (tmp == Double.NEGATIVE_INFINITY || tmp == Double.POSITIVE_INFINITY || Double.isNaN(tmp)) {
                    System.out.println("Метод расходится за " + counter + " итераций.");

                    return null;
                }

                current[i] = tmp;
                max = Math.max(max, Math.abs(current[i] - previous[i]));

                tmp = 0;
            }

            criteria = max;
            max = Double.MIN_VALUE;
            counter++;
        } while (counter < limit && criteria > precision);

        if (counter == limit) {
            System.out.println("Результат не получен за " + limit + " итераций.");
            return null;
        }

        for (int i = 0; i < current.length; i++) {
            previous[i] = Math.abs(current[i] - previous[i]);
        }

        System.out.println("Вектор погрешностей: " + Arrays.toString(previous));

        System.out.println("Результат получен за " + counter + " итераций.");
        return current;
    }
}

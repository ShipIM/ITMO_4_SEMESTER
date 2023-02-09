import java.util.Arrays;
import java.util.HashMap;

public class SimpleIteration {
    private final DataSource dataSource;

    public SimpleIteration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public float[] process() {
        float[][] tmp = dataSource.data();
        float precision = dataSource.precision();

        tmp = diagonal(tmp);

        tmp = transform(tmp);

        if (condition(tmp))
            System.out.println("Достаточное условие сходимости выполняется.");
        else
            System.out.println("Достаточное условие сходимости не выполняется.");

        return iterate(tmp, precision, 10000);
    }

    private float[][] diagonal(float[][] array) {
        HashMap<Integer, float[]> sorted = new HashMap<>();
        float sum, max, previous;
        int column = 0;

        for (float[] columns : array) {
            max = Float.MIN_VALUE;
            sum = 0;

            for (int i = 0; i < array.length; i++) {
                previous = max;
                max = Math.max(max, Math.abs(columns[i]));

                if (previous != max) column = i;

                sum += Math.abs(columns[i]);
            }

            sum -= max;
            if (max >= sum) if (!sorted.containsKey(column)) sorted.put(column, columns);
            else {
                System.out.println("Приведение к диагональному представлению невозможно.");
                return array;
            }
            else {
                System.out.println("Приведение к диагональному представлению невозможно.");
                return array;
            }
        }

        float[][] result = new float[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            if (sorted.containsKey(i)) result[i] = sorted.get(i);
            else {
                System.out.println("Приведение к диагональному преобладанию невозможно.");
                return array;
            }
        }

        return result;
    }

    private float[][] transform(float[][] array) {
        float[][] result = new float[array.length][array[0].length];
        float parameter;

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

    private boolean condition(float[][] array) {
        float max = Float.MIN_VALUE, sum;

        for (float[] columns : array) {
            sum = 0;

            for (int i = 0; i < columns.length - 1; i++) {
                sum += Math.abs(columns[i]);
            }

            max = Math.max(max, sum);
        }

        return max < 1;
    }

    private float[] iterate(float[][] array, float precision, int limit) {
        float[] previous, current = new float[array.length];
        for (int i = 0; i < array.length; i++)
            current[i] = array[i][array[0].length - 1];

        float criteria, max = Float.MIN_VALUE, tmp = 0;
        int counter = 0;

        do {
            previous = current.clone();

            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[0].length - 1; j++) {
                    tmp += array[i][j] * previous[j];
                }
                tmp += array[i][array[0].length - 1];

                current[i] = tmp;
                max = Math.max(max, Math.abs(current[i] - previous[i]));

                tmp = 0;
            }

            criteria = max;
            max = Float.MIN_VALUE;
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

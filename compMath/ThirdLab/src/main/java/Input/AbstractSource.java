package Input;

import java.util.Scanner;

public abstract class AbstractSource implements DataSource {
    protected final Scanner input;

    public AbstractSource(Scanner input) {
        this.input = input;
    }

    @Override
    public double precision() {
        return Double.parseDouble(input.nextLine());
    }
}

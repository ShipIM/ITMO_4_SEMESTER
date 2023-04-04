package Input;

import Functions.Function;
import Methods.Method;
import javafx.util.Pair;

import java.util.List;

public interface DataSource {
    int expression(List<Function> functions);

    Pair<Double, Double> bounds();

    int method(List<Method> methods);

    double precision();
}

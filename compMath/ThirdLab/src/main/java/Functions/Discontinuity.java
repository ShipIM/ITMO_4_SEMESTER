package Functions;

public enum Discontinuity {
    A_POINT("Подынтегральная функция терпит бесконечный разрыв в точке A"),
    B_POINT("Подынтегральная функция терпит бесконечный разрыв в точке B"),
    INTERVAL("Подынтегральная функция терпит бесконечный разрыв на отрезке интегрирования"),
    NONE("Подынтегральная функция не содержит разрывов");

    private final String description;

    Discontinuity(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

package Methods;

import Functions.Function;

@FunctionalInterface
public interface Method {
    Result compute(double leftBound, double rightBound, double accuracy, Function function);

    class Result {
        private double value;
        private long division;

        public Result() {

        }

        public Result(double value, long division) {
            this.value = value;
            this.division = division;
        }

        public double getValue() {
            return value;
        }

        public long getDivision() {
            return division;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}

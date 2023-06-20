public class RectangleMethod {
    private final Data data;

    public RectangleMethod(Data data) {
        this.data = data;
    }


    public double doMethod() {
        int n = 2;
        double previousAnswer = 0;
        double answer = Double.MAX_VALUE;
        if (data.findDiscontinuities() == null) {
            while (Math.abs(answer - previousAnswer) > data.getEpsilon()) {
                previousAnswer = answer;
                if (data.getMethodNumber() == 1) {
                    answer = getSquareLeft(data.getLowerLimit(), data.getUpperLimit(), n);
                }
                if (data.getMethodNumber() == 2) {
                    answer = getSquareRight(data.getLowerLimit(), data.getUpperLimit(), n);
                }
                if (data.getMethodNumber() == 3) {
                    answer = getSquareMiddle(data.getLowerLimit(), data.getUpperLimit(), n);
                }
                n *= 2;
            }
        } else {
            System.out.println("В интервал интегрирования входит точка разрыва первого рода. Рассчитаем интеграл слева и справа от точки разрыва.");
            while (Math.abs(answer - previousAnswer) > data.getEpsilon()) {
                previousAnswer = answer;
                double leftPart, rightPart;
                if (data.getMethodNumber() == 1) {
                    leftPart = getSquareLeft(data.getLowerLimit(), data.findDiscontinuities(), n);
                    rightPart = getSquareLeft(data.findDiscontinuities(), data.getUpperLimit(), n);

                } else if (data.getMethodNumber() == 2) {
                    leftPart = getSquareRight(data.getLowerLimit(), data.findDiscontinuities(), n);
                    rightPart = getSquareRight(data.findDiscontinuities(), data.getUpperLimit(), n);
                } else {
                    leftPart = getSquareMiddle(data.getLowerLimit(), data.findDiscontinuities(), n);
                    rightPart = getSquareMiddle(data.findDiscontinuities(), data.getUpperLimit(), n);
                }
                answer = leftPart + rightPart;
                n *= 2;
            }
        }
        return answer;
    }

    public double getSquareLeft(double lowerLimit, double upperLimit, int n) {
        double h = (upperLimit - lowerLimit) / n;
        double square = 0;
        for (int i = 0; i < n; i++) {
            double xi = lowerLimit + i * h;
            square += data.getFunction(xi);
        }
        return h * square;
    }

    public double getSquareRight(double lowerLimit, double upperLimit, int n) {
        double h = (upperLimit - lowerLimit) / n;
        double square = 0;
        for (int i = 1; i <= n; i++) {
            double xi = lowerLimit + i * h;
            square += data.getFunction(xi);
        }
        return h * square;
    }

    public double getSquareMiddle(double lowerLimit, double upperLimit, int n) {
        double h = (upperLimit - lowerLimit) / n;
        double square = 0;
        for (int i = 0; i < n; i++) {
            double xi = lowerLimit + i * h + h / 2;
            square += data.getFunction(xi);
        }
        return h * square;
    }
}

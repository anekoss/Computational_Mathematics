public class Data {
    private int functionNumber;
    private int methodNumber;
    private double lowerLimit;
    private double upperLimit;
    private double epsilon;

    public void setFunctionNumber(int functionNumber) {
        this.functionNumber = functionNumber;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(double epsilon) {
        this.epsilon = epsilon;
    }


    public int getMethodNumber() {
        return methodNumber;
    }

    public void setMethodNumber(int methodNumber) {
        this.methodNumber = methodNumber;
    }

    public double getFunction(double x) {
        switch (functionNumber) {
            case 1:
                return Math.pow(x, 3) - 2 * x + 3;
            case 2:
                return Math.sin(x) / x;
            case 3:
                return 3 * Math.log(x) / (x - 1);
            default:
                return x * Math.cos(x);
        }
    }

    public void isFunctionDefined() throws FunctionNotDefinedException {
        if (functionNumber == 2 && (upperLimit == 0 || lowerLimit == 0))
            throw new FunctionNotDefinedException("Одно из введенных границ не входит в область допустимых значений функции");
        if (functionNumber == 3 && (upperLimit <= 0 || lowerLimit <= 0 || upperLimit == 1 || lowerLimit == 1))
            throw new FunctionNotDefinedException("Одно из введенных границ не входит в область допустимых значений функции");
    }

    public Double findDiscontinuities() {
        if (functionNumber == 2) {
            if (lowerLimit < 0 && upperLimit > 0) {
                return 0.;
            }
        } else if (functionNumber == 3) {
            if (lowerLimit < 1 && upperLimit > 1) {
                return 1.;
            }
        }
        return null;
    }

}

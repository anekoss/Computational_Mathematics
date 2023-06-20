import data.Equation;
import data.Point;

import java.util.ArrayList;

public class MilneMethod {
    private final Equation equation;
    private final double h;

    public MilneMethod(Equation equation) {
        this.equation = equation;
        this.h = equation.getStep();
    }

    private double doRungeKuttaMethod(double x, double y) {
        double k1 = h * equation.calcFunction(x, y);
        double k2 = h * equation.calcFunction(x + h / 2, y + k1 / 2.);
        double k3 = h * equation.calcFunction(x + h / 2, y + k2 / 2.);
        double k4 = h * equation.calcFunction(x + h, y + k3);
        return y + (k1 + 2 * k2 + 2 * k3 + k4) / 6.;
    }

    public void doMilneMethod() {
        double epsilon = equation.getEpsilon();
        ArrayList<Point> points = equation.getPoints();
        ArrayList<Double> function = equation.getFunc();
        double leftBorder = equation.getLeftBound();
        double y1 = doRungeKuttaMethod(leftBorder, equation.getStartY());
        double y2 = doRungeKuttaMethod(leftBorder + h, y1);
        double y3 = doRungeKuttaMethod(leftBorder + 2 * h, y2);
        points.add(new Point(leftBorder + h, y1));
        points.add(new Point(leftBorder + 2 * h, y2));
        points.add(new Point(leftBorder + 3 * h, y3));
        double f0 = equation.calcFunction(leftBorder, equation.getStartY());
        double f1 = equation.calcFunction(leftBorder + h, y1);
        double f2 = equation.calcFunction(leftBorder + 2 * h, y2);
        double f3 = equation.calcFunction(leftBorder + 3 * h, y3);
        function.add(f0);
        function.add(f1);
        function.add(f2);
        function.add(f3);
        int i = 4;
        for (double x = leftBorder + 4 * h; x <= equation.getRightBound()+h; x += h) {
            double yPrediction = points.get(i - 4).getY() + 4 * h * (2 * function.get(i - 1) - function.get(i - 2) + 2 * function.get(i - 3)) / 3.;
            while (true) {
                double yCorrection = points.get(i - 2).getY() + h * (function.get(i - 2) + 4 * function.get(i - 1) + equation.calcFunction(x, yPrediction)) / 3;
                double currentEpsilon = Math.abs(yCorrection - yPrediction) / 29;
                if (currentEpsilon <= epsilon) {
                    points.add(new Point(x, yCorrection));
                    function.add(equation.calcFunction(x, yCorrection));
                    break;
                } else {
                    yPrediction = yCorrection;
                }
            }
            i++;
        }
    }

}

package method;

import data.Equation;
import data.Point;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Spline {
    protected ArrayList<Double> xList;
    protected ArrayList<Double> yList;
    protected int type;
    protected final Equation equation;
    public HashMap<Integer, double[]> coefficients = new HashMap<>();


    public Spline(Equation equation) {
        this.xList = equation.getXList();
        this.yList = equation.getYList(1);
        this.equation = equation;
    }

    public void changeType(int type) {
        this.type = type;
        this.yList = equation.getYList(type);
    }

    public void doSpline() {
        calcCoefficients();
        equation.getPointsForChart().put(1,createFunctionForChart());
        equation.fillAnalyticSolveList();
        changeType(2);
        calcCoefficients();
        equation.getPointsForChart().put(2, createFunctionForChart());


    }

    public double[] calcA() {
        double[] a = new double[yList.size()];
        for (int i = 0; i < yList.size(); i++) {
            a[i] = yList.get(i);
        }
        return a;
    }

    public double[] calcH() {
        double[] h = new double[xList.size() - 1];
        for (int i = 0; i < h.length; i++) {
            h[i] = xList.get(i + 1) - xList.get(i);
        }
        return h;
    }

    public double[][] calcMatrix(double[] h) {
        double[][] matrix = new double[xList.size()][xList.size()];
        matrix[0][0] = 1;
        matrix[xList.size() - 1][xList.size() - 1] = 1;
        for (int i = 1; i < matrix.length - 1; i++) {
            matrix[i][i - 1] = h[i - 1];
            matrix[i][i + 1] = h[i];
            matrix[i][i] = (h[i] + h[i - 1]) * 2;
        }
        return matrix;
    }

    public double[] calcFreeTerm(double[] h) {
        double[] freeTerm = new double[xList.size()];
        freeTerm[0] = 0;
        freeTerm[xList.size() - 1] = 0;
        for (int i = 1; i < xList.size() - 1; i++) {
            freeTerm[i] = 3 * ((yList.get(i + 1) - yList.get(i)) / h[i] - (yList.get(i) - yList.get(i - 1)) / h[i - 1]);
        }
        return freeTerm;
    }


    public double[] calcC(double[][] matrix, double[] freeTerm) {
        double[] cArg = new double[xList.size()];
        if (Determinate.calDet(matrix, matrix.length) != 0 && IterativeMethod.checkDiagonallyDominant(freeTerm.length, matrix, freeTerm)) {
            IterativeMethod.normalize(freeTerm.length, matrix, freeTerm);
            cArg = IterativeMethod.calcRoots(freeTerm.length, matrix, freeTerm, 0.00001, freeTerm.clone());
        }
        return cArg;
    }

    public double[] calcD(double[] h, double[] c) {
        double[] d = new double[xList.size() - 1];
        for (int i = 1; i < d.length + 1; i++) {
            d[i - 1] = (c[i] - c[i - 1]) / 3 / h[i - 1];
        }
        return d;
    }

    public double[] calcB(double[] h, double[] a, double[] c, double[] d) {
        double[] b = new double[xList.size() - 1];
        for (int i = 0; i < b.length; i++) {
            b[i] = (a[i + 1] - a[i]) / h[i] + h[i] * c[i + 1] - h[i] * h[i] * d[i];
        }
        return b;
    }

    public List<Point> createFunctionForChart() {
        ArrayList<Point> functionForChart = new ArrayList<>();
        double startX = xList.get(0);
        double step = (xList.get(xList.size() - 1) - startX) / 99;
        for (int i = 0; i < 100; i++) {
            double x = startX + step * i;
            double y = getFunction(x);
            functionForChart.add(new Point(x, y));
        }
        return functionForChart;
    }

    public double getFunction(double x) {
        ArrayList<Point> points = equation.getPoints();
        double[] a = coefficients.get(0);
        double[] b = coefficients.get(1);
        double[] c = coefficients.get(2);
        double[] d = coefficients.get(3);
        for (int i = 1; i < points.size(); i++) {
            double val = points.get(i).getX();
            if (x <= val) {
                return a[i] + b[i-1] * (x - val) + c[i] * (x - val) * (x - val) + d[i-1] * (x - val) * (x - val) * (x - val);
            }
        }
        return 0;
    }


    public void calcCoefficients() {

        double[] a = calcA();
        double[] h = calcH();
        double[] c = calcC(calcMatrix(h), calcFreeTerm(h));
        double[] d = calcD(h, c);
        double[] b = calcB(h, a, c, d);
        coefficients.put(0, a);
        coefficients.put(1, b);
        coefficients.put(2, c);
        coefficients.put(3, d);

    }

}


package method;

import data.Function;

import java.util.ArrayList;

public class Spline {
    protected final ArrayList<Double> xList;
    protected final ArrayList<Double> yList;
    private final Function function;

    public Spline(Function function) {
        this.function = function;
        this.xList = function.getXList();
        this.yList = function.getYList();
    }

    public double[] calcA() {
        double[] a = new double[xList.size()];
        for (int i = 0; i < a.length; i++) {
            a[i] = yList.get(i);
        }
        return a;
    }


    public double[] calcB(double[] h, double[] a, double[] c, double[] d) {
        double[] b = new double[xList.size() - 1];
        for (int i = 0; i < b.length; i++) {
            b[i] = (a[i + 1] - a[i]) / h[i] + h[i] * c[i + 1] - h[i] * h[i] * d[i];
        }
        return b;
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


    public ArrayList<Double> calcY(double[] a, double[] b, double[] c, double[] d, ArrayList<Double> xVal) {
        ArrayList<Double> y = new ArrayList<>();
        for (int i = 0; i < xVal.size(); i++) {
            if (xVal.get(i) <= xList.get(1) + 0.0000001) {
                y.add(a[1] + b[0] * (xVal.get(i) - xList.get(1)) + c[1] * (xVal.get(i) - xList.get(1)) * (xVal.get(i) - xList.get(1)) + d[0] * (xVal.get(i) - xList.get(1)) * (xVal.get(i) - xList.get(1)) * (xVal.get(i) - xList.get(1)));
            } else if (xVal.get(i) <= xList.get(2) + 0.0000001) {
                y.add(a[2] + b[1] * (xVal.get(i) - xList.get(2)) + c[2] * (xVal.get(i) - xList.get(2)) * (xVal.get(i) - xList.get(2)) + d[1] * (xVal.get(i) - xList.get(2)) * (xVal.get(i) - xList.get(2)) * (xVal.get(i) - xList.get(2)));
            } else if (xVal.get(i) <= xList.get(3) + 0.0000001) {
                y.add(a[3] + b[2] * (xVal.get(i) - xList.get(3)) + c[3] * (xVal.get(i) - xList.get(3)) * (xVal.get(i) - xList.get(3)) + d[2] * (xVal.get(i) - xList.get(3)) * (xVal.get(i) - xList.get(3)) * (xVal.get(i) - xList.get(3)));
            } else if (xVal.get(i) <= xList.get(4) + 0.0000001) {
                y.add(a[4] + b[3] * (xVal.get(i) - xList.get(4)) + c[4] * (xVal.get(i) - xList.get(4)) * (xVal.get(i) - xList.get(4)) + d[3] * (xVal.get(i) - xList.get(4)) * (xVal.get(i) - xList.get(4)) * (xVal.get(i) - xList.get(4)));
            } else if (xVal.get(i) <= xList.get(5) + 0.0000001) {
                y.add(a[5] + b[4] * (xVal.get(i) - xList.get(5)) + c[5] * (xVal.get(i) - xList.get(5)) * (xVal.get(i) - xList.get(5)) + d[4] * (xVal.get(i) - xList.get(5)) * (xVal.get(i) - xList.get(5)) * (xVal.get(i) - xList.get(5)));
            } else if (xVal.get(i) <= xList.get(6) + 0.0000001) {
                y.add(a[6] + b[5] * (xVal.get(i) - xList.get(6)) + c[6] * (xVal.get(i) - xList.get(6)) * (xVal.get(i) - xList.get(6)) + d[5] * (xVal.get(i) - xList.get(6)) * (xVal.get(i) - xList.get(6)) * (xVal.get(i) - xList.get(6)));
            } else if (xVal.get(i) <= xList.get(7) + 0.0000001) {
                y.add(a[7] + b[6] * (xVal.get(i) - xList.get(7)) + c[7] * (xVal.get(i) - xList.get(7)) * (xVal.get(i) - xList.get(7)) + d[6] * (xVal.get(i) - xList.get(7)) * (xVal.get(i) - xList.get(7)) * (xVal.get(i) - xList.get(7)));
            }
        }

        calcYVal(a, b, c, d, function.getX());

        return y;
    }

    public void calcYVal(double[] a, double[] b, double[] c, double[] d, double x) {
        double y = 0;
        if (x <= xList.get(1)) {
            y = (a[1] + b[0] * (x - xList.get(1)) + c[1] * (x - xList.get(1)) * (x - xList.get(1)) + d[0] * (x - xList.get(1)) * (x - xList.get(1)) * (x - xList.get(1)));
        } else if (x <= xList.get(2)) {
            y = (a[2] + b[1] * (x - xList.get(2)) + c[2] * (x - xList.get(2)) * (x - xList.get(2)) + d[1] * (x - xList.get(2)) * (x - xList.get(2)) * (x - xList.get(2)));
        } else if (x <= xList.get(3)) {
            y = (a[3] + b[2] * (x - xList.get(3)) + c[3] * (x - xList.get(3)) * (x - xList.get(3)) + d[2] * (x - xList.get(3)) * (x - xList.get(3)) * (x - xList.get(3)));
        } else if (x <= xList.get(4)) {
            y = (a[4] + b[3] * (x - xList.get(4)) + c[4] * (x - xList.get(4)) * (x - xList.get(4)) + d[3] * (x - xList.get(4)) * (x - xList.get(4)) * (x - xList.get(4)));
        } else if (x <= xList.get(5)) {
            y = (a[5] + b[4] * (x - xList.get(5)) + c[5] * (x - xList.get(5)) * (x - xList.get(5)) + d[4] * (x - xList.get(5)) * (x - xList.get(5)) * (x - xList.get(5)));
        } else if (x <= xList.get(6)) {
            y = (a[6] + b[5] * (x - xList.get(6)) + c[6] * (x - xList.get(6)) * (x - xList.get(6)) + d[5] * (x - xList.get(6)) * (x - xList.get(6)) * (x - xList.get(6)));
        } else if (x <= xList.get(7)) {
            y = (a[7] + b[6] * (x - xList.get(7)) + c[7] * (x - xList.get(7)) * (x - xList.get(7)) + d[6] * (x - xList.get(7)) * (x - xList.get(7)) * (x - xList.get(7)));
        }
        function.setY(y);
    }

    public ArrayList<Double> calcX() {
        ArrayList<Double> x = new ArrayList<>();

        for (Double i = xList.get(0); i <= xList.get(xList.size() - 1) + 0.00001; i += 0.1) {
            x.add(i);
        }
        return x;
    }
}


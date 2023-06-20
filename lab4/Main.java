import data.Function;
import method.Spline;
import utils.IoHandler;
import utils.Plot;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Function function = new Function();
        IoHandler ioHandler = new IoHandler(function);
        ioHandler.chooseFunction();
        function.createLists(function.getFunctionNumber());
        Spline spline = new Spline(function);
        double[] a = spline.calcA();
        double[] h = spline.calcH();
        double[] c = spline.calcC(spline.calcMatrix(h), spline.calcFreeTerm(h));
        double[] d = spline.calcD(h, c);
        double[] b = spline.calcB(h, a, c, d);
        ioHandler.getSplineValue();
        ArrayList<Double> x = spline.calcX();
        ArrayList<Double> y = spline.calcY(a, b, c, d, x);
        ioHandler.printCoefficient(function.getFunctionNumber(), a, b, c, d);
        new Plot(function, x, y).draw();

    }
}
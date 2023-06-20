import data.Equation;
import method.Spline;
import utils.IoHandler;

public class Main {
    public static void main(String[] args) {
        Equation equation = new Equation();
        IoHandler ioHandler = new IoHandler(equation);
        ioHandler.input();
        new MilneMethod(equation).doMilneMethod();
        ioHandler.printPoints();
        Spline spline = new Spline(equation);
        spline.calcCoefficients();
        spline.doSpline();
        new Plot(equation).draw();
    }
}
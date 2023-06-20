package data;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Equation {
    private int equationNumber;
    private double leftBound;
    private double rightBound;
    private double step;
    private double startY;
    private double epsilon;
    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Double> func = new ArrayList<>();
    private ArrayList<Point> analyticSolve = new ArrayList<>();
    private double constant;
    private HashMap<Integer, List<Point>> pointsForChart = new HashMap<>();

    public double calcFunction(double x, double y) {
        switch (equationNumber) {
            case 1:
                return -3 * y;
            case 2:
                return y * (x * x + 1);
            default:
                return Math.exp(x - y);
        }
    }

    private void calcConstant() {
        switch (equationNumber) {
            case 1:
                constant = startY / Math.exp(-3 * leftBound);
                break;
            case 2:
                constant =  startY / Math.exp(Math.pow(leftBound, 3) / 3 + leftBound);
                break;
            default:
                constant =  Math.exp(startY) - Math.exp(leftBound);
                break;
        }
    }

    private double getAnalyticSolve(double x) {
        switch (equationNumber) {
            case 1:
                return constant * Math.exp(-3 * x);
            case 2:
                return constant * Math.exp(Math.pow(x, 3) / 3 + x);
            default:
                return Math.log(getConstant() + Math.exp(x));
        }
    }

    public void fillAnalyticSolveList() {
        calcConstant();
        for (Point point : points) {
            analyticSolve.add(new Point(point.getX(), getAnalyticSolve(point.getX())));
        }
    }

    public ArrayList<Double> getXList() {
        ArrayList<Double> xList = new ArrayList<>();
        for (Point point : points) {
            xList.add(point.getX());
        }
        return xList;
    }

    public ArrayList<Double> getYList(int type) {
        ArrayList<Point> temp;
        if (type == 1) {
            temp = points;
        } else {
            temp = analyticSolve;
        }
        ArrayList<Double> yList = new ArrayList<>();
        for (Point point : temp) {
            yList.add(point.getY());
        }
        return yList;
    }
}


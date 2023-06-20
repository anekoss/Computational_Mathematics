
import data.Equation;
import data.Point;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.style.theme.GGPlot2Theme;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Plot {
    private final Equation equation;

    public Plot(Equation equation) {
        this.equation = equation;
    }

    public void draw() {
        ArrayList<Double> xMilneCoord = getXCoordinates(equation.getPointsForChart().get(1));
        ArrayList<Double> yMilneCoord = getYCoordinates(equation.getPointsForChart().get(1));
        ArrayList<Double> xAnalyticSolveCoord = getXCoordinates(equation.getPointsForChart().get(2));
        ArrayList<Double> yAnalyticSolveCoord = getYCoordinates(equation.getPointsForChart().get(2));
        double startX = equation.getLeftBound();
        double startY = equation.getStartY();

        XYChart chart = new XYChartBuilder()
                .width(1260)
                .height(768)
                .title("Interpolation")
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();

        XYSeries startPointDraw = chart.addSeries("Start point", new double[]{startX}, new double[]{startY});
        startPointDraw.setLineStyle(SeriesLines.NONE);

        XYSeries MilneMethodDraw = chart.addSeries("Milne method", xMilneCoord, yMilneCoord);
        MilneMethodDraw.setLineStyle(SeriesLines.SOLID);
        MilneMethodDraw.setLineColor(Color.green);
        MilneMethodDraw.setMarker(SeriesMarkers.NONE);

        XYSeries analyticSolveDraw = chart.addSeries("Analytic solve", xAnalyticSolveCoord, yAnalyticSolveCoord);
        analyticSolveDraw.setLineStyle(SeriesLines.SOLID);
        analyticSolveDraw.setLineColor(Color.pink);
        analyticSolveDraw.setMarker(SeriesMarkers.NONE);

        chart.getStyler().setTheme(new GGPlot2Theme());
        chart.getStyler().setCursorEnabled(true);
        new SwingWrapper(chart).displayChart();

    }

    private ArrayList<Double> getXCoordinates(List<Point> points) {
        ArrayList<Double> xCoord = new ArrayList<>();
        for (Point point : points) {
            xCoord.add(point.getX());
        }
        return xCoord;
    }

    private ArrayList<Double> getYCoordinates(List<Point> points) {
        ArrayList<Double> yCoord = new ArrayList<>();
        for (Point point : points) {
            yCoord.add(point.getY());
        }
        return yCoord;
    }


}

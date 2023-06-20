package utils;

import data.Function;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Plot {
    private final Function function;
    private final ArrayList<Double> x;
    private final ArrayList<Double> y;

    public Plot(Function function, ArrayList<Double> x, ArrayList<Double> y) {
        this.function = function;
        this.x = x;
        this.y = y;
    }

    public void draw() {
        XYChart chart = new XYChartBuilder()
                .width(1024)
                .height(768)
                .title("Интерполяция")
                .xAxisTitle("X")
                .yAxisTitle("Y")
                .build();

        XYSeries points = chart.addSeries("Точки", function.getXList(), function.getYList());
        points.setLineStyle(SeriesLines.NONE);
        points.setMarkerColor(Color.CYAN);

        XYSeries point = chart.addSeries("Точка интерполяции", Collections.singletonList(function.getX()), Collections.singletonList(function.getY()));
        point.setLineStyle(SeriesLines.NONE);
        point.setMarkerColor(Color.BLUE);

        XYSeries spline = chart.addSeries("Интерполяция кубическими сплайнами", x, y);
        spline.setMarker(SeriesMarkers.NONE);
        spline.setLineStyle(SeriesLines.DASH_DOT);
        spline.setLineColor(Color.pink);

        new SwingWrapper(chart).displayChart();
    }

}

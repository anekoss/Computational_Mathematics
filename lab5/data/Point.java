package data;

import lombok.Data;

@Data
public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        setX(x);
        setY(y);
    }
}

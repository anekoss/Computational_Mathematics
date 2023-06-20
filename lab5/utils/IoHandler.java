package utils;

import data.Equation;
import data.Point;

import java.util.ArrayList;
import java.util.Scanner;

public class IoHandler {
    private final Equation equation;
    Scanner scanner = new Scanner(System.in);

    public IoHandler(Equation equation) {
        this.equation = equation;
    }

    public void input() {
        equation.setEquationNumber(chooseEquation());
        System.out.println();
        double[] bounds = readBounds();
        equation.setLeftBound(bounds[0]);
        equation.setRightBound(bounds[1]);
        System.out.println();
        equation.setStep(readStep(bounds));
        System.out.println();
        equation.setStartY(readY());
        equation.getPoints().add(new Point(bounds[0], equation.getStartY()));
        System.out.println();
        equation.setEpsilon(readEpsilon());
        System.out.println();
    }


    private int chooseEquation() {
        System.out.println("Выберите функцию из списка. Введите число от 1 до 3");
        System.out.println("1. y' = -3 * y");
        System.out.println("2. y' = y * (x^2 + 1)");
        System.out.println("3. y' = e^(x - y)");
        return readInt();
    }

    private double[] readBounds() {
        while (true) {
            System.out.println("Введите левую границу");
            double leftBound = readDouble();
            System.out.println();
            System.out.println("Введите правую границу");
            double rightBound = readDouble();
            if (leftBound > rightBound) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                System.out.println();
            } else {
                return new double[] {leftBound, rightBound};
            }
        }
    }

    private double readStep(double[] bounds) {
        System.out.println("Введите шаг");
        while (true) {
            double step;
            step = readDouble();
            if (step <= 0 || step > bounds[1] - bounds[0]) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            } else {
                return step;
            }
        }
    }

    private double readY() {
        System.out.println("Введите начальное значение y");
        return readDouble();
    }

    private int readInt() {
        while (true) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number >= 1 && number <= 3) {
                    return number;
                } else {
                    System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }

    private double readDouble() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }

    private double readEpsilon() {
        System.out.println("Введите точность");
        while (true) {
            double epsilon = readDouble();
            if (epsilon > 0) return epsilon;
            System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
        }
    }

    public void printPoints() {
        System.out.println("   x   |   y   ");
        System.out.println("-------|-------");
        ArrayList<Point> points = equation.getPoints();
        for (Point point : points) {
            System.out.printf("%.4f", point.getX());
            System.out.print(" | ");
            System.out.printf("%.4f\n", point.getY());
        }
        System.out.println();

    }


}

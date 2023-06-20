package utils;

import data.Function;

import java.util.ArrayList;
import java.util.Scanner;

public class IoHandler {

    private final Function function;
    Scanner scanner = new Scanner(System.in);

    public IoHandler(Function function) {
        this.function = function;
    }

    public void chooseFunction() {
        System.out.println("Выберите функцию из списка. Введите число от 1 до 5");
        printPoints(1);
        System.out.println();
        printPoints(2);
        System.out.println();
        printPoints(3);
        System.out.println();
        printPoints(4);
        System.out.println();
        printPoints(5);
        System.out.println();
        function.setFunctionNumber(readInt());
//        System.out.println("Введите координату x точки интерполяции. Введите число от " + function.getXList().get(0) + " до " + function.getXList().get(function.getXList().size() - 1));
//        function.setX(readDouble());
        System.out.println();
    }

    public void getSplineValue() {
        System.out.println("Введите координату x точки интерполяции. Введите число от " + function.getXList().get(0) + " до " + function.getXList().get(function.getXList().size() - 1));
        function.setX(readDouble());
    }

    private void printPoints(int functionNumber) {
        function.createLists(functionNumber);
        System.out.println(functionNumber + ".");
        System.out.print("\tx | ");
        ArrayList<Double> xList = function.getXList();
        for (Double x : xList) {
            System.out.print(x + " | ");
        }
        System.out.println();
        System.out.print("\ty | ");
        ArrayList<Double> yList = function.getYList();
        for (Double y : yList) {
            System.out.print(y + " | ");
        }
        System.out.println();
    }

    public void printCoefficient(int functionNumber, double[] a, double[] b, double[] c, double[] d) {
        ArrayList<Double> xList = function.getXList();
        System.out.println("Коэффициенты функции рассчитаны методом интерполяции кубическими сплайнами");
        for (int i = 0; i < xList.size() - 1; i++) {
            System.out.print(function.getXList().get(0) + " <= x < " + function.getXList().get(i + 1) + " | ");
            System.out.print(a[i + 1] + " | ");
            System.out.print(b[i] + " | ");
            System.out.print(c[i + 1] + " | ");
            System.out.print(d[i] + " | ");
            System.out.println();
        }
        System.out.println();
        System.out.println("Значение функции в точке интерполяции: ");
        System.out.println("x: " + function.getX());
        System.out.println("y: " + function.getY());
    }

    private int readInt() {
        while (true) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number >= 1 && number <= 5) {
                    return number;
                } else {
                    System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }

    private double readDouble() {
        while (true) {
            String input = scanner.nextLine();
            try {
                double number = Double.parseDouble(input);
                if (number >= function.getXList().get(0) && number <= function.getXList().get(function.getXList().size() - 1)) {
                    return number;
                } else {
                    System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }
}

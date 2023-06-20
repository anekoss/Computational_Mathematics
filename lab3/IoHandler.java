import java.util.Scanner;

public class IoHandler {
    private final Scanner scanner = new Scanner(System.in);
    private Data data;

    public IoHandler(Data data) {
        this.data = data;
    }

    public void input(){
        data.setFunctionNumber(chooseFunction());
        System.out.println();
        data.setMethodNumber(chooseMethod());
        System.out.println();
        double[] limits = readLimits();
        data.setLowerLimit(limits[0]);
        data.setUpperLimit(limits[1]);
        System.out.println();
        data.setEpsilon(readEpsilon());
        System.out.println();

    }

    private int chooseFunction() {
        System.out.println("Выберите функцию из списка. Введите число от 1 до 4");
        System.out.println("1. y = x^3 - 2x + 3");
        System.out.println("2. y = sinx / x");
        System.out.println("3. y = 3lnx / (x - 1)");
        System.out.println("4. y = xcosx");
        return readInt();
    }

    private int chooseMethod() {
        System.out.println("Выберите метод из списка. Введите число от 1 до 3");
        System.out.println("1. метод левых прямоугольников");
        System.out.println("2. метод правых прямоугольников");
        System.out.println("3. метод средних прямоугольников");
        return readInt();

    }

    public int readInt() {
        while (true) {
            String input = scanner.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number >= 1 && number <= 4) {
                    return number;
                } else {
                    System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                }
            } catch (NumberFormatException exc) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }

    public double readDouble() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }

    public double[] readLimits() {
        while (true) {
            System.out.println("Введите нижний предел интегрирования");
            double lowerLimit = readDouble();
            System.out.println("Введите верхний предел интегрирования");
            double upperLimit = readDouble();
            if (lowerLimit > upperLimit) {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
                System.out.println();
            } else {
                return new double[]{lowerLimit, upperLimit};
            }
        }
    }


    public double readEpsilon() {
        System.out.println("Введите точность");
        while (true) {
            double epsilon = readDouble();
            if (epsilon > 0) {
                return epsilon;
            } else {
                System.out.println("Данные введены некорректно. Пожалуйста, повторите ввод");
            }
        }
    }
}

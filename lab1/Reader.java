import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Reader {
    Scanner scanner = new Scanner(System.in);
    private double[][] matrix;
    private double[] freeTerms;
    private int dim;
    private double epsilon;

    public void readFromConsole() {
        System.out.println("Введите размер матрицы из отрезка [3, 20]");
        dim = readIntFromConsole();
        matrix = new double[dim][dim];
        while (true) {
            boolean isCorrect = true;
            System.out.println("Введите построчно матрицу коэффициентов");
            for (int i = 0; i < dim; i++) {
                String input = scanner.nextLine().trim();
                double[] row = checkMatrix(input);
                if (row == null) {
                    isCorrect = false;
                    break;
                } else {
                    matrix[i] = row;
                }
            }
            if (isCorrect) {
                break;
            } else {
                System.out.println();
            }
        }
        freeTerms = new double[dim];
        System.out.println("Введите столбец свободных членов");
        for (int i = 0; i < dim; i++) {
            freeTerms[i] = readDoubleFromConsole();
        }
        System.out.println("Введите приближение");
        epsilon = readDoubleFromConsole();

    }

    public void readFromFile() {
        String fileName;
        File file;
        System.out.println("Введите имя файла");
        while (true) {
            if (scanner.hasNext()) {
                fileName = scanner.nextLine().trim();
                file = new File(fileName);
                if (!file.exists() || !file.canRead()) {
                    System.out.println("Невозможно прочитать файл, пожалуйста, повторите ввод");
                } else {
                    break;
                }
            }
        }
        try {
            FileReader fileReader = new FileReader(file);
            scanner = new Scanner(fileReader);
            while (scanner.hasNext()) {
                try {
                    dim = Integer.parseInt(scanner.nextLine().trim());
                    if (dim < 3 || dim > 20) {
                        System.out.println("Введено некорректное значение размера матрицы");
                        break;
                    }
                    matrix = new double[dim][dim];
                    freeTerms = new double[dim];
                    boolean isCorrect = true;
                    for (int i = 0; i < dim; i++) {
                        if (scanner.hasNext()) {
                            String input = scanner.nextLine().trim();
                            double[] row = checkMatrix(input);
                            if (row == null) {
                                isCorrect = false;
                                break;
                            } else {
                                for (int j = 0; j < dim; j++) {
                                    matrix[i][j] = row[j];
                                }
                            }
                        } else {
                            isCorrect = false;
                            break;
                        }

                    }
                    if (isCorrect) {
                        for (int i = 0; i < dim; i++) {
                            if (scanner.hasNext()) {
                                try {
                                    freeTerms[i] = Double.parseDouble(scanner.nextLine().trim());
                                } catch (NumberFormatException e) {
                                    System.out.println("Введенные данные некорректны");
                                    isCorrect = false;
                                    break;
                                }
                            } else {
                                System.out.println("Недостаточно данных для дальнейших вычислений");
                                isCorrect = false;
                            }
                        }
                        if (isCorrect) {
                            System.out.println("Введите приближение");
                            scanner = new Scanner(System.in);
                            epsilon = readDoubleFromConsole();
                        } else {
                            matrix = null;
                        }
                    } else {
                        matrix = null;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Введено некорректное значение размера матрицы");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Указанный файл не найден");
        }
    }

    public void fillWithRandomValues() {
        System.out.println("Введите размер матрицы из отрезка [3, 20]");
        dim = readIntFromConsole();
        matrix = new double[dim][dim];
        freeTerms = new double[dim];
        for (int i = 0; i < dim; i++) {
            freeTerms[i] = Math.random() * 76;
            for (int j = 0; j < dim; j++) {
                matrix[i][j] = Math.random() * 76;
            }
        }
        System.out.println("Введите приближение");
        epsilon = readDoubleFromConsole();
    }

    public double[] checkMatrix(String input) {
        double[] row = new double[dim];
        String[] elems = input.split(" ");
        if (elems.length != dim) {
            System.out.println("Количество коэффициентов не совпадает с размером матрицы");
            row = null;
        } else {
            boolean isCorrect = true;
            for (String elem : elems) {
                if (!validateDouble(elem)) {
                    isCorrect = false;
                    break;
                }
            }
            if (isCorrect) {
                for (int i = 0; i < elems.length; i++) {
                    row[i] = Double.parseDouble(elems[i]);
                }
            } else {
                System.out.println("Введенные данные некорректны");
                row = null;
            }
        }
        return row;
    }

    public int readIntFromConsole() {
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            if (validateInt(input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Введено некорректное значение, пожалуйста, повторите ввод");
            }
        }
    }

    public double readDoubleFromConsole() {
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            if (validateDouble(input)) {

                return Double.parseDouble(input);
            } else {
                System.out.println("Введено некорректное значение, пожалуйста, повторите ввод");
            }
        }
    }

    public boolean validateDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean validateInt(String input) {
        try {
            int value = Integer.parseInt(input);
            return value >= 3 && value <= 20;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getDim() {
        return dim;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public double[] getFreeTerms() {
        return freeTerms;
    }
}
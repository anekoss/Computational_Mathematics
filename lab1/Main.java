import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        Determinate determinate = new Determinate();
        IterativeMethod iterativeMethod = new IterativeMethod();
        String input;
        System.out.println("Выберите способ ввода данных в программу. Введите номер желаемого метода");
        System.out.println("1. Пользовательский ввод");
        System.out.println("2. Ввод данных из файла");
        System.out.println("3. Генерация случайных чисел");
        while (true) {
            input = scanner.nextLine();
            try {
                int method = Integer.parseInt(input);
                if (method == 1) {
                    reader.readFromConsole();
                    break;
                } else if (method == 2) {
                    reader.readFromFile();
                    break;
                } else if (method == 3) {
                    reader.fillWithRandomValues();
                    break;
                } else
                    System.out.println("Введите целое число от 1 до 3");
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число от 1 до 3");
            }
        }
        double[][] matrix = reader.getMatrix();
        int dim = reader.getDim();
        double[] freeTerms = reader.getFreeTerms();
        double epsilon = reader.getEpsilon();
        if (matrix != null && freeTerms != null) {
            System.out.println();
            System.out.println("Введенная матрица коэффициентов");
            for (int i = 0; i < dim; i++) {
                for (int j = 0; j < dim; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("Введенный массив свободных членов");
            {
                for (int i = 0; i < dim; i++) {
                    System.out.print(freeTerms[i] + " ");
                }
            }
            System.out.println();
            double det = determinate.calDet(matrix, dim);
            System.out.println();
            System.out.println("Определитель матрицы: " + det);
            if (det == 0) {
                System.out.println("Определитель матрицы равен 0. Невозможно применить метод простых итераций");
            } else {
                System.out.println();
                if (iterativeMethod.checkDiagonallyDominant(dim, matrix, freeTerms)) {
                    System.out.println("Приведем матрицу к виду с преобладанием диагональных элементов");
                    for (int i = 0; i < dim; i++) {
                        for (int j = 0; j < dim; j++) {
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("Нормализуем матрицу");
                    iterativeMethod.normalize(dim, matrix, freeTerms);
                    for (int i = 0; i < dim; i++) {
                        for (int j = 0; j < dim; j++) {
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("Проверим условие сходимости");
                    if (iterativeMethod.checkConvergenceCondition(dim, matrix)) {
                        System.out.println("Условие сходимости выполнено");
                        System.out.println();
                        System.out.println("Ответ:");
                        double[] roots = iterativeMethod.calcRoots(dim, matrix, freeTerms, epsilon, freeTerms.clone());
                        for (int i = 0; i < dim; i++) {
                            System.out.println("x" + (i + 1) + " = " + roots[i]);
                        }
                        double[] error = iterativeMethod.getError();
                        System.out.println();
                        System.out.println("Погрешности: ");
                        for (int i = 0; i < error.length; i++) {
                            System.out.println("x" + (i + 1) + ": " + error[i]);
                        }
                        System.out.println();
                        System.out.println("Количество итерация: " + iterativeMethod.getCountIterations());
                    } else {
                        System.out.println("Условие сходимости не выполнено");
                    }
                } else {
                    System.out.println("Невозможно привести матрицу к виду с преобладанием диагональных элементов");
                }
            }
        }
    }
}
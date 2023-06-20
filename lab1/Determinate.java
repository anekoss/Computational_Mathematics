public class Determinate {
    public double calDet(double[][] matrix, int dim) {
        double[][] newMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < dim; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix.length);
        }
        int multiplier = 1;
        for (int i = 0; i < dim - 1; i++) {
            int j = i;
            while (newMatrix[j][i] == 0 && j < dim - 1) {
                j++;
            }
            if (j == dim) {
                return 0;
            } else if (j != i) {
                for (int k = i; k < dim; k++) {
                    double temp = newMatrix[i][k];
                    newMatrix[i][k] = newMatrix[j][k];
                    newMatrix[j][k] = temp;
                    multiplier = multiplier * (-1);
                }
            }
            for (j = i + 1; j < dim; j++) {
                if (newMatrix[j][i] != 0) {
                    double index = newMatrix[j][i] / newMatrix[i][i];
                    for (int k = i; k < dim; k++) {
                        newMatrix[j][k] -= newMatrix[i][k] * index;
                    }
                }
            }
        }
        double result = 1;
        for (int i = 0; i < dim; i++) {
            result *= newMatrix[i][i];
        }
        if (result == 0) {
            return 0;
        }
        return multiplier * result;
    }
}

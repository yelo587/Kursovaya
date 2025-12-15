import java.util.Arrays;

/**
 * Класс для работы с матрицами.
 */
public class Matrix {
    private int[][] data;

    /**
     * Конструктор для создания матрицы из двумерного массива.
     *
     * @param data двумерный массив элементов матрицы
     */
    public Matrix(int[][] data) {
        this.data = data;
    }

    /**
     * Сложение двух матриц.
     *
     * @param other вторая матрица
     * @return результат сложения
     * @throws MatrixException если размеры матриц не совпадают
     */
    public Matrix add(Matrix other) throws MatrixException {
        if (data.length != other.data.length || data[0].length != other.data[0].length) {
            throw new MatrixException("Matrix sizes do not match for addition");
        }
        int[][] result = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                result[i][j] = data[i][j] + other.data[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Вычитание двух матриц.
     *
     * @param other вторая матрица
     * @return результат вычитания
     * @throws MatrixException если размеры матриц не совпадают
     */
    public Matrix subtract(Matrix other) throws MatrixException {
        if (data.length != other.data.length || data[0].length != other.data[0].length) {
            throw new MatrixException("Matrix sizes do not match for subtraction");
        }
        int[][] result = new int[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                result[i][j] = data[i][j] - other.data[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Умножение двух матриц.
     *
     * @param other вторая матрица
     * @return результат умножения
     * @throws MatrixException если количество столбцов первой матрицы не равно количеству строк второй
     */
    public Matrix multiply(Matrix other) throws MatrixException {
        if (data[0].length != other.data.length) {
            throw new MatrixException("Number of columns of the first matrix must equal number of rows of the second matrix");
        }
        int[][] result = new int[data.length][other.data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < other.data[0].length; j++) {
                for (int k = 0; k < data[0].length; k++) {
                    result[i][j] += data[i][k] * other.data[k][j];
                }
            }
        }
        return new Matrix(result);
    }

    /**
     * Расчёт определителя матрицы.
     *
     * @return определитель матрицы
     * @throws MatrixException если матрица не квадратная
     */
    public int determinant() throws MatrixException {
        if (data.length != data[0].length) {
            throw new MatrixException("Matrix must be square to calculate determinant");
        }
        return determinantRecursive(data);
    }

    private int determinantRecursive(int[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        int det = 0;
        for (int i = 0; i < matrix.length; i++) {
            int sign = (i % 2 == 0) ? 1 : -1;
            det += sign * matrix[0][i] * determinantRecursive(minor(matrix, 0, i));
        }
        return det;
    }

    private int[][] minor(int[][] matrix, int row, int col) {
        int size = matrix.length;
        int[][] result = new int[size - 1][size - 1];
        for (int i = 0, r = 0; i < size; i++) {
            if (i != row) {
                for (int j = 0, c = 0; j < size; j++) {
                    if (j != col) {
                        result[r][c++] = matrix[i][j];
                    }
                }
                r++;
            }
        }
        return result;
    }

    /**
     * Вывод матрицы на экран.
     */
    public void print() {
        for (int[] row : data) {
            System.out.println(Arrays.toString(row));
        }
    }
}

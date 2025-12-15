import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для чтения матриц из файлов.
 */
public class MatrixFileReader {
    /**
     * Чтение матрицы из файла.
     *
     * @param filename имя файла
     * @return матрица из файла
     * @throws MatrixException если файл не найден или данные неверны
     */
    public static Matrix readMatrix(String filename) throws MatrixException {
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.trim().split("\\s+");
                if (values.length == 0 || (values.length == 1 && values[0].isEmpty())) {
                    continue;
                }
                int[] row = new int[values.length];
                for (int i = 0; i < values.length; i++) {
                    row[i] = Integer.parseInt(values[i]);
                }
                rows.add(row);
            }
        } catch (IOException e) {
            throw new MatrixException("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new MatrixException("Invalid number format in file: " + e.getMessage());
        }

        if (rows.isEmpty()) {
            throw new MatrixException("File does not contain matrix data");
        }

        int cols = rows.get(0).length;
        for (int[] row : rows) {
            if (row.length != cols) {
                throw new MatrixException("All rows in the matrix must have the same number of columns");
            }
        }

        return new Matrix(rows.toArray(new int[0][]));
    }
}

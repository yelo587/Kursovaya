import java.util.Scanner;

/**
 * Основной класс для запуска программы.
 */
public class MatrixApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter path to the first matrix file:");
            String file1 = scanner.nextLine().trim().replace("\"", "");
            System.out.println("Enter path to the second matrix file:");
            String file2 = scanner.nextLine().trim().replace("\"", "");

            Matrix matrix1 = MatrixFileReader.readMatrix(file1);
            Matrix matrix2 = MatrixFileReader.readMatrix(file2);

            MatrixLogger.info("Matrices have been successfully read from files.");

            Matrix result = null;
            System.out.println("Choose operation: 1 - addition, 2 - subtraction, 3 - multiplication, 4 - determinant");
            int operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    result = matrix1.add(matrix2);
                    MatrixLogger.info("Matrix addition performed.");
                    break;
                case 2:
                    result = matrix1.subtract(matrix2);
                    MatrixLogger.info("Matrix subtraction performed.");
                    break;
                case 3:
                    result = matrix1.multiply(matrix2);
                    MatrixLogger.info("Matrix multiplication performed.");
                    break;
                case 4:
                    int det1 = matrix1.determinant();
                    int det2 = matrix2.determinant();
                    System.out.println("Determinant of the first matrix: " + det1);
                    System.out.println("Determinant of the second matrix: " + det2);
                    MatrixLogger.info("Matrix determinants have been calculated.");
                    return;
                default:
                    MatrixLogger.error("Invalid operation was chosen.");
                    System.out.println("Invalid operation.");
                    return;
            }

            result.print();
            MatrixLogger.info("Result was printed to console.");

        } catch (MatrixException e) {
            MatrixLogger.error(e.getMessage());
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            MatrixLogger.error("Unexpected error: " + e.getMessage());
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}

package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int row = dimensions[0];
        int col = dimensions[1];

        int[][] galaxy = createGalaxy(row, col);

        String command = scanner.nextLine();

        long sum = 0;

        while (!"Let the Force be with you".equals(command)) {
            int[] coordinatesOfPeter = Arrays.stream(command.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] coordinatesOfEvil = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            int evilRow = coordinatesOfEvil[0];
            int evilCol = coordinatesOfEvil[1];

            moveEvil(galaxy, evilRow, evilCol);

            int rowPeter = coordinatesOfPeter[0];
            int colPeter = coordinatesOfPeter[1];

            sum = moveJedi(galaxy, sum, rowPeter, colPeter);

            command = scanner.nextLine();
        }

        System.out.println(sum);
    }

    private static long moveJedi(int[][] galaxy, long sum, int rowPeter, int colPeter) {
        while (rowPeter >= 0 && colPeter < galaxy[1].length) {
            if (rowPeter < galaxy.length && colPeter >= 0 && colPeter < galaxy[0].length) {
                sum += galaxy[rowPeter][colPeter];
            }

            colPeter++;
            rowPeter--;
        }
        return sum;
    }

    private static void moveEvil(int[][] galaxy, int evilRow, int evilCol) {
        while (evilRow >= 0 && evilCol >= 0) {
            if (evilRow < galaxy.length && evilCol < galaxy[0].length) {
                galaxy[evilRow][evilCol] = 0;
            }
            evilRow--;
            evilCol--;
        }
    }

    private static int[][] createGalaxy(int row, int col) {
        int[][] matrix = new int[row][col];

        int valueForCell = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = valueForCell++;
            }
        }
        return matrix;
    }
}

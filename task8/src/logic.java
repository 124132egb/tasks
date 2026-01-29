import java.io.*;
import java.util.*;

public class logic {

    public static int[][] readArrayFromFile(String filename) throws IOException {
        List<int[]> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int expectedLength = -1;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                if (expectedLength == -1) {
                    expectedLength = tokens.length;
                } else if (tokens.length != expectedLength) {
                    throw new IOException("Некорректная прямоугольность массива");
                }
                int[] row = new int[tokens.length];
                for (int i = 0; i < tokens.length; i++) {
                    row[i] = Integer.parseInt(tokens[i]);
                }
                rows.add(row);
            }
        }
        int[][] array = new int[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            array[i] = rows.get(i);
        }
        return array;
    }

    public static void writeArrayToFile(int[][] array, String filename) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int[] row : array) {
                for (int i = 0; i < row.length; i++) {
                    bw.write(Integer.toString(row[i]));
                    if (i < row.length - 1) bw.write(" ");
                }
                bw.newLine();
            }
        }
    }

    public static boolean isRectangular(int[][] array) {
        if (array.length == 0) return true;
        int length = array[0].length;
        for (int[] row : array) {
            if (row.length != length) return false;
        }
        return true;
    }

    public static int countElementsSumOfTwoOther(int[][] array) {
        int n = array.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int val = array[i][0];
            boolean canBeFormed = false;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                for (int k = 0; k < n; k++) {
                    if (k == j || i == k) continue;
                    if (array[j][0] + array[k][0] == val) {
                        canBeFormed = true;
                        break;
                    }
                }
                if (canBeFormed) break;
            }
            if (canBeFormed) count++;
        }
        return count;
    }

    public static void sortColumn(int[][] array, int columnIndex) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j][columnIndex] < array[minIdx][columnIndex]) {
                    minIdx = j;
                }
            }
            int[] temp = array[i];
            array[i] = array[minIdx];
            array[minIdx] = temp;
        }
    }
}
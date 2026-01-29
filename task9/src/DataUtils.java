import java.io.*;
import java.util.*;

public class DataUtils {

    public static List<List<Integer>> readDataFromFile(String filename) throws IOException {
        List<List<Integer>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Integer> row = parseLineToIntList(line);
                data.add(row);
            }
        }
        return data;
    }

    public static void writeDataToFile(String filename, List<List<Integer>> data) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (List<Integer> row : data) {
                bw.write(joinIntList(row));
                bw.newLine();
            }
        }
    }

    private static List<Integer> parseLineToIntList(String line) {
        List<Integer> list = new ArrayList<>();
        String[] parts = line.trim().split("\\s+");
        for (String part : parts) {
            try {
                list.add(Integer.parseInt(part));
            } catch (NumberFormatException e) {
            }
        }
        return list;
    }

    private static String joinIntList(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static void sort(List<Integer> list, int index1, int index2) {
        int size = list.size();
        int start = Math.max(0, Math.min(index1, index2));
        int end = Math.min(size - 1, Math.max(index1, index2));

        boolean ascending = index1 <= index2;

        boolean swapped;
        do {
            swapped = false;
            for (int i = start; i < end; i++) {
                boolean condition = ascending ? list.get(i) > list.get(i + 1) : list.get(i) < list.get(i + 1);
                if (condition) {
                    int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    public static Object[][] convertToArray(List<List<Integer>> data) {
        int rows = data.size();
        int cols = data.stream().mapToInt(List::size).max().orElse(0);
        Object[][] array = new Object[rows][cols];
        for (int i = 0; i < rows; i++) {
            List<Integer> row = data.get(i);
            for (int j = 0; j < row.size(); j++) {
                array[i][j] = row.get(j);
            }
            for (int j = row.size(); j < cols; j++) {
                array[i][j] = null; // или 0
            }
        }
        return array;
    }

    public static List<List<Integer>> convertToList(Object[][] data) {
        List<List<Integer>> list = new ArrayList<>();
        for (Object[] row : data) {
            List<Integer> rowList = new ArrayList<>();
            for (Object val : row) {
                if (val != null) {
                    rowList.add((Integer) val);
                }
            }
            list.add(rowList);
        }
        return list;
    }
}
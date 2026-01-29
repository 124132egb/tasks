import javax.swing.*;
import javax.swing.table.*;
import java.io.*;

public class utils {

    public static void displayArrayInTable(int[][] array, JTable table) {
        String[] columns = new String[array[0].length];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = "Col " + i;
        }
        Object[][] data = new Object[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                data[i][j] = array[i][j];
            }
        }
        table.setModel(new DefaultTableModel(data, columns));
    }

    public static int[][] readArrayFromTable(JTable table) throws NumberFormatException {
        TableModel model = table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        int[][] array = new int[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Object value = model.getValueAt(i, j);
                array[i][j] = Integer.parseInt(value.toString());
            }
        }
        return array;
    }

    public static void saveTableToFile(JTable table, String filename) throws IOException {
        int[][] array = readArrayFromTable(table);
        logic.writeArrayToFile(array, filename);
    }
}
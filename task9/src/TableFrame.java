import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class TableFrame extends JFrame {
    private JTable table;

    public TableFrame() {
        setTitle("Двумерный массив");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        JButton loadButton = new JButton("Загрузить из файла");
        JButton saveButton = new JButton("Сохранить в файл");
        panel.add(loadButton);
        panel.add(saveButton);
        add(panel, BorderLayout.SOUTH);

        loadButton.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int ret = fc.showOpenDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                String filename = fc.getSelectedFile().getAbsolutePath();
                try {
                    List<List<Integer>> data = DataUtils.readDataFromFile(filename);
                    Object[][] array = DataUtils.convertToArray(data);
                    table.setModel(new DefaultTableModel(array, generateColumnNames(array[0].length)));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка чтения файла");
                }
            }
        });

        saveButton.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int ret = fc.showSaveDialog(this);
            if (ret == JFileChooser.APPROVE_OPTION) {
                String filename = fc.getSelectedFile().getAbsolutePath();
                Object[][] data = getTableData();
                List<List<Integer>> listData = DataUtils.convertToList(data);
                try {
                    DataUtils.writeDataToFile(filename, listData);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Ошибка записи файла");
                }
            }
        });
    }

    private Object[][] getTableData() {
        TableModel model = table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();
        Object[][] data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = model.getValueAt(i, j);
            }
        }
        return data;
    }

    private String[] generateColumnNames(int count) {
        String[] names = new String[count];
        for (int i = 0; i < count; i++) {
            names[i] = "Col " + (i + 1);
        }
        return names;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TableFrame().setVisible(true);
        });
    }
}
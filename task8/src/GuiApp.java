import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class GuiApp extends JFrame {
    private JTable table;
    private JButton btnLoad, btnSave, btnCount, btnSortColumn;
    private JTextField txtColumnIndex;

    public GuiApp() {
        setTitle("Задача: Анализ массива");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        table = new JTable();

        btnLoad = new JButton("Загрузить из файла");
        btnSave = new JButton("Сохранить в файл");
        btnCount = new JButton("Подсчитать");
        txtColumnIndex = new JTextField("0", 3);
        JButton btnSortColumn = new JButton("Отсортировать столбец");

        JPanel panelTop = new JPanel();
        panelTop.add(btnLoad);
        panelTop.add(btnSave);
        panelTop.add(new JLabel("Столбец для сортировки:"));
        panelTop.add(txtColumnIndex);
        panelTop.add(btnSortColumn);
        panelTop.add(btnCount);

        add(panelTop, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnLoad.addActionListener(e -> loadFromFile());
        btnSave.addActionListener(e -> saveToFile());
        btnCount.addActionListener(e -> countElements());
        btnSortColumn.addActionListener(e -> sortColumn());
    }

    private void loadFromFile() {
        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getAbsolutePath();
            try {
                int[][] array = logic.readArrayFromFile(filename);
                if (!logic.isRectangular(array)) {
                    JOptionPane.showMessageDialog(this, "Массив не является прямоугольным");
                    return;
                }
                utils.displayArrayInTable(array, table);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
            }
        }
    }

    private void saveToFile() {
        JFileChooser fc = new JFileChooser();
        int res = fc.showSaveDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getAbsolutePath();
            try {
                utils.saveTableToFile(table, filename);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ошибка: " + ex.getMessage());
            }
        }
    }

    private void countElements() {
        try {
            int[][] array = utils.readArrayFromTable(table);
            if (!logic.isRectangular(array)) {
                JOptionPane.showMessageDialog(this, "Массив не является прямоугольным");
                return;
            }
            int result = logic.countElementsSumOfTwoOther(array);
            JOptionPane.showMessageDialog(this, "Результат: " + result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage());
        }
    }

    private void sortColumn() {
        try {
            int colIndex = Integer.parseInt(txtColumnIndex.getText());
            int[][] array = utils.readArrayFromTable(table);
            if (colIndex < 0 || colIndex >= array[0].length) {
                JOptionPane.showMessageDialog(this, "Недопустимый индекс столбца");
                return;
            }
            logic.sortColumn(array, colIndex);
            utils.displayArrayInTable(array, table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ошибка: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GuiApp().setVisible(true);
        });
    }
}
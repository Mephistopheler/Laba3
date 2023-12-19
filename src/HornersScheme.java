//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class HornersScheme extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private Double[] coefficients;
    private JFileChooser fileChooser = null;
    private JMenuItem aboutMenuItem;
    private JMenuItem saveToTextMenuItem;
    private JMenuItem saveToGraphicsMenuItem;
    private JMenuItem searchValueMenuItem;
    private JLabel aboutPhotoLabel;
    private JLabel aboutNameTF;
    private JButton githubLinkButton;
    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTextField textFieldStep;
    private Box hBoxResult;
    private GornerTableCellRenderer renderer = new GornerTableCellRenderer();
    private GornerTableModel data;

    public HornersScheme(Double[] coefficients) {
        super("Табулирование многочлена на отрезке по схеме Горнера");
        this.coefficients = coefficients;
        this.setSize(700, 500);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocation((kit.getScreenSize().width - 700) / 2, (kit.getScreenSize().height - 500) / 2);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu aboutMenu = new JMenu("Справка");
        menuBar.add(aboutMenu);
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        JMenu tableMenu = new JMenu("Таблица");
        menuBar.add(tableMenu);
        Action aboutAction = new AbstractAction("О программе") {
            public void actionPerformed(ActionEvent event) {
                JDialog dialog = new JDialog(HornersScheme.this, "Автор", true);
                dialog.setDefaultCloseOperation(2);
                dialog.setSize(360, 360);
                HornersScheme.this.aboutNameTF = new JLabel("Островский Иван");
                Box box = Box.createVerticalBox();
                box.add(HornersScheme.this.aboutNameTF);
                Box hbox = Box.createHorizontalBox();
                hbox.add(Box.createHorizontalStrut(20));
                hbox.add(box);
                dialog.getContentPane().add(hbox);
                dialog.setVisible(true);
            }
        };
        this.aboutMenuItem = aboutMenu.add(aboutAction);
        Action saveToTextAction = new AbstractAction("Сохранить в текстовый файл") {
            public void actionPerformed(ActionEvent event) {
                if (HornersScheme.this.fileChooser == null) {
                    HornersScheme.this.fileChooser = new JFileChooser();
                    HornersScheme.this.fileChooser.setCurrentDirectory(new File("."));
                }

                if (HornersScheme.this.fileChooser.showSaveDialog(HornersScheme.this) == 0) {
                    HornersScheme.this.saveToTextFile(HornersScheme.this.fileChooser.getSelectedFile());
                }

            }
        };
        this.saveToTextMenuItem = fileMenu.add(saveToTextAction);
        this.saveToTextMenuItem.setEnabled(false);
        Action saveToGraphicsAction = new AbstractAction("Сохранить данные для построения графика") {
            public void actionPerformed(ActionEvent event) {
                if (HornersScheme.this.fileChooser == null) {
                    HornersScheme.this.fileChooser = new JFileChooser();
                    HornersScheme.this.fileChooser.setCurrentDirectory(new File("."));
                }

                if (HornersScheme.this.fileChooser.showSaveDialog(HornersScheme.this) == 0) {
                    HornersScheme.this.saveToGraphicsFile(HornersScheme.this.fileChooser.getSelectedFile());
                }

            }
        };

        this.saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
        this.saveToGraphicsMenuItem.setEnabled(false);
        Action searchValueAction = new AbstractAction("Найти значение многочлена") {
            public void actionPerformed(ActionEvent event) {
                String value = JOptionPane.showInputDialog(HornersScheme.this, "Введите значение для поиска", "Поиск значения", 3);
                HornersScheme.this.renderer.setNeedle(value);
                HornersScheme.this.getContentPane().repaint();
            }
        };
        this.searchValueMenuItem = tableMenu.add(searchValueAction);
        this.searchValueMenuItem.setEnabled(false);
        JLabel labelForFrom = new JLabel("X изменяется на интервале от:");
        this.textFieldFrom = new JTextField("0.0", 10);
        this.textFieldFrom.setMaximumSize(this.textFieldFrom.getPreferredSize());
        JLabel labelForTo = new JLabel("до:");
        this.textFieldTo = new JTextField("1.0", 10);
        this.textFieldTo.setMaximumSize(this.textFieldTo.getPreferredSize());
        JLabel labelForStep = new JLabel("с шагом:");
        this.textFieldStep = new JTextField("0.1", 10);
        this.textFieldStep.setMaximumSize(this.textFieldStep.getPreferredSize());
        Box hboxRange = Box.createHorizontalBox();
        hboxRange.setBorder(BorderFactory.createBevelBorder(1));
        hboxRange.add(Box.createHorizontalGlue());
        hboxRange.add(labelForFrom);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(this.textFieldFrom);
        hboxRange.add(Box.createHorizontalStrut(20));
        hboxRange.add(labelForTo);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(this.textFieldTo);
        hboxRange.add(Box.createHorizontalStrut(20));
        hboxRange.add(labelForStep);
        hboxRange.add(Box.createHorizontalStrut(10));
        hboxRange.add(this.textFieldStep);
        hboxRange.add(Box.createHorizontalGlue());
        hboxRange.setPreferredSize(new Dimension((int)hboxRange.getMaximumSize().getWidth(), (int)hboxRange.getMinimumSize().getHeight() * 2));
        hboxRange.setPreferredSize(new Dimension((int)hboxRange.getMaximumSize().getWidth(), (int)hboxRange.getMinimumSize().getHeight() * 2));
        this.getContentPane().add(hboxRange, "North");
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double from = Double.parseDouble(HornersScheme.this.textFieldFrom.getText());
                    Double to = Double.parseDouble(HornersScheme.this.textFieldTo.getText());
                    Double step = Double.parseDouble(HornersScheme.this.textFieldStep.getText());
                    HornersScheme.this.data = new GornerTableModel(from, to, step, HornersScheme.this.coefficients);
                    JTable table = new JTable(HornersScheme.this.data);
                    table.setDefaultRenderer(Double.class, HornersScheme.this.renderer);
                    table.setRowHeight(30);
                    HornersScheme.this.hBoxResult.removeAll();
                    HornersScheme.this.hBoxResult.add(new JScrollPane(table));
                    HornersScheme.this.getContentPane().validate();
                    HornersScheme.this.saveToTextMenuItem.setEnabled(true);
                    HornersScheme.this.saveToGraphicsMenuItem.setEnabled(true);
                    HornersScheme.this.searchValueMenuItem.setEnabled(true);
                } catch (NumberFormatException var6) {
                    JOptionPane.showMessageDialog(HornersScheme.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", 2);
                }

            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                HornersScheme.this.textFieldFrom.setText("0.0");
                HornersScheme.this.textFieldTo.setText("1.0");
                HornersScheme.this.textFieldStep.setText("0.1");
                HornersScheme.this.hBoxResult.removeAll();
                HornersScheme.this.hBoxResult.add(new JPanel());
                HornersScheme.this.saveToTextMenuItem.setEnabled(false);
                HornersScheme.this.saveToGraphicsMenuItem.setEnabled(false);
                HornersScheme.this.getContentPane().validate();
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setPreferredSize(new Dimension((int)hboxButtons.getMaximumSize().getWidth(), (int)hboxButtons.getMinimumSize().getHeight() * 2));
        this.getContentPane().add(hboxButtons, "South");
        this.hBoxResult = Box.createHorizontalBox();
        this.hBoxResult.add(new JPanel());
        this.getContentPane().add(this.hBoxResult, "Center");
    }

    protected void saveToGraphicsFile(File selectedFile) {
        try {
// Создать байтовый поток вывода, направленный в указанный файл
            DataOutputStream out = new DataOutputStream(new
                    FileOutputStream(selectedFile));
            for (int i = 0; i<data.getRowCount(); i++) {
// Записать в поток вывода значение X в точке
                out.writeDouble((Double)data.getValueAt(i,0));
// значение многочлена в точке
                out.writeDouble((Double)data.getValueAt(i,0));
            }
// Закрыть поток вывода
            out.close();
        } catch (Exception e) {
// Исключительную ситуацию "ФайлНеНайден" в данном случае можно
// не обрабатывать, так как мы файл создаѐм, а не открываем
        }
    }


    protected void saveToTextFile(File selectedFile) {
        try {
// Создать новый символьный поток вывода, направленный в указанный файл
            PrintStream out = new PrintStream(selectedFile);
// Записать в поток вывода заголовочные сведения
            out.println("Результаты табулирования многочлена по схеме Горнера");
            out.print("Многочлен: ");
            for (int i=0; i<coefficients.length; i++) {
                out.print(coefficients[i] + "*X^" + (coefficients.length-i-1));
                if (i!=coefficients.length-1)
                    out.print(" + ");
            }
            out.println("");
            out.println("Интервал от " + data.getFrom() + " до " +
                    data.getTo() + " с шагом " + data.getStep());
            out.println("================================================");
// Записать в поток вывода значения в точках
            for (int i = 0; i<data.getRowCount(); i++) {
                out.println("Значение в точке " + data.getValueAt(i,0) +
                        " равно " + data.getValueAt(i,1));
            }
// Закрыть поток
            out.close();
        } catch (FileNotFoundException e) {
        }



    }
}

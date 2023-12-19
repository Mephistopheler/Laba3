//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return this.from;
    }

    public Double getTo() {
        return this.to;
    }

    public Double getStep() {
        return this.step;
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return (int)Math.ceil((this.to - this.from) / this.step) + 1;
    }

    public Object getValueAt(int row, int col) {
        double x = this.from + this.step * (double)row;
        if (col == 0) {
            return x;
        } else if (col == 2) {
            double fractionalPart = this.calculateHorner(x) % 1.0;
            System.out.println(fractionalPart);
           // double sqr=fractionalPart*1000;
            return this.isSquare(fractionalPart) ? true : false;
        } else {
            Double result = 0.0;
            result = this.coefficients[0];

            for(int i = 0; i < this.coefficients.length; ++i) {
                result = this.calculateHorner(x);
            }

            return result;
        }
    }

    private boolean isSquare(double number) {
        // int sqrt = (int)Math.sqrt(number);
       // System.out.println(sqrt);
        String str = Double.toString(number);
        System.out.println(str);
        char[] charArray = str.toCharArray();
        int counter = 0;
        int counterEnd = 0;
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            counter++;

            if(c == '0'){
                counterEnd++;
            }
                if(counterEnd==2){
                    counter--;
                    break;
                }

        }
        //System.out.println(str.substring(2, counter));

            Integer i2 = Integer.valueOf(str.substring(2, counter));
            System.out.println(i2);
            int sqrt = (int) Math.sqrt(i2);
            return sqrt * sqrt == i2;



    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "Дробная часть является квадратом?";
        }
    }

    private double calculateHorner(double x) {
        Double b = this.coefficients[this.coefficients.length - 1];

        for(int i = this.coefficients.length - 2; i >= 0; --i) {
            b = b * x + this.coefficients[i];
        }

        return b;
    }

    public Class<?> getColumnClass(int col) {
        return col != 0 && col != 1 ? Boolean.class : Double.class;
    }
}

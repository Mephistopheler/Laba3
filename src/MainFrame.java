

public class MainFrame {
    public MainFrame() {
    }

    public static void main(String[] args) {
        Double[] a = new Double[]{1.625, 0., 1.};
        HornersScheme frame = new HornersScheme(a);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}

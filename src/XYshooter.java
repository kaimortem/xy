import java.awt.EventQueue;
import javax.swing.JFrame;

public class XYshooter extends JFrame {

    public XYshooter() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("XY Shooter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            XYshooter ex = new XYshooter();
            ex.setVisible(true);
        });
    }
}
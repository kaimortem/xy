import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverBoard extends JPanel {
    JButton restart;
    Constants constants = new Constants();

    public GameOverBoard() {
        defineBoard();

        setVisible(true);
    }

    private void defineBoard() {
        //setFocusable(true);
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(constants.B_WIDTH, constants.B_HEIGHT));
    }
}

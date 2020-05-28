import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuBoard extends JPanel{
    Constants constants;

    public StartMenuBoard() {
        defineBoard();
    }

    private void defineBoard() {
        //setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(constants.B_WIDTH, constants.B_HEIGHT));
        setVisible(true);
    }


}

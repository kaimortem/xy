import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShooterMouseListener implements MouseListener {
    Board board;
    public ShooterMouseListener(Board b) {
        board = b;
        board.addMouseListener(this);

    }
    public void mouseClicked(MouseEvent e) {
        if(GameStatus.inGame == false) {
            board.initBoard();
        }
    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
}

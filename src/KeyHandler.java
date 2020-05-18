import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyHandler {

    public Point difference = new Point();
    private int dx = 0;
    private int dy = 0;
    private KeyEvent e;
    public KeyHandler(KeyEvent e) {
        this.e = e;
    }
    public void keyPressed() {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 2;
        }
        setDifference(dx, dy);
    }

    public void keyReleased() {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        setDifference(dx, dy);
    }
    private void setDifference(int dx, int dy) {
        difference.x = dx;
        difference.y = dy;
    }
}

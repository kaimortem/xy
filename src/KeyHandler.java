import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyHandler {

    public Point difference = new Point();
    public boolean fireMissile = false;
    private KeyEvent e;
    public KeyHandler(KeyEvent e) {
        this.e = e;
    }
    public void keyPressed() {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fireMissile = true;
        }

        if (key == KeyEvent.VK_LEFT) {
            difference.x = -2;
        }

        if (key == KeyEvent.VK_RIGHT) {
            difference.x = 2;
        }

        if (key == KeyEvent.VK_UP) {
            difference.y = -2;
        }

        if (key == KeyEvent.VK_DOWN) {
            difference.y = 2;
        }
    }

    public void keyReleased() {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            difference.x = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            difference.x = 0;
        }

        if (key == KeyEvent.VK_UP) {
            difference.y = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            difference.y = 0;
        }
    }
}

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class KeyHandler {

    public Point difference = new Point();
    public boolean fireMissile = false;
    private KeyEvent e;
    private final Set<Integer> pressedKeys = new HashSet<>();


    public KeyHandler(KeyEvent e) {
        this.e = e;
    }
    public void keyPressed() {
        int keydown = e.getKeyCode();

        pressedKeys.add(keydown);
        if(!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                int key = it.next();
                if (key == KeyEvent.VK_ESCAPE) {
                    GameStatus.exit = true;
                }

                if (key == KeyEvent.VK_SPACE) {
                    fireMissile = true;
                }

                if (key == KeyEvent.VK_LEFT) {
                    difference.x = -1;
                }

                if (key == KeyEvent.VK_RIGHT) {
                    difference.x = 1;
                }

                if (key == KeyEvent.VK_UP) {
                    difference.y = -1;
                }

                if (key == KeyEvent.VK_DOWN) {
                    difference.y = 1;
                }
            }
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

        pressedKeys.remove(key);
    }
}

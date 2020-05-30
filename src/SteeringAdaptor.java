import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SteeringAdaptor {
    private final SpaceShip spaceship;
    KeyHandler keyHandler;
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String MOVE_UP = "move up";
    private static final String MOVE_DOWN = "move down";
    private static final String FIRE = "move fire";

    public SteeringAdaptor(SpaceShip spaceship) {
        this.spaceship = spaceship;

    }




    //

    public void keyReleased(KeyEvent e) {
        keyHandler = new KeyHandler(e);
        keyHandler.keyReleased();
        spaceship.difference.x = keyHandler.difference.x;
        spaceship.difference.y = keyHandler.difference.y;
    }

    private void fireMissile(KeyHandler keyHandler) {
        if(keyHandler.fireMissile){
            spaceship.fire();
            keyHandler.fireMissile = false;
        }
    }


    public void keyPressed(KeyEvent e) {
        keyHandler = new KeyHandler(e);
        keyHandler.keyPressed();
        spaceship.difference.x = keyHandler.difference.x;
        spaceship.difference.y = keyHandler.difference.y;
        fireMissile(keyHandler);
    }
}
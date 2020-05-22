import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SteeringAdaptor extends KeyAdapter {
    private final SpaceShip spaceship;
    KeyHandler keyHandler;
        public SteeringAdaptor(SpaceShip spaceship) {
            this.spaceship = spaceship;
        }

        @Override
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

        @Override
        public void keyPressed(KeyEvent e) {
            keyHandler = new KeyHandler(e);
            keyHandler.keyPressed();
            spaceship.difference.x = keyHandler.difference.x;
            spaceship.difference.y = keyHandler.difference.y;
            fireMissile(keyHandler);
        }
    }


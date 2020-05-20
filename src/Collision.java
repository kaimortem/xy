import java.awt.*;
import java.util.List;

public class Collision {
    private SpaceShip spaceship;
    private List<Alien> aliens;

    public Collision(List<Alien> aliens, SpaceShip spaceship) {
        this.aliens = aliens;
        this.spaceship = spaceship;
    }

    public void checkCollisions() {

        Rectangle r3 = spaceship.getBounds();

        for (Alien alien : aliens) {

            Rectangle r2 = alien.getBounds();

            if (r3.intersects(r2)) {

                spaceship.setVisible(false);
                alien.setVisible(false);
                GameStatus.inGame = false;
            }
        }

        List<Missile> ms = spaceship.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Alien alien : aliens) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {

                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }
}

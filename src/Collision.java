import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.util.List;

public class Collision {
    private SpaceShip spaceship;
    private List<Alien> aliens;
    private ExplosionSmall explosion;
    private boolean inGame;
    public boolean gameOver = false;
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
                gameOver = true;
                GameStatus.inGame = false;
            }
        }

        List<Missile> ms = spaceship.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Alien alien : aliens) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {
                    explosion = new ExplosionSmall(m.location);
                    explosion.setVisible(true);

                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }

    public boolean getInGame() {
        return inGame;
    }

    public ExplosionSmall getExplosion() {
        return explosion;
    }
}

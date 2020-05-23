import java.util.ArrayList;
import java.util.List;

public class Update extends Thread{
    private SpaceShip spaceship;
    private List<Alien> aliens;
    private List<Missile> ms;

    public Update(List<Alien> aliens, SpaceShip spaceship) {
        this.aliens = aliens;
        this.spaceship = spaceship;
    }

    public Update(SpaceShip spaceship) {
        this.spaceship = spaceship;
        ship();
    }

    public Update(ArrayList<Missile> ms) {
        this.ms = ms;
        missiles(ms);
    }

    public Update(List<Alien> aliens) {
        this.aliens = aliens;
        aliens();
    }

    public void ship() {

        if (spaceship.isVisible()) {

            spaceship.move();
        }
    }

    public void missiles(List<Missile> ms) {
        ms.removeIf(missile -> !missile.isVisible());
        ms.forEach(missile -> missile.move());
    }
    public void aliens() {

        if (aliens.isEmpty()) {
            GameStatus.success = true;
            GameStatus.inGame = false;
            return;
        }

        aliens.removeIf(alien -> !alien.isVisible());
        aliens.forEach(alien -> alien.move());

    }
}

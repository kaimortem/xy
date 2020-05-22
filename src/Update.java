import java.util.List;

public class Update {
    private SpaceShip spaceship;
    private List<Alien> aliens;


    public Update(List<Alien> aliens, SpaceShip spaceship) {
        this.aliens = aliens;
        this.spaceship = spaceship;
    }

    public void ship() {

        if (spaceship.isVisible()) {

            spaceship.move();
        }
    }

    public void missiles() {

        List<Missile> ms = spaceship.getMissiles();

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

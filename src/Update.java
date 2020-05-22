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

        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    public void aliens() {

        if (aliens.isEmpty()) {

            GameStatus.inGame = false;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);

            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }
    }
}

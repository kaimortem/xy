import javax.swing.*;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;


public class Update extends Thread{
    private SpaceShip spaceship;
    private List<Alien> aliens = new ArrayList<>();;
    private List<Missile> missiles = new ArrayList<>();;
    private List<ExplosionSmall> explosions = new ArrayList<>();;

    public Update(Timer gameTime) {
        if( !GameStatus.inGame ) {
            gameTime.stop();
        }
    }

    public Update(SpaceShip spaceship) {
        this.spaceship = spaceship;
        ship();
    }

    public Update(Salvo salvo) {
        this.missiles = salvo.missiles;
        missiles();
    }

    public Update(Brood brood) {
        this.aliens = brood.aliens;
        aliens();
    }

    public Update(HellFire hellFire) {
        this.explosions = hellFire.explosions;
        endExplosions();
        explosions();
    }

    private void endExplosions() {
        this.explosions.removeIf(explosion -> !explosion.isVisible());
    }

    private void explosions() {
        this.explosions.forEach(explosion -> {
            explosion.progress();
        });
    }

    public void ship() {

        if (spaceship.isVisible()) {

            spaceship.move();
        }
    }

    public void missiles() {
        missiles.removeIf(missile -> !missile.isVisible());
        missiles.forEach(missile -> missile.move());
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

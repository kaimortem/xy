import java.awt.*;
import java.util.List;

public class Missile extends Sprite {

    public Missile(Point location) {
        super(location);

        initMissile();
    }

    private void initMissile() {

        loadImage("images/missile.png");
        getImageDimensions();
    }

    public void move() {

        location.x += constants.MISSILE_SPEED;

        if (location.x > constants.BOARD_WIDTH) {
            visible = false;
        }
    }
}
import java.awt.*;
import java.util.List;

public class Missile extends GamePiece {

    public Missile(Point location) {

        super(location);
        initMissile();
    }

    protected GamePiece makeMissile(Point location) {
        return new Missile(location);
    }

    private void initMissile() {

        loadImage("images/missile.png");
        getImageDimensions();
    }

    private void checkBorderCase(){
        if (location.x > constants.BOARD_WIDTH) { visible = false; }
    }

    public void move() {

        location.x += constants.MISSILE_SPEED;
        checkBorderCase();
    }
}
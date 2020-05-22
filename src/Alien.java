import java.awt.*;

public class Alien extends GamePiece {

    public Alien(Point location) {
        super(location);

        initAlien();
    }

    protected GamePiece makeAlien(Point location) {
        return new Alien(location);
    }

    private void initAlien() {

        loadImage("images/alien.png");
        getImageDimensions();
    }

    public void move() {

        if (location.x < 0) {
            location.x = constants.INITIAL_X;
        }

        location.x -= 1;
    }
}
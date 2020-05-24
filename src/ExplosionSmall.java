import java.awt.*;

public class ExplosionSmall extends GamePiece {
    private int frameIndex = 0;
    public ExplosionSmall(Point explosionLocation) {
        super(explosionLocation);
        initExplosion();
    }

    private void initExplosion() {
        String imageName = "images/explosion_small6.png";
        loadImage(imageName);
        getImageDimensions();
    }

    public void progress(){
        frameIndex += 1;
        if (frameIndex >= 15) {
            setVisible(false);
        }
    }

    public int getX() {

        return location.x;
    }

    public int getY() {

        return location.y;
    }
}

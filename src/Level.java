public class Level {
    public int delay;
    public int missileSpeed;
    public int numberOfAliens;
    public Level(int index) {
        delay = Math.abs(9 - index);
        if (index >=6 ) {
            delay = 3;
        }
        numberOfAliens = index * 1 + 1;
    }

}

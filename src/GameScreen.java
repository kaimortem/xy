import javax.swing.*;
import java.awt.*;

public class GameScreen {
    private Button restart = new Button("Restart");
    private Button start = new Button("START!");

    public static boolean restartDrawn = false;

    private GameOver gameOver = new GameOver();

    public GameScreen () {

    }

    public Button makeRestartButton() {

        restart.setBounds(180, 200, 80, 30);
        restart.setBackground(Color.WHITE);
        restartDrawn = true;

        return restart;
    }

    public void makeEndGameScreen () {
        makeRestartButton();
    }

    public Button makeStartButton() {
        start.setBounds(180, 200, 80, 30);
        start.setBackground(Color.WHITE);
        return start;
    }

    public void makeStartGameScreen() {
        makeStartButton();
    }
}

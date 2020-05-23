import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private SpaceShip spaceship;
    private List<Alien> aliens;

    private Collision collision;

    private Constants constants = new Constants();
    private final Point initial = new Point(Constants.ICRAFT_X, Constants.ICRAFT_Y);
    private GameScreen gameScreen;
    private GameOver gameOver = new GameOver();
    private Button start;
    private Button restart;

    public Board() {
        defineBoard();
        GameStatus.startGame = true;
        gameScreen = new GameScreen();
        start = gameScreen.makeStartButton();
        start.addActionListener(this);
        add(start);
        timer = new Timer(constants.DELAY, this);
    }

    private void defineBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(constants.B_WIDTH, constants.B_HEIGHT));
    }

    private void defineGame() {
        GameStatus.inGame = true;
        spaceship = new SpaceShip(initial);
        addKeyListener(new SteeringAdaptor(spaceship));
        initAliens();
        collision = new Collision(aliens, spaceship);
    }

    public void initBoard() {
        defineBoard();
        defineGame();
    }

    private void addAlienToBoard (int[] point){
        Point alienLocation = new Point(point[0], point[1]);
        aliens.add(new Alien(alienLocation));
    }

    public void initAliens() {
        aliens = new ArrayList<>();
        for (int[] point : constants.alienPositons) { addAlienToBoard(point); }
    }

    private void drawGameObject(Graphics g, GamePiece gamePiece) {
        if (gamePiece.isVisible())
        {
            g.drawImage(
                gamePiece.getImage(),
                gamePiece.getX(),
                gamePiece.getY(),
                this);
        }
    }

    private void drawObjects(Graphics g) {
        drawGameObject(g, spaceship);
        List<Missile> ms = spaceship.getMissiles();
        for (Missile missile : ms) { drawGameObject(g, missile); }
        for (Alien alien : aliens) { drawGameObject(g, alien); }
        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    public void startGame() {
        initBoard();
        timer.start();
        GameStatus.inGame = true;
    }

    private void addRestartButton() {
        restart = gameScreen.makeRestartButton();
        restart.addActionListener(this);
        add(restart);
        GameScreen.restartDrawn = true;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(GameStatus.isGameOver() && !GameStatus.startGame) {
            gameOver.draw(g);
            if(!GameScreen.restartDrawn) {
                addRestartButton();
            }
        }

        if(GameStatus.inGame == true) { drawObjects(g); }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {
            startGame();
            remove(start);
            GameStatus.startGame = false;
        }

        if(e.getSource() == restart) {
            startGame();
            remove(restart);
        }

        inGame();
        Thread shipUpdateThread = new Update(spaceship);
        Thread missileUpdateThread = new Update((ArrayList<Missile>) spaceship.getMissiles());
        Thread aliensUpdateThread = new Update(aliens);

        shipUpdateThread.start();
        missileUpdateThread.start();
        aliensUpdateThread.start();

        collision.checkCollisions();
        repaint();
    }

    private void inGame() {
        if (!GameStatus.inGame) {
            timer.stop();
        }
    }
}
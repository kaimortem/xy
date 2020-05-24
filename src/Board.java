import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private SpaceShip spaceship;
    private Brood brood = new Brood();
    private HellFire hellfire = new HellFire();
    private Salvo salvo = new Salvo();

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
        collision = new Collision(brood.aliens, spaceship);
    }

    public void initBoard() {
        defineBoard();
        defineGame();
    }

    private void addAlienToBoard (int[] point){
        Point alienLocation = new Point(point[0], point[1]);
        brood.aliens.add(new Alien(alienLocation));
    }

    public void initAliens() {
        brood.aliens = new ArrayList<>();
        for (int[] point : constants.alienPositons) { addAlienToBoard(point); }
    }

    private void drawGameObject(Graphics g, GamePiece gamePiece) {
        try {
            if (gamePiece.isVisible()) {
                g.drawImage(
                        gamePiece.getImage(),
                        gamePiece.getX(),
                        gamePiece.getY(),
                        this);
            }
        }catch (NullPointerException e){

        }
    }

    private void drawObjects(Graphics g) {
        drawGameObject(g, spaceship);
        salvo.missiles = spaceship.getMissiles();
        ExplosionSmall exp = collision.getExplosion();
        if(exp != null) { hellfire.add(exp); }
        for (Missile missile : salvo.missiles) { drawGameObject(g, missile); }
        for (Alien alien : brood.aliens) { drawGameObject(g, alien); }
        for (ExplosionSmall explosion : hellfire.explosions) { drawGameObject(g, explosion);};
        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + brood.aliens.size(), 5, 15);
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
            GameScreen.restartDrawn = false;
        }

        inGame();
        Thread shipUpdateThread = new Update(spaceship);
        salvo.missiles = spaceship.getMissiles();
        Thread missileUpdateThread = new Update(salvo);
        Thread aliensUpdateThread = new Update(brood);
        Thread explosionUpdateThread = new Update(hellfire);

        shipUpdateThread.start();
        missileUpdateThread.start();
        aliensUpdateThread.start();
        explosionUpdateThread.start();

        collision.checkCollisions();
        repaint();
    }

    private void inGame() {
        if (!GameStatus.inGame) {
            timer.stop();
        }
    }
}
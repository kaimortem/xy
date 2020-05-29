import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private SpaceShip spaceship;
    private Brood brood = new Brood();
    private HellFire hellfire = new HellFire();
    private Salvo salvo = new Salvo();
    private Collision collision;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private Constants constants = new Constants();
    private final Point initial = new Point(Constants.ICRAFT_X, Constants.ICRAFT_Y);
    private int gameLevel;
    private Level level;
    private XYshooter topFrame;

    public Board(int levelIndex) {
        gameLevel = levelIndex;
        level = new Level(levelIndex);
        defineBoard();
        GameStatus.startGame = true;
        timer = new Timer(constants.DELAY, this);
    }

    private void defineBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(constants.B_WIDTH, constants.B_HEIGHT));
    }

    private void defineGame() {
        GameStatus.inGame = true;
        GameStatus.success = false;
        spaceship = new SpaceShip(initial);
        addKeyListener(new SteeringAdaptor(spaceship));
        initAliens();
        collision = new Collision(brood.aliens, spaceship);
    }

    public void initBoard() {
        defineBoard();
        defineGame();
    }

    private void addAlienToBoard(int[] point) {
        Point alienLocation = new Point(point[0], point[1]);
        brood.aliens.add(new Alien(alienLocation));
    }

    public void initAliens() {
        brood.aliens = new ArrayList<>();
        for (int i = 0; i < level.numberOfAliens; i++ ) {
            int random = new Random().nextInt(constants.alienPositons.length);
            int[] point = constants.alienPositons[random];
            addAlienToBoard(point);
        }
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
        }catch (NullPointerException e) {

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
        g.drawString("LEVEL: " + gameLevel + " Aliens left: " + brood.aliens.size(), 5, 15);
    }

    public void startGame() {
        initBoard();
        timer.start();
        GameStatus.inGame = true;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(GameStatus.inGame) {
            drawObjects(g);
            Toolkit.getDefaultToolkit().sync();
            return;
        }
        levelWin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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

    private void levelWin(){
        if(GameStatus.success){
            System.out.println("Level up");
            GameStatus.success = false;
            try {
                topFrame = (XYshooter) SwingUtilities.getRoot(this);
                topFrame.gameBoard = new Board(gameLevel + 1);
                topFrame.levelUp(gameLevel + 1);
            }
            catch (NullPointerException e) {

            }
        }
    }

    private void inGame() {
        if (!GameStatus.inGame) {
            timer.stop();
            try {
                topFrame = (XYshooter) SwingUtilities.getWindowAncestor(this);
                topFrame.gameOver();
            }catch (NullPointerException e) {

            }
        }
    }


}
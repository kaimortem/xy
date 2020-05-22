import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private Timer timer;
    private SpaceShip spaceship;
    private List<Alien> aliens;
    private Point location;
    private Collision collision;
    private Update update;
    private GameStatus status = new GameStatus();
    private GameOver gameOver = new GameOver();
    private Constants constants = new Constants();
    private Point initial = new Point(Constants.ICRAFT_X, Constants.ICRAFT_Y);
    private Button restart = new Button("Restart");
    private boolean restartDrawn;
    public Board() {
        timer = new Timer(constants.DELAY, this);
        initBoard();
    }

    protected void initBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        GameStatus.inGame = true;
        restartDrawn = false;
        setPreferredSize(new Dimension(constants.B_WIDTH, constants.B_HEIGHT));
        spaceship = new SpaceShip(initial);
        addKeyListener(new SteeringAdaptor(spaceship));
        initAliens();
        collision = new Collision(aliens, spaceship);
        timer.start();
    }

    private void addAlienToBoard (int[] point){
        Point alienLocation = new Point(point[0], point[1]);
        aliens.add(new Alien(alienLocation));
    }

    public void initAliens() {
        aliens = new ArrayList<>();
        for (int[] point : constants.alienPositons) { addAlienToBoard(point); }
    }

    private void endGameScreen (Graphics g) {
        gameOver.draw(g);
        restartButton();
    }

    private void restartButton() {

        if((GameStatus.inGame == false) &&
                restartDrawn == false
        ) {
            restart.setBounds(180, 200, 80, 30);
            restart.setBackground(Color.WHITE);
            add(restart);
            restart.addActionListener(e -> {
                initBoard();
                remove(restart);
            });
            restartDrawn = true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(GameStatus.isGameOver()) { endGameScreen(g);}
        if(GameStatus.inGame) { drawObjects(g); };
        Toolkit.getDefaultToolkit().sync();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();
        update = new Update(aliens, spaceship);
        update.ship();
        update.missiles();
        update.aliens();
        collision.checkCollisions();
        repaint();
    }

    private void inGame() {
        if (!GameStatus.inGame) {
            timer.stop();
        }
    }
}
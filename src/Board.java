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
    private Constants constants = new Constants();

    public Board() {
        initBoard();
    }

    private void initBoard() {

        setFocusable(true);
        setBackground(Color.BLACK);
        GameStatus.inGame = true;

        setPreferredSize(new Dimension(constants.B_WIDTH, constants.B_HEIGHT));

        location = new Point (constants.ICRAFT_X, constants.ICRAFT_Y);
        spaceship = new SpaceShip(location);
        addKeyListener(new SteeringAdaptor(spaceship));

        initAliens();
        collision = new Collision(aliens, spaceship);

        timer = new Timer(constants.DELAY, this);
        timer.start();
    }

    public void initAliens() {
        Point alienLocation;

        aliens = new ArrayList<>();

        for (int[] p : constants.alienPositons) {
            //...
            alienLocation = new Point(p[0], p[1]);
            aliens.add(new Alien(alienLocation));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (GameStatus.inGame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        if (spaceship.isVisible()) {
            g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(),
                    this);
        }

        List<Missile> ms = spaceship.getMissiles();

        for (Missile missile : ms) {
            if (missile.isVisible()) {
                g.drawImage(missile.getImage(), missile.getX(),
                        missile.getY(), this);
            }
        }

        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
            }
        }

        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (constants.B_WIDTH - fm.stringWidth(msg)) / 2,
                constants.B_HEIGHT / 2);
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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static com.sun.java.accessibility.util.SwingEventMonitor.addChangeListener;

public class XYshooter extends JFrame implements ActionListener{
    Board gameBoard;
    StartMenuBoard startMenu;
    JButton start;
    JButton restart;
    GameOverBoard gameOverBoard;
    JPanel levelUpBoard;
    VictoryCondition victoryCondition;
    GameGoal goal;
    JPanel boards;
    CardLayout cardLayout;
    private int level;

    public XYshooter() {
        level = 1;
        addChangeListener(e -> {
            Collision coll = (Collision)e.getSource();
            if(coll.gameOver) {
                gameOver();
            }
        });
        victoryCondition = new VictoryCondition();
        victoryCondition.addListener(goal);
        initUI();
    }

    private void initUI() {

        new BorderLayout();
        cardLayout = new CardLayout();
        boards = new JPanel(cardLayout);
        start = makeStartButton();
        start.addActionListener(this);
        startMenu = new StartMenuBoard();
        gameOverBoard = new GameOverBoard();
        gameBoard = new Board(level);
        levelUpBoard = new JPanel();
        boards.add(gameOverBoard, "gameOver");
        boards.add(startMenu, "menu");
        boards.add(gameBoard, "game");
        boards.add(levelUpBoard, "");
        startMenu.add(start, BorderLayout.CENTER);
        add(boards, BorderLayout.CENTER);

        cardLayout.show(boards, "menu");

        setResizable(false);
        pack();
        setTitle("XY Shooter");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton makeStartButton() {
        JButton start = new JButton("Start game");
        start.setBounds(180, 200, 80, 30);
        return start;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            XYshooter ex = new XYshooter();
            ex.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start) {
            System.out.println("game started");
            cardLayout.show(boards, "game");
            gameBoard.setVisible(true);
            gameBoard.setFocusable(true);
            gameBoard.grabFocus();
            gameBoard.requestFocus();
            gameBoard.initBoard();
            gameBoard.startGame();
            GameStatus.startGame = false;
            remove(start);
            setVisible(true);
        }

        if(e.getSource() == restart) {
            System.out.println("game restarting");
            cardLayout.show(boards, "game");
            gameBoard.grabFocus();
            gameBoard.requestFocus();
            gameBoard.initBoard();
            gameBoard.startGame();
            gameBoard.setVisible(true);
            restart.setVisible(false);
            GameStatus.restart = false;
            pack();
            setVisible(true);
        }
    }

    public void levelUp(int level) {
        cardLayout.removeLayoutComponent(gameBoard);
        gameBoard = new Board(level);
        boards.add(gameBoard, "game");
        cardLayout.show(boards, "game");
        gameBoard.grabFocus();
        gameBoard.requestFocus();
        gameBoard.initBoard();
        gameBoard.startGame();
        gameBoard.setVisible(true);
        pack();
        setVisible(true);
    }

    public void gameOver() {
        System.out.println("finished");
        if(restart != null) gameOverBoard.remove(restart);
        restart = new JButton("RESTART");
        restart.addActionListener(this);
        restart.setBounds(100, 200, 80, 30);
        restart.setVisible(true);
        gameOverBoard.add(restart, BorderLayout.CENTER);
        gameOverBoard.grabFocus();
        gameOverBoard.requestFocus();
        cardLayout.show(boards,"gameOver");
        pack();
        setVisible(true);
    }
}
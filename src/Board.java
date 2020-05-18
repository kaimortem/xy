//package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private SpaceShip spaceShip;
    private final int DELAY = 10;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        spaceShip = new SpaceShip();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(spaceShip.getImage(), spaceShip.getX(),
                spaceShip.getY(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        step();
    }

    private void step() {

        spaceShip.move();

        repaint(spaceShip.getX()-1, spaceShip.getY()-1,
                spaceShip.getWidth()+2, spaceShip.getHeight()+2);
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            KeyHandler keyHandler = new KeyHandler(e);
            keyHandler.keyReleased();
            spaceShip.dx = keyHandler.difference.x;
            spaceShip.dy = keyHandler.difference.y;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            KeyHandler keyHandler = new KeyHandler(e);
            keyHandler.keyPressed();
            spaceShip.dx = keyHandler.difference.x;
            spaceShip.dy = keyHandler.difference.y;
        }
    }
}
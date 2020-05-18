//package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

// Copyright Jan Bodnar from http://zetcode.com
public class SpaceShip {

    public int dx;
    public int dy;
    private int x = 40;
    private int y = 60;
    private int w;
    private int h;
    private Image image;


    public SpaceShip() {

        loadImage();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("images/spaceship.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public void move() {

        x += dx;
        y += dy;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public int getWidth() {

        return w;
    }

    public int getHeight() {

        return h;
    }

    public Image getImage() {

        return image;
    }


}
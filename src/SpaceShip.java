//package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Copyright Jan Bodnar from http://zetcode.com
public class SpaceShip extends Sprite {

    //TODO introduce Point class difference
    public int dx;
    public int dy;
    //TODO introduce Point class ship location

    //TODO introduce Point class remotest

    private List<Missile> missiles;

    public SpaceShip(int x, int y) {
        super(x,y);
        initSpaceShip();
    }

    private void initSpaceShip() {

        missiles = new ArrayList<>();

        loadImage("images/spaceship.png");
        getImageDimensions();
    }

    public void move() {

        x += dx;
        y += dy;
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public Image getImage() {

        return image;
    }


}
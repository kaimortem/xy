//package com.zetcode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Copyright Jan Bodnar from http://zetcode.com
public class SpaceShip extends GamePiece {

    public Point difference = new Point();
    private List<Missile> missiles;

    public SpaceShip(Point location) {
        super(location);
        initSpaceShip();
    }

    protected GamePiece makeSpaceShip (Point location) {
        return new SpaceShip(location);
    }

    private void initSpaceShip() {

        missiles = new ArrayList<>();

        loadImage("images/spaceship.png");
        getImageDimensions();
    }

    public void move() {

        location.x += difference.x;
        location.y += difference.y;
    }

    public void fire() {

        Point missileLocation = new Point(location.x + width, location.y + height / 2);
        missiles.add(new Missile(missileLocation));
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public int getX() {

        return location.x;
    }

    public int getY() {

        return location.y;
    }

    public Image getImage() {

        return image;
    }


}
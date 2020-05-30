//package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// Copyright Jan Bodnar from http://zetcode.com
public class SpaceShip extends GamePiece {

    public Point difference = new Point();
    private List<Missile> missiles;
    private boolean fireOn;
    public SpaceShip(Point location) {
        super(location);
        initSpaceShip();
    }

    protected GamePiece makeSpaceShip (Point location) {
        return new SpaceShip(location);
    }

    private void initSpaceShip() {

        missiles = new ArrayList<>();
        fireOn = false;
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

    public AbstractAction getActionUp() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                location.y += -2;
                if(fireOn) {fire();}
            }
        };
    }

    public AbstractAction getActionDown() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                location.y += 2;
                if(fireOn) {fire();}
            }
        };
    }

    public AbstractAction getFireOff() {

        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireOn = false;
            }
        };
    }

    public AbstractAction getContinuousFire () {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireOn = true;
                fire();
            }
        };

    }

    public AbstractAction getMoveUpAndShoot() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                location.y += -2;
                fire();
            }
        };
    }

    public AbstractAction getMoveDownAndShoot() {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                location.y += 2;
                fire();
            }
        };
    }

}
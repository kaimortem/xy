//package com.zetcode;

import javax.swing.*;
import java.awt.*;

public class Sprite {
    protected Constants constants = new Constants();

    protected Point location = new Point();
    //TODO: introduce Point remotest
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    public Sprite(Point location) {
        this.location.x = location.x;
        this.location.y = location.y;
        visible = true;
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return location.x;
    }

    public int getY() {
        return location.y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(location.x, location.y, width, height);
    }
}

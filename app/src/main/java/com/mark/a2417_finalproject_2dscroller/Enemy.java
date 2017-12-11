package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Class outline for a generic Enemy.
 */

public class Enemy {


    private int maxHealth;
    private int currentHealth;
    private int power;
    private int speedX;
    private int centerX;
    private int centerY;
    private Background bg;

    private Rect rectangle;
    private int width;
    private int height;
    private boolean active;

    private Paint painter;
    private int xPos;
    private int yPos;

    private int direction;
    private int xSpeed;


    public Enemy(int centerX, int centerY) {
        setCenterX(centerX);
        setCenterY(centerY);
    }

// TODO determine sizes in MainActivity
// TODO add values to Constants
    public Enemy(int side) {   // Context context) {
        direction = side;
//        rectangle = rect;
        width = 300;  //rectangle.width();
        height = 400; // rectangle.height();
        painter = new Paint();
        painter.setColor(Color.RED);

        if (side < 2) {
            xPos = (2 * width) + Constants.SCREEN_WIDTH;
            xSpeed = -100; // TODO Constant.
        } else {
            xPos = 2 * -width;
            xSpeed = 100; // TODO Constant.
        }
        yPos = 300; // TODO Constant.

//        xPos = rectangle.left;
//        yPos = rectangle.top;
        rectangle = new Rect(xPos, yPos, xPos + width, yPos + height);
    }




    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
        canvas.drawRect(rectangle, painter);
    }

    public void update() {
        xPos += xSpeed;
// TODO check bounds
        rectangle.set(xPos, yPos, xPos + width, yPos + height);
//        centerX += speedX;
//        speedX = bg.getSpeedX();
    }

    public void die() {

    }

    public void attack() {

    }


// TODO may need to change when adding bitmaps.
    public boolean checkCollision(Player player) {
        return Rect.intersects(rectangle, player.getPlayerRect());
    }




    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Background getBg() {
        return bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

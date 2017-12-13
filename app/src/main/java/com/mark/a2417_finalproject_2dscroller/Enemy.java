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
    private boolean active = true;

    private Paint painter;
    private int xPos;
    private int yPos;

    private int direction;
    private int xSpeed;

    private AnimationManager mAnimationManager;

    private int state = 0;

// TODO determine sizes in MainActivity
// TODO add values to Constants
    public Enemy(int side, Sprite[] sprites) {   // Context context) {
        direction = side;
//        rectangle = rect;
        height = Constants.PLAYER_HEIGHT; // 400; // rectangle.height();
        float scaler = sprites[0].getWholeHeight() / height;
        width = (int)(sprites[0].getWholeWidth() / scaler); // 300;  //rectangle.width();

        painter = new Paint();
        painter.setColor(Color.RED);

        if (side < 2) { // Start on Right and move left.
            xPos = (2 * width) + Constants.SCREEN_WIDTH;
            xSpeed = -100; // TODO Constant.
        } else { // Start on Left and move right.
            xPos = 2 * -width;
            xSpeed = 100; // TODO Constant.
        }
        yPos = Constants.PLAYER_START_Y; // 800; // TODO Constant.

//        xPos = rectangle.left;
//        yPos = rectangle.top;
        rectangle = new Rect(xPos, yPos, xPos + width, yPos + height);

        mAnimationManager = new AnimationManager(sprites);
    }




    public void draw(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        canvas.drawRect(rectangle, painter);
        mAnimationManager.draw(canvas, rectangle);
    }

    public void update() {
        if (state == 0) {
            xPos += xSpeed;
            if (direction < 2 && (xPos + width) < 0) {
                active = false;
            } else if (direction == 2 && xPos > Constants.SCREEN_WIDTH) {
                active = false;
            }
        } else {
// TODO if hit player, set flag variable
// TODO then can check here if flag is true = move on
        }
// TODO check bounds
        rectangle.set(xPos, yPos, xPos + width, yPos + height);
        mAnimationManager.playAnim(state);
        mAnimationManager.update();
        if (mAnimationManager.isDone(state)) {
            state = 0;
        }
//        centerX += speedX;
//        speedX = bg.getSpeedX();
    }

    public void die() {
        state = 2;
        active = false;
    }

    public void attack() {
        state = 1;
    }


// TODO may need to change when adding bitmaps.
    protected boolean checkCollision(Rect player, boolean attacking) {
        if (Rect.intersects(rectangle, player)) {
            if (attacking) {
                die();
            } else {
                attack();
            }
            return true;
        }
        return false;
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


    public Rect getRectangle() { return rectangle; }

    public boolean isDone() { return mAnimationManager.isDone(state); }
}

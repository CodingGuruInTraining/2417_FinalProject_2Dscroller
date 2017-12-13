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

//        mAnimationManager.playAnim(state);
//        mAnimationManager.update();
//        if (mAnimationManager.isDone(state)) {
//            state = 0;
//        }

//        if (state == 0) {
//        if (state != 1) {
            xPos += xSpeed;
//        }
            if (direction < 2 && (xPos + width) < 0) {
                active = false;
            } else if (direction == 2 && xPos > Constants.SCREEN_WIDTH) {
                active = false;
            }

//        } else {
// TODO if hit player, set flag variable
// TODO then can check here if flag is true = move on
//        }
        rectangle.set(xPos, yPos, xPos + width, yPos + height);
            if (!active) {
                die();
                xSpeed = 0;
            }
        mAnimationManager.playAnim(state);
        mAnimationManager.update();
        if (mAnimationManager.isDone(state)) {
            state = 0;
        }
    }

    public void die() {
        state = 2;
        active = false;
    }

    public void attack() {
        state = 1;
    }


    protected boolean checkCollision(Rect player) {
        if (Rect.intersects(rectangle, player)) {
//            if (attacking) {
//                die();
//            } else {
//                attack();
//            }
            attack();
//            mAnimationManager.playAnim(state);
            return true;
        }
        return false;
    }

    protected boolean checkAttackRange(Rect player, boolean attacking) {
        float buffer = (player.right - player.left) / 2;
        if (xPos <= player.right + buffer && xPos > player.left) {
            // Moving left and in range.
            if (attacking) {
                die();
            }
            return true;

        } else if (xPos + width >= player.left + buffer && xPos < player.left) {
            // Moving right and in range.
            if (attacking) {
                die();
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

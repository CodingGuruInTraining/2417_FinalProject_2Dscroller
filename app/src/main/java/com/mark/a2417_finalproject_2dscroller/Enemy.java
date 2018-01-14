package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * This Class outlines the Enemy objects.
 */


public class Enemy {

    // Variables.
    private Rect rectangle;
    private int width;
    private int height;
    private int xPos;
    private int yPos;

    protected boolean active = true;
    private boolean readyForDeath = false;
    private boolean hitPlayer = false;
    private boolean collided = false;

    private int direction;
    private int xSpeed;

    private AnimationManager mAnimationManager;
    private int state = 0;

    private double animStartTime;
    private double animTime;



    // Constructor.
    public Enemy(int side, Sprite[] sprites) {
        // Which side of the screen does this Enemy spawn.
        direction = side;

        // Initializes values.
        height = Constants.PLAYER_HEIGHT;
        float scaler = sprites[0].getWholeHeight() / height;
        width = (int)(sprites[0].getWholeWidth() / scaler);

        // Determines and sets X and Y coordinates.
        if (side < 2) {     // Start on Right and move left.
            xPos = (2 * width) + Constants.SCREEN_WIDTH;
            xSpeed = -(Constants.ENEMY_SPEED);
        } else {            // Start on Left and move right.
            xPos = 2 * -width;
            xSpeed = Constants.ENEMY_SPEED;
        }
        yPos = Constants.PLAYER_START_Y;

        // Creates a rectangle object for collision detection.
        rectangle = new Rect(xPos, yPos, xPos + width, yPos + height);

        // Instantiates an Animation Manager.
        mAnimationManager = new AnimationManager(sprites);

// TODO trying finding an alternative to this strategy.
        // Retrieves the animation time for the dying sprite.
        animTime = (mAnimationManager.getAnimTime(2)) * 1000;
    }



    // Draw method.
    public void draw(Canvas canvas) {
        mAnimationManager.draw(canvas, rectangle);
    }



    // Update method.
    public void update(Rect player) {
        if (active) {
            // Checks whether the enemy is moving.
            if (xSpeed != 0) {
                // Adjusts X position (moving).
                xPos += xSpeed;

                // Adjusts location rectangle.
                rectangle.set(xPos, yPos, xPos + width, yPos + height);

                // Check if out of bounds.
                checkBounds();

                // Check for collision with player.
                checkCollision(player);

//TODO issue might be with the animation's done check.
                // Checks if animation has completed after collision.
                checkFinished();
            }
            // Checks if enemy is active before playing animation.
            if (active) {
                mAnimationManager.playAnim(state);
                mAnimationManager.update();
            }
        }
    }



    // Check if out of bounds.
    private void checkBounds() {
        if ((direction < 2 && (xPos + width) < 0) || (direction == 2 && xPos > Constants.SCREEN_WIDTH)) {
            Log.d("tag", "enemy off screen");
            active = false;
            readyForDeath = true;
        }
    }



    // Check if enemy has collided with player.
    private void checkCollision(Rect player) {
        if (!collided) {
            // Checks for any overlap.
            if (Rect.intersects(rectangle, player)) {
                Log.d("tag", "COLLISION");
                state = 2;
                xSpeed = 0;
                collided = true;
                animStartTime = System.currentTimeMillis();
            }
        }
    }



    // Check if dying animation has finished.
    private void checkFinished() {
        if (collided) {
            if (System.currentTimeMillis() >= (animStartTime + animTime)) {
                Log.d("tag", "animation is done");
                readyForDeath = true;
                active = false;
            }
        }
    }



    // Getters.
    public boolean isActive() { return active; }
    public Rect getRectangle() { return rectangle; }
    public boolean isDone() { return mAnimationManager.isDone(state); }
    public boolean isReadyForDeath() { return readyForDeath; }
    public boolean isHitPlayer() {
        return hitPlayer;
    }
}

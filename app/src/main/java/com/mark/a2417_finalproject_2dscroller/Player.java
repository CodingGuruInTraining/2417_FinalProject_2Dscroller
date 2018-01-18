package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

/**
 * Class for Player objects.
 */

public class Player {
    // Variables.
    private Rect playerRect;
    private boolean isMoving;
    private int movementSpeed;
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    private AnimationManager mAnimationManager;
    private Context mContext;

    private int state = 0;
    private int threshold;
    private boolean attacking = false;

    private int[] spriteWidths;


    // Constructor.
    public Player(Rect rectangle, Context context) {
        // Sets up variables.
        mContext = context;
        playerRect = rectangle;
        width = playerRect.width();
        height = playerRect.height();
        xPos = playerRect.left;
        yPos = playerRect.top;
        movementSpeed = Constants.MOVE_SPEED;
        threshold = (int) (Constants.SCREEN_WIDTH * Constants.THRESHHOLD_RATIO);

        // Creates Sprite objects.
        Sprite idle = makeSprite(R.drawable.idle_sprite, 1f, 4, 3, 10);
        Sprite attack = makeSprite(R.drawable.attack_sprite_fix, 0.5f, 10, 1, 10);
        Sprite walk = makeSprite(R.drawable.run_sprite, 1f, 4, 3, 10);

        // Calculates scaling ratio and changes width based on that.
        float scaler = idle.getWholeHeight() / height;
// TODO this width may be a problem since sprites are slightly different.
// TODO maybe find the widest and use that's width.
        width = (int)(idle.getWholeWidth() / scaler);

        double atkWidth = width * 2;
        double walkWidth = width * 1.5;

        spriteWidths = new int[] {width, (int)atkWidth, (int)walkWidth};

        // Adjusts rectangle object to use newly calculated width.
        playerRect.right = xPos + width;

        // Instantiates animation manager object.
        mAnimationManager = new AnimationManager(new Sprite[] {idle, attack, walk});
    }




    // Draw function.
    protected void draw(Canvas canvas) {
        mAnimationManager.draw(canvas, playerRect);
    }




    // Update function.
    protected void update(int direction, boolean attacking) {
        // Checks whether to move the player or not.
        checkMovement(direction);

        // Uses attacking flag to determine player's state.
        checkAttacking(direction, attacking);

        // Plays sprite animation.
        mAnimationManager.playAnim(state);
        mAnimationManager.update();
    }




    // Method to retrieve sprite image.
    private Sprite makeSprite(int id, float time, int rows, int cols, int count) {
        Bitmap sprite = BitmapFactory.decodeResource(mContext.getResources(), id);
        return new Sprite(sprite, time, rows, cols, count);
    }



    private void checkMovement(int direction) {
// TODO was there a reason not to use a static width???
        // Retrieves the width of current sprite.
//        int currWidth = (int)mAnimationManager.getActiveWidth();

// TODO move out to a function.
        // Checks if player is moving.
        // Value above 0 indicates is moving.
        if (direction > 0) {
            // Checks which direction player is heading and adjusts X position.
            if (direction == 1) {   // Moving left.
                xPos -= movementSpeed;
                // Checks if at left bounds.
                if (xPos < 0) {
                    xPos = 0;
                }
            } else if (direction == 2) {    // Moving right.
                // Checks if at right/threshold bounds.
                if ((xPos + spriteWidths[state]) >= threshold) {
                    xPos -= (spriteWidths[state] / 2);
                }
                xPos += movementSpeed;
            }
// TODO this might be a second (redundant?) check for right bounds.
//            int rightX = xPos + spriteWidths[state];
//            if (rightX >= threshold) {
//                rightX = threshold + (spriteWidths[state] / 2);
//                xPos = rightX - spriteWidths[state];
//            }
            // Updates player's rectangle object.
            playerRect.set(xPos, yPos, xPos + spriteWidths[state], yPos + height);
        }
    }




    // Checks whether the player is attacking and activates sprite.
    private void checkAttacking(int direction, boolean isAttacking) {
        if (attacking) {
            if (mAnimationManager.isDone(1)) {
                attacking = false;
                state = 0;
            }
        } else {
            if (isAttacking) {
                attacking = true;
                state = 1;
            } else {
                if (direction > 0) {
                    state = 2;
                } else {
                    state = 0;
                }
            }
        }
    }




    // Getters and Setters.
    public Rect getPlayerRect() {
        return playerRect;
    }
    public boolean getIsMoving() { return isMoving; }
    public void setIsMoving(boolean moving) { isMoving = moving; }
    public int getMovementSpeed() {
        return movementSpeed;
    }
    public int getState() { return state; }
    public boolean isAttacking() { return attacking; }
}

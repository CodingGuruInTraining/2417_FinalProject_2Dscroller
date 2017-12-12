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

    private Rect playerRect;
    private boolean isMoving;
    private int movementSpeed = 75;     // TODO add to constants.
    private int xPos;
    private int yPos;
    private int width;
    private int height;

// TODO probably need a class later to handle sprites so this is temporary.
    private Bitmap playerImage;

    private AnimationManager mAnimationManager;
    private Sprite idle;
    private Sprite attack;
    private Context mContext;

    // Constructor.
    public Player(Rect rectangle, Context context) {
        mContext = context;
        playerRect = rectangle;
        width = playerRect.width();
        height = playerRect.height();
        xPos = playerRect.left;
        yPos = playerRect.top;
//        playerImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle__000);
//        playerImage = Bitmap.createScaledBitmap(playerImage, width)   <<< start of splitting sprite image page

//        Bitmap sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle_sprite);
//        Bitmap sprite = BitmapFactory.decodeResource(context.getResources(), R.drawable.attack_sprite);

        idle = makeSprite(R.drawable.idle_sprite, 1f, 4, 3, 10);
        attack = makeSprite(R.drawable.attack_sprite, 1.5f, 4, 3, 10);

        float scaler = idle.getWholeHeight() / height;
// TODO this width may be a problem since sprites are all different.
// TODO maybe find the widest and use that's width.
        width = (int)(idle.getWholeWidth() / scaler);

        playerRect.right = xPos + width;
//        Bitmap[] sprites = new Bitmap[10];
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 3; j++) {
//
//            }
//        }
//        idle = new Sprite(sprite, 2);

        mAnimationManager = new AnimationManager(new Sprite[] {idle, attack});
    }


    // Draw function.
    protected void draw(Canvas canvas) {
// TODO replace with sprite once mechanics are worked out.
//        Paint paint = new Paint();
//        paint.setColor(Color.GREEN);
//        canvas.drawRect(playerRect, paint);
//        canvas.drawBitmap(playerImage, null, playerRect, null);
        mAnimationManager.draw(canvas, playerRect);
    }


    // Update function.
    protected void update(int direction, boolean attacking) {
        if (direction > 0) {
            if (direction == 1) {
                xPos -= movementSpeed;
            } else if (direction == 2) {
                xPos += movementSpeed;
            }
            int currWidth = (int)mAnimationManager.getActiveWidth();
            playerRect.set(xPos, yPos, xPos + currWidth, yPos + height);
        }
// TODO will need to change once more sprites are added.
        mAnimationManager.playAnim(0);
        mAnimationManager.update();
    }


    // Function to use attack sprite for engaging an enemy.
    private void attack() {
        Log.d("tag", "attacking!");
    }


    private Sprite makeSprite(int id, float time, int rows, int cols, int count) {

        Bitmap sprite = BitmapFactory.decodeResource(mContext.getResources(), id);
        return new Sprite(sprite, time, rows, cols, count);
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
}

package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
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


    // Constructor.
    public Player(Rect rectangle, Context context) {
        playerRect = rectangle;
        width = playerRect.width();
        height = playerRect.height();
        xPos = playerRect.left;
        yPos = playerRect.top;
        playerImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle__000);
//        playerImage = Bitmap.createScaledBitmap(playerImage, width)   <<< start of splitting sprite image page
    }


    // Draw function.
    protected void draw(Canvas canvas) {
// TODO replace with sprite once mechanics are worked out.
//        Paint paint = new Paint();
//        paint.setColor(Color.GREEN);
//        canvas.drawRect(playerRect, paint);
        canvas.drawBitmap(playerImage, null, playerRect, null);
    }


    // Update function.
    protected void update(int direction, boolean attacking) {
        if (direction > 0) {
            if (direction == 1) {
                xPos -= movementSpeed;
            } else if (direction == 2) {
                xPos += movementSpeed;
            }
            playerRect.set(xPos, yPos, xPos + width, yPos + height);
        }
    }


    // Function to use attack sprite for engaging an enemy.
    private void attack() {
        Log.d("tag", "attacking!");
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

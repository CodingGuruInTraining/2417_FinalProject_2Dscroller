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
//    private int ground;

// TODO probably need a class later to handle sprites so this is temporary.
    private Bitmap playerImage;

//    public Player(int width, int height) {
//
//    }

    public Player(Rect rectangle, Context context) {
        playerRect = rectangle;
        width = playerRect.width();
        height = playerRect.height();
        xPos = playerRect.left;
        yPos = playerRect.top;
        playerImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle__000);
//        playerImage = Bitmap.createScaledBitmap(playerImage, width)   <<< start of splitting sprite image page
    }

    protected void draw(Canvas canvas) {
// TODO replace with sprite once mechanics are worked out.
//        Paint paint = new Paint();
//        paint.setColor(Color.GREEN);
//        canvas.drawRect(playerRect, paint);
        canvas.drawBitmap(playerImage, null, playerRect, null);
    }

//    protected void update(Point point) {
    protected void update(int direction, boolean attacking) {
//        int width = playerRect.width() / 2;
//        int height = playerRect.height() / 2;

//        if ((point.y + height/2) > )

        // Changes the position of the player based on where the Point is.
//        playerRect.set(point.x - width/2,
//                point.y - height/2,
//                point.x + width/2,
//                point.y + height/2);
        if (direction > 0) {
            if (direction == 1) {
                xPos -= movementSpeed;
            } else if (direction == 2) {
                xPos += movementSpeed;
            }
            playerRect.set(xPos, yPos, xPos + width, yPos + height);
        }
    }

    protected void attack() {
        Log.d("tag", "attacking!");
    }

    public Rect getPlayerRect() {
        return playerRect;
    }

    public boolean getIsMoving() { return isMoving; }

    public void setIsMoving(boolean moving) { isMoving = moving; }
//    public void setGround(int ground) { this.ground = ground; }


    public int getMovementSpeed() {
        return movementSpeed;
    }
}

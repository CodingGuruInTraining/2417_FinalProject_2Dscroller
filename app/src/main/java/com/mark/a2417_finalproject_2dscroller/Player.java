package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Class for Player objects.
 */

public class Player {

    private Rect playerRect;
    private boolean isMoving;
    private int movementSpeed = 10;     // TODO add to constants.
    private int xPos;
    private int yPos;
    private int width;
    private int height;
//    private int ground;

//    public Player(int width, int height) {
//
//    }

    public Player(Rect rectangle) {
        playerRect = rectangle;
        width = playerRect.width();
        height = playerRect.height();
    }

    protected void draw(Canvas canvas) {
// TODO replace with sprite once mechanics are worked out.
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect(playerRect, paint);
    }

    protected void update(Point point) {
//        int width = playerRect.width() / 2;
//        int height = playerRect.height() / 2;

//        if ((point.y + height/2) > )

        // Changes the position of the player based on where the Point is.
        playerRect.set(point.x - width/2,
                point.y - height/2,
                point.x + width/2,
                point.y + height/2);
    }

    public Rect getPlayerRect() {
        return playerRect;
    }

    public boolean getIsMoving() { return isMoving; }

    public void setIsMoving(boolean moving) { isMoving = moving; }
//    public void setGround(int ground) { this.ground = ground; }
}

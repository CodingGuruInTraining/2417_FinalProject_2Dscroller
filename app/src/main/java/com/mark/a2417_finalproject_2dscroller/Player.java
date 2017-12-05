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

//    public Player(int width, int height) {
//
//    }

    public Player(Rect rectangle) {
        playerRect = rectangle;
    }

    protected void draw(Canvas canvas) {
// TODO replace with sprite once mechanics are worked out.
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect(playerRect, paint);
    }

    protected void update(Point point) {
        int width = playerRect.width() / 2;
        int height = playerRect.height() / 2;
        // Changes the position of the player based on where the Point is.
        playerRect.set(point.x - width, point.y - height, point.x + width, point.y + height);
    }

    public Rect getPlayerRect() {
        return playerRect;
    }
}

package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Practice Sprite Class from tutorial.
 */

public class Sprite {

    private int x;
    private int y;
    private int xSpd;
    private int ySpd;
    private int width;
    private int height;

    private Bitmap mBitmap;
    private Context mContext;

    private int currentFrame = 0;
    private int direction = 0;      // left or right for me; all 4 directions for tutorial



    public Sprite(Context context, Bitmap bitmap) {
        this.mContext = context;
        this.mBitmap = bitmap;
        // divide by number of rows
        width = mBitmap.getWidth();
        // divide by number of columns
        height = mBitmap.getHeight();
        x = 0;
        y = 0;
        xSpd = 5;
        ySpd = 0;
    }

    public void draw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = direction * height;      // <<< which row of spritesheet.
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);      // <<< what part of spritesheet to cut
        Rect dst = new Rect(x, y, x + width, y + height);   // where moving object to
        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    public void update() {
        // if facing this, change speed

        currentFrame = ++currentFrame % 4;  // number of columns is 4 here
        x += xSpd;
        y += ySpd;
    }
}

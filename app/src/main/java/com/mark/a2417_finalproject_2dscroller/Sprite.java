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


    private Bitmap[] frames;
    private int frameIndex;
    private float frameTime;
    private long lastFrame;
    private boolean isPlaying = false;

    private Bitmap spriteSheet;
    private int rowIndex;
    private int colIndex;
    private float picWidth;
    private float picHeight;


//    public Sprite(Context context, Bitmap bitmap) {
//        this.mContext = context;
//        this.mBitmap = bitmap;
//        // divide by number of rows
//        width = mBitmap.getWidth();
//        // divide by number of columns
//        height = mBitmap.getHeight();
//        x = 0;
//        y = 0;
//        xSpd = 5;
//        ySpd = 0;
//    }


//    public Sprite(Bitmap[] sprites, float animTime) {
//        this.frames = sprites;
//        frameIndex = 0;
//
//        // Evenly space the time between frames.
//        frameTime = animTime / frames.length;
//
//        lastFrame = System.currentTimeMillis();
//    }

    // TODO pass number of rows and columns to constructor
    public Sprite(Bitmap sprite, float animTime) {
        spriteSheet = sprite;
        frameIndex = 0;
        rowIndex = 0;
        colIndex = 0;

        // Height and width of image on screen.
        picHeight = Constants.PLAYER_HEIGHT; // sprite.getHeight() / 4;
        int scaler = sprite.getHeight() / (int)picHeight;
        picWidth = sprite.getWidth() / scaler;

        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, (int)(picWidth * 3), (int)(picHeight * 4), true);

// TODO get height and width of spritesheet broken up

        frameTime = animTime / 10;
        lastFrame = System.currentTimeMillis();
    }

    public void draw(Canvas canvas, Rect destination) {
        if (!isPlaying) {
            return;
        }

//        scaleRect(destination);
        Rect src = new Rect((int)(colIndex * picWidth),
                (int)(rowIndex * picHeight),
                (int)(colIndex * picWidth + picWidth),
                (int)(rowIndex * picHeight + picHeight));

//        canvas.drawBitmap(frames[frameIndex], null, destination, null);
        canvas.drawBitmap(spriteSheet, src, destination, null);

//        update();
//        int srcX = currentFrame * width;
//        int srcY = direction * height;      // <<< which row of spritesheet.
//        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);      // <<< what part of spritesheet to cut
//        Rect dst = new Rect(x, y, x + width, y + height);   // where moving object to
//        canvas.drawBitmap(mBitmap, src, dst, null);
    }

    public void update() {
        if (!isPlaying) {
            return;
        }

        if (System.currentTimeMillis() - lastFrame > frameTime * 1000) {
            frameIndex++;
            frameIndex = frameIndex >= 10 ? 0 : frameIndex;
            lastFrame = System.currentTimeMillis();
            colIndex++;
            if (colIndex > 2) {
                colIndex = 0;
                rowIndex++;
                if (rowIndex > 3) {
                    rowIndex = 0;
                }
            } else if (colIndex == 1 && rowIndex == 3) {
                colIndex = 0;
                rowIndex = 0;
            }
        }


//        // if facing this, change speed
//
//        currentFrame = ++currentFrame % 4;  // number of columns is 4 here
//        x += xSpd;
//        y += ySpd;
    }


    public void play() {
        isPlaying = true;
        frameIndex = 0;
        rowIndex = 0;
        colIndex = 0;
        lastFrame = System.currentTimeMillis();
    }

    public void stop() {
        isPlaying = false;
    }

    private void scaleRect(Rect rect) {
        float widthHeightRatio = (float)(frames[frameIndex].getWidth()/frames[frameIndex].getHeight());
        if (rect.width() > rect.height()) {
            rect.left = rect.right - (int)(rect.height() * widthHeightRatio);
        } else {
            rect.top = rect.bottom - (int)(rect.width() * (1/widthHeightRatio));
        }
    }


    public boolean isPlaying() { return isPlaying; }
}

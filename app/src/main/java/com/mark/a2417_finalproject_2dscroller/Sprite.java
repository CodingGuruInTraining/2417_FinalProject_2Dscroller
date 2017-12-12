package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Practice Sprite Class from tutorial.
 */

public class Sprite {

    private int frameIndex;
    private float frameTime;
    private long lastFrame;
    private boolean isPlaying = false;

    private Bitmap spriteSheet;
    private int rowIndex;
    private int colIndex;
    private float picWidth;
    private float picHeight;

    private int rows;
    private int cols;
    private int count;


    // TODO pass number of rows and columns to constructor
    public Sprite(Bitmap sprite, float animTime, int rows, int cols, int count) {
        spriteSheet = sprite;
        this.rows = rows;
        this.cols = cols;
        this.count = count;
        frameIndex = 0;
        rowIndex = 0;
        colIndex = 0;

        // Height and width of image on screen.
        picHeight = Constants.PLAYER_HEIGHT; // sprite.getHeight() / 4;
        int scaler = sprite.getHeight() / (int)picHeight;
        picWidth = sprite.getWidth() / scaler;

        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, (int)(picWidth * this.cols),
                (int)(picHeight * this.rows), true);

// TODO get height and width of spritesheet broken up

        frameTime = animTime / this.count;
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
            if (frameIndex >= count) {
                frameIndex = 0;
                colIndex = 0;
                rowIndex = 0;
            } else {
                colIndex++;
                if (colIndex >= cols) {
                    colIndex = 0;
                    rowIndex++;
                    if (rowIndex >= rows) {
                        rowIndex = 0;
                    }
                }
            }
//            frameIndex = frameIndex >= count ? 0 : frameIndex;
            lastFrame = System.currentTimeMillis();

//            else if (frameIndex) // (colIndex == 1 && rowIndex == 3) {
//                colIndex = 0;
//                rowIndex = 0;
//            }
        }
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



    public boolean isPlaying() { return isPlaying; }
    public float getWholeWidth() { return picWidth * cols; }
    public float getWholeHeight() { return picHeight * rows; }
    public float getWidth() { return picWidth; }
}

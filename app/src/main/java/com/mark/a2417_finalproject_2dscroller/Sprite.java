package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

/**
 * Class for outlining sprite.
 */

public class Sprite {

    private int frameIndex;     // Current frame.
    private double frameLength;
    private long whenLastFrame;
    private boolean isPlaying = false;

    private Bitmap spriteSheet;
    private int rowIndex;
    private int colIndex;
    private float picWidth;
    private float picHeight;

    private int rows;
    private int cols;
    private int count;      // Number of frames in animation.

    private boolean done;
    private double totalAnimTime;




    // Constructor.
    public Sprite(Bitmap sprite, double animTime, int rows, int cols, int count) {
        // Initializes variables.
        spriteSheet = sprite;
        this.rows = rows;
        this.cols = cols;
        this.count = count;
        frameIndex = 0;
        rowIndex = 0;
        colIndex = 0;
        totalAnimTime = animTime;

        // Height and width of image on screen.
        picHeight = Constants.PLAYER_HEIGHT;
        double scaler = sprite.getHeight() / picHeight;
        picWidth = (int)(sprite.getWidth() / scaler);

        // Scales spritesheet.
        spriteSheet = Bitmap.createScaledBitmap(spriteSheet, (int)(picWidth * this.cols),
                (int)(picHeight * this.rows), true);

        // Calculates how long each frame should be.
        frameLength = 1000 * (animTime / this.count);

        // Captures current time to be used later.
        whenLastFrame = System.currentTimeMillis();
    }



    // Draw method.
    public void draw(Canvas canvas, Rect destination) {
        if (!isPlaying) {
            return;
        }
// TODO maybe try implementing this for flipping image
//        Matrix m = new Matrix();
//        m.setScale(-1, 1);
//        m.postTranslate(Constants.SCREEN_WIDTH, 0);
//        canvas.drawBitmap(spriteSheet, m, null);
//        https://stackoverflow.com/questions/7925278/drawing-mirrored-bitmaps-in-android


        // Creates a source rectangle using the column and row variables
        // to guide along the spritesheet.
        Rect src = new Rect((int)(colIndex * picWidth),
                (int)(rowIndex * picHeight),
                (int)(colIndex * picWidth + picWidth),
                (int)(rowIndex * picHeight + picHeight));

        // Draws sprite.
        canvas.drawBitmap(spriteSheet, src, destination, null);
    }




    // Update method.
    public void update() {
        if (!isPlaying) {
            return;
        }

        // Checks if it is time for the next frame to begin; otherwise,
        // nothing is updated.
        if (System.currentTimeMillis() - whenLastFrame > frameLength) {
            // Increments frame counter.
            frameIndex++;
            // Checks if the last frame has been reached.
            if (frameIndex >= count) {
                // Resets counters.
                frameIndex = 0;
                colIndex = 0;
                rowIndex = 0;
                done = true;
                Log.d("tag", "sprite done");
            } else {
                // Increments column counter.
                colIndex++;
                // Checks if last column has been reached.
                if (colIndex >= cols) {
                    // Resets counter.
                    colIndex = 0;
                    // Increments row counter.
                    rowIndex++;
                    // Checks if last row has been reached.
                    if (rowIndex >= rows) {
                        // Resets counter.
                        rowIndex = 0;
                    }
                }
            }
            // Updates last frame variable.
            whenLastFrame = System.currentTimeMillis();
        }
    }




    // Method used to start updating process.
    public void play() {
        // Sets values to defaults.
        isPlaying = true;
        frameIndex = 0;
        rowIndex = 0;
        colIndex = 0;
        whenLastFrame = System.currentTimeMillis();
        done = false;
    }


    // Method used to stop updating process.
    public void stop() {
        isPlaying = false;
    }


    // Getters.
    public boolean isPlaying() { return isPlaying; }
    public float getWholeWidth() { return picWidth * cols; }
    public float getWholeHeight() { return picHeight * rows; }
    public float getWidth() { return picWidth; }
    public boolean isDone() { return done; }
    public double getTotalAnimTime() {return totalAnimTime; }
}

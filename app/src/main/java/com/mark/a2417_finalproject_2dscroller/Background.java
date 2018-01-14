package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Class for drawing the background scene.
 */

public class Background {

    private Drawable cityBackground;
    private Bitmap cityBackground2;
    private Drawable ground;

    private int x;
    private int y;
    private int width;
    private int height;

    private int groundY;
    private int groundWidth;
    private boolean shortGround = false;
    private int groundX;

    private int dx;
    private int startX = 0;



    // Constructor.
    public Background(Context context) {
        // Retrieves background images.
        cityBackground =  context.getDrawable(R.drawable.city_background_night);
        cityBackground2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.city_background_night);

        // Error handler.
        try {
            Constants.SCREEN_SCALER = (Constants.SCREEN_HEIGHT / cityBackground2.getHeight());
        }
        catch (NullPointerException e) {
            Log.e("tag", "error getting background height");
        }

        // Initializes values.
        this.x = 0;
        this.y = 0;
        this.groundX = 0;
        this.width = Constants.SCREEN_WIDTH;
        this.height = (int)(Constants.SCREEN_HEIGHT * Constants.BACKGROUND_HEIGHT_RATIO);
        this.dx = Constants.MOVE_SPEED;


        // Sets up the ground image of background.
        // Retrieves ground image.
        ground = context.getDrawable(R.drawable.ground);

        // Error handler - Drawable's getter may throw error.
        try {
            // Compares image width to screen to determine whether to replicate image.
            if (ground.getIntrinsicWidth() < Constants.SCREEN_WIDTH) {
                groundWidth = ground.getIntrinsicWidth();
                shortGround = true;
            }
        } catch (NullPointerException e) {
            Log.e("tag", "error getting ground image height");
        }

        // Initializes values.
        int groundHeight = Constants.SCREEN_HEIGHT - height;
        groundY = Constants.SCREEN_HEIGHT - groundHeight;
    }



    // Draw method.
    public void draw(Canvas canvas) {
        // Clears the canvas before redrawing.
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        // Colors the base black.
        canvas.drawColor(Color.BLACK);

        // Draws background image.
        cityBackground.setBounds(x, y, width*2, height);
        cityBackground.draw(canvas);

        // Checks if right edge of background image has reached the screen.
        // Adds a second image to end.
// TODO fix transition while scrolling.
        if (width *2 < Constants.SCREEN_WIDTH) {
            cityBackground.setBounds((width *2) + x, y, (width * 4) + x, height);
            cityBackground.draw(canvas);
        }

        // Draws ground image.
        ground.setBounds(groundX, groundY, groundWidth, Constants.SCREEN_HEIGHT);
        ground.draw(canvas);

        // Continues adding another image to right side till the screen width is covered.
        if (shortGround) {
            int counter = 1;
            int rightX = 0;
            while (rightX < Constants.SCREEN_WIDTH) {
                rightX = groundX + groundWidth + (groundWidth * counter);
                ground.setBounds(groundX + (groundWidth * counter), groundY, rightX, Constants.SCREEN_HEIGHT);
                ground.draw(canvas);
                counter++;
            }
        }
    }



    // Update method.
    public void update(boolean movingTime) {
        if (movingTime) {
            // Moves the background's x coordinate.
            x -= dx;
// TODO test whether this part is issue with background scrolling.
            if (x < -(width * 2)) {
                x = startX;
            }
        }
    }
}

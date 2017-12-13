package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Class for drawing the background scene.
 */

public class Background {

    private Drawable cityBackground;
    private Context mContext;
    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap cityBackground2;
    private Drawable ground;
    private int groundHeight;
    private int groundY;
    private int groundWidth;
    private boolean shortGround = false;
    private int groundX;

    private int dx;
    private int startX = 0;


    public Background(Context context) {
        mContext = context;
        cityBackground =  mContext.getDrawable(R.drawable.city_background_night);

        cityBackground2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.city_background_night);


        try {
            Constants.SCREEN_SCALER = (Constants.SCREEN_HEIGHT / cityBackground2.getHeight());
        }
        catch (NullPointerException e) {
            Log.e("tag", "error getting background height");
        }


        this.x = 0;
        this.y = 0;
        this.groundX = 0;
// TODO make these constants and set in MainActivity
        this.width = Constants.SCREEN_WIDTH; // (int)(cityBackground2.getWidth() * Constants.SCREEN_SCALER); // Constants.SCREEN_WIDTH;
        this.height = 4 * (Constants.SCREEN_HEIGHT / 5);
        this.dx = Constants.MOVE_SPEED;

        ground = mContext.getDrawable(R.drawable.ground);
        if (ground.getIntrinsicWidth() < Constants.SCREEN_WIDTH) {
            groundWidth = ground.getIntrinsicWidth();
            shortGround = true;
        }
        groundHeight = Constants.SCREEN_HEIGHT - height;
        groundY = Constants.SCREEN_HEIGHT - groundHeight;
    }

    public void draw(Canvas canvas) {




        canvas.drawColor(Color.rgb( 53, 43, 140));

        cityBackground.setBounds(x, y, width*2, height);
        cityBackground.draw(canvas);
        if (width *2 < Constants.SCREEN_WIDTH) {
            cityBackground.setBounds((width *2) + x, y, (width * 4) + x, height);
            cityBackground.draw(canvas);
        }
//        canvas.drawBitmap(cityBackground2,new Rect(x, y, width, height), new Rect(x, y, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT), null);

        ground.setBounds(groundX, groundY, groundWidth, Constants.SCREEN_HEIGHT);
        ground.draw(canvas);
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
//        canvas.drawBitmap(cityBackground2, null, new Rect(x, y, width, height), null);
//
//        if (x < 0) {
//            canvas.drawBitmap(cityBackground2, x + width, y, null);
//        }
    }

    public void update(boolean movingTime) {
        if (movingTime) {
            // Moves the background's x coordinate.
            x -= dx;
            if (x < -(width * 2)) {
                x = startX;
            }
        }
    }



//    private Bitmap lessResolution(int width, int height) {
//        int reqHeight = height;
//        int reqWidth = width;
//        BitmapFactory.Options options = new BitmapFactory.Options();
//
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(mContext.getResources(), R.drawable.city_background_night, options);
//
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//
//        options.inJustDecodeBounds = false;
//
//        return BitmapFactory.decodeResource(mContext.getResources(), R.drawable.city_background_night, options);
//    }
//
//    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//
//        if (height > reqHeight || width > reqWidth) {
//            final int heightRatio = Math.round((float) height / (float) reqHeight);
//            final int widthRatio = Math.round((float) width / (float) reqWidth);
//
//            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
//        }
//        return inSampleSize;
//    }



    public void setScrollSpeed(int dx) {
        this.dx = dx;
    }
    public int getGroundY() { return groundY; }
}

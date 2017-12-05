package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
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

    private int dx;
    private int startX = 0;


    public Background(Context context) {
        mContext = context;
        cityBackground =  mContext.getDrawable(R.drawable.city_background_night);

        cityBackground2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.city_background_night);
        dx = -10;

        this.x = 0;
        this.y = 0;
        this.width = Constants.SCREEN_WIDTH;
        this.height = 2 * (Constants.SCREEN_HEIGHT / 3);


    }

    public void draw(Canvas canvas) {



// TODO maybe save color values to constants...
        canvas.drawColor(Color.rgb( 53, 43, 140));
// TODO maybe try scaling width off the screen
        cityBackground.setBounds(x, y, width, height);
        cityBackground.draw(canvas);

//        canvas.drawBitmap(cityBackground2, null, new Rect(x, y, width, height), null);
//
//        if (x < 0) {
//            canvas.drawBitmap(cityBackground2, x + width, y, null);
//        }
    }

    public void update() {

        // Moves the background's x coordinate.
//        x += dx;
//        if (x < -width) {
//            x = startX;
//        }
    }

    public void setScroll(int dx) {
        this.dx = dx;
    }
}

package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

/**
 * Class for drawing the background scene.
 */

public class Background {

    Drawable cityBackground;
    Context mContext;

    public Background(Context context) {
        mContext = context;
        cityBackground =  mContext.getDrawable(R.drawable.city_background_night);
    }

    public void draw(Canvas canvas) {
// TODO maybe save color values to constants...
        canvas.drawColor(Color.rgb( 53, 43, 140));
// TODO maybe try scaling width off the screen
        cityBackground.setBounds(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/2);
        cityBackground.draw(canvas);
    }
}

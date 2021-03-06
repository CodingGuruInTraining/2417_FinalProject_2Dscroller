package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 *  Class for holding information about an Action button and drawing it.
 */

public class ActionButtons {

    private Paint painter;
    private float xPos;
    private float yPos;
    private float widthHeight;

    private float radius;
    private Rect rectangle;

    // Constructor.
    public ActionButtons() {
        // Creates paint object and designates color.
        painter = new Paint();
        painter.setColor(Color.argb(80,0,0,0));

        // Constant values of button's size and position.
        // Values won't change during game.
        widthHeight = Constants.SCREEN_WIDTH / Constants.ACTION_X_RATIO;

        // Calculating X and Y position.
        xPos = Constants.SCREEN_WIDTH - (Constants.SCREEN_WIDTH / Constants.STICK_X_RATIO) - widthHeight;
        yPos = Constants.SCREEN_HEIGHT - (widthHeight * Constants.ACTION_Y_RATIO);

        radius = widthHeight / 2;

        int sqrX = (int)(xPos - radius);
        int sqrY = (int)(yPos - radius);

        // Creates rectangle of button to be used in onTouch event.
        rectangle = new Rect(sqrX, sqrY, (int)(sqrX + widthHeight), (int)(sqrY + widthHeight));
    }

    // Simple draw method placing circle on screen.
    public void draw(Canvas canvas) {
        canvas.drawCircle(xPos, yPos, radius, painter);
    }

    // Getter.
    public Rect getButton() { return rectangle; }
}

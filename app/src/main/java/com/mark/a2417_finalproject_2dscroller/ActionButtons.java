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
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private float radius;
    private Rect rectangle;

    // Constructor.
    public ActionButtons() {
        // Creates paint object and designates color.
        painter = new Paint();
        painter.setColor(Color.GREEN);

        // Constant values of button's size and position.
        // Values won't change during game.
        width = Constants.SCREEN_WIDTH / Constants.ACTION_X_RATIO;
        height = Constants.SCREEN_WIDTH / Constants.ACTION_X_RATIO;
        xPos = Constants.SCREEN_WIDTH - (Constants.SCREEN_WIDTH / Constants.STICK_X_RATIO) - width;
        yPos = (int) (Constants.SCREEN_HEIGHT - height * Constants.ACTION_Y_RATIO);

        radius = width / 2;

        // Creates rectangle of button to be used in onTouch event.
        rectangle = new Rect(xPos, yPos, xPos + width, yPos + height);
    }

    // Simple draw method placing circle on screen.
    public void draw(Canvas canvas) {
        canvas.drawCircle(xPos, yPos, radius, painter);
    }

    // Getter.
    public Rect getButton() { return rectangle; }
}

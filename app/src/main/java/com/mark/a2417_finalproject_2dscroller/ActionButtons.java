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
        xPos = 1300;
        yPos = 800;
        width = 200;
        height = 200;
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

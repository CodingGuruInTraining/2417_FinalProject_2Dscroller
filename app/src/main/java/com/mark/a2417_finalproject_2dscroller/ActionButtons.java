package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 *
 */

public class ActionButtons {

    private Paint painter;
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private float radius;
    private Rect rectangle;


    public ActionButtons() {
        painter = new Paint();
        painter.setColor(Color.GREEN);
        xPos = 1300;
        yPos = 800;
        width = 200;
        height = 200;
        radius = width / 2;
        rectangle = new Rect(xPos, yPos, xPos + width, yPos + height);
    }


    public void draw(Canvas canvas) {
        canvas.drawCircle(xPos, yPos, radius, painter);
    }


    public Rect getButton() { return rectangle; }
}

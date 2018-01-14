package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * This Class outlines a score-keeping object.
 */

public class ScoreKeeper {
    // Variables.
    private int playerPoints = 0;
    private int enemyPoints = 0;
    private int increaseAmount;

    private int playerX;
    private int playerY;
    private int enemyX;
    private int enemyY;

    private Paint painter;



    // Constructor.
    public ScoreKeeper() {
        // Retrieves constant values and determines where to draw on screen.
        increaseAmount = Constants.POINTS;
        playerX = (Constants.SCREEN_WIDTH / Constants.SCORE_WIDTH_RATIO);
        playerY = (Constants.SCREEN_HEIGHT / Constants.SCORE_HEIGHT_RATIO);
        enemyX = playerX;
        enemyY = playerY * 2;

        // Sets up Paint object.
        painter = new Paint();
        painter.setColor(Color.WHITE);
        painter.setTextSize(Constants.SCREEN_HEIGHT / Constants.FONT_RATIO);
    }



    // Draw method.
    public void draw(Canvas canvas) {
        // Draws scores to screen.
        canvas.drawText("Player: " + playerPoints, playerX, playerY, painter);
        canvas.drawText("Enemy: " + enemyPoints, enemyX, enemyY, painter);
    }



    // Method used to increase Enemy score.
    public void addForEnemy() {
        enemyPoints += increaseAmount;
    }

    // Method used to increase Player score.
    public void addForPlayer() {
        playerPoints += increaseAmount;
    }
}

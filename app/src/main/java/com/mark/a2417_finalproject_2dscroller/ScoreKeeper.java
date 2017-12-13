package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *
 */

public class ScoreKeeper {

    private int playerPoints = 0;
    private int enemyPoints = 0;
    private int increaseAmount;

    private int playerX;
    private int playerY;
    private int enemyX;
    private int enemyY;

    private Paint painter;

    public ScoreKeeper() {
        increaseAmount = Constants.POINTS;
        playerX = (Constants.SCREEN_WIDTH / Constants.SCORE_WIDTH_RATIO);
        playerY = (Constants.SCREEN_HEIGHT / Constants.SCORE_HEIGHT_RATIO);

        enemyX = playerX;
        enemyY = playerY * 2;


        painter = new Paint();
        painter.setColor(Color.WHITE);
        painter.setTextSize(Constants.SCREEN_HEIGHT / Constants.FONT_RATIO);
    }

    public void draw(Canvas canvas) {
        canvas.drawText("Player: " + playerPoints, playerX, playerY, painter);
        canvas.drawText("Enemy: " + enemyPoints, enemyX, enemyY, painter);
    }

    public void addForEnemy() {
        enemyPoints += increaseAmount;
    }

    public void addForPlayer() {
        playerPoints += increaseAmount;
    }
}

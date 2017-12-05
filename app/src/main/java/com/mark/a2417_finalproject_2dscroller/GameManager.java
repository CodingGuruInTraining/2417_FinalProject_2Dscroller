package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Class to manage various parts of the game.
 */

public class GameManager extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private Player mPlayer;


    public GameManager(Context context) {
        super(context);

        // Set up interface to interact with SurfaceView.
        getHolder().addCallback(this);

        // Instantiate game thread.
        thread = new GameThread(getHolder(), this);

        // Uses constants to determine where player should start and
        // what its size will be. These are declared in MainActivity.
        mPlayer = new Player(new Rect(
                Constants.PLAYER_START_X,
                Constants.PLAYER_START_Y,
                Constants.PLAYER_START_X + Constants.PLAYER_WIDTH,
                Constants.PLAYER_START_Y + Constants.PLAYER_HEIGHT));

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // Restart game thread.
        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception error) {
                error.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Detect how the screen was touched and acts on it.
        switch (event.getAction()) {
            // Press down.
            case MotionEvent.ACTION_DOWN:
                break;

            // Moving finger across screen.
            case MotionEvent.ACTION_MOVE:
                break;

            // Lifting finger off screen.
            case MotionEvent.ACTION_UP:
                break;
        }

        // Always returning true detects every touch to screen.
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.WHITE);
        mPlayer.draw(canvas);
    }

    public void update() {
        mPlayer.update();
    }
}

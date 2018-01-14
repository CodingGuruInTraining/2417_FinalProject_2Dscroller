package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Class to define the main thread in game.
 */

public class GameThread extends Thread {
    // Variables.
    private SurfaceHolder mSurfaceHolder;
    private GameManager mGameManager;

    // Flag for whether the game is in play.
    private boolean running;
    // Canvas object for drawing on screen.
    private static Canvas mCanvas;

    private static int maxFps;




    // Constructor.
    public GameThread(SurfaceHolder holder, GameManager gameManager) {
        super();
        this.mSurfaceHolder = holder;
        this.mGameManager = gameManager;

        maxFps = Constants.MAX_FPS;
    }



    // Run method.
    @Override
    public void run() {
        // Time variables used to manage frames.
        long startTime;
        long waitTime;
        long targetTime = 1000/maxFps;
        long timeMilliseconds;
        int frameCount = 0;

        // Game loop.
        while (running) {
            startTime = System.nanoTime();
            mCanvas = null;

            // Attempt to synchronize with the game manager in case
            // there is a problem with multiple threads.
            try {
                mCanvas = this.mSurfaceHolder.lockCanvas();
                synchronized (mSurfaceHolder) {
                    this.mGameManager.update();
                    this.mGameManager.draw(mCanvas);
                }
            } catch (Exception error) {
                Log.e("tag", "error with game thread: ");
                error.printStackTrace();
            } finally {
                if (mCanvas != null) {
                    try {
                        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                    } catch (Exception error) {
                        Log.e("tag", "error with game thread: ");
                        error.printStackTrace();
                    }
                }
            }
            // Get time in milliseconds.
            // Compare time at start of frame and current time.
            timeMilliseconds = (System.nanoTime() - startTime)/1000000;
            // targetTime is what want, timeMilliseconds is what the actual time is.
            waitTime = targetTime - timeMilliseconds;
            try {
                // Check if finished the frame earlier than the target time.
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
            // Increments frame counter.
            frameCount++;

            // Checks if max frames has been reached.
            if (frameCount == maxFps) {
                frameCount = 0;
            }
        }
    }



    // Setter.
    public void setRunning(boolean running) {
        this.running = running;
    }
}

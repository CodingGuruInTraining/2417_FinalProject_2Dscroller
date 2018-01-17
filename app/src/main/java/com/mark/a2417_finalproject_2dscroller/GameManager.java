package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.erz.joysticklibrary.JoyStick;

import java.util.ArrayList;

/**
 * Class to manage various parts of the game.
 */

public class GameManager extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;
    private Player mPlayer;
    private JoyStick mJoyStick;
    private Background mBackground;
    private RelativeLayout parent;
    private Context mContext;
    private EnemyManager mEnemyManager;
    private ActionButtons attackButton;
    private ScoreKeeper mScoreKeeper;

    private int playerDirection = 0;
    private boolean playerAttacking = false;
    private float screenThreshold;



    // Constructor.
    public GameManager(Context context) {
        super(context);
        this.mContext = context;
        // Set up interface to interact with SurfaceView.
        getHolder().addCallback(this);

        // Instantiate game thread.
        thread = new GameThread(getHolder(), this);

        // Instantiate background object.
        mBackground = new Background(context);

        // Uses constants to determine where player should start and
        // what its size will be. These are declared in MainActivity.
        mPlayer = new Player(new Rect(
                Constants.PLAYER_START_X,
                Constants.PLAYER_START_Y,
                Constants.PLAYER_START_X + Constants.PLAYER_WIDTH,
                Constants.PLAYER_START_Y + Constants.PLAYER_HEIGHT), context);

        // Instantiates enemy manager object for handling all enemy objects.
        mEnemyManager = new EnemyManager(mContext);

        // Instantiates action button object.
        attackButton = new ActionButtons();

        // Instantiates scorekeeper object.
        mScoreKeeper = new ScoreKeeper();

        // Calculates the point on screen where scrolling should begin.
        screenThreshold = Constants.SCREEN_WIDTH * Constants.THRESHHOLD_RATIO;

        setFocusableInTouchMode(true);
    }



    // SurfaceView required methods.
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        // Restart game thread.
        thread = new GameThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        // N/A
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // Closes game thread.
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



    // TouchEvent for SurfaceView. Used to react to certain touch locations.
// TODO may need to use floating action button instead.
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Detect how the screen was touched and acts on it.
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:       // Press down.
                // Checks if action button was pressed.
                if (attackButton.getButton().contains((int)event.getX(), (int)event.getY())) {
                    playerAttacking = true;
                    Log.d("tag", "attack button pressed down");
                }
                break;

            case MotionEvent.ACTION_MOVE:       // Moving finger across screen.
                // N/A
                break;

            case MotionEvent.ACTION_UP:         // Lifting finger off screen.
                // Sets flag back to false.
//                playerAttacking = false;
                break;

            case MotionEvent.ACTION_BUTTON_PRESS:
                Log.d("tag", "action button press motion");
                break;

        }
        // Always returning true detects every touch to screen.
        return true;
    }




    // Draw function that determines which objects should be drawn.
//    @Override
    public void mydraw(Canvas canvas) {
//        super.draw(canvas);

        mBackground.draw(canvas);
        mPlayer.draw(canvas);
        mEnemyManager.draw(canvas);
        attackButton.draw(canvas);
        mScoreKeeper.draw(canvas);
    }



    // Update function that determines which objects are updated.
    public void update() {
        // Checks if the SurfaceView's parent object has been captured in variable yet.
        // Currently, this is only needed during the first update in order to create
        // a reference to the joystick (won't work in constructor). Joystick widget
        // seems to only work if added in a View/Layout at the start of the app (MainActivity).
        if (parent == null) {
            findParentLayout();
            setupJoystick();
        }
        // Makes temporary object to reduce method calls.
        Rect tempRect = mPlayer.getPlayerRect();

        // Checks if player has reached point where background should start moving.
        if (tempRect.right >= screenThreshold) {
            mBackground.update(true);
        }
        // Calls player's update function and passes flags.
        mPlayer.update(playerDirection, playerAttacking);
//        if (mPlayer.getState() == 0) {
            playerAttacking = false;
//        }
//        playerAttacking = mPlayer.isAttacking();

        // Calls the enemy manager to run its updates.
        mEnemyManager.update(tempRect);

//        setFocusable(true);
        this.setFocusableInTouchMode(true);
        mJoyStick.setFocusableInTouchMode(true);
    }



    // Function to create reference to joystick widget and add event listener.
    // Only called once at start of app.
    private void setupJoystick() {
        // Gets reference assuming joystick is the 2nd child.
        mJoyStick = (JoyStick) parent.getChildAt(1);

        // Set up listener.
        mJoyStick.setListener(new JoyStick.JoyStickListener() {
            @Override
// TODO find more accurate way to detect movement.
            public void onMove(JoyStick joyStick, double angle, double power, int direction) {
                // Checks if joystick is in motion.
                // A flag variable is set depending on outcome.
                if (direction >= 0) {   // NOT IDLE
                    // Checks what direction the joystick is in at the moment.
                    if (direction == 3 || direction == 4 || direction == 5) {   // RIGHT
                        playerDirection = 2;
                    } else if (direction < 2 || direction == 7) {               // LEFT
                        playerDirection = 1;
                    }
                } else {
                    playerDirection = 0;    // IDLE
                }
            }
            @Override
            public void onTap() {
                // N/A
            }
            @Override
            public void onDoubleTap() {
                // N/A
            }
        });
    }



    // Event listener used for testing touch events (TESTING).
    @Override
    public boolean performClick() {
        Log.d("tag", "performed click");
        return true;
    }




    // Function to capture reference to SurfaceView's parent layout.
    // Only called once at start of app.
    private void findParentLayout() {
        ViewParent parent = this.getParent();

        if (parent != null) {
            if (parent instanceof RelativeLayout) {
                this.parent = (RelativeLayout) parent;
            }
        }
    }
}

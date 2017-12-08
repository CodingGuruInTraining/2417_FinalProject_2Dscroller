package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewParent;
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
    private Point playerPoint;
    private JoyStick mJoyStick;
    private Background mBackground;
    private boolean playerMoving = false;
    private RelativeLayout parent;
    private Context mContext;


    public GameManager(Context context) {
        super(context);
        this.mContext = context;
        // Set up interface to interact with SurfaceView.
        getHolder().addCallback(this);

        // Instantiate game thread.
        thread = new GameThread(getHolder(), this);

        mBackground = new Background(context);

        // Uses constants to determine where player should start and
        // what its size will be. These are declared in MainActivity.
        mPlayer = new Player(new Rect(
                Constants.PLAYER_START_X,
                Constants.PLAYER_START_Y,
                Constants.PLAYER_START_X + Constants.PLAYER_WIDTH,
                Constants.PLAYER_START_Y + Constants.PLAYER_HEIGHT), context);


        playerPoint = new Point(mPlayer.getPlayerRect().left, mPlayer.getPlayerRect().top);
        mPlayer.update(playerPoint);

//        final FrameLayout frameLayout = new FrameLayout(context);

//        mJoyStick = (JoyStick) findViewById(R.layout.joystickwidget);
//        mJoyStick = new JoyStick(context);

//        this.mJoyStick.findViewById(R.layout.joystickwidget); // addView(frameLayout);
//        frameLayout.addView(mJoyStick);

//        ArrayList frame = new ArrayList();
//        frame.add(frameLayout);

//        this.addTouchables(frame);

//        setupJoystick();

// TODO set up enemies

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
                if (mPlayer.getPlayerRect().contains((int) event.getX(), (int) event.getY())) {
                    playerMoving = true;
                }
                break;

            // Moving finger across screen.
            case MotionEvent.ACTION_MOVE:
                if (playerMoving) {
                    // Check if player is at ground level.
                    float y = event.getY();
                    int height = mPlayer.getPlayerRect().height();
                    int ground = mBackground.getGroundY();
                    if ((y + height) > ground) {
                        y = ground - height;
                    }
                    playerPoint.set((int) event.getX(), (int) y);
                }
                break;

            // Lifting finger off screen.
            case MotionEvent.ACTION_UP:
                playerMoving = false;
                break;
        }

        // Always returning true detects every touch to screen.
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        mBackground.draw(canvas);
        mPlayer.draw(canvas);
//        setupJoystick();
//        mJoyStick.draw(canvas);

//        Paint paint = new Paint();
//        paint.setColor(Color.BLUE);
//        canvas.drawRect(mJoyStick.getLeft(), mJoyStick.getTop(), mJoyStick.getRight(), mJoyStick.getBottom(), paint);
    }

    public void update() {

        if (parent == null) {
            findParentLayout();
            setupJoystick();
        }

// TODO find out where to move to and adjust Point object.
        mBackground.update();
        mPlayer.setIsMoving(playerMoving);
        if (playerMoving) {

        }
        mPlayer.update(playerPoint);


    }
//
    private void setupJoystick() {

        mJoyStick = (JoyStick) parent.getChildAt(1);
        mJoyStick.setListener(new JoyStick.JoyStickListener() {
            @Override
            public void onMove(JoyStick joyStick, double angle, double power, int direction) {
                Log.d("tag", "moved!");
            }

            @Override
            public void onTap() {

            }

            @Override
            public void onDoubleTap() {

            }
        });

//        mJoyStick = new JoyStick(mContext);
//        mJoyStick.bringToFront();
//        parent.addView(mJoyStick);
//        mJoyStick.bringToFront();
//
//        mJoyStick.setPadColor(Color.BLACK);
//        mJoyStick.bringToFront();
//        mJoyStick.forceHasOverlappingRendering(true);
//        mJoyStick.setLeft(500);
//        mJoyStick.setTop(300);
//        mJoyStick.setRight(800);
//        mJoyStick.setBottom(600);
//        mJoyStick.setMinimumWidth(300);
//        mJoyStick.setMinimumHeight(300);
//        mJoyStick.setButtonColor(Color.RED);
//        mJoyStick.enableStayPut(true);
//        mJoyStick.setForegroundGravity(300);
//        mJoyStick.setX(500);
//        mJoyStick.setY(300);
//        mJoyStick.setType(JoyStick.TYPE_2_AXIS_LEFT_RIGHT);
//
//        mJoyStick.setButtonRadiusScale(5);
//
//        mJoyStick.setActivated(true);
//        mJoyStick.setEnabled(true);



//// TODO try these if possible:
////        http://www.akexorcist.com/2012/10/android-code-joystick-controller.html
        //https://web.archive.org/web/20111229070701/http://www.java2s.com/Open-Source/Android/Widget/mobile-anarchy-widgets/com/MobileAnarchy/Android/Widgets/Joystick/JoystickView.java.htm
    }



    private void findParentLayout() {
        ViewParent parent = this.getParent();
//        RelativeLayout r;

        if (parent != null) {
            if (parent instanceof RelativeLayout) {
                this.parent = (RelativeLayout) parent;
            }
        }
    }
}

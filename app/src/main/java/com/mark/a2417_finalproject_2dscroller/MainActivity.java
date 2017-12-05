package com.mark.a2417_finalproject_2dscroller;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make app fullscreen.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Get rid of title bar on top.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setConstants();

        setContentView(new GameManager(this));
    }

    private void setConstants() {
        // Set constant values for screen size.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

// TODO may need to adjust ratio later.
        Constants.PLAYER_WIDTH = (dm.widthPixels / Constants.PLAYER_SIZE_RATIO);
        Constants.PLAYER_HEIGHT = (dm.heightPixels / Constants.PLAYER_SIZE_RATIO);

        // Set player's start location.
        Constants.PLAYER_START_X = (dm.widthPixels / Constants.PLAYER_START_RATIO);
        Constants.PLAYER_START_Y = dm.heightPixels - (dm.heightPixels / Constants.PLAYER_START_RATIO);
    }
}









// References:
    // game setup and processes (whole series) - https://www.youtube.com/watch?v=OojQitoAEXs
    // joystick - https://android-arsenal.com/details/1/2712#!package
    // drawing background image - https://stackoverflow.com/questions/5176441/drawable-image-on-a-canvas
    // side scrolling background - https://www.youtube.com/watch?v=GPzTSpZwFoU&index=3&list=PLWweaDaGRHjvQlpLV0yZDmRKVBdy6rSlg





    // Game Art:
        // city night background - https://opengameart.org/content/city-background-repetitive-3

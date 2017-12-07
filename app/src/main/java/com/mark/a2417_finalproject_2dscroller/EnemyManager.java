package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import java.util.ArrayList;


/**
 *
 */

public class EnemyManager {

    private ArrayList<Enemy> enemies;
    private int activeEnemies;



    public EnemyManager() {
        enemies = new ArrayList<Enemy>();
    }



    public void draw(Canvas canvas) {
        for (Enemy enemy : enemies) {
//            if (enemy.isActive()) {

                enemy.draw(canvas);


//            }
             if (activeEnemies < 5) {
//                enemy.setActive(true);
            }
            // else don't draw
        }
    }

    public void update() {
        for (Enemy enemy : enemies) {



        }
    }



    private void createEnemy() {
        // TODO pick 1/3 numbers to decide which side of screen to spaawn on.
        // TODO set Enemy's direction AND location (static start point).
        // TODO add to arraylist.
    }
}

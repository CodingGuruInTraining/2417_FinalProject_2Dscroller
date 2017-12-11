package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;


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
//             if (activeEnemies < 5) {
////                enemy.setActive(true);
//            }
            // else don't draw
        }
    }

    public void update() {
        if (enemies.size() < 3) {
            createEnemy();
        }

        for (Enemy enemy : enemies) {
            enemy.update();
        }
    }



    private void createEnemy() {
        // TODO pick 1/3 numbers to decide which side of screen to spaawn on.
        // TODO set Enemy's direction AND location (static start point).
        // TODO add to arraylist.
        Random random = new Random();
        int side = random.nextInt(3);
        Enemy enemy = new Enemy(side);
        enemies.add(enemy);
    }


    public boolean checkCollisions(Rect player) {
        for (Enemy enemy : enemies) {
// TODO check if player collided
// TODO check if enemy is in range to attack
// TODO check if in player's attack range and player is attacking
            if (enemy.checkCollision(player)) {
                enemies.remove(enemy);
                return true;
            }
        }
        return false;
    }
}

package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;


/**
 * Class for managing all the Enemy objects and their functions.
 */

public class EnemyManager {

    private ArrayList<Enemy> enemies;
    private int activeEnemies;


    // Constructor.
    public EnemyManager() {
        enemies = new ArrayList<Enemy>();
    }



    // Function that draws each Enemy object.
    public void draw(Canvas canvas) {
        for (Enemy enemy : enemies) {
                enemy.draw(canvas);
        }
    }


    // Function that updates each Enemy object.
    public void update() {
        // Checks if there are at least a certain number of active
        // enemies in play.
// TODO determine constant value for size check.
        if (enemies.size() < 3) {
            createEnemy();
        }

        // Loops through array and calls each enemy's update function.
        for (Enemy enemy : enemies) {
            enemy.update();
        }
    }


    // Function to check whether an enemy has collided with the player.
    // This is called from the GameManager.
    public boolean checkCollisions(Rect player) {
        for (Enemy enemy : enemies) {
// TODO check if enemy is in range to attack
// TODO check if in player's attack range and player is attacking
            if (enemy.checkCollision(player)) {
                // Removes enemy from array.
                enemies.remove(enemy);
                return true;
            }
        }
        return false;
    }




    // Function to create new Enemy object.
    private void createEnemy() {
        // A random number is picked to decide which side of screen to spawn on.
        // There is a higher chance to spawn on the right side.
        Random random = new Random();
        int side = random.nextInt(3);
        Enemy enemy = new Enemy(side);
        enemies.add(enemy);
    }
}

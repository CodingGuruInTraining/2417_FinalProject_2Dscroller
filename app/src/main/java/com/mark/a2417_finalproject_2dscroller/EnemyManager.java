package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * Class for managing all the Enemy objects and their functions.
 */

public class EnemyManager {
    // Variables.
    private ArrayList<Enemy> enemies;
    private Context mContext;
    private Sprite[] allSprites;
    private int maxEnemies;



    // Constructor.
    public EnemyManager(Context context) {
        enemies = new ArrayList<Enemy>();
        mContext = context;
        maxEnemies = Constants.MAX_ENEMIES;

        // Creates Sprite objects for each animation.
        Sprite walk = makeSprite(R.drawable.zombie_walk, 2f, 4, 3, 10);
        Sprite attack = makeSprite(R.drawable.zombie_attack, 1f, 4, 2, 7);
        Sprite die = makeSprite(R.drawable.zombie_die, 1f, 4, 2, 8);

        // Stores all sprites in a list.
        allSprites = new Sprite[] {walk, attack, die};
    }



    // Draw method.
    public void draw(Canvas canvas) {
        for (Enemy enemy : enemies) {
            enemy.draw(canvas);
        }
    }


    // Update method.
    public void update(Rect player) {
        // Checks if there are at least a certain number of active
        // enemies in play.
        if (enemies.size() < maxEnemies) {
            createEnemy();
        }

        // Loops through array and calls each enemy's update function.
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.update(player);
            if (!enemy.active) {
                iterator.remove();
                Log.d("tag", "removed enemy; count = " + enemies.size());
            }
        }
    }



    // Function to create new Enemy object.
    private void createEnemy() {
        // A random number is picked to decide which side of screen to spawn on.
        // There is a higher chance to spawn on the right side.
        Random random = new Random();
        int side = random.nextInt(3);
        Enemy enemy = new Enemy(side, allSprites);
        enemies.add(enemy);
    }


    // Function to retrieve sprite image.
    private Sprite makeSprite(int id, float time, int rows, int cols, int count) {
        Bitmap sprite = BitmapFactory.decodeResource(mContext.getResources(), id);
        return new Sprite(sprite, time, rows, cols, count);
    }
}

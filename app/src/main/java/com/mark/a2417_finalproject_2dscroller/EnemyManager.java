package com.mark.a2417_finalproject_2dscroller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;


/**
 * Class for managing all the Enemy objects and their functions.
 */

public class EnemyManager {

    private ArrayList<Enemy> enemies;
    private int activeEnemies;
    private Context mContext;
    private Sprite walk;
    private Sprite attack;
    private Sprite die;
    private AnimationManager mAnimationManager;
    private Sprite[] allSprites;


    // Constructor.
    public EnemyManager(Context context) {
        enemies = new ArrayList<Enemy>();
        mContext = context;

        walk = makeSprite(R.drawable.zombie_walk, 2f, 4, 3, 10);
        attack = makeSprite(R.drawable.zombie_attack, 1f, 4, 2, 7);
        die = makeSprite(R.drawable.zombie_die, 1f, 4, 2, 8);

        allSprites = new Sprite[] {walk, attack, die};

//        mAnimationManager = new AnimationManager(new Sprite[] {walk, attack, die});
    }



    // Function that draws each Enemy object.
    public void draw(Canvas canvas) {
        for (Enemy enemy : enemies) {
//            mAnimationManager.draw(canvas, enemy.getRectangle());
                enemy.draw(canvas);
        }
    }


    // Function that updates each Enemy object.
    public void update(Rect player) {
        // Checks if there are at least a certain number of active
        // enemies in play.
// TODO determine constant value for size check.
        if (enemies.size() < 3) {
            createEnemy();
        }

        // Loops through array and calls each enemy's update function.
        for (Enemy enemy : enemies) {

//            if ((!enemy.isReadyForDeath() || !enemy.isHitPlayer()) && enemy.isDone()) {
//                enemies.remove(enemy);
//            }
            enemy.update(player);
//            mAnimationManager.update();
        }
    }


//    public boolean checkAttackRanges(Rect player, boolean attacking) {
////        for (Enemy enemy : enemies) {
////            if (!enemy.isReadyForDeath() && !enemy.isHitPlayer()) {
////                if (enemy.checkAttackRange(player, attacking)) {
////                    Log.d("tag", "player is in range and attacking");
////                    return true;
////                }
////            }
////        }
//        return false;
//    }
//
//
//    // Function to check whether an enemy has collided with the player.
//    // This is called from the GameManager.
//    public boolean checkCollisions(Rect player) {
//        for (Enemy enemy : enemies) {
//
//            boolean a = enemy.checkCollision(player);
//
////            if (!enemy.isReadyForDeath() && !enemy.isHitPlayer()) {
////            if (enemy.checkCollision(player)) {
//////                if (enemy.isActive()) {
////                // Enemy attacked player.
//////                    player.hitPlayer();
//////                }
//////                else {
//////                    // Removes enemy from array.
////////                    enemies.remove(enemy);
//////                }
////
////                return true;
////            }
////            }
//        }
//        return false;
//    }




    // Function to create new Enemy object.
    private void createEnemy() {
        // A random number is picked to decide which side of screen to spawn on.
        // There is a higher chance to spawn on the right side.
        Random random = new Random();
        int side = random.nextInt(3);
        Enemy enemy = new Enemy(side, allSprites);
        enemies.add(enemy);
    }


    private Sprite makeSprite(int id, float time, int rows, int cols, int count) {
        Bitmap sprite = BitmapFactory.decodeResource(mContext.getResources(), id);
        return new Sprite(sprite, time, rows, cols, count);
    }
}

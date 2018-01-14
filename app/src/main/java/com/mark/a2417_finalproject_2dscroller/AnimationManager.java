package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Manager class that coordinates which sprite animation should be playing.
 * This class also acts as a middle-man to retrieve information about
 * sprites for other Classes.
 */

public class AnimationManager {
    // Variables.
    private Sprite[] animations;
    private int animIndex = 0;



    // Constructor.
    public AnimationManager(Sprite[] sprites) {
        animations = sprites;
    }



    // Method called to start a sprite animation.
    public void playAnim(int index) {
        for (int i = 0; i < animations.length; i++) {
            if (i == index) {
                if (!animations[index].isPlaying()) {
                    animations[i].play();
                }
            } else {
                animations[i].stop();
            }
        }
        animIndex = index;
    }


    // Draw method.
    public void draw(Canvas canvas, Rect rect) {
        if (animations[animIndex].isPlaying()) {
            animations[animIndex].draw(canvas, rect);
        }
    }


    // Update method.
    public void update() {
        if (animations[animIndex].isPlaying()) {
            animations[animIndex].update();
        }
    }


    // Getters.
    public float getActiveWidth() {
// TODO look into this: should it receive an index instead?
        for (int i = 0; i < animations.length; i++) {
            if (animations[i].isPlaying()) {
                return animations[i].getWidth();
            }
        }
        return 0;
    }
    // More Getters.
    public boolean isDone(int index) { return animations[index].isDone(); }
    public double getAnimTime(int index) { return animations[index].getTotalAnimTime(); }
}

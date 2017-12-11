package com.mark.a2417_finalproject_2dscroller;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 *
 */

public class AnimationManager {

    private Sprite[] animations;
    private int animIndex = 0;

    public AnimationManager(Sprite[] sprites) {
        animations = sprites;
    }

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


    public void draw(Canvas canvas, Rect rect) {
        if (animations[animIndex].isPlaying()) {
            animations[animIndex].draw(canvas, rect);
        }
    }


    public void update() {
        if (animations[animIndex].isPlaying()) {
            animations[animIndex].update();
        }
    }


}

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
//        if (animations[animIndex].isDone()) {
//            animations[animIndex].stop();
//            animations[index].play();
//            animIndex = index;
//        }
//        if (!animations[animIndex].isPlaying()) {
//            for (int i = 0; i < animations.length; i++) {
//                if (animations[i].isPlaying())
//            }
//
//
//
//
//
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
//        }
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

    public float getActiveWidth() {
        for (int i = 0; i < animations.length; i++) {
            if (animations[i].isPlaying()) {
                return animations[i].getWidth();
            }
        }
        return 0;
    }

    public boolean isDone(int index) { return animations[index].isDone(); }
}

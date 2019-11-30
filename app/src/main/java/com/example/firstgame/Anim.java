package com.example.firstgame;

import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class Anim {

    public void animateItCode(ImageView imv) {
        imv.setScaleX(1);
        imv.setScaleY(1);
        //imv.setRotation(0);
        imv.animate()
                //.rotation(360)
                .scaleX(0)
                .scaleY(0)
//                .setListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        animateItCode();
//                    }
//
//                    @Override
//                    public void onAnimationCancel(Animator animation) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(Animator animation) {
//
//                    }
//                })
                .setDuration(1000)
                .setInterpolator(new AccelerateInterpolator())
                .start();

    }

}

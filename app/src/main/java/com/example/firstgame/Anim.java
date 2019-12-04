package com.example.firstgame;

import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

public class Anim {

    //animation for take down life
    public void animateItCode(ImageView imv) {
        imv.setScaleX(1);
        imv.setScaleY(1);
        imv.animate()
                .scaleX(0)
                .scaleY(0)
                .setDuration(350)
                .setInterpolator(new AccelerateInterpolator())
                .start();

    }

}

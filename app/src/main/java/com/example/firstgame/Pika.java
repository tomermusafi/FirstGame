package com.example.firstgame;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class Pika {
    private static Anim anim;
    private static MySignal signal;
    private static Context context;
    private int pos = 1;
    static int life = 3;
    static Sound sound;

    public Pika(Context context, Sound sound) {
         anim = new Anim();
         signal = new MySignal();
         this.context = context;
         this.sound = sound;


    }

    public void moveRight(){
        switch (pos){
            case 0:{
                MainActivity.arrImgActor[0].setVisibility(View.INVISIBLE);
                MainActivity.arrImgActor[1].setVisibility(View.VISIBLE);
                ++pos;
            }
            break;
            case 1:{
                MainActivity.arrImgActor[1].setVisibility(View.INVISIBLE);
                MainActivity.arrImgActor[2].setVisibility(View.VISIBLE);
                ++pos;
            }
            break;
            case 2:{

            }
            break;
        }

    }

    public void moveLeft(){

        switch (pos){
            case 0:{

            }
            break;
            case 1:{
                MainActivity.arrImgActor[1].setVisibility(View.INVISIBLE);
                MainActivity.arrImgActor[0].setVisibility(View.VISIBLE);
                --pos;
            }
            break;
            case 2:{
                MainActivity.arrImgActor[2].setVisibility(View.INVISIBLE);
                MainActivity.arrImgActor[1].setVisibility(View.VISIBLE);
                --pos;
            }
            break;
        }

    }

    public static void checkLife(){
        if((MainActivity.arrImgActor[0].getVisibility() == View.VISIBLE)
        && (MainActivity.arrImgObstacle[6][0].getVisibility() == View.VISIBLE)
            ){
            bybyLife();
            MainActivity.handler.postDelayed(MainActivity.myRunLife, 500);

        }

        if((MainActivity.arrImgActor[1].getVisibility() == View.VISIBLE)
                && (MainActivity.arrImgObstacle[6][1].getVisibility() == View.VISIBLE)
                ){
            bybyLife();
            MainActivity.handler.postDelayed(MainActivity.myRunLife, 500);
        }

        if((MainActivity.arrImgActor[2].getVisibility() == View.VISIBLE)
                && (MainActivity.arrImgObstacle[6][2].getVisibility() == View.VISIBLE)
                ){
            bybyLife();
            MainActivity.handler.postDelayed(MainActivity.myRunLife, 500);
        }
    }

    private static void bybyLife(){
        switch (life){
            case 3: {
                whenInjurd(0);
            }
            break;
            case 2:{
                whenInjurd(1);
            }
            break;
            case 1:{
                whenInjurd(2);
            }
            break;
        }
    }
    private static void whenInjurd(int pos){
        Toast.makeText(context, "Aaawuch", Toast.LENGTH_SHORT).show();
        signal.vibrate(context, 500);
        anim.animateItCode(MainActivity.arrImgLife[pos]);
        sound.pikaaaaSound();
        --life;
    }


    public static void setLife(int life) {
        Pika.life = life;
    }
}

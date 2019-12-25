package com.example.firstgame;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Pika {
    private static Anim anim;
    private static MySignal signal;
    private static Context context;
    private static int pos ;
    static int life = 3;
    static Sound sound;
    ImageView [] pikaArray;
    int size;
    static int actorRow;

    public Pika(Context context, Sound sound, ImageView [] pikaArray, int pos, int size, int actorRow) {
         anim = new Anim();
         signal = new MySignal();
         this.context = context;
         this.sound = sound;
         this.pikaArray = pikaArray;
         this.pos = pos;
         this.size = size;
         this.actorRow = actorRow;


    }

    //move the player right
    public void moveRight(){

        int temp;
        temp = pos;
        if(pos < size-1) {
            pos++;
            pikaArray[temp].setVisibility(View.INVISIBLE);
            pikaArray[pos].setVisibility(View.VISIBLE);
        }


    }

    //move the player left
    public void moveLeft(){

        int temp;
        temp = pos;
        if(pos > 0) {
            pos--;
            pikaArray[temp].setVisibility(View.INVISIBLE);
            pikaArray[pos].setVisibility(View.VISIBLE);
        }



    }

    //check if player injured by obstacle
    public  void checkLife(){

        if((MainActivity.arrImgActor[pos].getVisibility() == View.VISIBLE)
                && (MainActivity.pokaCoinMatrix5[actorRow][pos] == 0)
        && (MainActivity.arrImgObstacle[actorRow][pos].getVisibility() == View.VISIBLE)
            ){
            Log.d("pttt", "hit");

            MainActivity.pokaCoinMatrix5[actorRow][pos] = -1;
            bybyLife();


        }else if((MainActivity.arrImgActor[pos].getVisibility() == View.VISIBLE)
                && (MainActivity.pokaCoinMatrix5[actorRow][pos] == 1)
                && (MainActivity.arrImgObstacle[actorRow][pos].getVisibility() == View.VISIBLE)
        ){
            Log.d("pttt", "hit");

            MainActivity.pokaCoinMatrix5[actorRow][pos] = -1;
            MainActivity.score.coinScore();
            sound.coinsSound();


        }


    }

    //take down life
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

    //show signal when player is injured
    private static void whenInjurd(int pos){
        //MainActivity.handler.postDelayed(MainActivity.myRunLife, 500);
        Toast.makeText(context, "Ohhh", Toast.LENGTH_SHORT).show();
        signal.vibrate(context, 500);
        anim.animateItCode(MainActivity.arrImgLife[pos]);
        sound.pikaaaaSound();
        --life;
    }


    public static void setLife(int life) {
        Pika.life = life;
    }
}

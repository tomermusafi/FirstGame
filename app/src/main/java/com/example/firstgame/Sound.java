package com.example.firstgame;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class Sound {

     public static MediaPlayer pikaaaa;
     public static MediaPlayer gameSound;
     public static MediaPlayer moveSound;

    public Sound(Context context) {
        moveSound = MediaPlayer.create(context, R.raw.move_sound);
        pikaaaa = MediaPlayer.create(context, R.raw.pikaaaa);
        gameSound = MediaPlayer.create(context, R.raw.game_sound);
    }

    public static void pikaaaaSound(){
        pikaaaa.start();
    }
    public void start_game_sound(){

        gameSound.setLooping(true);
        gameSound.start();
    }

    public void pause_game_sound(){
        gameSound.pause();
    }
    public void resume_game_sound(){
        gameSound.seekTo(gameSound.getCurrentPosition());
        gameSound.start();
    }
    public static void move_Sound(){
        moveSound.start();

    }


    public void stop_game_sound(){
        gameSound.stop();
    }


}

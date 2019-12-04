package com.example.firstgame;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {

     public static MediaPlayer pikaaaa;
     public static MediaPlayer gameSound;
     public static MediaPlayer moveSound;

    public Sound(Context context) {
        moveSound = MediaPlayer.create(context, R.raw.move_sound);
        pikaaaa = MediaPlayer.create(context, R.raw.pikaaaa);
        gameSound = MediaPlayer.create(context, R.raw.game_sound);
    }

    //sound of player injured
    public static void pikaaaaSound(){
        pikaaaa.start();
    }

    //sound of background game music
    public void start_game_sound(){
        gameSound.setVolume((float)0.3, (float)0.3);
        gameSound.setLooping(true);
        gameSound.start();
    }

    //pause background sound
    public void pause_game_sound(){
        gameSound.pause();
    }

    //resume background sound
    public void resume_game_sound(){
        gameSound.seekTo(gameSound.getCurrentPosition());
        gameSound.start();
    }

    //movement sound
    public static void move_Sound(){
        moveSound.setVolume((float)0.5, (float)0.5);
        moveSound.start();

    }

    //stop background sound
    public void stop_game_sound(){
        gameSound.stop();
    }


}

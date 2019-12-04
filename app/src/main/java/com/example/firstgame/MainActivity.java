package com.example.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // All the arrays in use
    static ImageView[][] arrImgObstacle;
    static ImageView[] arrImgActor;
    static ImageView[] arrImgLife;

    // Handler and runnable
    static Handler handler = new Handler();
    static Runnable myRun;
    static Runnable myRunLife = new Runnable() {
        @Override
        public void run() {

        }
    };
    //to check if resume
    static int resumeCount = 0;

    // number of cols
    final int NUM_OF_COLS = 3;

    // in charge of the speed of the obstacle
    final int SLEEP_TIME = 100;

    // positions of obstacles in cols
    static int pos_col0 = 0, pos_col1 = 0,pos_col2 = 0, pos = 10;

    // the col which choose in random
    int colChoose = 0;
    Random random = new Random();

    //Obstacle object
    Poka p0 = new Poka(0);
    Poka p1 = new Poka(1);
    Poka p2 = new Poka(2);

    //Player object
    Pika pika;

    //intent time
    Time time = new Time(0, 0);

    // intent score
    Score score = new Score(0);

    //buttons
    Button main_BTN_left, main_BTN_right;

    //textViews
    static TextView main_TXV_time,main_TXV_score;

    //sound object
    Sound sound;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ggg", "on create");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //intent sound
        sound = new Sound(this);

        //intent player
        pika = new Pika(this, sound);

        //Set player life
        pika.setLife(3);

        // Start the timer
        time.setSecond(0);
        time.setMinute(0);
        time.countTime();

        //start count score
        score.setScore(0);
        score.countScore();

        // intent the pos values
        pos = 10;
        pos_col0 = 0;
        pos_col1 = 0;
        pos_col2 = 0;


        //life array
        arrImgLife = new ImageView[] {
                findViewById(R.id.main_IMG_live0)
                ,findViewById(R.id.main_IMG_live1)
                ,findViewById(R.id.main_IMG_live2)};

        //player array
        arrImgActor = new ImageView[] {
                findViewById(R.id.main_IMV_0)
                , findViewById(R.id.main_IMV_1)
                , findViewById(R.id.main_IMV_2) };

        //obstacle matrix
        arrImgObstacle = new ImageView[][]{
                {findViewById(R.id.main_IMV_00),findViewById(R.id.main_IMV_01),findViewById(R.id.main_IMV_02)},
                {findViewById(R.id.main_IMV_10),findViewById(R.id.main_IMV_11),findViewById(R.id.main_IMV_12)},
                {findViewById(R.id.main_IMV_20),findViewById(R.id.main_IMV_21),findViewById(R.id.main_IMV_22)},
                {findViewById(R.id.main_IMV_30),findViewById(R.id.main_IMV_31),findViewById(R.id.main_IMV_32)},
                {findViewById(R.id.main_IMV_40),findViewById(R.id.main_IMV_41),findViewById(R.id.main_IMV_42)},
                {findViewById(R.id.main_IMV_50),findViewById(R.id.main_IMV_51),findViewById(R.id.main_IMV_52)},
                {findViewById(R.id.main_IMV_60),findViewById(R.id.main_IMV_61),findViewById(R.id.main_IMV_62)},
                {findViewById(R.id.main_IMV_70),findViewById(R.id.main_IMV_71),findViewById(R.id.main_IMV_72)},
                {findViewById(R.id.main_IMV_80),findViewById(R.id.main_IMV_81),findViewById(R.id.main_IMV_82)}
        };



        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_TXV_time = findViewById(R.id.main_TXV_time);
        main_TXV_score = findViewById(R.id.main_TXV_score);

        main_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pika.moveLeft();
                moveSound();
            }
        });

        main_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pika.moveRight();
                moveSound();
            }
        });

        //start sound's game
        sound.start_game_sound();

        // obstacle falling
        wave();


    }


    private void moveSound(){
        sound.move_Sound();
    }


    private void wave() {

         myRun = new Runnable() {
            @Override
            public void run() {
                wave();
                imageFall();
            }
        };

         handler.postDelayed(myRun, SLEEP_TIME);
    }

    public  void imageFall(){

        if (pos == 10){
             colChoose = random.nextInt(NUM_OF_COLS);
             pos = 0;
        }
        if(Pika.life == 0 ){
            handler.removeCallbacks(myRun);
            time.stopTime();
            score.stopScore();
            Intent intent = new Intent(this, GameOverPage.class);
            startActivity(intent);
            sound.stop_game_sound();
            finish();

        }
        switch (colChoose){
            case 0:{
                p0.fallImg();
                ++pos;

            }
            break;

            case 1:{
                p1.fallImg();
                ++pos;
            }
            break;

            case 2:{
                p2.fallImg();
                ++pos;
            }
            break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ggg", "on stop");
        sound.pause_game_sound();
        handler.removeCallbacks(myRun);
        time.stopTime();
        score.stopScore();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("ggg", "on back pressed");
        handler.removeCallbacks(myRun);
        time.stopTime();
        score.stopScore();
        sound.stop_game_sound();
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ggg", "on resume");
        sound.resume_game_sound();
        if (resumeCount > 0){
            handler.postDelayed(myRun, SLEEP_TIME);
            Time.handler.postDelayed(Time.myRun, 1000);
            Score.handler.postDelayed(Score.myRun, 1000);

        }
        else {
            resumeCount++;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ggg", "on destroy");
        resumeCount = 0;
    }

}

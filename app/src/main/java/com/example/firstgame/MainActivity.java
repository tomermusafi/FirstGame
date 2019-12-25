package com.example.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout main_LL_left, main_LL_right;

    boolean handlerFlag;
    int colCoin = 0, colPoka = 0, rowCoin = 0, rowPoka = 0;

    //sensors
    Accelerometer accelerometer;
    Gyroscope gyroscope;

    boolean speed_slow;
    boolean sensor_off;

    int numOfRowsPOne;

    // All the arrays in use
    static ImageView[][] arrImgObstacle;
    static ImageView[] arrImgActor;
    static ImageView[] arrImgLife;

    static int [][] pokaCoinMatrix5 = new int[15][5];

    // Handler and runnable
    static Handler handler1 = new Handler();
    static Handler handler2 = new Handler();
    static Handler handler3 = new Handler();
    static Runnable myRun1;
    static Runnable myRun2;
    static Runnable myRun3;

    //to check if resume
    static int resumeCount = 0;

    // number of cols
    int numOfCols;

    // in charge of the speed of the obstacle
    int gameSleepTime;
    int loopObstecalSleepTime;

    //Obstacle object
    Poka poka;
    Poka coin;
    //Player object
    Pika pika;

    //intent distance
    Distance distance = new Distance();

    // intent score
    static Score score = new Score();

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

        handlerFlag = true;
        poka = new Poka(R.drawable.pokeball, 0);
        coin = new Poka(R.drawable.pokecoin, 1);

        numOfCols = OpenPage.numOfLanes;
        speed_slow = OpenPage.speed_flag;
        sensor_off = OpenPage.sensor_flag;
        //intent sound
        sound = new Sound(this);


        // set distance
        distance.setDistance(0);

        //set score
        score.setScore(0);

        // intent the pos values
        if(numOfCols == 3){
            //pos = 10;
            numOfRowsPOne = 9;
            gameSleepTime = 300;
            loopObstecalSleepTime = 150;
        }
        else{
            //pos = 16;
            numOfRowsPOne = 15;
            gameSleepTime = 230;
            loopObstecalSleepTime = 115;
        }

        if(speed_slow){
            if(numOfCols == 3){
                //pos = 10;
                numOfRowsPOne = 9;
                gameSleepTime = 400;
                loopObstecalSleepTime = 200;
            }
            else{
                //pos = 16;
                numOfRowsPOne = 15;
                gameSleepTime = 400;
                loopObstecalSleepTime = 200;
            }

        }


        //life array
        arrImgLife = new ImageView[] {
                findViewById(R.id.main_IMG_live0)
                ,findViewById(R.id.main_IMG_live1)
                ,findViewById(R.id.main_IMG_live2)};

        //player array
        arrImgActor = new ImageView[]{
                findViewById(R.id.main_IMV_0),
                findViewById(R.id.main_IMV_1),
                findViewById(R.id.main_IMV_2),
                findViewById(R.id.main_IMV_3),
                findViewById(R.id.main_IMV_4),
        };

        //obstacle matrix
        arrImgObstacle = new ImageView[][]{
                {findViewById(R.id.main_IMV_00),findViewById(R.id.main_IMV_01),findViewById(R.id.main_IMV_02), findViewById(R.id.main_IMV_03), findViewById(R.id.main_IMV_04)},
                {findViewById(R.id.main_IMV_10),findViewById(R.id.main_IMV_11),findViewById(R.id.main_IMV_12), findViewById(R.id.main_IMV_13), findViewById(R.id.main_IMV_14)},
                {findViewById(R.id.main_IMV_20),findViewById(R.id.main_IMV_21),findViewById(R.id.main_IMV_22), findViewById(R.id.main_IMV_23), findViewById(R.id.main_IMV_24)},
                {findViewById(R.id.main_IMV_30),findViewById(R.id.main_IMV_31),findViewById(R.id.main_IMV_32), findViewById(R.id.main_IMV_33), findViewById(R.id.main_IMV_34)},
                {findViewById(R.id.main_IMV_40),findViewById(R.id.main_IMV_41),findViewById(R.id.main_IMV_42), findViewById(R.id.main_IMV_43), findViewById(R.id.main_IMV_44)},
                {findViewById(R.id.main_IMV_50),findViewById(R.id.main_IMV_51),findViewById(R.id.main_IMV_52), findViewById(R.id.main_IMV_53), findViewById(R.id.main_IMV_54)},
                {findViewById(R.id.main_IMV_60),findViewById(R.id.main_IMV_61),findViewById(R.id.main_IMV_62), findViewById(R.id.main_IMV_63), findViewById(R.id.main_IMV_64)},
                {findViewById(R.id.main_IMV_70),findViewById(R.id.main_IMV_71),findViewById(R.id.main_IMV_72), findViewById(R.id.main_IMV_73), findViewById(R.id.main_IMV_74)},
                {findViewById(R.id.main_IMV_80),findViewById(R.id.main_IMV_81),findViewById(R.id.main_IMV_82), findViewById(R.id.main_IMV_83), findViewById(R.id.main_IMV_84)},
                {findViewById(R.id.main_IMV_90),findViewById(R.id.main_IMV_91),findViewById(R.id.main_IMV_92), findViewById(R.id.main_IMV_93), findViewById(R.id.main_IMV_94)},
                {findViewById(R.id.main_IMV_100),findViewById(R.id.main_IMV_101),findViewById(R.id.main_IMV_102), findViewById(R.id.main_IMV_103), findViewById(R.id.main_IMV_104)},
                {findViewById(R.id.main_IMV_110),findViewById(R.id.main_IMV_111),findViewById(R.id.main_IMV_112), findViewById(R.id.main_IMV_113), findViewById(R.id.main_IMV_114)},
                {findViewById(R.id.main_IMV_120),findViewById(R.id.main_IMV_121),findViewById(R.id.main_IMV_122), findViewById(R.id.main_IMV_123), findViewById(R.id.main_IMV_124)},
                {findViewById(R.id.main_IMV_130),findViewById(R.id.main_IMV_131),findViewById(R.id.main_IMV_132), findViewById(R.id.main_IMV_133), findViewById(R.id.main_IMV_134)},
                {findViewById(R.id.main_IMV_140),findViewById(R.id.main_IMV_141),findViewById(R.id.main_IMV_142), findViewById(R.id.main_IMV_143), findViewById(R.id.main_IMV_144)},
        };

        //intent player

        if(numOfCols == 3){
            for(int i = 0; i < 15; i++){
                for(int j = 0; j < 5; j++){
                    if(j == 3 || j == 4 || i > 8){
                        arrImgObstacle[i][j].setVisibility(View.GONE);
                    }
                }
            }
            arrImgActor[3].setVisibility(View.GONE);
            arrImgActor[4].setVisibility(View.GONE);

            pika = new Pika(this, sound, arrImgActor, 1, 3, 6);
            arrImgActor[1].setVisibility(View.VISIBLE);

        }else{
            pika = new Pika(this, sound, arrImgActor, (arrImgActor.length / 2), arrImgActor.length, 11);
            arrImgActor[2].setVisibility(View.VISIBLE);
        }

        //Set player life
        pika.setLife(3);

        //main_BTN_left = findViewById(R.id.main_BTN_left);
        //main_BTN_right = findViewById(R.id.main_BTN_right);
        main_TXV_time = findViewById(R.id.main_TXV_time);
        main_TXV_score = findViewById(R.id.main_TXV_score);
        main_LL_left = findViewById(R.id.main_LL_left);
        main_LL_right = findViewById(R.id.main_LL_right);



        //intent sensors
        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);
//        accelerometer.setListener(new Accelerometer.Listener() {
//            @Override
//            public void onTranslation(float tx, float ty, float tz) {
//
//                //Log.d("ooo", ""+ tx);
//                if(ty > 1.0f){
//                    Log.d("ooo", "right " + ty);
//                    pika.moveLeft();
//                }
//                else if(ty < -1.0f){
//                    Log.d("ooo", "left " + ty);
//                    pika.moveRight();
//                }
//            }
//        });

        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {

//                if(rx > 1.0f){
//                    sleepTime = 50;
//                }
//                else if(rx < -1.0f){
//                    sleepTime = 500;
//                }
//                else{
//                    sleepTime = 100;
//                }

                Log.d("ooo", ""+ ry);
                if(ry > 1.0f){
                    Log.d("ooo", "right");
                    Log.d("ooo", ""+ ry);
                    pika.moveRight();
                    moveSound();

                }
                else if(ry < -1.0f){
                    Log.d("ooo", "left");
                    Log.d("ooo", ""+ ry);
                    pika.moveLeft();
                    moveSound();

                }

            }
        });



        main_LL_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sensor_off){
                    pika.moveLeft();
                    moveSound();
                }

            }
        });

        main_LL_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sensor_off){
                    pika.moveRight();
                    moveSound();
                }
            }
        });

        //start sound's game
        sound.start_game_sound();

        // obstacle falling
        loopGame();

    }


    private void moveSound(){
        sound.move_Sound();
    }


    public void loopGame() {

        score.countScore();
        distance.countDistance();
        handler1 = new Handler();
        myRun1 = new Runnable() {
            @Override
            public void run() {

                int pokaCol = (int) ((Math.random() * ((numOfCols-1) + 1)));
                int coinCol = (int) ((Math.random() * ((numOfCols-1) + 1)));
                if(pokaCol != coinCol){
                    loopPoka(pokaCol, 0);
                    loopCoin(coinCol, 0);

                }

                loopGame();

            }
        };

        if(handlerFlag){
            handler1.postDelayed(myRun1, gameSleepTime);
        }

        if(Pika.life == 0 ){
            handler1.removeCallbacks(myRun1);
            handler2.removeCallbacks(myRun2);
            handler3.removeCallbacks(myRun3);
            Intent intent = new Intent(this, GameOverPage.class);
            intent.putExtra(GameOverPage.SCORE, score.getScore());
            intent.putExtra(GameOverPage.TIME, main_TXV_time.getText().toString());
            startActivity(intent);
            sound.stop_game_sound();
            finish();

        }


    }

    private void loopPoka(final int row, final int col) {

        handler2 = new Handler();
        myRun2 = new Runnable() {
            @Override
            public void run() {
                if (col <= numOfRowsPOne ) {
                    if(!handlerFlag){
                        colPoka = col;
                        rowPoka = row;
                    }
                    loopPoka(row, col + 1);
                    movePokaInLane(row, col);
                }
            }
        };
        if(handlerFlag){
            handler2.postDelayed(myRun2, loopObstecalSleepTime);
        }

    }

    private void movePokaInLane(int col, int row) {

        if (row != 0) {
            arrImgObstacle[row - 1][col].setVisibility(View.INVISIBLE);
        }
        if (row < numOfRowsPOne) {
            arrImgObstacle[row][col].setImageResource(poka.img);
            arrImgObstacle[row][col].setVisibility(View.VISIBLE);
            pokaCoinMatrix5[row][col] = poka.getTag();
            pika.checkLife();

        }

    }

    private void moveCoinInLane(int col, int row) {

        if (row != 0) {
            arrImgObstacle[row - 1][col].setVisibility(View.INVISIBLE);
        }
        if (row < numOfRowsPOne) {
            arrImgObstacle[row][col].setImageResource(coin.img);
            arrImgObstacle[row][col].setVisibility(View.VISIBLE);
            pokaCoinMatrix5[row][col] = coin.getTag();

        }

    }

    private void loopCoin(final int row, final int col) {
         handler3 = new Handler();
         myRun3 = new Runnable() {
            @Override
            public void run() {
                if (col <= numOfRowsPOne ) {
                    if(!handlerFlag){
                        colCoin = col;
                        rowCoin = row;
                    }
                    loopCoin(row, col + 1);
                    moveCoinInLane(row, col);
                }
            }
        };
         if(handlerFlag){
             handler3.postDelayed(myRun3, loopObstecalSleepTime);
         }

    }

    @Override
    protected void onStop() {
        super.onStop();
        handlerFlag = false;
        accelerometer.unregister();
        gyroscope.unregister();
        Log.d("ggg", "on stop");
        sound.pause_game_sound();

    }

    @Override
    protected void onPause() {
        super.onPause();
        handlerFlag = false;
        handler1.removeCallbacks(myRun1);

        accelerometer.unregister();
        gyroscope.unregister();
        Log.d("ggg", "on pause");
        sound.pause_game_sound();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("ggg", "on back pressed");
        handler1.removeCallbacks(myRun1);
        handler2.removeCallbacks(myRun2);
        handler3.removeCallbacks(myRun3);
        sound.stop_game_sound();
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        handlerFlag = true;

        if(sensor_off){
            gyroscope.unregister();
        }else{
            gyroscope.register();
        }
        accelerometer.register();

        Log.d("ggg", "on resume");
        sound.resume_game_sound();
        if (resumeCount > 0){
            handler1.postDelayed(myRun1, gameSleepTime);
            for(int i = 0;  i < numOfRowsPOne; i++){
                for(int j = 0; j < numOfCols; j++){
                    if(arrImgObstacle[i][j].getVisibility() != View.GONE){
                        arrImgObstacle[i][j].setVisibility(View.INVISIBLE);
                    }

                }
            }
            loopCoin(rowCoin, colCoin);
            loopPoka(rowPoka, colPoka);
            loopCoin(rowCoin, colCoin);
            loopPoka(rowPoka, colPoka);

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

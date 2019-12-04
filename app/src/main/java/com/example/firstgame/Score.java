package com.example.firstgame;

import android.os.Handler;

public class Score {
    int score;
    static Runnable myRun;
    static Handler handler;

    public Score(int score) {
        this.score = score;
        handler = new Handler();
    }

    //start count score
    public void countScore(){


        myRun  =  new Runnable() {
            @Override
            public void run() {
                countScore();
                count();

            }
        };
        handler.postDelayed(myRun, 1000);
    }

    //calculation score
    private void count(){
        score += 100;
        MainActivity.main_TXV_score.setText("" + score);

    }

    //stop count score
    public void stopScore(){
        handler.removeCallbacks(myRun);
    }

    public Score setScore(int score) {
        this.score = score;
        return this;
    }

}

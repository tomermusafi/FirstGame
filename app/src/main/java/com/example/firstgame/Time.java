package com.example.firstgame;

import android.os.Handler;

public class Time {

    int second;
    int minute;
    static Runnable myRun;
    static Handler handler;

    public Time(int second, int minute) {
        this.second = second;
        this.minute = minute;
        handler = new Handler();
    }

    //start run time
    public void countTime(){


         myRun  =  new Runnable() {
                @Override
                public void run() {
                    countTime();
                    count();

                }
            };
        handler.postDelayed(myRun, 1000);
    }

    //time calculation
    private void count(){
        second++;

        if(second == 60){
            second = 0;
            minute++;
        }
        MainActivity.main_TXV_time.setText("" + minute + ":" + second);

    }

    //stop time
    public void stopTime(){
        handler.removeCallbacks(myRun);
    }


    public Time setSecond(int second) {
        this.second = second;
        return this;
    }

    public Time setMinute(int minute) {
        this.minute = minute;
        return this;
    }
}

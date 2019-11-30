package com.example.firstgame;

import android.os.Handler;

public class Time {

    int hundredthSecond = 0 ;
    int second = 0;
    int minute = 0;
    Runnable myRun;
    Handler handler;

    public Time(int hundredthSecond, int second, int minute) {
        this.hundredthSecond = hundredthSecond;
        this.second = second;
        this.minute = minute;
        handler = new Handler();
    }

    public void countTime(){


         myRun  =  new Runnable() {
                @Override
                public void run() {
                    countTime();
                    count();

                }
            };
        handler.postDelayed(myRun, 10);
    }

    private void count(){
        hundredthSecond ++;
        if(hundredthSecond == 100){
            hundredthSecond = 0;
            second++;
        }
        if(second == 60){
            second = 0;
            minute++;
        }
        MainActivity.main_TXV_time.setText("" + minute + ":" + second + ":" + hundredthSecond);
    }

    public void stopTime(){
        handler.removeCallbacks(myRun);
    }

    public Time setHundredthSecond(int hundredthSecond) {
        this.hundredthSecond = hundredthSecond;
        return this;
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

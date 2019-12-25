package com.example.firstgame;


public class Distance {

    int distance;

    public Distance(){

    }

    public Distance(int distance) {
        this.distance = distance;
    }

    //start run distance
    public void countDistance(){
        distance += 10;
        MainActivity.main_TXV_time.setText("" + distance);
    }


    public Distance setDistance(int distance) {
        this.distance = distance;
        return this;
    }

    public int getDistance() {
        return distance;
    }
}

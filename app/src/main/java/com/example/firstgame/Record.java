package com.example.firstgame;


public class Record {
    int score;
    String date;
    double [] location;
    String name;

    public Record(int score, String date, double [] location, String name) {
        this.score = score;
        this.date = date;
        this.location = location;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public double[] getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}

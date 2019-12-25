package com.example.firstgame;


public class Score {
    int score;

    public Score(){

    }
    public Score(int score) {
        this.score = score;
    }

    //start count score
    public void countScore(){
        score += 50;
        MainActivity.main_TXV_score.setText("" + score);
    }


    public Score setScore(int score) {
        this.score = score;
        return this;
    }

    public int getScore() {
        return score;
    }

    public void coinScore(){
        score += 100;
        MainActivity.main_TXV_score.setText("" + score);
    }
}

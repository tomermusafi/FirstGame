package com.example.firstgame;

import android.view.View;


public class Poka {

    int posCol;
    int col;

    public Poka(int i) {
        col = i;
        if(col == 0){
            posCol = MainActivity.pos_col0;
        }
        else if(col == 1){
            posCol = MainActivity.pos_col1;
        }
        else{
            posCol = MainActivity.pos_col2;
        }
    }


    public void fallImg(){


        if(posCol == 10){
            posCol = 0;
            //MainActivity.handler.removeCallbacks(myRun);
        }

        for (int i = 0; i < 9; i++){
            if(posCol==i){
                MainActivity.arrImgObstacle[i][col].setVisibility(View.VISIBLE);
                Pika.checkLife();

            }else {
                MainActivity.arrImgObstacle[i][col].setVisibility(View.INVISIBLE);
            }
        }
        posCol++;




    }


}

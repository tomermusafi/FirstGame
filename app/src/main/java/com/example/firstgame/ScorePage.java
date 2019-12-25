package com.example.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;



public class ScorePage extends AppCompatActivity implements View.OnClickListener {

    Button scorePage_BTN_menu;

    MySharedPreferences msp;
    Gson gson;

    TextView [] scoreArray;
    ImageView [] markerArray;
    Record [] rec;
    //int i;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_score_page);


        index = 0;
        markerArray = new ImageView[]{
                findViewById(R.id.scorePage_IMG_location0),
                findViewById(R.id.scorePage_IMG_location1),
                findViewById(R.id.scorePage_IMG_location2),
                findViewById(R.id.scorePage_IMG_location3),
                findViewById(R.id.scorePage_IMG_location4),
                findViewById(R.id.scorePage_IMG_location5),
                findViewById(R.id.scorePage_IMG_location6),
                findViewById(R.id.scorePage_IMG_location7),
                findViewById(R.id.scorePage_IMG_location8),
                findViewById(R.id.scorePage_IMG_location9),
        };

         scoreArray = new TextView[]{
                findViewById(R.id.scorePage_TXV_score0),
                findViewById(R.id.scorePage_TXV_score1),
                findViewById(R.id.scorePage_TXV_score2),
                findViewById(R.id.scorePage_TXV_score3),
                findViewById(R.id.scorePage_TXV_score4),
                findViewById(R.id.scorePage_TXV_score5),
                findViewById(R.id.scorePage_TXV_score6),
                findViewById(R.id.scorePage_TXV_score7),
                findViewById(R.id.scorePage_TXV_score8),
                findViewById(R.id.scorePage_TXV_score9),
                findViewById(R.id.scorePage_TXV_score0)
        };

        msp = new MySharedPreferences(this);
        gson = new Gson();
        scorePage_BTN_menu = findViewById(R.id.scorePage_BTN_menu);

        scorePage_BTN_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String records = msp.getString("myRecords6",null);
        if(records != null) {
            rec = gson.fromJson(records, Record[].class);
            for (int i = 0; i < rec.length; i++) {
                scoreArray[i].setText("" + rec[i].score + " \n " + rec[i].getDate());

                switch (i){
                    case 0:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(0);

                            }
                        });
                    }
                    break;
                    case 1:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(1);

                            }
                        });
                    }
                    break;
                    case 2:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(2);

                            }
                        });
                    }
                    break;
                    case 3:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(3);

                            }
                        });
                    }
                    break;
                    case 4:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(4);

                            }
                        });
                    }
                    break;
                    case 5:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(5);

                            }
                        });
                    }
                    break;
                    case 6:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(6);

                            }
                        });
                    }
                    break;
                    case 7:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(7);

                            }
                        });
                    }
                    break;
                    case 8:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(8);

                            }
                        });
                    }
                    break;
                    case 9:{
                        markerArray[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                insertLocation(9);

                            }
                        });
                    }
                    break;

                }

            }
            Log.d("ggg", records);


        }
    }

    private void insertLocation(int index) {
        Intent intent = new Intent(ScorePage.this, MapActivity.class);////////////////myLocation class
        intent.putExtra(MapActivity.LATITUDE, rec[index].getLocation()[0]);
        intent.putExtra(MapActivity.LONGITUDE, rec[index].getLocation()[1]);
        intent.putExtra(MapActivity.NAME, rec[index].getName());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}

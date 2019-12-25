package com.example.firstgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GameOverPage extends AppCompatActivity {

    final static String SCORE = "SCORE";
    final static String TIME = "TIME";

    Record record;

    int score;
    String time;

    //buttons
    Button gameOverPage_BTN_playAgain, gameOverPage_BTN_menu;

    //textViews
    TextView gameOverPage_TXV_time, gameOverPage_TXV_score;

    EditText gameOverPage_EDT_name;

    MySharedPreferences msp;
    Gson gson;

    List<Record> topRecordsArray = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_over_page);

        score = getIntent().getIntExtra(SCORE, 0);
        time = getIntent().getStringExtra(TIME);

        msp = new MySharedPreferences(this);
        gson = new Gson();

        gameOverPage_BTN_playAgain = findViewById(R.id.gameOverPage_BTN_playAgain);
        gameOverPage_BTN_menu = findViewById(R.id.gameOverPage_BTN_menu);
        gameOverPage_TXV_time = findViewById(R.id.gameOverPage_TXV_time);
        gameOverPage_TXV_score = findViewById(R.id.gameOverPage_TXV_score);
        gameOverPage_EDT_name = findViewById(R.id.gameOverPage_EDT_name);

        gameOverPage_BTN_playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGamePage();
            }
        });

        gameOverPage_BTN_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenuPage();
            }
        });



        gameOverPage_TXV_time.setText(time);
        gameOverPage_TXV_score.setText("" + score);






    }

    private void saveRecord(){
        // TODO: 12/24/2019
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());
        double [] location;
        if(OpenPage.getCurrentLocation() != null){
             location = new double[]{
                     OpenPage.getCurrentLocation().getLatitude()
                     , OpenPage.getCurrentLocation().getLongitude()};
        }else{
            location = new double[]{
                   0.0
                   , 0.0};
        }

        //0.005044891891895
        //- 0.00360892052834
        //Log.d("pttt", "loc: "+ location.getLongitude());
        String records = msp.getString("myRecords6", null);
        record = new Record(score, currentDate, location, gameOverPage_EDT_name.getText().toString());
        if(records == null){
            Log.d("lll", "rec1: "+ record);
            topRecordsArray.add(record);
            String json = gson.toJson(topRecordsArray);
            Log.d("oooo", json);
            msp.putString("myRecords6", json);
        }else {
            topRecordsArray = new ArrayList(Arrays.asList(gson.fromJson(records, Record[].class)));
            //record = new Record(score, currentDate, location);
            Log.d("lll", "rec: "+ record);
            topRecordsArray.add(record);
            Log.d("lll", "arr: "+ topRecordsArray);
            Collections.sort(topRecordsArray, new Comparator<Record>() {
                @Override
                public int compare(Record a, Record b) {
                    return Integer.compare(b.getScore(), a.getScore());
                }

            });
            if(topRecordsArray.size() > 10){
                for (int i = 0; i < topRecordsArray.size(); i++) {
                    if(i > 9){
                        topRecordsArray.remove(i);
                    }
                }
                String json = gson.toJson(topRecordsArray);
                msp.putString("myRecords6", json);
            }else{
                String json = gson.toJson(topRecordsArray);
                msp.putString("myRecords6", json);
            }
            Log.d("jjj", records);

        }
    }



    //go to the main page(the game)
    private void goToGamePage(){
        saveRecord();
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
        finish();

    }

    //go to the menu page(the first page)
    private void goToMenuPage(){
        saveRecord();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

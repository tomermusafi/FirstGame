package com.example.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class GameOverPage extends AppCompatActivity {

    Button gameOverPage_BTN_playAgain, gameOverPage_BTN_menu;
    TextView gameOverPage_TXV_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_over_page);

        gameOverPage_BTN_playAgain = findViewById(R.id.gameOverPage_BTN_playAgain);
        gameOverPage_BTN_menu = findViewById(R.id.gameOverPage_BTN_menu);
        gameOverPage_TXV_time = findViewById(R.id.gameOverPage_TXV_time);

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

        gameOverPage_TXV_time.setText(MainActivity.main_TXV_time.getText().toString());


    }

    private void goToGamePage(){
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
        finish();

    }

    private void goToMenuPage(){
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

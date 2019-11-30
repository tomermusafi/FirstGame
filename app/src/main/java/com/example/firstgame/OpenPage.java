package com.example.firstgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class OpenPage extends AppCompatActivity {

    Button open_BTN_play, open_BTN_records, open_BTN_quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_open_page);

        open_BTN_play = findViewById(R.id.open_BTN_play);
        open_BTN_records = findViewById(R.id.open_BTN_records);
        open_BTN_quit = findViewById(R.id.open_BTN_quit);

        open_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        open_BTN_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void startGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

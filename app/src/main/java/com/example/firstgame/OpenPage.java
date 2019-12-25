package com.example.firstgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class OpenPage extends AppCompatActivity {


    static Location currentLocation;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;

    //buttons
    Button open_BTN_play, open_BTN_records, open_BTN_quit, open_BTN_numOfLane, open_BTN_speed, open_BTN_sensor;

    static int numOfLanes = 3;
    static boolean click_flag = false, speed_flag = false, sensor_flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_open_page);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        

        mapFunc();

        Stetho.initializeWithDefaults(this);


        open_BTN_play = findViewById(R.id.open_BTN_play);
        open_BTN_records = findViewById(R.id.open_BTN_records);
        open_BTN_quit = findViewById(R.id.open_BTN_quit);
        open_BTN_numOfLane = findViewById(R.id.open_BTN_numOfLane);
        open_BTN_sensor = findViewById(R.id.open_BTN_sensor);
        open_BTN_speed = findViewById(R.id.open_BTN_speed);

        open_BTN_numOfLane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click_flag = !click_flag;
                if (click_flag){
                    numOfLanes = 5;
                    open_BTN_numOfLane.setText(""+numOfLanes);
                }
                else {
                    numOfLanes = 3;
                    open_BTN_numOfLane.setText(""+numOfLanes);
                }

            }
        });

        open_BTN_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speed_flag = !speed_flag;
                if(speed_flag){
                    open_BTN_speed.setText("slow");
                }else{
                    open_BTN_speed.setText("fast");
                }
            }
        });

        open_BTN_sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensor_flag = !sensor_flag;
                if(sensor_flag){
                    open_BTN_sensor.setText("sensor off");
                }else{
                    open_BTN_sensor.setText("sensor on");
                }
            }
        });

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

        open_BTN_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToScoresPage();
            }
        });
    }

    //go to the main page(the game)
    public void startGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToScoresPage(){
        Intent intent = new Intent(this, ScorePage.class);
        startActivity(intent);
    }

    private void mapFunc() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                new AlertDialog.Builder(this)
                        .setTitle("Request Location permission")
                        .setMessage("You have to give this permission to access this feature ")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(OpenPage.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object

                                currentLocation = location;
                                //Toast.makeText(getApplicationContext(), currentLocation.getLatitude()+", "+ currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                                Log.d("pppp",""+currentLocation.getLatitude()+", "+ currentLocation.getLongitude() );

                            }
                        }
                    });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //abc
            }else{

            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        mapFunc();
    }

    public static Location getCurrentLocation() {
        return currentLocation;
    }
}

package com.example.fistassigment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Activity_Main extends AppCompatActivity {
    private Button Main_BTN_Start;
    private Button Main_BTN_GoToTopTen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Main_BTN_Start = findViewById(R.id.Main_BTN_Start);
        Main_BTN_GoToTopTen = findViewById(R.id.Main_BTN_GoToTopTen);

        Main_BTN_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityPlay();
            }
        });

        Main_BTN_GoToTopTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTop10();
            }
        });

        // Check And require location permission
        CheckPremission();
    }

    // Check map permissions
    private void CheckPremission() {
        if (!(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED)) {
            Toast.makeText(this, R.string.error_permission_map, Toast.LENGTH_LONG).show();
        }
    }


    private void openActivityPlay() {
        Intent intent = new Intent(Activity_Main.this, Activity_Play.class);
        startActivity(intent);
    }

    private void openTop10() {
        Intent intent = new Intent(Activity_Main.this, Activity_TopTen.class);
        startActivity(intent);
    }
}

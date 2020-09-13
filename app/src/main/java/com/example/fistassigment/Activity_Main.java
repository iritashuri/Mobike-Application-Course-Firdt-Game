package com.example.fistassigment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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

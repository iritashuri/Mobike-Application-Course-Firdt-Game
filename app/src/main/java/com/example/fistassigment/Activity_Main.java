package com.example.fistassigment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Main extends AppCompatActivity {
    private Button START_BTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        START_BTN = findViewById(R.id.START_BTN);
        START_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityPlay();
            }
        });
    }


    private void openActivityPlay() {
        Intent intent = new Intent(Activity_Main.this, Activity_Play.class);
        //intent.putExtra(Activity_Settings.EXTRA_KEY_DATA, 5);
        startActivity(intent);
    }
}
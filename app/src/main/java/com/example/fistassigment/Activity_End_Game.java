package com.example.fistassigment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class Activity_End_Game extends AppCompatActivity {
    private MySPV mySPV;
    private TextView ENDGAME_TXT_CURRENT_WINNER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__end__game);
        Gson gson = new Gson();
        mySPV = new MySPV(this);
        ENDGAME_TXT_CURRENT_WINNER = findViewById(R.id.ENDGAME_TXT_CURRENT_WINNER);


        String json_winner = mySPV.getString(MySPV.KEYS.CURRENT_WINNER, "No Winner");
        Winners winner = gson.fromJson(json_winner, Winners.class);
        ENDGAME_TXT_CURRENT_WINNER.setText("" + winner.getName() + " is the winner with " + winner.getNumOfMoves() + " moves!");

    }
}
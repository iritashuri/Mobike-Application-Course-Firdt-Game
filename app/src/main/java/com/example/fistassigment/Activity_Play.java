package com.example.fistassigment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Activity_Play extends AppCompatActivity {
    private Button[] player1_buttons = new Button[3];
    private Button[] player2_buttons = new Button[3];;

    private ProgressBar player1_progressBar;
    private ProgressBar player2_progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity__play);

        // Define Variables
        player1_buttons[0] = findViewById(R.id.PLAY_BTN_palyer1_lazer);
        player2_buttons[0] = findViewById(R.id.PLAY_BTN_palyer2_lazer);
        player1_buttons[1] = findViewById(R.id.PLAY_BTN_palyer1_whipe);
        player2_buttons[1] = findViewById(R.id.PLAY_BTN_palyer2_whipe);
        player1_buttons[2] = findViewById(R.id.PLAY_BTN_palyer1_box);
        player2_buttons[2] = findViewById(R.id.PLAY_BTN_palyer2_box);

        player1_progressBar = findViewById(R.id.progressBar_player1);
        player2_progressBar = findViewById(R.id.progressBar_player2);

        // Set initial value to progresBar
        player1_progressBar.setProgress(200);
        player2_progressBar.setProgress(200);




        play(player1_buttons, player2_buttons, player1_progressBar, player2_progressBar);

    }

    private void play(Button[] player1_buttons, Button[] player2_buttons,final ProgressBar player1_progressBar, final ProgressBar player2_progressBar) {
        disableButtons(player2_buttons);
        player(player1_buttons, player2_progressBar, player2_buttons);
        player(player2_buttons, player1_progressBar, player1_buttons);
    }

    private void player(final Button[] player_buttons, final ProgressBar enemy_progressBar, final Button[] enemy_buttons) {
        player_buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enemy_progressBar.setProgress(enemy_progressBar.getProgress() - 50);
                switchTurn(player_buttons, enemy_buttons);
            }

        });
        player_buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enemy_progressBar.setProgress(enemy_progressBar.getProgress() -  40);
                switchTurn(player_buttons, enemy_buttons);
            }

        });
        player_buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enemy_progressBar.setProgress(enemy_progressBar.getProgress() -  30);
                switchTurn(player_buttons, enemy_buttons);
            }

        });
    }

    private void switchTurn(Button[] player_buttons,Button[] enemy_buttons){
        checkIfGameEnded();
        disableButtons(player_buttons);
        enableButtons(enemy_buttons);
    }

    private void checkIfGameEnded() {
        if(player1_progressBar.getProgress() <= 0 || player2_progressBar.getProgress() <= 0){
            String winner;
            if(player1_progressBar.getProgress() <= 0)
                winner = "Mickey Mouse";
            else
                winner = "Donald Duck";
            Toast toast = Toast.makeText(getApplicationContext(),
                    "End of the game. " + winner + " is the winner!!",
                    Toast.LENGTH_SHORT);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    private void disableButtons(Button[] buttons){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setEnabled(false);
        }
    }

    private void enableButtons(Button buttons[]){
        for(int i = 0; i < buttons.length; i++){
            buttons[i].setEnabled(true);
        }
    }
}
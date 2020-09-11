package com.example.fistassigment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

public class Activity_Play extends AppCompatActivity {
    private Button[] player1_buttons = new Button[3];
    private Button[] player2_buttons = new Button[3];;

    private ProgressBar player1_progressBar;
    private ProgressBar player2_progressBar;

    private int player = 0;
    private  boolean end= false;
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
        player1_progressBar.setProgress(100);
        player2_progressBar.setProgress(100);

        final Handler handler = new Handler();
        final int delay = 1000; //milliseconds

        handler.postDelayed(new Runnable(){
            public void run(){
                Log.d("pttt", "end = " + end);
                play(player1_buttons, player2_buttons, player1_progressBar, player2_progressBar);
                if(!end)
                    handler.postDelayed(this, delay);
            }
        }, delay);
    }

    private void play(final Button[] player1_buttons, final Button[] player2_buttons, final ProgressBar player1_progressBar, final ProgressBar player2_progressBar) {
        disableButtons(player2_buttons);

        Log.d("pttt", "player = " + player);
        if(!end) {
            if (player%2 == 0) {
                player_turn(player1_buttons, player2_progressBar, player2_buttons);
            } else{
                player_turn(player2_buttons, player1_progressBar, player1_buttons);
            }
            player++;
        }
    }

    private void player_turn(final Button[] player_buttons, final ProgressBar enemy_progressBar, final Button[] enemy_buttons) {
          int random = new Random().nextInt(3);
            switch (random) {
                case 0:
                    //player_buttons[0].setBackgroundColor(Color.parseColor("#D9E3F3"));
                    player_buttons[0].performClick();
                    enemy_progressBar.setProgress(enemy_progressBar.getProgress() - 25);
                    switchTurn(player_buttons, enemy_buttons);
                    //player_buttons[0].setBackgroundColor(Color.parseColor("#3CDDCE"));
                    Log.d("pttt", "clicked " + random);
                    break;
                case 1:
                    //player_buttons[1].setBackgroundColor(Color.parseColor("#D9E3F3"));
                    player_buttons[1].performClick();
                    Log.d("pttt", "clicked " + random);
                    enemy_progressBar.setProgress(enemy_progressBar.getProgress() - 20);
                    switchTurn(player_buttons, enemy_buttons);
                    //player_buttons[1].setBackgroundColor(Color.parseColor("#3CDDCE"));
                    break;
                case 2:
                    //player_buttons[2].setBackgroundColor(Color.parseColor("#D9E3F3"));
                    player_buttons[2].performClick();
                    Log.d("pttt", "clicked " + random);
                    enemy_progressBar.setProgress(enemy_progressBar.getProgress() - 15);
                    switchTurn(player_buttons, enemy_buttons);
                    //player_buttons[2].setBackgroundColor(Color.parseColor("#3CDDCE"));
                    break;
                default:
                    end = true;
            }
    }

    private void switchTurn(Button[] player_buttons,Button[] enemy_buttons){
      checkIfGameEnded();
      disableButtons(player_buttons);
      enableButtons(enemy_buttons);
    }

    private void checkIfGameEnded() {
        if(player1_progressBar.getProgress() <= 0 || player2_progressBar.getProgress() <= 0){
            end = true;
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
            openTopTen();
            finish();
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

    private void openTopTen() {
        Intent intent = new Intent(Activity_Play.this, Activity_TopTen.class);
        startActivity(intent);
    }
}
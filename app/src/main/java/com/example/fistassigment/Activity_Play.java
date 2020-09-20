package com.example.fistassigment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class Activity_Play extends AppCompatActivity implements LocationListener {


    private MySPV mySPV;
    Gson gson = new Gson();
    final Handler handler = new Handler();
    MediaPlayer sound;

    // Location
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double latitude, longitude;
    private Location location;

    private Button[] player1_buttons = new Button[3];
    private Button[] player2_buttons = new Button[3];

    private ProgressBar player1_progressBar;
    private ProgressBar player2_progressBar;

    // if Player is even - player1 turn, else player2 turn
    private int player;
    private boolean end = false;

    // Set number of moves for each player - start with 1
    private int player1_moves_counter = 1;
    private int player2_moves_counter = 1;


    // Define Dice parameters
    private ImageView Play_IMGBTN_Dice;
    private TextView Play_TXT_PlayerTurn;
    private Random rand = new Random();

    // Define dice scores for each player  and initial it with 1
    int player1_dice_score = 1;
    int player2_dice_score = 1;

    // Boolean variable that define if we can start the game - initial with false
    boolean is_game_can_start = false;

    // Define Top10 array list to be loaded from SP
    private ArrayList<Winners> tops;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity__play);

        // Set SP
        mySPV = new MySPV(this);

        //


        // Set player
        player = 0;

        // Set Who is  rolling the dice
        Play_TXT_PlayerTurn = findViewById(R.id.Play_TXT_PlayerTurn);
        Play_TXT_PlayerTurn.setText("Donald Duck");

        // Set location
        setLocation();

        // Load top10ArrayList from SP
        loadTopTenArrayList();

        // Set Variables as attack buttons and progress bars
        SetPlayersVars();

        // Set dice
        setDice();


    }

    private void SetPlayersVars() {
        player1_buttons[0] = findViewById(R.id.PLAY_BTN_palyer1_lazer);
        player2_buttons[0] = findViewById(R.id.PLAY_BTN_palyer2_lazer);
        player1_buttons[1] = findViewById(R.id.PLAY_BTN_palyer1_whipe);
        player2_buttons[1] = findViewById(R.id.PLAY_BTN_palyer2_whipe);
        player1_buttons[2] = findViewById(R.id.PLAY_BTN_palyer1_box);
        player2_buttons[2] = findViewById(R.id.PLAY_BTN_palyer2_box);

        player1_progressBar = findViewById(R.id.progressBar_player1);
        player2_progressBar = findViewById(R.id.progressBar_player2);

        // Set initial value to progress bar
        player1_progressBar.setProgress(100);
        player2_progressBar.setProgress(100);
    }

    // Set up dice and call rollDice function
    private void setDice() {
        Play_IMGBTN_Dice = findViewById(R.id.Play_IMGBTN_Dice);
        Play_IMGBTN_Dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!is_game_can_start)
                    rollDice();
                else {
                    Play_TXT_PlayerTurn.setText("");
                    view.setVisibility(View.GONE);
                    play();
                }
            }
        });
    }

    private void setLocation() {
        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);

        // Check map permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, R.string.error_permission_map, Toast.LENGTH_LONG).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    // Roll dice with random number and act according the number
    private void rollDice() {
        int randomNumber = rand.nextInt(6) + 1;
        switch (randomNumber){
            // Show image of the dice number and set payer dice score with the randomNumber
            case 1:
                Play_IMGBTN_Dice.setImageResource(R.drawable.dice1);
                setPlayerScore(1);
                break;
            case 2:
                Play_IMGBTN_Dice.setImageResource(R.drawable.dice2);
                setPlayerScore(2);
                break;
            case 3:
                Play_IMGBTN_Dice.setImageResource(R.drawable.dice3);
                setPlayerScore(3);
                break;
            case 4:
                Play_IMGBTN_Dice.setImageResource(R.drawable.dice4);
                setPlayerScore(4);
                break;
            case 5:
                Play_IMGBTN_Dice.setImageResource(R.drawable.dice5);
                setPlayerScore(5);
                break;
            case 6:
                Play_IMGBTN_Dice.setImageResource(R.drawable.dice6);
                setPlayerScore(6);
                break;
        }
    }

    // Set player dice score
    private void setPlayerScore(int score){
        // If its first player (player%2 == 0) then update player variable to fit second player and show second player name
        if(player == 0){
            player1_dice_score = score;
            player++;
            // Set second player in the text view, wait 1 scond
            handler.postDelayed(new Runnable() {
                public void run() {
                    Play_TXT_PlayerTurn.setText("Mickey Mouse");
                }
            }, 500);   //0.5 seconds

        }
        else{
            // If its second player compare between the players score and start new game (if no tie in dice score)
            player2_dice_score = score;
            FindWhoStartAndSetPlayr();
        }
    }
    private void FindWhoStartAndSetPlayr() {
        // If no tie, the player with the best dice score start
        if(player1_dice_score > player2_dice_score){
            setFirstPlayer(0, "Donald Duck");
        }
        else if(player2_dice_score > player1_dice_score){
            setFirstPlayer(1, "Mickey Mouse");
        }
        // If tie roll dice again
        else{
            player = 0;
            Play_TXT_PlayerTurn.setText("Tie, Try again");
            handler.postDelayed(new Runnable() {
                public void run() {
                    Play_TXT_PlayerTurn.setText("Donald Duck");
                    Play_IMGBTN_Dice.setImageResource(R.drawable.startdice);
                }
            }, 1000);   //1 seconds
        }
    }

    // Set first player
    private void setFirstPlayer(int start_player, String player_name){
        player = start_player;
        final String player_name_f = player_name;

        // Set first player and display 'start' button
        handler.postDelayed(new Runnable() {
            public void run() {
                Play_TXT_PlayerTurn.setText(player_name_f + " Start\n Press here to play");
                Play_IMGBTN_Dice.setImageResource(R.drawable.video);
            }
        }, 1000);   //1 seconds

        // Set is_game_can_start to be true in order to start the game
        is_game_can_start = true;
    }


    // Start the game
    private void play() {
        // Disable buttons of the player who play second
        if(player%2 == 0)
            disableButtons(player2_buttons);
        else
            disableButtons(player1_buttons);
        // Set delay to 1 scond - every 1 second 1 player move once
        final int delay = 1000; //milliseconds
        handler.postDelayed(new Runnable(){
            public  void run(){
                // If its not the end of the game play 1 move with current player
                // (if player % 2 == 0) it first player, els its second player
                if(!end) {
                    if (player % 2 == 0) {
                        // Update number of moves for first player
                        player1_moves_counter++;
                        player_turn(player1_buttons, player2_progressBar, player2_buttons);
                    } else {
                        // Update number of moves for second player
                        player2_moves_counter++;
                        player_turn(player2_buttons, player1_progressBar, player1_buttons);
                    }
                    // Update player turn
                    player++;
                }
                // If its not the end of the game repeat this function again every second
                if(!end)
                    handler.postDelayed(this, delay);
            }
        }, delay);
    }

    // Play 1 turn
    private void player_turn(final Button[] player_buttons, final ProgressBar enemy_progressBar, final Button[] enemy_buttons) {
        //Clean sound buffer
        if(sound != null)
            sound.reset();
        // Act according to a random number between 0 to 2
        int random = rand.nextInt(3);
        switch (random) {
                // Case 0 - lazer, case 1 - whip, case 3 - box)
                case 0:
                    sound = MediaPlayer.create(this, R.raw.laser);
                    clickBtn(player_buttons[0], enemy_progressBar, player_buttons, enemy_buttons, 25);
                    break;
                case 1:
                    sound = MediaPlayer.create(this, R.raw.whip);
                    clickBtn(player_buttons[1], enemy_progressBar, player_buttons, enemy_buttons, 20);
                    break;
                case 2:
                    sound = MediaPlayer.create(this, R.raw.punch);
                    clickBtn(player_buttons[2], enemy_progressBar, player_buttons, enemy_buttons, 10);
                    break;
                default:
                    end = true;
            }
    }

    private void clickBtn(Button button, ProgressBar enemy_progressBar, Button[] player_buttons, Button[] enemy_buttons, int increase) {
        sound.start();
        // Illustrate button clicking
        changeColor(button);
        // Increase enemy progress bar according the clicked button
        enemy_progressBar.setProgress(enemy_progressBar.getProgress() - increase);
        // Switch players turns
        switchTurn(player_buttons, enemy_buttons);
    }

    // Change button color for a second (to illustrate clicking)
    private void changeColor(final Button btn) {
        new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {
                btn.setBackgroundColor(Color.parseColor("#D9E3F3"));
            }
            public void onFinish() {
                btn.setBackgroundColor(Color.parseColor("#3CDDCE"));
            }
        }.start();
    }

    private void switchTurn(final Button[] player_buttons, Button[] enemy_buttons){
      // check if the game ended
      checkIfGameEnded();

      // If not disable buttons of current player and enable next player buttons
      disableButtons(player_buttons);
        handler.postDelayed(new Runnable() {
            public void run() {
                disableButtons(player_buttons);
            }
        }, 500);   //1 seconds
      enableButtons(enemy_buttons);
    }

    private void checkIfGameEnded() {
        // If one off the progress bars is empty the game ended
        if(player1_progressBar.getProgress() <= 0 || player2_progressBar.getProgress() <= 0){
            // set end variable  as true
            end = true;
            // Define winner obj
            Winners winner = new Winners();
            // Set winner
            if(player1_progressBar.getProgress() <= 0)
                setWinner("Mickey Mouse", player2_moves_counter, winner);
            else
                setWinner("Donald Duck", player1_moves_counter, winner);
            setWinnerOnSP(winner);

            // Check if winner needs to be inserted to to10 and if yes, insert him.
            checkAndAddWinnerToScoresArray(winner);

            // Save tops list with sp in a json format
            String json = gson.toJson(tops);
            mySPV.putString(MySPV.KEYS.TOP_TEN, json);

            // End the Game and open Activity_End_Game to see the winner
            openEndGame();
            finish();
        }
    }

    // Set winner in current sp in order to display it in Activity_End_Game page
    private void setWinnerOnSP(Winners winner) {
        String json = gson.toJson(winner);
        mySPV.putString(MySPV.KEYS.CURRENT_WINNER, json);
    }

    private void checkAndAddWinnerToScoresArray(Winners winner) {
        boolean is_inserted  = false;
        // Go over each winner in the winners array list and insert current one in the correct place
            for (Winners t : tops) {
                if (winner.getNumOfMoves() <= t.getNumOfMoves()) {
                    tops.add(tops.indexOf(t), winner);
                    is_inserted = true;
                    break;
                }
            }
            //If list size > 0 and winner didn't insert to the array list, add it in the end of the list
            if(tops.size() < 10 && !is_inserted) {
                tops.add(winner);
            }
    }

    // Set winner name, number of moves and player number.
    private void setWinner(String name, int player1_moves_counter, Winners winner) {
        winner.setName(name);
        winner.setNumOfMoves(player1_moves_counter);
        winner.setPlayer_number(player);

        // Set Location
        winner.setLat(latitude);
        winner.setLon(longitude);
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

    private void openEndGame() {
        Intent intent = new Intent(Activity_Play.this, Activity_End_Game.class);
        startActivity(intent);
    }

    // Load top10 array list from sp and convert json format to array list
    private void loadTopTenArrayList(){
        String json = mySPV.getString(MySPV.KEYS.TOP_TEN, null);
        Type type = new TypeToken<ArrayList<Winners>>() {}.getType();
        tops = gson.fromJson(json, type);

        if (tops == null){
            tops = new ArrayList<>();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }
}
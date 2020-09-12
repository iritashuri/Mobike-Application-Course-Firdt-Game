package com.example.fistassigment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Activity_Json extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_json);


        ArrayList<Winners> scores = new ArrayList<>();
        scores.add(new Winners(32, 24, 1523423323, 5, ""));
        scores.add(new Winners(33, -34, 153243323, 22, ""));
        scores.add(new Winners(-31, -334, 44544545 , 52, ""));
        scores.add(new Winners(-334, 34, 134544646, 15, ""));
        scores.add(new Winners(1, 874, 134544646, 25, ""));
        scores.add(new Winners(22, 74, 134544646, 5, ""));
        scores.add(new Winners(42, 84, 134544646, 5, ""));


        Gson gson = new Gson();

        //String json = gson.toJson(superman);


        //String afterJson = MySP.getStringFromSP(this, "SUPERMAN", "");

        //SuperHero afterHero = gson.fromJson(afterJson, SuperHero.class);

        //Log.d("pttt", "Name: " + afterHero.getName());

    }
}
package com.example.fistassigment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Activity_TopTen extends AppCompatActivity implements CallBack_TopTen{

    private MySPV mySPV;
    Gson gson = new Gson();
    final Handler handler = new Handler();

    //Define fragments
    private Fragment_List fragment_list;
    private Fragment_List fragment_map;

    //Buttons


    // Define Top10 array list to be loaded from SP
    private ArrayList<Winners> tops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__top_ten);

        // Set SP
        mySPV = new MySPV(this);

        findViews();
        initFragments();
        // Set fragment_list
    }


    @Override
    protected void onStart() {
        super.onStart();
        fragment_list.setTable(tops);
        Log.d("pttt", "size of top " + tops.size());
        for(int i = 0; i < tops.size(); i++){
            Log.d("pttt", tops.get(i).getName());
        }
    }

    private void findViews() {
        //main_LBL_title = findViewById(R.id.main_LBL_title);
    }


    private void initFragments() {
        fragment_list = Fragment_List.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.TOPTEN_TBL_TopTen,fragment_list);
        transaction.commit();

        fragment_list.setListCallBack(this);

        //Init map
    }



    // Load top10 array list from sp and convert json format to array list
    @Override
    public void GetTopsFromSP() {
        String json = mySPV.getString(MySPV.KEYS.TOP_TEN, null);
        Type type = new TypeToken<ArrayList<Winners>>() {}.getType();
        tops = gson.fromJson(json, type);

        if (tops == null){
            tops = new ArrayList<>();
        }
    }
}
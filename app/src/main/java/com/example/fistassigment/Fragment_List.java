package com.example.fistassigment;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class Fragment_List extends Fragment {


    protected View view;
    private TableLayout List_TBL_TopTen;

    ArrayList <Winners> tops;

    private CallBack_TopTen callBack_topTen;

    public void setListCallBack(CallBack_TopTen callBack_topTen) {
        this.callBack_topTen = callBack_topTen;
    }

    public static Fragment_List newInstance() {

        Fragment_List fragment = new Fragment_List();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view==null){
            view = inflater.inflate(R.layout.fragment_list, container, false);
        }

        findViews(view);

        if (callBack_topTen != null) {
            callBack_topTen.GetTopsFromSP();
        }

        return view;
    }

    private void findViews(View view) {
        List_TBL_TopTen = view.findViewById(R.id.List_TBL_TopTen);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    protected void setTable(ArrayList<Winners> tops){
        int counter = 1;
        for(Winners current: tops) {
            if(counter <= 10) {
                setRowWithWinner(current.getName(), current.getNumOfMoves(), current.getTimestamp(), counter);
                //increase counter
                counter++;
            }
            else return;
        }
    }

    private void setRowWithWinner(String winner_name, int numberOfMoves, long timestamp, int counter) {
        // Define new row
        TableRow row = new TableRow(getActivity());

        // Define columns
        TextView place = new TextView(getActivity().getApplication());
        TextView name = new TextView(getActivity().getApplication());
        TextView numOfMoves = new TextView(getActivity().getApplication());
        TextView time = new TextView(getActivity().getApplication());

        // Set columns text texts
        place.setText("" + counter);
        name.setText(winner_name + "        ");
        numOfMoves.setText("" + numberOfMoves);
        time.setText("" + timestamp);

        //Style
        place.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
        name.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
        numOfMoves.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
        time.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

        //Gravity
        place.setGravity(1);
        name.setGravity(1);
        numOfMoves.setGravity(1);
        time.setGravity(1);

        // Add columns to new row
        row.addView(place);
        row.addView(name);
        row.addView(numOfMoves);
        row.addView(time);

        // Add new row to table
        List_TBL_TopTen.addView(row);
    }

    private void setCell(String str, TableRow row ){
        // Define columns
        TextView place = new TextView(getActivity().getApplication());
        // Set columns text texts
        place.setText(str);
        //Style
        place.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
        place.setGravity(1);
        row.addView(place);

    }

    private void setTextView(TextView txt, String s) {
        txt.setText(s);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
        }
    }
}

package com.example.formula1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Schedule extends Fragment {
    private int year = 2021;
    private String race = "Search Race";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_schedule);

        ListView schedule = (ListView)findViewById(R.id.race_schedule);

        List<Race> races = allRaces();

        MyAdapter adapter = new MyAdapter(races,this);
        schedule.setAdapter(adapter);
        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule,container,false);
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_schedule);

        ListView schedule = (ListView)view.findViewById(R.id.race_schedule);

        List<Race> races = new Championship(this.year).getRaces();

        MyAdapter adapter = new MyAdapter(races,getActivity());

        schedule.setAdapter(adapter);

        EditText search_race = (EditText) view.findViewById(R.id.search_race);
        search_race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                race = search_race.getText().toString();
                search_race.setText(race);
                System.out.println(race);
            }

        });

        EditText search_year = (EditText) view.findViewById(R.id.search_year);
        search_race.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = Integer.parseInt(search_year.getText().toString());
                search_race.setText(year);
                System.out.println(year);
            }

        });


        return view;
    }


}

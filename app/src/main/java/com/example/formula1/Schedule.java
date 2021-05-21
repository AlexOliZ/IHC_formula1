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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Schedule extends Fragment {

    private List<Race> allRaces(){
        return new ArrayList<>(Arrays.asList(new Race("Portimão","Portugal","First Race in Portugal",1,1,2021,14,10),
                new Race("Monaco","Monaco","The Classic Race of Monaco!",1,2,2021,14,12),
                new Race("Monza","Italy","RIP Ferrari",1,1,2021,14,9),
                new Race("Imola","Italy","First race Since 1988",1,3,2021,14,12),
                new Race("Spa","Belgium","One of the best races in the calendar",1,4,2021,14,10)));
    }

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

        List<Race> races = allRaces();

        MyAdapter adapter = new MyAdapter(races,getActivity());
        schedule.setAdapter(adapter);
        return view;
    }


}

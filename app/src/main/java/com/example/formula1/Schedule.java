package com.example.formula1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class Schedule extends Fragment {
    private int year = 2021;
    private String race = "Search Race";
    private List<Race> races;
    private List<Championship> championships = allChampionships();
    private SearchView search_race;
    private SearchView search_year;
    private ListView schedule;
    private View view;
    private MyAdapter adapter;
    private Context context;

    private List<Championship> allChampionships() {
        return new ArrayList<>(Arrays.asList(
                new Championship(2018),
                new Championship(2019),
                new Championship(2020),
                new Championship(2021),
                new Championship(2022),
                new Championship(2023),
                new Championship(2024),
                new Championship(2025)
        ));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_schedule,container,false);
        schedule = (ListView)view.findViewById(R.id.race_schedule);

        setUpToolbar(schedule);



        //search_race = (SearchView) view.findViewById(R.id.search_race);

        for(Championship championship: championships){
            if(championship.getYear() == 2021){
                races = championship.getRaces();
            }
        }

        MyAdapter adapter = new MyAdapter(races,getActivity());
        schedule.setAdapter(adapter);

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.search_menu);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.toolbar_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //super.onCreateOptionsMenu(menu, inflater);


        MenuItem search_race_item = menu.findItem(R.id.search_race);
        MenuItem search_year_item = menu.findItem(R.id.search_year);
        MenuItem notifications_item = menu.findItem(R.id.notifications);

        search_race_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        search_year_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        notifications_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);


        search_race = (SearchView) search_race_item.getActionView();
        search_race.setMaxWidth(Integer.MAX_VALUE);
        search_race.setQueryHint("Search");
        search_race.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Race> filter_race = new ArrayList<Race>();

                for(Race race: races){
                    if(race.getName().toLowerCase().contains(query.toLowerCase())){
                        filter_race.add(race);
                    }
                }

                MyAdapter adapter = new MyAdapter(races,getActivity());
                schedule.setAdapter(adapter);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Race> filter_race = new ArrayList<Race>();

                for(Race race: races){
                    if(race.getName().toLowerCase().contains(newText.toLowerCase())){
                        filter_race.add(race);
                    }
                }

                MyAdapter adapter = new MyAdapter(races,getActivity());
                schedule.setAdapter(adapter);

                return false;
            }
        });

        search_year = (SearchView) search_race_item.getActionView();
        search_year.setQueryHint("Search");
        search_year.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Championship> filter_championship = new ArrayList<Championship>();

                for(Championship championship: championships){
                    if(championship.getYear() == Integer.parseInt(newText)){
                        filter_championship.add(championship);
                    }
                }

                return false;
            }
        });


    }
}

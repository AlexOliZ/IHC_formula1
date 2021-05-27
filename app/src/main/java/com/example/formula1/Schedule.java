package com.example.formula1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Schedule extends Fragment {
    private static int year = 2021;
    private String race = "Search Race";
    private List<Race> races;
    private static List<Championship> championships = allChampionships();
    private SearchView search_race;
    private Button search_year;
    private ListView schedule;
    private View view;
    private Calendar cal;
    private MyAdapter adapter;
    private Context context;

    private static List<Championship> allChampionships() {
        return new ArrayList<>(Arrays.asList(
                new Championship(2005),
                new Championship(2006),
                new Championship(2007),
                new Championship(2008),
                new Championship(2009),
                new Championship(2010),
                new Championship(2011),
                new Championship(2012),
                new Championship(2013),
                new Championship(2014),
                new Championship(2015),
                new Championship(2016),
                new Championship(2017),
                new Championship(2018),
                new Championship(2019),
                new Championship(2020),
                new Championship(2021)
        ));
    }

    public static void selectYear(int value){
        year = value;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        setHasOptionsMenu(true);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_schedule,container,false);
        //schedule = (ListView)view.findViewById(R.id.race_schedule);

        setUpToolbar(view);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.race_schedule);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        //search_race = (SearchView) view.findViewById(R.id.search_race);

        for(Championship championship: championships){
            if(championship.getYear() == year){
                races = championship.getRaces();
            }
        }
        int position=0;
        if(year == cal.get(Calendar.YEAR)) {
            for (int i = 0; i < races.size(); i++) {
                if (races.get(i).getMonth() == cal.get(Calendar.MONTH) + 1 && races.get(i).getDay() >= cal.get(Calendar.DAY_OF_MONTH)) {
                    position = i;
                    break;
                } else if (races.get(i).getMonth() >= cal.get(Calendar.MONTH) + 1 || races.get(i).getDay() >= cal.get(Calendar.DAY_OF_MONTH)) {
                    position = i;
                    break;
                }
            }
        }
        /* year -> pop up textview e quando escolher o ano cria um novo adapter com as corridas desse ano*/
        adapter = new MyAdapter(getActivity(),context, (ArrayList<Race>) races);
        recyclerView.setAdapter(adapter);
        System.out.println(position);
        recyclerView.smoothScrollToPosition(position);
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

        search_race_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        search_year_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        notifications_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        search_race = (SearchView) search_race_item.getActionView();
        search_race.setMaxWidth(Integer.MAX_VALUE);
        search_race.setQueryHint("Search Race");

        search_race.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        ImageButton race_notifications = (ImageButton) notifications_item.getActionView();
        race_notifications.setBackgroundResource(R.drawable.toolbar_notifications_disabled);
        /* em vez de abrir as settings abre uma cena a listar as notificações com um botão para as settings ?*/

        race_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new Settings());
                fragmentTransaction.commit();
            }
        });

        Button search_year = (Button) search_year_item.getActionView();
        search_year.setBackgroundColor(Color.parseColor("#DD1515"));
        search_year.setText(Integer.toString(year));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        search_year.setLayoutParams(params);
        search_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new select_year());
                fragmentTransaction.commit();
            }
        });
    }
}

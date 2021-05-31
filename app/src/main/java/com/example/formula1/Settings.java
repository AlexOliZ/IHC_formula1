package com.example.formula1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Settings extends Fragment {
    private View view;
    private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getActivity();
        Variables.month = Variables.cal.get(Calendar.MONTH);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_settings,container,false);


        EditText race_notification = (EditText) view.findViewById(R.id.add_race_notification);
        /*limpar texto quando clicar*/

        EditText championship_notification = (EditText) view.findViewById(R.id.add_championship_notification);
        /*limpar texto quando clicar*/

        ImageButton add_race_notification = (ImageButton) view.findViewById(R.id.add_race_button_notification);

        ImageButton add_championship_notification = (ImageButton) view.findViewById(R.id.add_championship_button_notification);
        /* set year */

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.notifications_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<Race> races = new ArrayList<>();
        for(Race race: Variables.championships.get(Variables.championships.size()-1).getRaces()){
            if(race.getNotify()){
                races.add(race);
                System.out.println(race.getName());
            }
        }

        Settings_Adapter notifcations_adapter = new Settings_Adapter(getActivity(),context,races);
        recyclerView.setAdapter(notifcations_adapter);

        return view;
    }

}
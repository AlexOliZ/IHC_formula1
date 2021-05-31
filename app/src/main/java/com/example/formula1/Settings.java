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
import android.widget.CheckBox;
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

        ImageButton add_race_notification = (ImageButton) view.findViewById(R.id.add_race_button_notification);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.notifications_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<Race> races = new ArrayList<>();
        for(Race race: Variables.championships.get(Variables.championships.size()-1).getRaces()){
            if(race.getNotify() && race.check_Notify()){
                races.add(race);
                System.out.println(race.getName());
            }
        }

        Settings_Adapter notifcations_adapter = new Settings_Adapter(getActivity(),context,races);
        recyclerView.setAdapter(notifcations_adapter);

        CheckBox notify_all = (CheckBox) view.findViewById(R.id.box_all);
        notify_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0 ; i<Variables.championships.get(Variables.championships.size()-1).getRaces().size() ; i++){
                    if(!Variables.championships.get(Variables.championships.size()-1).getRaces().get(i).getNotify()){
                        Variables.championships.get(Variables.championships.size() - 1).getRaces().get(i).Notify();
                    }
                }
                Variables.notify_all = !Variables.notify_all;
                notifcations_adapter.notifyDataSetChanged();
            }
        });

        notify_all.setChecked(Variables.notify_all);

        notifcations_adapter.notifyDataSetChanged();
        EditText race_notification = (EditText) view.findViewById(R.id.add_race_notification);
        /*limpar texto quando clicar*/
        ImageButton race_notification_button = (ImageButton) view.findViewById(R.id.add_race_button_notification);
        race_notification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                System.out.println(race_notification.getText());
                for(int i=0 ; i<Variables.championships.get(Variables.championships.size()-1).getRaces().size() ; i++){
                    if(Variables.championships.get(Variables.championships.size()-1).getRaces().get(i).getName().toLowerCase().equals(race_notification.getText().toString().trim().toLowerCase())){
                        System.out.println(Variables.championships.get(Variables.championships.size() - 1).getRaces().get(i).getNotify());
                        if(!Variables.championships.get(Variables.championships.size() - 1).getRaces().get(i).getNotify())
                            Variables.championships.get(Variables.championships.size() - 1).getRaces().get(i).Notify();
                        notifcations_adapter.notifyDataSetChanged();
                        System.out.println(Variables.championships.get(Variables.championships.size() - 1).getRaces().get(i).getNotify());
                    }
                }
            }
        });


        return view;
    }

}
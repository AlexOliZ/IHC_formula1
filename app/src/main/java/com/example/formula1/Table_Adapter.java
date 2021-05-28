package com.example.formula1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;


public class Table_Adapter extends RecyclerView.Adapter<Table_Adapter.MyViewHolder>{
    private Race race;
    private final Context context;
    private FragmentActivity activity;
    /* if true notifca todas as corridas */
    /* notification settings notify_races */
    private boolean notification = false;

    public Table_Adapter(FragmentActivity activity , Context context,Race race){
        this.race = race;

        this.context = context;
        this.activity = activity;
    }

    public void notify_races(){this.notification= !this.notification;}

    @NonNull
    @Override
    public Table_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.race_table,parent,false);
        return new MyViewHolder(view);
    }

    public Table_Adapter getAdapter(){return this;}

    @Override
    public void onBindViewHolder(@NonNull Table_Adapter.MyViewHolder holder, int position) {
        String name = Variables.names.get(position);
        String p = Variables.positions.get(position);
        String team = Variables.teams.get(position);

        holder.pilot_name.setText(name);
        holder.pilot_position.setText(p);
        holder.team_name.setText(team);

        holder.pilot_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new Teams());
                fragmentTransaction.commit();
            }
        });

        holder.team_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new Teams());
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public long getItemId(int position) {
        Table_Adapter adapter = getAdapter();
        return (adapter == null || position < 0) ? AdapterView.INVALID_ROW_ID : adapter.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return Variables.names.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView pilot_name;
        TextView team_name;
        TextView pilot_position;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            pilot_name = itemView.findViewById(R.id.pilot_name);
            team_name = itemView.findViewById(R.id.pilot_team);
            pilot_position = itemView.findViewById(R.id.pilot_position);
        }
    }
}
package com.example.formula1;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    private List<Race> filter_races;
    private final Context context;
    private FragmentActivity activity;
    /* if true notifca todas as corridas */
    /* notification settings notify_races */
    private boolean notification = false;

    public MyAdapter(FragmentActivity activity , Context context){
        this.filter_races = new ArrayList<>(Variables.selected_champ.getRaces());
        this.context = context;
        this.activity = activity;
    }

    public void notify_races(){this.notification= !this.notification;}

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.race_row,parent,false);

        return new MyViewHolder(view);
    }

    public MyAdapter getAdapter(){return this;}

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Race race = filter_races.get(position);
        holder.race_name.setText(race.getName());
        holder.race_date.setText(race.getDate() + " " + Integer.toString(race.getHours()) + "h");
        holder.race_image.setImageResource(race.getImage());
        System.out.println("year: "+Integer.toString(Variables.year));
        System.out.println("month: "+Integer.toString(Variables.month));
        System.out.println("day: "+Integer.toString(Variables.day));
        System.out.println("hour: "+Integer.toString(Variables.hour));
        holder.race_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                Variables.selected_race = race;
                fragmentTransaction.replace(R.id.fragmentContainerView, new Race_Info());
                fragmentTransaction.commit();
            }
        });

        holder.race_notify.setChecked(race.getNotify());

        holder.race_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Do something here.
                for(Championship c: Variables.championships) {
                    if(c.getYear()==filter_races.get(position).getYear()) {
                        for (Race r : c.getRaces()) {
                            if (r.getDay() == filter_races.get(position).getDay() && r.getMonth() == filter_races.get(position).getMonth()) {
                                r.Notify();
                                if(r.getNotify() == true){
                                    Toast.makeText(context, "NOTIFICATION ADDED WITH SUCCESS!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(context, "NOTIFICATION REMOVED WITH SUCCESS!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }
            }
        });
        if(!race.check_Notify())
            holder.race_notify.setVisibility(View.INVISIBLE);
        else
            holder.race_notify.setChecked(race.getNotify() || Variables.notify_all);
    }

    @Override
    public long getItemId(int position) {
        //return position;
        //return races.get(position).getId();
        //return (races.get(position).getName().hashCode());
        MyAdapter adapter = getAdapter();
        return (adapter == null || position < 0) ? AdapterView.INVALID_ROW_ID : adapter.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return filter_races.size();
    }

    @Override
    public Filter getFilter() {
        return raceFilter;
    }

    private final Filter raceFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Race> filterd_list = new ArrayList<>();
            if(constraint == null){
                filterd_list.addAll(Variables.selected_champ.getRaces());
            }else{
                String filter_query = constraint.toString().toLowerCase().trim();
                String[] filter_list = filter_query.split(" ");
                boolean check;
                for(Championship championship: Variables.championships) {
                    for (Race race : championship.getRaces()) {
                        check = true;
                        String filter_string = race.getDate() +' '+ race.getName().toLowerCase() +' '+ race.getCountry().toLowerCase();
                        //if (filter_string.contains(filter_query))
                        for(String str: filter_list){
                            if(!filter_string.contains(str))
                                check=false;
                        }
                        if(check)
                            filterd_list.add(race);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterd_list;
            results.count = filterd_list.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filter_races.clear();
            filter_races.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView race_name;
        TextView race_date;
        ImageView race_image;
        CheckBox race_notify;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            race_name = itemView.findViewById(R.id.race_name);
            race_date = itemView.findViewById(R.id.race_date);
            race_image = itemView.findViewById(R.id.race_image);
            race_notify = itemView.findViewById(R.id.notify_button);
        }
    }
}
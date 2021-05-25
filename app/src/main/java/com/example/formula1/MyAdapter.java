package com.example.formula1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.time.LocalDate;
import java.util.TimeZone;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    private List<Race> races;
    private List<Race> filter_races;
    private final Context context;
    private LocalDate currentdate;
    private int day;
    private int month;
    private int year;
    private Calendar cal;
    /* if true notifca todas as corridas */
    /* notification settings notify_races */
    private boolean notification = false;

    public MyAdapter(Context context, ArrayList<Race> races){
        this.races = races;
        this.filter_races = new ArrayList<>(races);
        this.context = context;
        //this.currentdate = LocalDate.now();
        this.cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DAY_OF_MONTH);
    }

    public void notify_races(){this.notification= !this.notification;}
    public List<Race> get_races(){return this.races;}

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.race_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Race race = races.get(position);
        holder.race_name.setText(race.getName());
        holder.race_date.setText(race.getDate());
        holder.race_image.setImageResource(race.getImage());
        // se a corrida for no dia de hoje mostrar a data
        if(this.year == race.getYear() && this.month == race.getMonth()) {
            if (this.day - race.getDay() < 7) {
                // race in less than ... days
                String warning = "RACE IN "+ Integer.toString(this.day-race.getDay()) + " DAYS";
                holder.race_date.setText(warning);
                holder.race_date.setTextColor(Color.parseColor("#DD1515"));
            } else if (this.day == race.getDay()) {
                // race TODAY AT
                holder.race_date.setText("RACE TODAY! AT "+Integer.toString(race.getHours())+"h") ;
                holder.race_date.setTextColor(Color.parseColor("#DD1515"));
            }
        }
        if (race.getNotify())
            holder.race_notify.setChecked(true);
    }

    @Override
    public long getItemId(int position) {
        return position;
        // return races.get(position).getId();
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
                filterd_list.addAll(races);
            }else{
                String filter_query = constraint.toString().toLowerCase().trim();
                for(Race race: races){
                    if(race.getName().toLowerCase().contains(filter_query))
                        filterd_list.add(race);
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
            race_notify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    final boolean isChecked = race_notify.isChecked();
                    // Do something here.

                }
            });
        }
    }
}
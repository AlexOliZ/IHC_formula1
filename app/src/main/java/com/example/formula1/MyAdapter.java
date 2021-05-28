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
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    private final List<Race> races;
    private List<Race> filter_races;
    private final Context context;
    private int day;
    private int month;
    private int year;
    private Calendar cal;
    private FragmentActivity activity;
    private int hour;
    /* if true notifca todas as corridas */
    /* notification settings notify_races */
    private boolean notification = false;

    public MyAdapter(FragmentActivity activity , Context context, ArrayList<Race> races){
        this.races = races;
        this.filter_races = new ArrayList<>(races);
        this.context = context;
        this.cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH)+1;
        this.day = cal.get(Calendar.DAY_OF_MONTH);
        this.activity = activity;
        this.hour = cal.get(Calendar.HOUR);
    }

    public void notify_races(){this.notification= !this.notification;}
    public List<Race> get_races(){return this.races;}

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
        holder.race_date.setText(race.getDate());
        holder.race_image.setImageResource(race.getImage());

        holder.race_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                Schedule.selectRace(race);
                fragmentTransaction.replace(R.id.fragmentContainerView, new Race_Info());
                fragmentTransaction.commit();
            }
        });

        holder.race_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final boolean isChecked = holder.race_notify.isChecked();
                // Do something here.
                filter_races.get(position).Notify();
            }
        });

        if(this.year > cal.get(Calendar.YEAR)){
            String warning = "SEE RESULTS "+ race.getDate();
            holder.race_date.setText(warning);
            holder.race_date.setTextColor(Color.parseColor("#DD1515"));
        }else if(this.year == race.getYear() ) {
            if (this.month == race.getMonth()) {
                if (this.day == race.getDay()) {
                    // race TODAY AT
                    if(this.hour<=race.getHours()) {
                        holder.race_date.setText("RACE TODAY! AT " + Integer.toString(race.getHours()) + "h");
                        holder.race_date.setTextColor(Color.parseColor("#DD1515"));
                    }else if (this.hour -2 <=race.getHours()) {
                        holder.race_date.setText("RACE STARTED");
                        holder.race_date.setTextColor(Color.parseColor("#DD1515"));
                        holder.race_notify.setVisibility(View.INVISIBLE);
                    } else {
                        holder.race_date.setText("RACE OVER ... see results");
                        holder.race_date.setTextColor(Color.parseColor("#DD1515"));
                        holder.race_notify.setVisibility(View.INVISIBLE);
                    }
                }else if (this.day - race.getDay() < 7) {
                    String warning = "RACE IN " + Integer.toString(this.day - race.getDay()) + " DAYS";
                    holder.race_date.setText(warning);
                    holder.race_date.setTextColor(Color.parseColor("#DD1515"));
                } else if (this.day < race.getDay()){
                    holder.race_date.setText("SEE RESULTS " + race.getDate());
                    holder.race_date.setTextColor(Color.parseColor("#000000"));
                    holder.race_notify.setVisibility(View.INVISIBLE);
                }
            } else if (this.month == race.getMonth()){
                holder.race_date.setText("SEE RESULTS " + race.getDate());
                holder.race_date.setTextColor(Color.parseColor("#000000"));
                holder.race_notify.setVisibility(View.GONE);
            }
        }
        if (race.getNotify())
            holder.race_notify.setChecked(true);
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
        }
    }
}
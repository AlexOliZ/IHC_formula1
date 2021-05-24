package com.example.formula1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    private final List<Race> races;
    private final List<Race> filter_races;
    private final Context context;

    public MyAdapter(Context context, ArrayList<Race> races){
        this.races = races;
        this.filter_races = new ArrayList<>(races);
        this.context = context;
    }

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
        holder.race_name.setText(race.getDate());
        holder.race_image.setImageResource(race.getImage());
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

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            race_name = itemView.findViewById(R.id.race_name);
            race_date = itemView.findViewById(R.id.race_date);
            race_image = itemView.findViewById(R.id.race_image);

        }
    }
}
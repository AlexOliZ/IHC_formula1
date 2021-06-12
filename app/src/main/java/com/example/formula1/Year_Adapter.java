package com.example.formula1;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class Year_Adapter extends RecyclerView.Adapter<Year_Adapter.MyViewHolder> implements Filterable {
    private List<Integer> filter_championships = new ArrayList<>();
    private Context context;
    private FragmentActivity activity;
    private List<Integer> years;


    public Year_Adapter(FragmentActivity activity , Context context, List<Integer> years){
        this.filter_championships = Variables.years;
        this.context = context;
        this.activity = activity;
        this.years = years;
    }

    @NonNull
    @Override
    public Year_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.year_row,parent,false);

        return new MyViewHolder(view);
    }

    public Year_Adapter getAdapter(){return this;}

    public void setFilter(String filter){
        List<Integer> aux = new ArrayList<>();
        for(Integer year: filter_championships){
            if(Integer.toString(year).contains(filter)){
                aux.add(year);
            }
        }
        filter_championships.clear();
        filter_championships.addAll(aux);
        getFilter().filter(filter);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull Year_Adapter.MyViewHolder holder, int position) {
        int year = years.get(position);
        holder.champ_year.setText(Integer.toString(year));

        holder.champ_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                Variables.selected_year=year;
                for(Championship champ: Variables.championships) {
                    if(champ.getYear()==year)
                        Variables.selected_champ = champ;
                }
                fragmentTransaction.replace(R.id.fragmentContainerView, new Schedule());
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        Year_Adapter adapter = getAdapter();
        return (adapter == null || position < 0) ? AdapterView.INVALID_ROW_ID : adapter.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return years.size();
    }

    @Override
    public Filter getFilter() {
        return raceFilter;
    }

    private final Filter raceFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Integer> filterd_list = new ArrayList<>();

            if(constraint == null){
                filterd_list.addAll(Variables.years);
            }else{
                String filter_query = constraint.toString().toLowerCase().trim();
                for(int year: Variables.years){
                    if(Integer.toString(year).contains(filter_query))
                        filterd_list.add(year);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterd_list;
            results.count = filterd_list.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filter_championships.clear();
            filter_championships.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView champ_year;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            champ_year = itemView.findViewById(R.id.champ_year);
            //champ_year.setTextSize(50);
        }
    }
}
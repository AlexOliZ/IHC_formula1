package com.example.formula1;

import android.content.Context;
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
    private List<Integer> championships = new ArrayList<>();
    private List<Integer> filter_championships = new ArrayList<>();
    private Context context;
    private FragmentActivity activity;


    public Year_Adapter(FragmentActivity activity , Context context, ArrayList<Integer> years){
        this.championships = years;
        this.filter_championships = years;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Year_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.year_row,parent,false);

        return new MyViewHolder(view);
    }

    public Year_Adapter getAdapter(){return this;}

    @Override
    public void onBindViewHolder(@NonNull Year_Adapter.MyViewHolder holder, int position) {
        int year = filter_championships.get(position);
        System.out.println(year);
        System.out.println(position);
        holder.champ_year.setText(Integer.toString(year));

        holder.champ_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                Schedule.selectYear(year);
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
        return filter_championships.size();
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
                filterd_list.addAll(championships);
            }else{
                String filter_query = constraint.toString().toLowerCase().trim();
                for(int year: championships){
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
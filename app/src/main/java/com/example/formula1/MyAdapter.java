package com.example.formula1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private final List<Race> races;
    private final Activity activity;

    public MyAdapter(List<Race> races,Activity activity){
        this.races = races;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return races.size();
    }

    @Override
    public Object getItem(int position) {
        return races.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        //LayoutInflater layoutInflater = inflater.inflate(R.layout.fragment_schedule, parent, false);
        //View race_row = layoutInflater.inflate(R.layout.race_row, parent);
        View race_row = activity.getLayoutInflater().inflate(R.layout.race_row, parent, false);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ir para uma nova pagina com a info da corrida , resultados ...

            }
        });

        ImageView image = race_row.findViewById(R.id.race_image);
        TextView name = (TextView)race_row.findViewById(R.id.race_name);
        TextView date = (TextView)race_row.findViewById(R.id.race_date);



        image.setImageResource(races.get(position).getImage());
        name.setText(races.get(position).getName());
        date.setText(races.get(position).getDate());

        return race_row;
    }

}
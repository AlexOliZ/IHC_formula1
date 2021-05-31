package com.example.formula1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.WordViewHolder> {

    private final ArrayList<Points> standings;
    private LayoutInflater mInflater;
    Context contexto;
    static int lastPos = 0;

    public StatisticsAdapter(Context context,
                             ArrayList<Points> standings) {
        contexto = context;
        this.standings = standings;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        mInflater = LayoutInflater.from(contexto);
        View mItemView = mInflater.inflate(R.layout.standing_row,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        String standing = standings.get(position).getStanding();
        holder.standingView.setText(standing);
        String pilot = standings.get(position).getPilot();
        holder.pilotView.setText(pilot);
        String team = standings.get(position).getTeam();
        holder.teamView.setText(team);
        String points = standings.get(position).getPoints();
        holder.pointsView.setText(points);
    }

    @Override
    public int getItemCount() {
        return standings.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView standingView;
        public final TextView pilotView;
        public final TextView teamView;
        public final TextView pointsView;
        final StatisticsAdapter mAdapter;

        public WordViewHolder(View itemView, StatisticsAdapter adapter) {
            super(itemView);
            standingView = itemView.findViewById(R.id.Standing);
            pilotView = itemView.findViewById(R.id.Name);
            teamView = itemView.findViewById(R.id.Team);
            pointsView = itemView.findViewById(R.id.Points);
            this.mAdapter = adapter;
        }
    }
}
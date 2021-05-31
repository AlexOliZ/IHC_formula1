package com.example.formula1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Settings_Adapter extends RecyclerView.Adapter<Settings_Adapter.MyViewHolder>{
    private Context context;
    private FragmentActivity activity;
    private List<Race> races;
    /* if true notifca todas as corridas */
    /* notification settings notify_races */
    private boolean notification = false;

    public Settings_Adapter(FragmentActivity activity , Context context, List<Race> races){
        this.context = context;
        this.activity = activity;
        this.races = races;
    }

    @NonNull
    @Override
    public Settings_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.settings_row,parent,false);
        return new Settings_Adapter.MyViewHolder(view);
    }

    public Settings_Adapter getAdapter(){return this;}

    @Override
    public void onBindViewHolder(@NonNull Settings_Adapter.MyViewHolder holder, int position) {
        holder.notification_name.setText(races.get(position).getName());
        System.out.println(races.get(position).getName());
        holder.notification_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.selected_race = races.get(position);
                Variables.selected_year = Variables.championships.get(Variables.championships.size()-1).getYear();
                FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new Race_Info());
                fragmentTransaction.commit();
            }
        });

        holder.notification_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                for(int i=0 ; i<Variables.championships.get(Variables.championships.size()-1).getRaces().size() ; i++){
                    if(Variables.championships.get(Variables.championships.size()-1).getRaces().get(i).getName().equals(races.get(position).getName())){
                        if(Variables.championships.get(Variables.championships.size()-1).getRaces().get(i).getNotify()) {
                            Variables.championships.get(Variables.championships.size() - 1).getRaces().get(i).Notify();
                            races.remove(position);
                            notifyItemRemoved(position);
                            notifyDataSetChanged();
                            break;
                        }

                    }
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        Settings_Adapter adapter = getAdapter();
        return (adapter == null || position < 0) ? AdapterView.INVALID_ROW_ID : adapter.getItemId(position);
    }

    @Override
    public int getItemCount() { return races.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView notification_name;
        ImageView notification_delete;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            notification_name = itemView.findViewById(R.id.name);
            notification_delete = itemView.findViewById(R.id.delete);
        }
    }

}

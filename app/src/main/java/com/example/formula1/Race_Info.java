package com.example.formula1;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class    Race_Info extends Fragment {
    private View view;
    private Context context;
    private Table_Adapter race_adapter;
    private Table_Adapter qual_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_race__information,container,false);

        ImageView race_image = (ImageView) view.findViewById(R.id.race_info_image);
        race_image.setImageResource(Variables.selected_race.getImage());

        TextView race_name = (TextView) view.findViewById(R.id.race_name);
        race_name.setText(Variables.selected_race.getName());

        TextView race_date = (TextView) view.findViewById(R.id.race_date);
        race_date.setText(Variables.selected_race.getDate());

        TextView race_description = (TextView) view.findViewById(R.id.race_description);
        race_description.setText(Variables.selected_race.getDescription());

        RecyclerView recyclerView_race = (RecyclerView)view.findViewById(R.id.race_results);
        recyclerView_race.setLayoutManager(new LinearLayoutManager(context));

        RecyclerView recyclerView_qual = (RecyclerView)view.findViewById(R.id.qualifying_results);
        recyclerView_qual.setLayoutManager(new LinearLayoutManager(context));

        race_adapter = new Table_Adapter(getActivity(),context, Variables.selected_race);
        recyclerView_race.setAdapter(race_adapter);

        qual_adapter = new Table_Adapter(getActivity(),context, Variables.selected_race);
        recyclerView_qual.setAdapter(qual_adapter);

        ImageButton go_back = (ImageButton) view.findViewById(R.id.go_back_button);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new Schedule());
                fragmentTransaction.commit();
            }
        });


        CheckBox race_notify = (CheckBox) view.findViewById(R.id.notify_button);
        race_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Do something here.
                for(Championship c: Variables.championships) {
                    if(c.getYear()==Variables.selected_year) {
                        for (Race r : c.getRaces()) {
                            if (r.getDay() == Variables.selected_race.getDay() && r.getMonth() == Variables.selected_race.getMonth()){
                                r.Notify();
                            if (r.getNotify() == true) {
                                Toast.makeText(context, "NOTIFICAÇÃO ADICIONADA", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "NOTIFICAÇÃO REMOVIDA", Toast.LENGTH_SHORT).show();
                            }
                        }
                        }
                    }
                }
            }
        });

        race_notify.setChecked(Variables.selected_race.getNotify());

        VideoView video = (VideoView)view.findViewById(R.id.race_video);
        String videoPath = "android.resource://"+context.getPackageName()+"/"+R.raw.video;
        Uri uri = Uri.parse(videoPath);
        video.setVideoURI(uri);

        MediaController mediaController = new MediaController(context);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);

        return view;
    }
}
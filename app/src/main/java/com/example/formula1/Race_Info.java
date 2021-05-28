package com.example.formula1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;

public class Race_Info extends Fragment {
    private View view;
    private Context context;
    private Table_Adapter race_adapter;
    private Table_Adapter qual_adapter;
    private Race race;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.race = Schedule.getRace();

        view = inflater.inflate(R.layout.fragment_race__information,container,false);

        ImageView race_image = (ImageView) view.findViewById(R.id.race_info_image);
        race_image.setImageResource(race.getImage());

        RecyclerView recyclerView_race = (RecyclerView)view.findViewById(R.id.race_results);
        recyclerView_race.setLayoutManager(new LinearLayoutManager(context));

        RecyclerView recyclerView_qual = (RecyclerView)view.findViewById(R.id.qualifying_results);
        recyclerView_qual.setLayoutManager(new LinearLayoutManager(context));

        race_adapter = new Table_Adapter(getActivity(),context, race);
        recyclerView_race.setAdapter(race_adapter);

        qual_adapter = new Table_Adapter(getActivity(),context, race);
        recyclerView_qual.setAdapter(qual_adapter);

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
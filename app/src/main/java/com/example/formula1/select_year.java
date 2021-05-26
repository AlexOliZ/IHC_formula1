package com.example.formula1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;

public class select_year extends Fragment {
    private ArrayList<Integer> years = new ArrayList<>(Arrays.asList(2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021));
    private View view;
    private Context context;
    private Year_Adapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_select_year,container,false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.championships_year);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ImageButton go_back = (ImageButton) view.findViewById(R.id.go_back_button);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, new Schedule());
                fragmentTransaction.commit();
            }
        });

        adapter = new Year_Adapter(getActivity(),context, (ArrayList<Integer>) years);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition((int)(adapter.getItemCount()/2));
        return view;
    }
}
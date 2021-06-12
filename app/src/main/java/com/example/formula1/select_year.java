package com.example.formula1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class select_year extends Fragment {

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


        EditText search_year = (EditText) view.findViewById(R.id.search_champ_year);

        search_year.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s)  { }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Integer> years = new ArrayList<>();
                for(Championship c: Variables.championships){
                    if(Integer.toString(c.getYear()).contains(search_year.getText()))
                        years.add(c.getYear());
                }
                System.out.println(years);
                adapter = new Year_Adapter(getActivity(),context,years);
                recyclerView.setAdapter(adapter);
                recyclerView.smoothScrollToPosition((int)(adapter.getItemCount()/2));
            }
        });


        List<Integer> years = new ArrayList<>();
        for(Championship c: Variables.championships){
            years.add(c.getYear());
        }

        adapter = new Year_Adapter(getActivity(),context,years);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition((int)(adapter.getItemCount()/2));
        return view;
    }
}
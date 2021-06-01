package com.example.formula1;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Pilot extends Fragment {


    private static int img;
    private static int imgT;
    private static ImageView mainImg;
    private static ImageView teamImg;
    private static TextView mainS;
    private static TextView num;
    private static TextView na;
    private static String s;
    private static String number;
    private static String name;


    public Pilot(String s, int img, int imgT, String name, String number){
        this.img=img;
        this.s=s;
        this.imgT=imgT;
        this.name= name;
        this.number= number;
    }

    public void setData(){
        mainImg.setImageDrawable(ContextCompat.getDrawable(getActivity(), img));
        teamImg.setImageDrawable(ContextCompat.getDrawable(getActivity(), imgT));
        mainS.setText(s);
        na.setText(name);
        num.setText(number);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentManager man = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = man.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.replace(R.id.frameLayout, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pilot, container, false);
        mainImg=(ImageView) view.findViewById(R.id.imgPilot);
        teamImg=(ImageView) view.findViewById(R.id.imgTeamP);

        mainS=(TextView) view.findViewById(R.id.textPilot);
        na=(TextView) view.findViewById(R.id.Name);
        num=(TextView) view.findViewById(R.id.Number);
        setData();
        return view;
    }

}
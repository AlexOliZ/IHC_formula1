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

        teamImg.setOnClickListener(this::onClick);
        mainS=(TextView) view.findViewById(R.id.textPilot);
        na=(TextView) view.findViewById(R.id.Name);
        num=(TextView) view.findViewById(R.id.Number);
        setData();
        return view;
    }

    @SuppressLint("ResourceType")
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.imgTeamP:
                Click(teamImg);
                break;
        }
    }

    public void Click(ImageView v) {
        Fragment fragment = null;
        Bitmap drawable = ((BitmapDrawable)v.getDrawable()).getBitmap();
        if (drawable.equals(((BitmapDrawable) getResources().getDrawable(R.drawable.profile_vrt_raw_bytes_1587515318_10187)).getBitmap()))
            replaceFragment(
                    new Team("For many, Ferrari and Formula 1 racing have become inseparable. The only team to have competed in every season since the world championship began, the Prancing Horse has grown from the humble dream of founder Enzo Ferrari to become one of the most iconic and recognised brands in the world. Success came quickly through the likes of Alberto Ascari and John Surtees, and continued – in amongst leaner times – with Niki Lauda in the 1970s and then Michael Schumacher in the 2000s, when Ferrari claimed a then unprecedented five consecutive title doubles, securing their status as the most successful and decorated team in F1 history...",
                            R.drawable.profile_vrt_raw_bytes_1587515318_10187,
                            R.drawable.charles_leclerc_ferrari_1,
                            R.drawable.carlos_sainz_jr_ferrari_1));
        else if (drawable.equals(((BitmapDrawable) getResources().getDrawable(R.drawable.transferir)).getBitmap())) {
            new Team("Mercedes’ modern F1 revival started with the creation of a works squad for 2010 - the platform for a meteoric rise up the Grand Prix order. The team generated huge excitement from the off with the sensational return of Michael Schumacher, but headlines soon followed on track: three podiums in their debut season, all via Nico Rosberg - who then claimed a breakthrough pole/victory double at China in 2012. The following season he was paired with Lewis Hamilton, the duo going on to stage some epic title battles as the Silver Arrows swept all before them to become one of the most dominant forces of the modern F1 era. And with Hamilton now partnered by steely Finn Valtteri Bottas, Mercedes remain very much the team to beat…",
                    R.drawable.transferir,
                    R.drawable.lewis_hamilton_mercedes_1,
                    R.drawable.valtteri_bottas_mercedes_1);
        }else if (drawable.equals(((BitmapDrawable) getResources().getDrawable(R.drawable.red_bull_logo)).getBitmap())) {
            new Team("Red Bull were no strangers to F1 - as sponsors - prior to formally entering as a works team in 2004. Nonetheless, the scale of their success over the following decade was staggering. After a first podium in 2006, the team hit their stride in 2009, claiming six victories and second in the constructors' standings. Over the next four seasons they were a tour de force, claiming consecutive title doubles between 2010 and 2013, with Sebastian Vettel emerging as the sport's youngest quadruple champion. Now their hopes of recapturing that glory lie with an equally exciting talent – one named Max Verstappen…",
                    R.drawable.red_bull_logo,
                    R.drawable.max_verstappen_red_bull_racing_1,
                    R.drawable.sergio_perez_red_bull_racing_1);
        }

    }
}
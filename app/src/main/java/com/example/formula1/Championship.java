package com.example.formula1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Championship {

    private List<Race> races;
    private int year;

    public Championship(int year){
        this.year = year;
        this.races = allRaces(year);
    }

    public List<Race> getRaces(){
        return this.races;
    }

    public int getYear(){ return this.year;}

    public void notify_all(){
        for(Race r: races)
            r.Notify();
    }

    private List<Race> allRaces(int year){
        return new ArrayList<>(Arrays.asList(new Race("Portimão","Portugal","First Race in Portugal",1,1,year,14,10),
                new Race("Monaco","Monaco","The Classic Race of Monaco!",1,2,year,14,12),
                new Race("Monza","Italy","Ferrari Track",1,3,year,14,9),
                new Race("Imola","Italy","First race Since 1988",1,4,year,14,12),
                new Race("Spa","Belgium","One of the best races in the calendar",1,5,year,14,10),
                new Race("Imola","Italy","First race Since 1988",27,5,year,14,12),
                new Race("Imola","Italy","First race Since 1988",1,6,year,17,12),
                new Race("Monaco","Monaco","The Classic Race of Monaco!",2,6,year,14,12),
                new Race("Portimão","Portugal","First Race in Portugal",1,7,year,14,10),
                new Race("Monza","Italy","Ferrari Track",1,8,year,14,9),
                new Race("Spa","Belgium","One of the best races in the calendar",1,10,year,14,10),
                new Race("Spa","Belgium","One of the best races in the calendar",1,11,year,14,10)));
    }
}

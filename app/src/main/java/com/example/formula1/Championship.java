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

    private List<Race> allRaces(int year){
        return new ArrayList<>(Arrays.asList(new Race("Portimão","Portugal","First Race in Portugal",1,1,year,14,10),
                new Race("Monaco","Monaco","The Classic Race of Monaco!",1,2,year,14,12),
                new Race("Monza","Italy","RIP Ferrari",1,1,year,14,9),
                new Race("Imola","Italy","First race Since 1988",1,3,year,14,12),
                new Race("Spa","Belgium","One of the best races in the calendar",1,4,year,14,10)));
    }
}

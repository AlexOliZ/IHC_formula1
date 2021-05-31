package com.example.formula1;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Variables {

    public static List<Championship> championships = allChampionships();
    public static Race selected_race;
    public static List<String> names = new ArrayList<>(Arrays.asList(new String[]{"Lewis", "Bottas","Vettel", "Lando", "Ricciardo", "Perez","Verstappen", "Sainz", "Alonso", "Leclerc", "Russel","Stroll","Latifi","Gasly","Tsunoda","Ocon","Schumacher","Nikita","Giovinazzi","Raikkonen"}));
    public static List<String> teams = new ArrayList<>(Arrays.asList(new String[]{"Mercedes", "Mercedes","Aston Martin", "Mclaren", "Mclaren", "Redbull", "Redbull","Ferrari", "Alpine", "Ferrari", "Williams","Aston Martin","Williams","ToroRosso","ToroRosso","Alpine","Haas","Haas","Alfa Romeo","Alfa Romeo"}));
    public static List<String>positions = new ArrayList<>(Arrays.asList(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
    public static Championship selected_champ = championships.get(championships.size()-1);
    public static int selected_year = 2021;
    public static ArrayList<Integer> years = new ArrayList<>(Arrays.asList(2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021));
    public static Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/London"));
    public static int day = cal.get(Calendar.DAY_OF_MONTH);
    public static int month = cal.get(Calendar.MONTH);
    public static int year = cal.get(Calendar.YEAR);
    public static int hour = cal.get(Calendar.HOUR_OF_DAY);
    public static boolean notify_all=false;


    private static List<Championship> allChampionships() {
        return new ArrayList<>(Arrays.asList(
                new Championship(2005),
                new Championship(2006),
                new Championship(2007),
                new Championship(2008),
                new Championship(2009),
                new Championship(2010),
                new Championship(2011),
                new Championship(2012),
                new Championship(2013),
                new Championship(2014),
                new Championship(2015),
                new Championship(2016),
                new Championship(2017),
                new Championship(2018),
                new Championship(2019),
                new Championship(2020),
                new Championship(2021)
        ));
    }
}

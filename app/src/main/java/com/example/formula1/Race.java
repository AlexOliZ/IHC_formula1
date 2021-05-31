package com.example.formula1;

import android.graphics.Color;
import android.view.View;

import java.util.Calendar;

public class Race {
    private String name;
    private String country;
    private int image;
    private String description;
    private int day;
    private int month;
    private int year;
    private int length;
    private int hours;
    private boolean notify;
    // Activity context;

    public Race(String name, String country, String description, int day, int month, int year, int hours, int length){
        // meter default value
        this.image = R.drawable.race;
        this.name = name;
        this.country = country;
        this.description = description;
        this.day = day;
        this.month = month;
        this.year = year;
        this.length = length;
        this.hours = hours;
        this.notify=false;
    }

    public boolean check_Notify(){
        if(this.year>Variables.year)
            return true;
        if(this.year == Variables.year){
            if(this.month>Variables.month+1)
                return true;
            if(Variables.month+1==this.month){
                if(this.day>Variables.day)
                    return true;
                if(this.day==Variables.day){
                    if(this.hours>Variables.hour)
                        return true;
                }
            }
        }

        return false;
    }

    public String getName(){ return this.name; }
    public String getCountry(){ return this.country; }
    public int getImage(){ return this.image; }
    public String getDescription(){ return this.description; }
    public String getDate(){ return Integer.toString(this.day) + "/" + Integer.toString(this.month) + "/" + Integer.toString(this.year);}
    public String getLength(){ return Integer.toString(this.length)+ " km"; }
    public int getYear(){ return this.year; }
    public int getMonth(){ return this.month; }
    public int getDay(){ return this.day; }
    public int getHours(){return this.hours;}
    public boolean getNotify(){ return this.notify; }
    public void Notify(){this.notify=!this.notify;}
}

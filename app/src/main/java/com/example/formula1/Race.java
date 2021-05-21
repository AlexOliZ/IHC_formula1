package com.example.formula1;

public class Race {
    private String name;
    private String country;
    private int image;
    private String description;
    private String date;
    private int day;
    private int month;
    private int year;
    private int length;
    private int hours;
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
    }

    public String getName(){ return this.name; }
    public String getCountry(){ return this.country; }
    public int getImage(){ return this.image; }
    public String getDescription(){ return this.description; }
    public String getDate(){ return Integer.toString(this.day) + "/" + Integer.toString(this.month) + "/" + Integer.toString(this.year) + "  " + this.hours + "h\n"; }
    public String getLength(){ return Integer.toString(this.length)+ " km"; }
    public int getYear(){ return this.year; }
    public int getMonth(){ return this.month; }
    public int getDay(){ return this.day; }
}

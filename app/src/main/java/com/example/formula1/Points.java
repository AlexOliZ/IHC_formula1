package com.example.formula1;

public class Points {
    private int Standing;
    private String Pilot;
    private String Team;
    private int Points;

    public Points(int s, String p, String t, int pts){
        this.Standing=s;
        this.Pilot=p;
        this.Team=t;
        this.Points=pts;
    }

    public int getPoints() {
        return Points;
    }

    public int getStanding() {
        return Standing;
    }

    public String getPilot() {
        return Pilot;
    }

    public String getTeam() {
        return Team;
    }
}

package com.example.formula1;

public class Points {
    private String Standing;
    private String Pilot;
    private String Team;
    private String Points;

    public Points(String s, String p, String t, String pts){
        this.Standing=s;
        this.Pilot=p;
        this.Team=t;
        this.Points=pts;
    }

    public String getPoints() {
        return Points;
    }

    public String getStanding() {
        return Standing;
    }

    public String getPilot() {
        return Pilot;
    }

    public String getTeam() {
        return Team;
    }
}

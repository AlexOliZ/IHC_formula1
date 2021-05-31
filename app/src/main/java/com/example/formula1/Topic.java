package com.example.formula1;

import java.util.LinkedList;

public class Topic {
    private String title;
    private String description;
    private String keyword;
    private LinkedList<String> comments = new LinkedList<>();


    public Topic(String t, String d, String k){
        this.title = t;
        this.description = d;
        this.keyword = k;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public LinkedList<String> getComments(){
        return this.comments;
    }

    public void addComments(String c){
        comments.add(c);
    }


}

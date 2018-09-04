package com.example.android.sportsnews.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailedInfo {

    @SerializedName("team1")
    private String team1;

    @SerializedName("team2")
    private String team2;

    @SerializedName("time")
    private String time;

    @SerializedName("place")
    private String place;

    @SerializedName("tournament")
    private String tournament;

    @SerializedName("article")
    private List<Article> articles;

    @SerializedName("prediction")
    private String prediction;

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public String getPrediction() {
        return prediction;
    }

    public String getTournament() {
        return tournament;
    }
}

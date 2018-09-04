package com.example.android.sportsnews.data;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("title")
    private String title;
    @SerializedName("coefficient")
    private String coefficient;
    @SerializedName("time")
    private String time;
    @SerializedName("place")
    private String place;
    @SerializedName("preview")
    private String preview;
    @SerializedName("article")
    private String article;

    public String getTitle() {
        return title;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getPreview() {
        return preview;
    }

    public String getArticle() {
        return article;
    }
}

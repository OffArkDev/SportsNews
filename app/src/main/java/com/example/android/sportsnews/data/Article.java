package com.example.android.sportsnews.data;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("header")
    private String header;

    @SerializedName("text")
    private String text;


    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }
}

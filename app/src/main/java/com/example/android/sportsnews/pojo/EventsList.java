package com.example.android.sportsnews.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventsList {
    @SerializedName("events")
    private List<Event> events = new ArrayList<>();

    public List<Event> getEvents() {
        return events;
    }
}

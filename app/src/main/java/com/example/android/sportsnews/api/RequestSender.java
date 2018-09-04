package com.example.android.sportsnews.api;

import com.example.android.sportsnews.data.DetailedInfo;
import com.example.android.sportsnews.data.Event;
import com.example.android.sportsnews.data.EventsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestSender {

    @GET("/list.php")
    Call<EventsList> getEvents(@Query("category") String category);

    @GET("/post.php")
    Call<DetailedInfo> getDetailedInfo(@Query("article") String article);
}

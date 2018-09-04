package com.example.android.sportsnews.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestController {
    private Retrofit retrofit;
    private RequestSender client;
    private String url;

    public RequestController() {
        url = "http://mikonatoruri.win/";

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(RequestSender.class);
    }
    public RequestSender createRequest() {
        return this.client;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}

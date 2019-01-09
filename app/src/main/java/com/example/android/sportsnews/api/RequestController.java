package com.example.android.sportsnews.api;

import com.example.android.sportsnews.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestController {

    private static RequestController instance = new RequestController();


    private Retrofit retrofit;
    private RequestSender client;
    private String url;

    private RequestController() {
        url = "http://mikonatoruri.win/";


        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        client = retrofit.create(RequestSender.class);
    }


    public static void initInstance() {
        if (instance == null) {
            instance = new RequestController();
        }
    }

    public static RequestController getInstance() {
        return instance;
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

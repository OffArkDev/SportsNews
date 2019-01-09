package com.example.android.sportsnews;

import android.app.Application;

import com.example.android.sportsnews.api.RequestController;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RequestController.initInstance();
    }
}

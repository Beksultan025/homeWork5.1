package com.example.lesson51;

import android.app.Application;

import com.example.lesson51.data.remote.FilmApi;
import com.example.lesson51.data.remote.RetrofitClient;

public class App extends Application {

    private RetrofitClient retrofitClient;
    public static FilmApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.createFilmApi();
    }
}

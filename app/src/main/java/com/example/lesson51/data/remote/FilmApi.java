package com.example.lesson51.data.remote;

import com.example.lesson51.data.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmApi {

    @GET("/films")
    Call<List<Film>> getFilms();

    @GET("/films/{id}")
    Call<Film> getFilmDetail(
            @Path("id") String id
    );

}

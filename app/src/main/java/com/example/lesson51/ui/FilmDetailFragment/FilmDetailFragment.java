package com.example.lesson51.ui.FilmDetailFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.lesson51.App;
import com.example.lesson51.R;
import com.example.lesson51.data.models.Film;
import com.example.lesson51.databinding.FragmentFilmDetailBinding;
import com.example.lesson51.databinding.FragmentFilmsBinding;
import com.example.lesson51.ui.films.FilmsAdapter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collection;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmDetailFragment extends Fragment {

    private FragmentFilmDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String id = bundle.getString("key");
        App.api.getFilmDetail(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    bind(response.body());
                } else {
                    Snackbar.make(binding.getRoot(), response.message(), BaseTransientBottomBar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
                }
            }
            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });

    }

    private void bind(Film body) {
        binding.tvText.setText(body.getTitle());
        binding.tvTexst.setText(body.getDescription());
        Glide.with(binding.imageForItem).load(body.getImage()).into(binding.imageForItem);
    }
}
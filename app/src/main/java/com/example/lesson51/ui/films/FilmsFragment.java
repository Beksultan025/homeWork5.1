package com.example.lesson51.ui.films;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lesson51.App;
import com.example.lesson51.R;
import com.example.lesson51.data.models.Film;
import com.example.lesson51.databinding.FragmentFilmsBinding;
import com.example.lesson51.ui.FilmDetailFragment.FilmDetailFragment;
import com.example.lesson51.ui.FilmDetailFragment.OnClick;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment implements OnClick {

    private FragmentFilmsBinding binding;
    private FilmsAdapter adapter;

    public FilmsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmsBinding.inflate(inflater , container , false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new FilmsAdapter();
        adapter.setOnClick(this);
        binding.recycler.setAdapter(adapter);
        

        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setFilms(response.body());
                }else {
                    Snackbar.make(
                            binding.getRoot() ,
                            response.message() ,
                            BaseTransientBottomBar.LENGTH_LONG
                    )
                            .setBackgroundTint(Color.RED)
                            .show();
                }

            }
            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(Film film) {
        Bundle bundle = new Bundle();
        bundle.putString("key" , film.getId());
        Fragment fragment = new FilmDetailFragment();
        fragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment , fragment).addToBackStack(null).commit();
    }
}
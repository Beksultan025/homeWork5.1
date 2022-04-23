package com.example.lesson51.ui.films;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lesson51.data.models.Film;
import com.example.lesson51.databinding.ItemFilmBinding;
import com.example.lesson51.ui.FilmDetailFragment.FilmDetailFragment;
import com.example.lesson51.ui.FilmDetailFragment.OnClick;

import java.util.ArrayList;
import java.util.List;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.FilmViewHolder> {

    private List<Film> films = new ArrayList<>();
    private OnClick onClick;

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FilmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.onBind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    protected class FilmViewHolder extends RecyclerView.ViewHolder {

        private ItemFilmBinding binding;

        public FilmViewHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Film film) {
            binding.tvTilte.setText(film.getTitle());
            binding.tvDescription.setText(film.getDescription());
            Glide.with(binding.imageItem).load(film.getImage()).into(binding.imageItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClick.onClick(film);
                }
            });
        }
    }
}

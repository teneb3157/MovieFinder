package ml.univ.moviefinder.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ml.univ.moviefinder.R;
import ml.univ.moviefinder.databinding.ItemMovieBinding;
import ml.univ.moviefinder.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    public interface OnMovieClickListener {
        void onMovieClick(Movie movie);
    }

    private final List<Movie> movies = new ArrayList<>();
    private final OnMovieClickListener listener;

    public MovieAdapter(OnMovieClickListener listener) {
        this.listener = listener;
    }

    public void setMovies(List<Movie> newMovies) {
        movies.clear();
        if (newMovies != null) {
            movies.addAll(newMovies);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding b = ItemMovieBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(b);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ItemMovieBinding b;

        MovieViewHolder(ItemMovieBinding b) {
            super(b.getRoot());
            this.b = b;
        }

        void bind(Movie movie) {
            b.tvTitle.setText(movie.getTitle());
            b.tvYear.setText(movie.getYear());
            b.ivPoster.setImageResource(R.drawable.ic_movie_placeholder);
            b.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMovieClick(movie);
                }
            });
        }
    }
}
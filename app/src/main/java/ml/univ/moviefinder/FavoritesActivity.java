package ml.univ.moviefinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ml.univ.moviefinder.adapter.MovieAdapter;
import ml.univ.moviefinder.data.FavoritesStore;
import ml.univ.moviefinder.databinding.ActivityFavoritesBinding;
import ml.univ.moviefinder.model.FakeData;
import ml.univ.moviefinder.model.Movie;

public class FavoritesActivity extends AppCompatActivity {

    private ActivityFavoritesBinding binding;
    private MovieAdapter adapter;
    private FavoritesStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        store = new FavoritesStore(this);
        adapter = new MovieAdapter(movie -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_IMDB_ID, movie.getImdbId());
            intent.putExtra(DetailActivity.EXTRA_TITLE, movie.getTitle());
            intent.putExtra(DetailActivity.EXTRA_YEAR, movie.getYear());
            startActivity(intent);
        });

        binding.rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        binding.rvFavorites.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        Set<String> favoriteIds = store.getAll();
        List<Movie> favoriteMovies = new ArrayList<>();
        for (Movie movie : FakeData.movies()) {
            if (favoriteIds.contains(movie.getImdbId())) {
                favoriteMovies.add(movie);
            }
        }
        adapter.setMovies(favoriteMovies);

        boolean isEmpty = favoriteMovies.isEmpty();
        binding.tvEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        binding.rvFavorites.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
    }
}
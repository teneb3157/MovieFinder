package ml.univ.moviefinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.Collections;

import ml.univ.moviefinder.adapter.MovieAdapter;
import ml.univ.moviefinder.databinding.ActivityMainBinding;
import ml.univ.moviefinder.viewmodel.MoviesViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MovieFinder";

    private ActivityMainBinding binding;
    private MoviesViewModel viewModel;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        adapter = new MovieAdapter(movie -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_IMDB_ID, movie.getImdbId());
            intent.putExtra(DetailActivity.EXTRA_TITLE, movie.getTitle());
            intent.putExtra(DetailActivity.EXTRA_YEAR, movie.getYear());
            startActivity(intent);
        });

        binding.rvMovies.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvMovies.setAdapter(adapter);

        binding.btnSearch.setOnClickListener(v -> {
            String query = binding.etQuery.getText() != null
                    ? binding.etQuery.getText().toString().trim()
                    : "";
            if (query.isEmpty()) {
                Toast.makeText(this, R.string.msg_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.search(query);
        });

        viewModel.getMovies().observe(this, movies -> {
            if (movies == null || movies.isEmpty()) {
                adapter.setMovies(Collections.emptyList());
                binding.rvMovies.setVisibility(View.GONE);
            } else {
                adapter.setMovies(movies);
                binding.rvMovies.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorites) {
            startActivity(new Intent(this, FavoritesActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
package ml.univ.moviefinder;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import ml.univ.moviefinder.adapter.MovieAdapter;
import ml.univ.moviefinder.databinding.ActivityMainBinding;
import ml.univ.moviefinder.model.FakeData;
import ml.univ.moviefinder.model.Movie;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        adapter = new MovieAdapter(movie ->
                Toast.makeText(this, movie.getTitle(), Toast.LENGTH_SHORT).show());

        int spanCount = getResources().getInteger(R.integer.span_count);
        binding.rvMovies.setLayoutManager(new GridLayoutManager(this, spanCount));
        binding.rvMovies.setAdapter(adapter);
        adapter.setMovies(FakeData.movies());

        binding.btnSearch.setOnClickListener(v -> onSearchClicked());
    }

    private void onSearchClicked() {
        String query = binding.etQuery.getText().toString().trim();
        List<Movie> result = new ArrayList<>();
        for (Movie m : FakeData.movies()) {
            if (m.getTitle().toLowerCase().contains(query.toLowerCase())) {
                result.add(m);
            }
        }
        adapter.setMovies(result);
        binding.tvMessage.setText(result.isEmpty()
                ? getString(R.string.msg_no_result)
                : getString(R.string.msg_welcome));
    }
}
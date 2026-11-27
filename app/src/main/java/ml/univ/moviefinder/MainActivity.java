package ml.univ.moviefinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import ml.univ.moviefinder.adapter.MovieAdapter;
import ml.univ.moviefinder.model.Movie;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MovieFinder";
    private MovieRepository repository;
    private MovieAdapter adapter;
    private EditText etSearch;
    private Button btnSearch;
    private Button btnFavorites;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new MovieRepository(this);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnFavorites = findViewById(R.id.btnFavorites);
        tvMessage = findViewById(R.id.tvMessage);

        RecyclerView recyclerView = findViewById(R.id.rvMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MovieAdapter(movie -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("EXTRA_TITLE", movie.getTitle());
            intent.putExtra("EXTRA_YEAR", movie.getYear());
            intent.putExtra("EXTRA_TYPE", "");
            intent.putExtra("EXTRA_POSTER", movie.getPoster());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> {
            String query = etSearch.getText().toString().trim();
            if (query.isEmpty()) {
                Toast.makeText(MainActivity.this, "Entre un titre de film", Toast.LENGTH_SHORT).show();
                return;
            }
            rechercherFilms(query);
        });

        btnFavorites.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });

        rechercherFilms("batman");
    }

    private void rechercherFilms(String motCle) {
        tvMessage.setText("Recherche en cours...");
        repository.searchMovies(motCle, new MovieRepository.SearchCallback() {
            @Override
            public void onSuccess(List<MovieDto> movies) {
                List<Movie> mappedMovies = new ArrayList<>();
                for (MovieDto dto : movies) {
                    Movie movie = new Movie(
                            dto.getTitle(),
                            dto.getYear(),
                            dto.getType(),
                            dto.getPoster()
                    );
                    mappedMovies.add(movie);
                }
                adapter.setMovies(mappedMovies);
                tvMessage.setText(mappedMovies.size() + " résultat(s) pour \"" + motCle + "\"");
            }

            @Override
            public void onError(String message) {
                Log.e(TAG, "Erreur : " + message);
                tvMessage.setText(message);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
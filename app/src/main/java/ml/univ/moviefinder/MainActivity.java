package ml.univ.moviefinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ml.univ.moviefinder.model.Movie;

public class MainActivity extends AppCompatActivity {

    private EditText etQuery;
    private Button btnSearch;
    private TextView tvMessage;
    private RecyclerView rvMovies;
    private MovieAdapter adapter;
    private final List<Movie> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etQuery = findViewById(R.id.etQuery);
        btnSearch = findViewById(R.id.btnSearch);
        tvMessage = findViewById(R.id.tvMessage);
        rvMovies = findViewById(R.id.rvMovies);

        movieList.addAll(FakeData.getMovies());
        adapter = new MovieAdapter(movieList);

        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(adapter);
    }
}
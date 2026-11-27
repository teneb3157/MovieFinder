package ml.univ.moviefinder;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private MovieRepository repository;
    private TextView tvFavTitle;
    private ListView lvFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        repository = new MovieRepository(this);
        tvFavTitle = findViewById(R.id.tvFavTitle);
        lvFavorites = findViewById(R.id.lvFavorites);

        chargerFavoris();
    }

    @Override
    protected void onResume() {
        super.onResume();
        chargerFavoris();
    }

    private void chargerFavoris() {
        repository.loadFavorites(new MovieRepository.FavoritesCallback() {
            @Override
            public void onResult(List<FavoriteMovie> favorites) {
                List<String> labels = new ArrayList<>();
                for (FavoriteMovie fav : favorites) {
                    labels.add(fav.getTitle() + " (" + fav.getYear() + ")");
                }

                runOnUiThread(() -> {
                    tvFavTitle.setText("Mes films favoris (" + labels.size() + ")");
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            FavoritesActivity.this,
                            android.R.layout.simple_list_item_1,
                            labels
                    );
                    lvFavorites.setAdapter(adapter);
                });
            }
        });
    }
}
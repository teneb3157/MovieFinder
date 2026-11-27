package ml.univ.moviefinder;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ml.univ.moviefinder.data.FavoritesStore;
import ml.univ.moviefinder.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_IMDB_ID = "extra_imdb_id";
    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_YEAR = "extra_year";

    private ActivityDetailBinding binding;
    private String imdbId;
    private String title;
    private String year;
    private FavoritesStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        imdbId = getIntent().getStringExtra(EXTRA_IMDB_ID);
        title = getIntent().getStringExtra(EXTRA_TITLE);
        year = getIntent().getStringExtra(EXTRA_YEAR);

        binding.tvDetailTitle.setText(title);
        binding.tvDetailYear.setText(year);

        store = new FavoritesStore(this);
        refreshFavoriteButton();
        binding.btnFavorite.setOnClickListener(v -> {
            store.toggle(imdbId);
            refreshFavoriteButton();
        });

        binding.btnShare.setOnClickListener(v -> shareMovie());
    }

    private void refreshFavoriteButton() {
        binding.btnFavorite.setText(store.isFavorite(imdbId)
                ? R.string.btn_remove_favorite
                : R.string.btn_add_favorite);
    }

    private void shareMovie() {
        String shareText = (title != null ? title : "")
                + (year != null && !year.isEmpty() ? " (" + year + ")" : "");
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, getString(R.string.btn_share));
        startActivity(shareIntent);
    }
}
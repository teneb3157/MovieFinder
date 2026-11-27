package ml.univ.moviefinder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private MovieRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        repository = new MovieRepository(this);

        ImageView ivPoster = findViewById(R.id.ivDetailPoster);
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvYear = findViewById(R.id.tvDetailYear);
        TextView tvType = findViewById(R.id.tvDetailType);
        Button btnAddFavorite = findViewById(R.id.btnAddFavorite);

        String title = getIntent().getStringExtra("EXTRA_TITLE");
        String year = getIntent().getStringExtra("EXTRA_YEAR");
        String type = getIntent().getStringExtra("EXTRA_TYPE");
        String poster = getIntent().getStringExtra("EXTRA_POSTER");

        if (title == null) title = "";
        if (year == null) year = "";
        if (type == null) type = "";
        if (poster == null) poster = "";

        tvTitle.setText(title);
        tvYear.setText("Année : " + year);
        tvType.setText("Type : " + (type.isEmpty() ? "movie" : type));

        if (!poster.isEmpty() && !"N/A".equals(poster)) {
            Glide.with(this).load(poster).into(ivPoster);
        } else {
            ivPoster.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        final String finalTitle = title;
        final String finalYear = year;
        final String finalType = type.isEmpty() ? "movie" : type;
        final String finalPoster = poster;

        btnAddFavorite.setOnClickListener(v -> {
            String fakeId = finalTitle + "_" + finalYear;
            FavoriteMovie fav = new FavoriteMovie(
                    fakeId,
                    finalTitle,
                    finalYear,
                    finalType,
                    finalPoster
            );
            repository.addFavorite(fav);
            Toast.makeText(DetailActivity.this, "Ajouté aux favoris !", Toast.LENGTH_SHORT).show();
        });
    }
}
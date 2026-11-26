package ml.univ.moviefinder;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ml.univ.moviefinder.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MovieFinder";
    private ActivityMainBinding binding;

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

        binding.btnSearch.setOnClickListener(v -> onSearchClicked());
    }

    private void onSearchClicked() {
        String query = binding.etQuery.getText().toString().trim();
        if (query.isEmpty()) {
            binding.etQuery.setError(getString(R.string.msg_empty));
            return;
        }
        Log.d(TAG, "Recherche : " + query);
        binding.tvMessage.setText(getString(R.string.msg_searching, query));
    }
}
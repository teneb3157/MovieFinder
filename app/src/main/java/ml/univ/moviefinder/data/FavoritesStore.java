package ml.univ.moviefinder.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritesStore {
    private static final String PREFS = "moviefinder_prefs";
    private static final String KEY = "favorites";
    private final SharedPreferences prefs;

    public FavoritesStore(Context context) {
        prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public Set<String> getAll() {
        return new HashSet<>(prefs.getStringSet(KEY, new HashSet<>()));
    }

    public boolean isFavorite(String imdbId) {
        return imdbId != null && getAll().contains(imdbId);
    }

    public void toggle(String imdbId) {
        if (imdbId == null || imdbId.isEmpty()) {
            return;
        }
        Set<String> set = getAll();
        if (!set.remove(imdbId)) {
            set.add(imdbId);
        }
        prefs.edit().putStringSet(KEY, set).apply();
    }
}
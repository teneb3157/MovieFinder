package ml.univ.moviefinder;

import android.content.Context;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;

public class MovieRepository {

    private static final String OMDB_API_KEY = "d8a8349";

    public interface SearchCallback {
        void onSuccess(List<MovieDto> movies);
        void onError(String message);
    }

    public interface FavoritesCallback {
        void onResult(List<FavoriteMovie> favorites);
    }

    private final OmdbApiService apiService;
    private final FavoriteMovieDao favoriteMovieDao;
    private final ExecutorService executorService;

    public MovieRepository(Context context) {
        this.apiService = RetrofitClient.getApiService();
        this.favoriteMovieDao = AppDatabase.getInstance(context).favoriteMovieDao();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void searchMovies(String query, SearchCallback callback) {
        apiService.searchMovies(OMDB_API_KEY, query).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, retrofit2.Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SearchResponse body = response.body();
                    if ("True".equalsIgnoreCase(body.getResponse()) && body.getSearch() != null) {
                        callback.onSuccess(body.getSearch());
                    } else {
                        String err = body.getError() != null ? body.getError() : "Aucun film trouvé.";
                        callback.onError(err);
                    }
                } else {
                    callback.onError("Erreur réseau : code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                callback.onError("Connexion impossible : " + t.getMessage());
            }
        });
    }

    public void addFavorite(FavoriteMovie movie) {
        executorService.execute(() -> favoriteMovieDao.insertFavorite(movie));
    }

    public void removeFavorite(FavoriteMovie movie) {
        executorService.execute(() -> favoriteMovieDao.deleteFavorite(movie));
    }

    public void loadFavorites(FavoritesCallback callback) {
        executorService.execute(() -> {
            List<FavoriteMovie> list = favoriteMovieDao.getAllFavorites();
            callback.onResult(list);
        });
    }
}
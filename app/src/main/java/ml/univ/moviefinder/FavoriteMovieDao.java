package ml.univ.moviefinder;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface FavoriteMovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(FavoriteMovie movie);

    @Delete
    void deleteFavorite(FavoriteMovie movie);

    @Query("SELECT * FROM favorite_movies ORDER BY title ASC")
    List<FavoriteMovie> getAllFavorites();

    @Query("SELECT * FROM favorite_movies WHERE imdbId = :imdbId LIMIT 1")
    FavoriteMovie getFavoriteById(String imdbId);
}
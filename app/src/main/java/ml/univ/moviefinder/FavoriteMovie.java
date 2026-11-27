package ml.univ.moviefinder;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_movies")
public class FavoriteMovie {

    @PrimaryKey
    @NonNull
    private String imdbId;

    private String title;
    private String year;
    private String type;
    private String poster;

    public FavoriteMovie(@NonNull String imdbId, String title, String year, String type, String poster) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.type = type;
        this.poster = poster;
    }

    @NonNull
    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(@NonNull String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
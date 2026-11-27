package ml.univ.moviefinder;

import com.google.gson.annotations.SerializedName;

public class MovieDto {

    @SerializedName("imdbID")
    private String imdbId;

    @SerializedName("Title")
    private String title;

    @SerializedName("Year")
    private String year;

    @SerializedName("Type")
    private String type;

    @SerializedName("Poster")
    private String poster;

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }
}
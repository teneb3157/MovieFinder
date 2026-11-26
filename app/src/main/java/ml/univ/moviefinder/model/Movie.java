package ml.univ.moviefinder.model;

public class Movie {
    private final String imdbId;
    private final String title;
    private final String year;
    private final String poster;

    public Movie(String imdbId, String title, String year, String poster) {
        this.imdbId = imdbId;
        this.title = title;
        this.year = year;
        this.poster = poster;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPoster() {
        return poster;
    }
}
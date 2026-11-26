package ml.univ.moviefinder.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FakeData {
    public static final List<Movie> MOVIES = Collections.unmodifiableList(Arrays.asList(
            new Movie("tt1375666", "Inception", "2010", ""),
            new Movie("tt0468569", "The Dark Knight", "2008", ""),
            new Movie("tt0816692", "Interstellar", "2014", ""),
            new Movie("tt6751668", "Parasite", "2019", ""),
            new Movie("tt0245429", "Spirited Away", "2001", "")
    ));

    private FakeData() {
    }

    public static List<Movie> movies() {
        return MOVIES;
    }
}
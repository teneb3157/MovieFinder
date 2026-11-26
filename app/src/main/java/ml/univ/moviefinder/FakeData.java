package ml.univ.moviefinder;

import java.util.ArrayList;
import java.util.List;

import ml.univ.moviefinder.model.Movie;

public class FakeData {

    public static List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Inception", "Christopher Nolan", 2010, 8.8));
        movies.add(new Movie("Interstellar", "Christopher Nolan", 2014, 8.7));
        movies.add(new Movie("Parasite", "Bong Joon-ho", 2019, 8.5));
        movies.add(new Movie("Spirited Away", "Hayao Miyazaki", 2001, 8.6));
        movies.add(new Movie("The Dark Knight", "Christopher Nolan", 2008, 9.0));
        return movies;
    }
}
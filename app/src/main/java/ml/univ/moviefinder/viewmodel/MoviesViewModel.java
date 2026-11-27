package ml.univ.moviefinder.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import ml.univ.moviefinder.model.FakeData;
import ml.univ.moviefinder.model.Movie;

public class MoviesViewModel extends ViewModel {

    private final MutableLiveData<List<Movie>> movies =
            new MutableLiveData<>(FakeData.movies());

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void search(String query) {
        String q = query.toLowerCase();
        List<Movie> result = new ArrayList<>();
        for (Movie m : FakeData.movies()) {
            if (m.getTitle().toLowerCase().contains(q)) {
                result.add(m);
            }
        }
        movies.setValue(result);
    }
}
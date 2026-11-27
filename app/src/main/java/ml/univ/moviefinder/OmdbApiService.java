package ml.univ.moviefinder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiService {

    @GET("/")
    Call<SearchResponse> searchMovies(
            @Query("apikey") String apiKey,
            @Query("s") String query
    );

    @GET("/")
    Call<MovieDto> getMovieById(
            @Query("apikey") String apiKey,
            @Query("i") String imdbId
    );
}
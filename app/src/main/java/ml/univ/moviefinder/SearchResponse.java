package ml.univ.moviefinder;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchResponse {

    @SerializedName("Search")
    private List<MovieDto> search;

    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("Response")
    private String response;

    @SerializedName("Error")
    private String error;

    public List<MovieDto> getSearch() {
        return search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }
}
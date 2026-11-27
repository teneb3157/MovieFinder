package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.google.gson.Gson;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import ml.univ.moviefinder.MovieDto;
import ml.univ.moviefinder.SearchResponse;

public class SearchResponseParsingTest {

    private static final String OK =
            "{\"Search\":[{\"Title\":\"Batman\",\"Year\":\"1989\"," +
                    "\"imdbID\":\"tt0096895\",\"Type\":\"movie\",\"Poster\":\"N/A\"}]," +
                    "\"totalResults\":\"1\",\"Response\":\"True\"}";

    private static final String KO =
            "{\"Response\":\"False\",\"Error\":\"Movie not found!\"}";

    private static String getPrivateString(Object target, String fieldName) throws Exception {
        Field f = target.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        return (String) f.get(target);
    }

    @Test
    public void parse_success() throws Exception {
        SearchResponse r = new Gson().fromJson(OK, SearchResponse.class);
        assertEquals("True", getPrivateString(r, "response"));
        List<MovieDto> list = r.getSearch();
        assertEquals(1, list.size());
        assertEquals("Batman", list.get(0).getTitle());
        assertEquals("tt0096895", list.get(0).getImdbId());
    }

    @Test
    public void parse_error() throws Exception {
        SearchResponse r = new Gson().fromJson(KO, SearchResponse.class);
        assertEquals("False", getPrivateString(r, "response"));
        assertNull(r.getSearch());
        assertEquals("Movie not found!", getPrivateString(r, "error"));
    }
}
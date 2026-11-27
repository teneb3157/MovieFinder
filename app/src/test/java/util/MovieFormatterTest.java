package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ml.univ.moviefinder.util.MovieFormatter;

public class MovieFormatterTest {

    @Test
    public void valueOrDefault_null_returnsDefault() {
        assertEquals("Non renseigné", MovieFormatter.valueOrDefault(null));
    }

    @Test
    public void valueOrDefault_na_returnsDefault() {
        assertEquals("Non renseigné", MovieFormatter.valueOrDefault("N/A"));
    }

    @Test
    public void valueOrDefault_text_isTrimmed() {
        assertEquals("Drame", MovieFormatter.valueOrDefault(" Drame "));
    }

    @Test
    public void hasPoster_na_isFalse() {
        assertFalse(MovieFormatter.hasPoster("N/A"));
        assertTrue(MovieFormatter.hasPoster("https://exemple.com/a.jpg"));
    }
}
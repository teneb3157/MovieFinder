package ml.univ.moviefinder.util;

public final class MovieFormatter {

    private MovieFormatter() {
    }

    public static String valueOrDefault(String value) {
        if (value == null) {
            return "Non renseigné";
        }
        String v = value.trim();
        return (v.isEmpty() || v.equalsIgnoreCase("N/A")) ? "Non renseigné" : v;
    }

    public static boolean hasPoster(String url) {
        return url != null && url.startsWith("http");
    }
}
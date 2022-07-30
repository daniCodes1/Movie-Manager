package persistence;

import model.Movie;
import model.Favourites;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMovie(String title, String genre, int releaseDate, Movie movie) {
        assertEquals(title, movie.getTitle());
        assertEquals(releaseDate, movie.getReleaseDate());
        assertEquals(genre, movie.getGenre());
    }
}

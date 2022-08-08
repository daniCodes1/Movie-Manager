package persistence;

import model.Movie;
import model.Favourites;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Favourites fav = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFavourites() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFavourites.json");
        try {
            Favourites fav = reader.read();
            assertEquals(0, fav.getFavourites().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFavourites() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFavourites.json");
        try {
            Favourites fav = reader.read();
            ArrayList<Movie> movies = fav.getFavourites();
            assertEquals(2, fav.getFavourites().size());
            checkMovie("Saw", "horror", 2004,  movies.get(0));
            checkMovie("Titanic", "romance", 1997,  movies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (Exception e) {
            // expected
        }
    }

    @Test
    void testReaderGeneralFavouritesWithException() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFavourites.json");
        try {
            Favourites fav = reader.read();
            ArrayList<Movie> movies = fav.getFavourites();
            assertEquals(2, fav.getFavourites().size());
            checkMovie("Saw", "horror", 2004,  movies.get(0));
            checkMovie("Titanic", "romance", 1997,  movies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (Exception e) {
            // expected
        }
    }
}

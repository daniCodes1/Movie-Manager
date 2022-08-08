package persistence;

import model.Movie;
import model.Favourites;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Favourites fav = new Favourites();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFavourites() {
        try {
            Favourites fav = new Favourites();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFavourites.json");
            writer.open();
            writer.write(fav);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFavourites.json");
            fav = reader.read();
            assertEquals(0, fav.getFavourites().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFavourites() {
        try {
            Favourites fav = new Favourites();
            try {
                fav.addMovieToFavourites("Titanic");
            } catch (Exception e) {
                fail();
            }
            try {
                fav.addMovieToFavourites("The Terminator");
            } catch (Exception e) {
                fail();
            }
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFavourites.json");
            writer.open();
            writer.write(fav);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFavourites.json");
            fav = reader.read();
            ArrayList<Movie> movies = fav.getFavourites();
            assertEquals(2, fav.getFavourites().size());
            checkMovie("Titanic", "romance", 1997, movies.get(0));
            checkMovie("The Terminator", "sci-fi", 1984, movies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}

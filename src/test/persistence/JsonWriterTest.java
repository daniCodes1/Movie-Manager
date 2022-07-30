package persistence;

import model.Movie;
import model.MovieList;
import model.Favourites;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
    void testWriterEmptyWorkroom() {
        try {
            Favourites fav = new Favourites();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(fav);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            fav = reader.read();
            assertEquals(0, fav.getFavourites().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Favourites fav = new Favourites();
            fav.addMovieToFavourites("Titanic");
            fav.addMovieToFavourites("The Terminator");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(fav);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
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

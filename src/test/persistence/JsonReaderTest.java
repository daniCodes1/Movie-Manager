package persistence;

import model.Movie;
import model.MovieList;
import model.Favourites;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Favourites fav = reader.read();
            assertEquals(0, fav.getFavourites().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Favourites fav = reader.read();
            ArrayList<Movie> thingies = fav.getFavourites();
            assertEquals(2, fav.getFavourites().size());
            checkMovie("Saw", "horror", 2004,  thingies.get(0));
            checkMovie("Titanic", "romance", 1997,  thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

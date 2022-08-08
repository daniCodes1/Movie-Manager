package model;

import model.exceptions.DuplicateException;
import model.exceptions.NotInDatabaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FavouritesTest {
    Favourites testFavourites;
    ArrayList<String> list;
    ArrayList<String> list2;


    @BeforeEach
    void runBefore() {
        testFavourites = new Favourites();
        list = new ArrayList<>();
    }

    @Test
    void testConstructor() {
        assertEquals(list, testFavourites.getFavourites());
    }

    @Test
    void testViewFavourites() {
        list.add("Casablanca");
        list2 = new ArrayList<>();
        list2.add("Casablanca");
        list2.add("Titanic");

        assertEquals(null, testFavourites.viewFavourites());
        try {
            testFavourites.addMovieToFavourites("Casablanca");
        } catch (Exception e) {
            fail();
        }
        assertTrue(testFavourites.favouritesContains("Casablanca"));
        assertEquals(testFavourites.viewFavourites(), list);

        try {
            testFavourites.addMovieToFavourites("Titanic");
        } catch (Exception e) {
            fail();
        }
        assertTrue(testFavourites.favouritesContains("Titanic"));
        assertEquals(testFavourites.viewFavourites(), list2);
    }

    @Test
    void testAddMovieToFavourites() {
        try {
            assertTrue(testFavourites.addMovieToFavourites("The Terminator"));
        } catch (NotInDatabaseException | DuplicateException e) {
            fail();
        }

        try {
            assertFalse(testFavourites.addMovieToFavourites("Not a Movie"));
            fail();
        } catch (NotInDatabaseException e) {
            // expected
        } catch (DuplicateException e) {
            fail();
        }
        try {
            assertTrue(testFavourites.addMovieToFavourites("Carrie"));
        } catch (Exception e) {
            fail();
        }
        try {
            assertFalse(testFavourites.addMovieToFavourites("The Terminator"));
        } catch (DuplicateException e) {
            // expected
        } catch (NotInDatabaseException e) {
            fail();
        }

    }

    @Test
    void testFavouritesContains() {
        assertFalse(testFavourites.favouritesContains("The Shining"));
        assertFalse(testFavourites.favouritesContains("Not a Movie"));
        try {
            testFavourites.addMovieToFavourites("Interstellar");
        } catch (Exception e) {
            fail();
        }
        assertTrue(testFavourites.favouritesContains("Interstellar"));

        try {
            testFavourites.addMovieToFavourites("Black Panther");
        } catch (Exception e) {
            fail();
        }
        assertTrue(testFavourites.favouritesContains("Black Panther"));
    }

    @Test
    void testRemoveMovieFromFavourites() {
        assertFalse(testFavourites.removeMovieFromFavourites("Saw"));
        try {
            testFavourites.addMovieToFavourites("The Shining");
        } catch (Exception e) {
            fail();
        }
        try {
            testFavourites.addMovieToFavourites("Saw");
        } catch (Exception e) {
            fail();
        }
        assertFalse(testFavourites.removeMovieFromFavourites("Not a Movie"));
        assertTrue(testFavourites.removeMovieFromFavourites("Saw"));
        assertFalse(testFavourites.favouritesContains("Saw"));
        assertTrue(testFavourites.favouritesContains("The Shining"));


    }

    @Test
    void testGetSize() {
        assertEquals(0, testFavourites.getSize());
        try {
            testFavourites.addMovieToFavourites("Titanic");
        } catch (Exception e) {
            fail();
        }
        assertEquals(1, testFavourites.getSize());
    }

    @Test
    void testNamesOfMovies() {
        assertEquals(0, testFavourites.namesOfMovies().size());
        try {
            testFavourites.addMovieToFavourites("Titanic");
        } catch (Exception e) {
            fail();
        }
        assertEquals(1, testFavourites.namesOfMovies().size());
    }


}
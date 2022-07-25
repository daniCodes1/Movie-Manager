package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FavouritesTest {
    ArrayList<Movie> testFavouriteMovies;
    Favourites testFavourites;
    MovieList testRecommended;
    Movie movie1;
    Movie movie2;
    ArrayList<String> list;
    ArrayList<String> list2;


    @BeforeEach
    void runBefore() {
        testFavouriteMovies = new ArrayList<>();
        testFavourites = new Favourites();
        testRecommended = new MovieList();
        list = new ArrayList<>();
        movie2 = new Movie("Casablanca", "romance", 1942);
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
        testFavourites.addMovieToFavourites("Casablanca");
        assertTrue(testFavourites.favouritesContains("Casablanca"));
        assertEquals(testFavourites.viewFavourites(), list);

        testFavourites.addMovieToFavourites("Titanic");
        assertTrue(testFavourites.favouritesContains("Titanic"));
        assertEquals(testFavourites.viewFavourites(), list2);
    }

    @Test
    void testAddMovieToFavourites() {
        assertTrue(testFavourites.addMovieToFavourites("The Terminator"));
        assertFalse(testFavourites.addMovieToFavourites("Lalala"));
        assertTrue(testFavourites.addMovieToFavourites("Carrie"));
        assertFalse(testFavourites.addMovieToFavourites("The Terminator"));

    }

  @Test
    void testFavouritesContains() {
        assertFalse(testFavourites.favouritesContains("The Shining"));
        assertFalse(testFavourites.favouritesContains("Lalalala"));
        testFavourites.addMovieToFavourites("Interstellar");
        assertTrue(testFavourites.favouritesContains("Interstellar"));
        testFavourites.addMovieToFavourites("Black Panther");
        testFavourites.addMovieToFavourites("Titanic");
        assertTrue(testFavourites.favouritesContains("Black Panther"));
  }

}
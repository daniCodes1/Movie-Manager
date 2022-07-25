package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.Collections.emptyList;
import static java.util.Collections.fill;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieListTest {
    MovieList testMovieList;
    MovieList names;
    ArrayList<String> list;
    ArrayList<Movie> movies;
    Movie casablanca;
    Movie saw;


    @BeforeEach
    void runBefore() {
        testMovieList = new MovieList();
        names = new MovieList();
        list = new ArrayList<>();
        casablanca = new Movie("Casablanca", "romance", 1942);
        saw = new Movie("Saw", "horror", 2004);
        movies = new ArrayList<>();

    }

    @Test
    void testConstructor() {
        assertEquals(list, testMovieList.getRecommended());
        assertEquals(list, testMovieList.getNames());
    }

    @Test
    void testAddMovies() {
        testMovieList.addMovies();
        assertEquals(72, testMovieList.getRecommended().size());
    }

    @Test
    void testPrintAllMovies() {
        assertEquals(list, testMovieList.printAllMovies());
        testMovieList.addMovies();
        assertEquals(72, testMovieList.printAllMovies().size());
    }

    @Test
    void testFilterGenre() {
        testMovieList.addMovies();
        testMovieList.filterGenre("horror");
        assertEquals(18, testMovieList.getFilteredGenre().size());
    }

    @Test
    void testFilterDate() {
        testMovieList.addMovies();
        testMovieList.filterGenre("horror");
        assertEquals(18, testMovieList.getFilteredGenre().size());
        testMovieList.filterDate("a");
        assertEquals(3, testMovieList.filteredReleaseDate.size());
    }


    @Test
    void testGetMovieNames() {
        assertEquals(72, testMovieList.getMovieNames().size());
        assertTrue(testMovieList.getMovieNames().contains("Titanic"));
        assertTrue(testMovieList.getMovieNames().contains("Carrie"));
    }

    @Test
    void testMovieToString() {
        movies.add(casablanca);
        list.add("Casablanca");
        assertEquals(list, testMovieList.movieToString(movies));
        assertEquals(1, testMovieList.movieToString(movies).size());

        movies.add(saw);
        list.add("Saw");
        assertEquals(list, testMovieList.movieToString(movies));
        assertEquals(2, testMovieList.movieToString(movies).size());
    }
}


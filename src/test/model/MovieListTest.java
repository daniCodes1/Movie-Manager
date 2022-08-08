package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieListTest {
    MovieList testMovieList;
    ArrayList<String> list;
    ArrayList<Movie> movies;
    Movie casablanca;
    Movie saw;
    Movie futureMovie;
    MovieList test1990;
    MovieList test2000;
    MovieList test2010;
    MovieList test2022;

    @BeforeEach
    void runBefore() {
        testMovieList = new MovieList();
        list = new ArrayList<>();
        casablanca = new Movie("Casablanca", "romance", 1942);
        futureMovie = new Movie("Future", "romance", 2030);
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

        test1990 = new MovieList();
        test1990.addMovies();
        test1990.filterGenre("romance");
        assertEquals(16, test1990.getFilteredGenre().size());
        test1990.filterDate("b");
        assertEquals(3, test1990.filteredReleaseDate.size());

        test2000 = new MovieList();
        test2000.addMovies();
        test2000.filterGenre("romance");
        assertEquals(16, test2000.getFilteredGenre().size());
        test2000.filterDate("c");
        assertEquals(4, test2000.filteredReleaseDate.size());

        test2010 = new MovieList();
        test2010.addMovies();
        test2010.filterGenre("romance");
        assertEquals(16, test2010.getFilteredGenre().size());
        test2010.filterDate("d");
        assertEquals(3, test2010.filteredReleaseDate.size());

        test2022 = new MovieList();
        futureMovie = new Movie("Future", "romance", 2040);

        test2022.addMovies();
        test2022.addMovie(futureMovie);
        test2022.filterGenre("romance");
        assertEquals(17, test2022.getFilteredGenre().size());
        test2022.filterDate("e");
        assertEquals(3, test2022.filteredReleaseDate.size());
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

    @Test
    void testGetMovie() {
        assertEquals(null, testMovieList.getMovie("Paw Patrol"));
    }

    @Test
    void testGetSize() {
        assertEquals(0, testMovieList.getSize());
        testMovieList.addMovie(casablanca);
        testMovieList.addMovie(saw);
        assertEquals(2, testMovieList.getSize());
    }
}


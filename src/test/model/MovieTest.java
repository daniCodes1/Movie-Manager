package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieTest {
    Movie casablanca;


    @BeforeEach
    void runBefore() {
        casablanca = (new Movie("Casablanca", "romance", 1942));
    }

    @Test
    void testConstructor() {
        assertEquals("Casablanca", casablanca.getTitle());
        assertEquals("romance", casablanca.getGenre());
        assertEquals(1942, casablanca.getReleaseDate());
    }
}

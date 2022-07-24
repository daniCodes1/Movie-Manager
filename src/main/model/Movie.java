package model;

import java.util.ArrayList;

// Represents a movie having a title, genre and release date
public class Movie {
    private String title;
    private String genre;
    private int releaseDate;

    // EFFECTS: constructs a movie with a title, genre and release date
    public Movie(String title, String genre, int releaseDate) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

}

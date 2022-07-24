package model;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String genre;
    private int releaseDate;
    private ArrayList<Movie> favourites;
    private ArrayList<Movie> allMovies;

    // EFFECTS: constructs a movie with a title, genre and release date
    public Movie(String title, String genre, int releaseDate) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public void addToFavourites(Movie m) {
        favourites.add(m);
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


    public void add(Movie m) {
        allMovies.add(m);
    }
}

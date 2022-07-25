package model;

import java.io.CharArrayReader;
import java.util.ArrayList;

// represents a favourites list storing user's favourite movies and list of all movies in database

public class Favourites {

    ArrayList<Movie> favourites;
    MovieList recommended;


    // EFFECTS: constructs a favourites collection with list of favourite movies and list of all movies in the database
    public Favourites() {
        favourites = new ArrayList<>();
        recommended = new MovieList();
    }


    // MODIFIES: this
    // EFFECTS: if favourites album is empty, return null, else return a list of titles of the movies in favourites
    public ArrayList<String> viewFavourites() {
        if (favourites.size() == 0) {
            return null;
        } else {
            return recommended.movieToString(favourites);
        }
    }

    // MODIFIES: this
    // EFFECTS: if given Movie is in the database and favourites does not already contain given movie, then add
    // given movie to end of the favourites album and returns true, else return false
    public Boolean addMovieToFavourites(String s) {
        if (recommended.getMovieNames().contains(s) && !(favouritesContains(s))) {
            favourites.add(recommended.getMovie(s));
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if favourites album contains movie with given name, else returns false
    public Boolean favouritesContains(String s) {
        for (Movie m : favourites) {
            if (s.equals(m.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Movie> getFavourites() {
        return favourites;
    }


}

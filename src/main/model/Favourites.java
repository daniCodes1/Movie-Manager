package model;

import java.util.ArrayList;

// represents a favourites list storing user's favourite movies and list of all movies in database

public class Favourites {

    ArrayList<Movie> favourites;
    MovieList recommended;


    public Favourites() {
        favourites = new ArrayList<>();
        recommended = new MovieList();
    }


    public ArrayList<String> viewFavourites() {
        if (favourites.size() == 0) {
            return null;
        } else {
            return recommended.movieToString(favourites);
        }
    }

    // REQUIRES: favourites does not already contain a Movie with the same title as the given movie
    // MODIFIES: this
    // EFFECTS: adds given movie to end of the favourites album
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

    // REQUIRES: m != null
    // MODIFIES: this
    // EFFECTS: removes this movie from the list of favourite movies
    public void removeMovie(Movie m) {
        favourites.remove(m);
    }


}

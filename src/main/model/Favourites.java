package model;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import model.Movie;
import model.MovieList;


public class Favourites {


    ArrayList<String> favourites;
    MovieList recommended;



    public Favourites() {
        favourites = new ArrayList<String>();
        recommended = new MovieList();

    }



    public ArrayList<String> viewFavourites() {
        if (favourites.size() == 0) {
            return null;
        } else {
            return favourites;
        }
    }
//
//    public void handleFavouriteMovie(String s) {
//        favourites.add(s);
//        viewFavourites();
//    }



    // REQUIRES: favourites does not already contain a Movie with the same title as the given movie
    // MODIFIES: this
    // EFFECTS: adds given movie to end of the favourites album
    public void addMovieToFavourites(String s) {
        favourites.add(s);
    }

    // REQUIRES: m != null
    // MODIFIES: this
    // EFFECTS: removes this movie from the list of favourite movies
    public void removeMovie(Movie m) {
        favourites.remove(m);
    }


}

package model;

import model.exceptions.DuplicateException;
import model.exceptions.NotInDatabaseException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a favourites list storing user's favourite movies and list of all movies in database
public class Favourites implements Writable {

    ArrayList<Movie> favourites;
    ArrayList<String> movieNames;

    // EFFECTS: constructs a favourites collection with list of favourite movies and list of all movies in the database
    public Favourites() {
        favourites = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: if favourites album is empty, return null, else return a list of titles of the movies in favourites
    public ArrayList<String> viewFavourites() {
        if (favourites.size() == 0) {
            return null;
        } else {
            return movieToString(favourites);
        }
    }

    // MODIFIES: this
    // EFFECTS: returns list containing titles of all the movies in the given list
    public ArrayList<String> movieToString(ArrayList<Movie> listOfMovies) {
        ArrayList<String> movieNames = new ArrayList<>();
        for (Movie m : listOfMovies) {
            movieNames.add(m.getTitle());
        }
        return movieNames;
    }

    // MODIFIES: this
    // EFFECTS: if given Movie is in the database and favourites album does not already contain given movie, then add
    // given movie to end of the favourites album and returns true, else returns false
    public Boolean addMovieToFavourites(String s) throws DuplicateException, NotInDatabaseException {
        MovieList recommended = new MovieList();
        if (!recommended.getAllMovieNames().contains(s)) {
            throw new NotInDatabaseException();
        } else if (favouritesContains(s)) {
            throw new DuplicateException();
        } else {
            favourites.add(recommended.getMovie(s));
            EventLog.getInstance().logEvent(new Event("Movie: " + s + " has been added to favourites!"));
            return true;
        }
    }

    // REQUIRES: movie is in database and is not already in favourites
    // MODIFIES: this
    // EFFECTS: adds movie to favourites
    public void addMovieNoExceptions(String s) {
        MovieList recommended = new MovieList();
        favourites.add(recommended.getMovie(s));
    }

    // MODIFIES: this
    // EFFECTS: if given movie is in favourites, removes it from favourites and returns true, else returns false
    public Boolean removeMovieFromFavourites(String s) {
        ArrayList<Movie> removed = new ArrayList<>();
        if (favouritesContains(s)) {
            for (Movie m : favourites) {
                if (!(s.equals(m.getTitle()))) {
                    removed.add(m);
                }
            }
            favourites = removed;
            EventLog.getInstance().logEvent(new Event("Movie: " + s + " has been removed from favourites!"));
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


    // MODIFIES: this
    // EFFECTS: returns list containing titles of all the movies in the given list
    public ArrayList<String> namesOfMovies() {
        movieNames = new ArrayList<>();
        for (Movie m : favourites) {
            movieNames.add(m.getTitle());
        }
        return movieNames;
    }

    // getters
    public ArrayList<Movie> getFavourites() {
        return favourites;
    }

    public int getSize() {
        return favourites.size();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("movies", favouritesToJson());
        return json;
    }

    // EFFECTS: returns things in this favourites album as a JSON array
    private JSONArray favouritesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie m : favourites) {
            jsonArray.put(m.toJson());
        }
        return jsonArray;
    }


}

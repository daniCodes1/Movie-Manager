package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// Represents a favourites list storing user's favourite movies and list of all movies in database
public class Favourites implements Writable {

    ArrayList<Movie> favourites;
    MovieList recommended;
    ArrayList<Movie> removed;

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
    // EFFECTS: if given Movie is in the database and favourites album does not already contain given movie, then add
    // given movie to end of the favourites album and returns true, else returns false
    public Boolean addMovieToFavourites(String s) {
        if (recommended.getMovieNames().contains(s) && !(favouritesContains(s))) {
            favourites.add(recommended.getMovie(s));
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeMovieFromFavourites(String s) {
        removed = new ArrayList<>();
        if (favouritesContains(s)) {
            for (Movie m : favourites) {
                if (!(s.equals(m.getTitle()))) {
                    removed.add(m);
                }
            }
            favourites = removed;
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


    // getter
    public ArrayList<Movie> getFavourites() {
        return favourites;
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

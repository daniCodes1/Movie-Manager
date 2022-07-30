package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a movie having a title, genre and release date
public class Movie implements Writable {
    private String title;
    private String genre;
    private int releaseDate;

    // EFFECTS: constructs a movie with a title, genre and release date
    public Movie(String title, String genre, int releaseDate) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("genre", genre);
        json.put("releaseDate", releaseDate);
        return json;
    }

    // getters
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

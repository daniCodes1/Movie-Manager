package persistence;

import model.MovieList;
import model.Favourites;
import model.Movie;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.*;

// based on JsonSerializationDemo ; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads a favourites album from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads favourites from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Favourites read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFavourites(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses favourites from JSON object and returns it
    private Favourites parseFavourites(JSONObject jsonObject) {
        Favourites fav = new Favourites();
        addFavourites(fav, jsonObject);
        return fav;
    }

    // MODIFIES: fav
    // EFFECTS: parses movies from JSON object and adds them to favourites album
    private void addFavourites(Favourites fav, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("movies");
        for (Object json : jsonArray) {
            JSONObject nextMovie = (JSONObject) json;
            addMovie(fav, nextMovie);
        }
    }

    // MODIFIES: fav
    // EFFECTS: parses movie from JSON object and adds it to favourites album
    private void addMovie(Favourites fav, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String genre = jsonObject.getString("genre");
        int releaseDate = jsonObject.getInt("releaseDate");
        Movie movie = new Movie(title, genre, releaseDate);
        fav.addMovieToFavourites(movie.getTitle());
    }
}

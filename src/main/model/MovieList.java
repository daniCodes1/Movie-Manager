package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MovieList {
    ArrayList<Movie> recommended;
    ArrayList<Movie> movieList;
    ArrayList<Movie> allMovies;
    ArrayList<Movie> allMoviesGenre;
    ArrayList<String> movieNames;
    ArrayList<String> database;
    ArrayList<String> names;


    public MovieList() {
        allMovies = new ArrayList<>();
        recommended = new ArrayList<>();
        allMoviesGenre = new ArrayList<>();
        movieList = new ArrayList<>();
        database = new ArrayList<>();
        names = new ArrayList<>();


    }

    public void addMovies() {
        recommended.add(new Movie("Casablanca", "romance", 1942));
        recommended.add(new Movie("When Harry Met Sally", "romance", 1989));
        recommended.add(new Movie("Titanic", "romance", 1997));
        recommended.add(new Movie("The Notebook", "romance", 2004));
        recommended.add(new Movie("La La Land", "romance", 2016));

        recommended.add(new Movie("The Matrix", "sci-fi", 1999));
        recommended.add(new Movie("E.T. The Extra-Terrestrial", "sci-fi", 1982));
        recommended.add(new Movie("Avatar", "sci-fi", 2009));
        recommended.add(new Movie("Jurassic World Dominion", "sci-fi", 2022));

        recommended.add(new Movie("The Shining", "horror", 1980));
        recommended.add(new Movie("The Exorcist", "horror", 1973));
        recommended.add(new Movie("The Blair Witch Project", "horror", 1999));
        recommended.add((new Movie("American Psycho", "horror", 2000)));
        recommended.add(new Movie("Saw", "horror", 2004));
        recommended.add(new Movie("The Conjuring", "horror", 2013));

        recommended.add(new Movie("Top Gun", "action", 1986));
        recommended.add(new Movie("Terminator 2", "action", 1991));
        recommended.add(new Movie("Fast & Furious 4", "action", 2009));
        recommended.add(new Movie("Spider-Man: No Way Home", "action", 2021));


    }


    public ArrayList<String> printAllMovies() {
        for (Movie m : recommended) {
            names.add(m.getTitle());
        }
        return names;
    }

    public void filterGenre(String s) {
        allMovies = new ArrayList<>();
        for (Movie m : recommended) {
            if (s.equals(m.getGenre())) {
                allMovies.add(m);
            }
        }
    }

    public ArrayList<String> filterDate(String s) { // int is representing the release date
        allMoviesGenre = new ArrayList<>();
        if (s.equals("0")) {
            before1990();
        } else {
            if (s.equals("1")) {
                before2000();
                return movieToString(allMoviesGenre);
            } else {
                if (s.equals("2")) {
                    before2010();
                    return movieToString(allMoviesGenre);
                } else {
                    for (Movie movie : allMovies) {
                        if (movie.getReleaseDate() >= 2010 && movie.getReleaseDate() <= 2022) {
                            allMoviesGenre.add(movie);
                        }
                    }
                }
            }
        }
        return movieToString(allMoviesGenre);
    }

    public void before1990() {
        for (Movie mo : allMovies) {
            if (mo.getReleaseDate() < 1990) {
                allMoviesGenre.add(mo);
            }
        }
    }

    public void before2000() {
        for (Movie mov : allMovies) {
            if (mov.getReleaseDate() >= 1990 && mov.getReleaseDate() < 2000) {
                allMoviesGenre.add(mov);
            }
        }
    }

    public void before2010() {
        for (Movie movi : allMovies) {
            if (movi.getReleaseDate() >= 2000 && movi.getReleaseDate() < 2010) {
                allMoviesGenre.add(movi);
            }
        }
    }

    public ArrayList<String> movieToString(ArrayList<Movie> listOfMovies) {
        movieNames = new ArrayList<String>();
        for (Movie m : listOfMovies) {
            movieNames.add(m.getTitle());
        }
        return movieNames;
    }
}


package model;

import java.util.ArrayList;

// Represents a movie collection containing list of all movies, a list filtered by genre and release date, and
// lists that consist of movies titles
public class MovieList {
    ArrayList<Movie> recommended;
    ArrayList<Movie> filteredGenre;
    ArrayList<Movie> filteredReleaseDate;
    ArrayList<String> movieNames;
    ArrayList<String> names;
    ArrayList<String> stringNames;

    // EFFECTS: constructs a movie collection with a movie database and list of titles of the movies in the database
    public MovieList() {
        recommended = new ArrayList<>();
        names = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds movies into database
    public void addMovies() {
        addRomance();
        addAction();
        addHorror();
        addSciFi();
    }

    // MODIFIES: this
    // EFFECTS: adds all romance movies into database
    public void addRomance() {
        recommended.add(new Movie("Casablanca", "romance", 1942));
        recommended.add(new Movie("Gone with the Wind", "romance", 1939));
        recommended.add(new Movie("Breakfast at Tiffany's", "romance", 1961));

        recommended.add(new Movie("Sixteen Candles", "romance", 1984));
        recommended.add(new Movie("When Harry Met Sally", "romance", 1989));
        recommended.add(new Movie("The Princess Bride", "romance", 1987));

        recommended.add(new Movie("The Bodyguard", "romance", 1992));
        recommended.add(new Movie("Persuasion", "romance", 1995));
        recommended.add(new Movie("Titanic", "romance", 1997));
        recommended.add(new Movie("10 Things I Hate About You", "romance", 1999));

        recommended.add(new Movie("Bridget Jones' Diary", "romance", 2001));
        recommended.add(new Movie("Love Actually", "romance", 2003));
        recommended.add(new Movie("The Notebook", "romance", 2004));

        recommended.add(new Movie("La La Land", "romance", 2016));
        recommended.add(new Movie("Me Before You", "romance", 2016));
        recommended.add(new Movie("A Star is Born", "romance", 2018));
    }

    // MODIFIES: this
    // EFFECTS: adds all sci-fi movies into database
    public void addSciFi() {
        recommended.add(new Movie("A Clockwork Orange", "sci-fi", 1971));
        recommended.add(new Movie("Star Wars", "sci-fi", 1977));
        recommended.add(new Movie("A Space Odyssey", "sci-fi", 1968));
        recommended.add(new Movie("Alien", "sci-fi", 1979));

        recommended.add(new Movie("E.T. The Extra-Terrestrial", "sci-fi", 1982));
        recommended.add(new Movie("Blade Runner", "sci-fi", 1982));
        recommended.add(new Movie("The Terminator", "sci-fi", 1984));
        recommended.add(new Movie("Back to the Future", "sci-fi", 1985));

        recommended.add(new Movie("The Handmaid's Tale", "sci-fi", 1990));
        recommended.add(new Movie("Stargate", "sci-fi", 1994));
        recommended.add(new Movie("The Truman Show", "sci-fi", 1998));
        recommended.add(new Movie("The Matrix", "sci-fi", 1999));

        recommended.add(new Movie("Avatar", "sci-fi", 2009));
        recommended.add(new Movie("Wall-E", "sci-fi", 2008));
        recommended.add(new Movie("Star Trek", "sci-fi", 2009));
        recommended.add(new Movie("Serenity", "sci-fi", 2005));

        recommended.add(new Movie("Jurassic World Dominion", "sci-fi", 2022));
        recommended.add(new Movie("Inception", "sci-fi", 2010));
        recommended.add(new Movie("Interstellar", "sci-fi", 2014));
        recommended.add(new Movie("The Martian", "sci-fi", 2015));
    }


    // MODIFIES: this
    // EFFECTS: adds all horror movies into database
    public void addHorror() {
        recommended.add(new Movie("Psycho", "horror", 1960));
        recommended.add(new Movie("The Exorcist", "horror", 1973));
        recommended.add(new Movie("Carrie", "horror", 1976));

        recommended.add(new Movie("The Shining", "horror", 1980));
        recommended.add(new Movie("Friday the 13th", "horror", 1980));
        recommended.add(new Movie("A Nightmare on Elm Street", "horror", 1984));

        recommended.add(new Movie("The Silence of the Lambs", "horror", 1991));
        recommended.add(new Movie("The Blair Witch Project", "horror", 1999));
        recommended.add(new Movie("Sleepy Hollow", "horror", 1999));

        recommended.add((new Movie("American Psycho", "horror", 2000)));
        recommended.add(new Movie("Saw", "horror", 2004));
        recommended.add(new Movie("The Mist", "horror", 2007));
        recommended.add(new Movie("Orphan", "horror", 2009));

        recommended.add(new Movie("The Conjuring", "horror", 2013));
        recommended.add(new Movie("Get Out", "horror", 2017));
        recommended.add(new Movie("Hereditary", "horror", 2018));
        recommended.add(new Movie("A Quiet Place", "horror", 2018));
        recommended.add(new Movie("Us", "horror", 2019));
    }

    // MODIFIES: this
    // EFFECTS: adds all action movies into database
    public void addAction() {
        recommended.add(new Movie("Dirty Harry", "action", 1971));
        recommended.add(new Movie("Enter the Dragon", "action", 1973));
        recommended.add(new Movie("The Three Musketeers", "action", 1973));

        recommended.add(new Movie("Top Gun", "action", 1986));
        recommended.add(new Movie("Batman", "action", 1989));
        recommended.add(new Movie("Predator", "action", 1987));
        recommended.add(new Movie("The Running Man", "action", 1987));

        recommended.add(new Movie("Terminator 2: Judgment Day", "action", 1991));
        recommended.add(new Movie("Mission Impossible", "action", 1996));
        recommended.add(new Movie("The Avengers", "action", 1998));

        recommended.add(new Movie("Fast & Furious 4", "action", 2009));
        recommended.add(new Movie("Pirates of the Caribbean: The Curse of the Black Pearl",
                "action", 2003));
        recommended.add(new Movie("Gladiator", "action", 2000));
        recommended.add(new Movie("Iron Man", "action", 2008));

        recommended.add(new Movie("Guardians of the Galaxy", "action", 2014));
        recommended.add(new Movie("Black Panther", "action", 2018));
        recommended.add(new Movie("Spider-Man: No Way Home", "action", 2021));
        recommended.add(new Movie("Avengers: Endgame", "action", 2019));
    }

    // MODIFIES: this
    // EFFECTS: returns list of titles of all the movies in the database
    public ArrayList<String> printAllMovies() {
        for (Movie m : recommended) {
            names.add(m.getTitle());
        }
        return names;
    }

    // MODIFIES: this
    // EFFECTS: adds movies matching the giving genre from the database to new list
    public void filterGenre(String s) {
        filteredGenre = new ArrayList<>();
        for (Movie m : recommended) {
            if (s.equals(m.getGenre())) {
                filteredGenre.add(m);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: filters the filteredGenre list and keeps movies matching the given release date
    public ArrayList<String> filterDate(String s) {
        filteredReleaseDate = new ArrayList<>();
        if (s.equals("a")) {
            before1980();
        } else {
            if (s.equals("b")) {
                before1990();
            } else {
                if (s.equals("c")) {
                    before2000();
                } else {
                    if (s.equals("d")) {
                        before2010();
                    } else {
                        before2022();
                    }
                }
            }
        }
        return movieToString(filteredReleaseDate);
    }


    // MODIFIES: this
    // EFFECTS: filters filteredGenre list and keeps movies with release date < 1980
    public void before1980() {
        for (Movie m : filteredGenre) {
            if (m.getReleaseDate() < 1980) {
                filteredReleaseDate.add(m);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: filters filteredGenre list and keeps movies with 1980 <= release date < 1990
    public void before1990() {
        for (Movie m : filteredGenre) {
            if (m.getReleaseDate() >= 1980 && m.getReleaseDate() < 1990) {
                filteredReleaseDate.add(m);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: filters filteredGenre list and keeps movie if 1990 <= release date < 2000
    public void before2000() {
        for (Movie m : filteredGenre) {
            if (m.getReleaseDate() >= 1990 && m.getReleaseDate() < 2000) {
                filteredReleaseDate.add(m);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: filters filteredGenre list and keeps movie if 2000 <= release date < 2010
    public void before2010() {
        for (Movie m : filteredGenre) {
            if (m.getReleaseDate() >= 2000 && m.getReleaseDate() < 2010) {
                filteredReleaseDate.add(m);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: filters filteredGenre list and keeps movies with  2010 <= release date <= 2022
    public void before2022() {
        for (Movie m : filteredGenre) {
            if (m.getReleaseDate() >= 2010 && m.getReleaseDate() <= 2022) {
                filteredReleaseDate.add(m);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: returns list of names of all the movies in the database
    public ArrayList<String> getMovieNames() {
        stringNames = new ArrayList<>();
        recommended = new ArrayList<>();
        addMovies();
        stringNames.addAll(movieToString(recommended));
        return stringNames;
    }

    // MODIFIES: this
    // EFFECTS: returns list containing titles of all the movies in the given list
    public ArrayList<String> movieToString(ArrayList<Movie> listOfMovies) {
        movieNames = new ArrayList<>();
        for (Movie m : listOfMovies) {
            movieNames.add(m.getTitle());
        }
        return movieNames;
    }

    // REQUIRES: Movie is not null
    // MODIFIES: this
    // EFFECTS: adds given movie to database
    public void addMovie(Movie m) {
        recommended.add(m);
    }

    //getters:

    public Movie getMovie(String s) {
        recommended = new ArrayList<>();
        addMovies();
        for (Movie m : recommended) {
            if (s.equals(m.getTitle())) {
                return m;
            }
        }
        return null;
    }

    public ArrayList getRecommended() {
        return recommended;
    }

    public ArrayList getNames() {
        return names;
    }

    public ArrayList getFilteredGenre() {
        return filteredGenre;
    }

}


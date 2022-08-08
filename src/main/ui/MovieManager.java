package ui;

import model.Favourites;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// based on Teller app; link below
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git

// based on JsonSerializationDemo ; link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Movie Manager application
public class MovieManager {

    private Favourites favourites;
    private Scanner input;
    private Boolean keepGoing;
    private MovieList recommended;
    private static final String JSON_STORE = "./data/favourites.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the movie application
    public MovieManager() {
        runMovie();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMovie() {
        keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you, have a good day!");
    }

    // MODIFIES: this
    // EFFECTS: initializes program
    private void init() {
        favourites = new Favourites();
        recommended = new MovieList();
        recommended.addMovies();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nYou can request the following information:");
        System.out.println("\t'm' to receive movie recommendations");
        System.out.println("\t'f' to view or add to your Favourites Album");
        System.out.println("\t'a' to view all movies available");
        System.out.println("\t'r' to remove movies from your Favourites Album");
        System.out.println("\t's' -> save your favourites to file");
        System.out.println("\t'l' -> load your favourites from file");
        System.out.println("\t'q' to quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        keepGoing = true;
        if (command.equals("m")) {
            doRecommendation();
        } else if (command.equals("a")) {
            System.out.println("Thank you for your interest! Here are all the movies available in our database: ");
            viewAllMovies();
        } else if (command.equals("q")) {
            keepGoing = false;
        } else if (command.equals("f")) {
            viewFavourites();
        } else if (command.equals("r")) {
            removeMovie();
        } else if (command.equals("s")) {
            saveFavourites();
        } else if (command.equals("l")) {
            loadFavourites();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void removeMovie() {
        System.out.println("\nPlease type the movie you would like to remove.");

        String command = input.next();

        if (favourites.removeMovieFromFavourites(command)) {
            System.out.println("Your movie has been removed.");
            endOptions();
        } else {
            System.out.println("This is an invalid entry. Please try again.");
            removeMovie();
        }
    }


    // MODIFIES: this
    // EFFECTS: prompts user to select a genre and filters movie collection for that genre
    private void doRecommendation() {
        System.out.println("\nPlease type the genre you want:");
        System.out.println("\thorror");
        System.out.println("\taction");
        System.out.println("\tromance");
        System.out.println("\tsci-fi");
        System.out.println("\t'q' to quit");

        String command = input.next();
        command = command.toLowerCase();

        keepGoing = true;

        if (command.equals("horror") || (command.equals("action")) || (command.equals("romance"))
                || (command.equals("sci-fi"))) {
            recommended.filterGenre(command);
            filterReleaseDate();
        } else if (command.equals("q")) {
            keepGoing = false;
        } else {
            System.out.println("Sorry, invalid input. Please try again.");
            doRecommendation();
        }
    }

    // MODIFIES: this
    // EFFECTS: prints out movies in the favourites album and returns to menu
    public void viewFavourites() {
        if (favourites.viewFavourites() == null) {
            System.out.println("You currently have no movies in your Favourites Album.");
            endOptions();
        } else {
            System.out.println("Here are your favourite movies: ");
            for (String movie : (favourites.viewFavourites())) {
                System.out.println(movie);
            }
            endOptions();
        }
    }

    // EFFECTS: prints out names of all movies in database
    public void viewAllMovies() {
        for (String s : recommended.printAllMovies()) {
            System.out.println(s);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a release date and filters movie collection for that date
    public void filterReleaseDate() {
        keepGoing = true;
        System.out.println("\nPlease choose a release date for your movie:");
        System.out.println("\t'a' for before 1980");
        System.out.println("\t'b' for 1980-1989");
        System.out.println("\t'c' for 1990-1999");
        System.out.println("\t'd' for 2000-2009");
        System.out.println("\t'e' for 2010-2022");
        System.out.println("\t'q' to quit");

        String command = input.next();
        command = command.toLowerCase();

        if (command.equals("a") || (command.equals("b")) || (command.equals("c"))
                || (command.equals("d")) || (command.equals("e"))) {
            System.out.println("Great choice! Here is your personalized recommendation list: "
                    + recommended.filterDate(command));
            endOptions();

        } else {
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                System.out.println("Sorry, invalid input. Please try again");
                filterReleaseDate();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select their next step after receiving a recommendation
    public void endOptions() {
        keepGoing = true;
        System.out.println("\nHow would you like to proceed?");
        System.out.println("\t'i' to input a movie into your Favourites Album");
        System.out.println("\t'm' to return to main menu");
        System.out.println("\t'q' to quit");

        String command = input.next();
        command = command.toLowerCase();

        handleEndOptions(command);
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    public void handleEndOptions(String fav) {
        keepGoing = true;
        if (fav.equals("q")) {
            keepGoing = false;
        } else {
            if (fav.equals("i")) {
                System.out.println("\tPlease type the movie you would like to add.");
                String command = input.next();
                findMovie(command);

            } else {
                if (fav.equals("m")) {
                    System.out.println("Returning to main menu...");

                } else {
                    System.out.println("Sorry, invalid input. Please try again.");
                    endOptions();
                }
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: if movie is in database and is not already in favourites, adds movie to favourites, else prompts
    // user to retype the movie they wish to add
    public void findMovie(String s) {
        keepGoing = true;
        try {
            favourites.addMovieToFavourites(s);
        } catch (Exception e) {
            System.out.println("This is an invalid or duplicate entry. Please try again.");
            handleEndOptions("i");
        }
        System.out.println("Your movie has been added.");
        showFavourites();
        endOptions();


    }

    // MODIFIES: this
    // EFFECTS: informs user if favourites album is empty, else print out list of titles of movies in favourites album
    public void showFavourites() {
        if (favourites.viewFavourites() == null) {
            System.out.println("You currently have no movies in your Favourites Album.");
        } else {
            System.out.println("Here are your favourite movies: ");
            for (String name : (favourites.viewFavourites())) {
                System.out.println(name);
            }
        }
    }


    // EFFECTS: saves the favourites album to file
    private void saveFavourites() {
        try {
            jsonWriter.open();
            jsonWriter.write(favourites);
            jsonWriter.close();
            System.out.println("Saved your Favourites Album to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads favourites album from file
    private void loadFavourites() {
        try {
            favourites = jsonReader.read();
            System.out.println("Loaded Favourites Album" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}






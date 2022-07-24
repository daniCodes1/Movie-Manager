package ui;

import model.Favourites;
import model.Movie;
import model.MovieList;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieManager {
    // Movie manager application

    private Favourites favourites;
    private Scanner input;
    private Boolean keepGoing;
    private MovieList recommended;
    private String command;


    // EFFECTS: runs the movie application
    public MovieManager() {
        runMovie();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMovie() {     // this was originally runTeller --> I changed it to runMovie
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
    // EFFECTS: processes user command
    private void processCommand(String command) {
        keepGoing = true;
        if (command.equals("r")) {
            doRecommendation();
        }
        if (command.equals("a")) {
            System.out.println("Thank you for your interest! Here are all the movies available in our database: ");
            for (String s : recommended.printAllMovies()) {
                System.out.println(s);
            }
        }
        if (command.equals("q")) {
            keepGoing = false;
        }
        if (command.equals("f")) {
            if (favourites.viewFavourites() == null) {
                System.out.println("You currently have no movies in your Favourites Album.");
                callFavourites();
            } else {
                System.out.println("Here are your favourite movies: ");
                System.out.println(favourites.viewFavourites());
                callFavourites();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes program
    private void init() {
        favourites = new Favourites();
        recommended = new MovieList();
        recommended.addMovies();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nYou can request the following information:");
        System.out.println("\t'r' to receive movie recommendations");
        System.out.println("\t'f' to view or add to your favourites album");
        System.out.println("\t'a' to view all movies available");
        System.out.println("\t'q' to quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void doRecommendation() {
        System.out.println("\nPlease type the genre you want:");
        System.out.println("\thorror");
        System.out.println("\taction");
        System.out.println("\tromance");
        System.out.println("\tsci-fi");
        System.out.println("\t'q' to quit");

        String command = input.next();
        command = command.toLowerCase();

        filterGenreOut(command);

    }

    public void filterGenreOut(String s) {
        keepGoing = true;

        if (s.equals("horror") || (s.equals("action")) || (s.equals("romance"))
                || (s.equals("sci-fi"))) {
            recommended.filterGenre(s);
            chooseReleaseDate();
        } else if (s.equals("q")) {
            keepGoing = false;
        } else {
            System.out.println("Sorry, invalid input. Please try again.");
            doRecommendation();
        }

    }


    public void chooseReleaseDate() {

        System.out.println("\nPlease choose a release date for your movie:");
        System.out.println("\t'0' for before 1990");
        System.out.println("\t'1' for 1990-2000");
        System.out.println("\t'2' for 2000-2010");
        System.out.println("\t'3' for 2010-2022");
        System.out.println("\t'q' to quit");

        String command = input.next();
        command = command.toLowerCase();

        filterDateOut(command);

    }

    public void filterDateOut(String s) {
        keepGoing = true;

        if (s.equals("0") || (s.equals("1")) || (s.equals("2"))
                || (s.equals("3"))) {
            System.out.println("Great choice! Here is your personalized recommendation list: "
                    + recommended.filterDate(s));
            callFavourites();

        } else {
            if (s.equals("q")) {
                keepGoing = false;
            } else {
                System.out.println("Sorry, invalid input. Please try again.");
                chooseReleaseDate();
            }
        }
    }


    public void callFavourites() {
        System.out.println("\nHow would you like to proceed?");
        System.out.println("\t'i' to input a favourite movie");
        System.out.println("\t'm' to return to main menu");
        System.out.println("\t'q' to quit");

        String command = input.next();
        command = command.toLowerCase();

        handleFavourites(command);
    }

    public void handleFavourites(String fav) {
        keepGoing = true;
        if (fav.equals("i")) {
            System.out.println("\tPlease type the movie you would like to add.");
            String command = input.next();
            //command = command.toLowerCase();
            favourites.addMovieToFavourites(command);
            if (favourites.viewFavourites() == null) {
                System.out.println("You currently have no movies in your Favourites Album.");
            } else {
                System.out.println("Here are your favourite movies: " + favourites.viewFavourites());
                callFavourites();
            }
        } else {
            if (fav.equals("q")) {
                keepGoing = false;
            } else {
                if (fav.equals("m")) {
                    System.out.println("Returning to main menu...");
                } else {
                    System.out.println("Sorry, invalid input. Please try again");
                    callFavourites();
                }
            }
        }
    }
}





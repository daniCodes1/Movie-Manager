package ui;

import model.Favourites;
import model.MovieList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.exceptions.DuplicateException;
import model.exceptions.NotInDatabaseException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// based on demo code within the link given to us in the EdX page:
// https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application


// Graphics user interface for Movie Manager Application
public class MovieManagerUI extends JFrame implements ActionListener, KeyListener {

    private static final String JSON_STORE = "./data/favourites.json";
    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private ImageIcon icon;
    private ImageIcon familyMovies;
    private ImageIcon error;
    private JPanel mainScreen;
    private JPanel buttonsPanel;
    private JLabel mainText;
    private JLabel title;
    private JPanel windowPanel;
    private JPanel moviesPanel;
    private JButton buttonAddMovie;
    private JButton buttonRemoveMovie;
    private JButton buttonSaveMovies;
    private JButton buttonLoadMovies;
    private JButton buttonToQuit;
    private JButton buttonToGetRecommendation;
    private JButton buttonToViewAll;
    private Favourites movies;
    private Favourites saved;
    private MovieList allMovies;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // constructor that sets up the movie manager application
    public MovieManagerUI() {
        this.movies = new Favourites();
        this.mainScreen = new JPanel();
        this.moviesPanel = new JPanel();
        this.icon = new ImageIcon("./data/movieReel.png");
        this.familyMovies = new ImageIcon("./data/picWithText.jpeg");
        this.error = new ImageIcon("./data/errorImage.jpeg");
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
        this.mainText = new JLabel();
        this.buttonsPanel = new JPanel();
        this.windowPanel = new JPanel();
        this.allMovies = new MovieList();

        initializeProgram();

    }

    // EFFECTS: sets up visual window for favourite movies and user options
    public void initializeProgram() {
        setTitle("Movie Manager");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        initializePanels();
        initializeButtons();

        doHomeScreen();
        addKeyListener(this);
        setFocusable(true);

        JLabel welcome = new JLabel("Welcome to your movie finder! Press 0 on your keyboard "
                + "to receive movie recommendations, or press 1 to view all movies available.");
        welcome.setFont(new Font("Arial", Font.PLAIN, 15));
        JOptionPane.showConfirmDialog(mainScreen, welcome,
                "Welcome", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
    }


    // EFFECTS: initializes all the JPanels for buttons and the upper/lower backgrounds
    private void initializePanels() {

        mainScreen.setBackground(Color.WHITE);

        buttonsPanel.setLayout(new GridLayout(6, 2));
        buttonsPanel.setBackground(new Color(255, 221, 244));

        windowPanel.setLayout(new BorderLayout());
        windowPanel.setBackground(new Color(203, 195, 227));

        title = new JLabel("Welcome to your MOVIE MANAGER!");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(new Color(203, 195, 227));
        title.setFont(new Font("Arial", Font.BOLD, 35));

        add(windowPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        add(mainScreen, BorderLayout.CENTER);
        add(title, BorderLayout.NORTH);
    }

    // EFFECTS: creates main panel where movies have been added to favourites list
    public void doHomeScreen() {
        if (movies.getSize() == 0) {
            mainText.setFont(new Font("SansSerif", Font.BOLD, 17));
            mainText.setText("You currently have no movies saved.");

            mainScreen.removeAll();
            mainScreen.add(mainText);

        } else {
            ArrayList<String> names = movies.namesOfMovies();
            String[] str = new String[names.size()];
            JList<String> list = new JList<>(names.toArray(str));
            mainScreen.removeAll();
            list.setFont(new Font("SansSerif", Font.BOLD, 17));
            mainScreen.add(list);
        }
        revalidate();
        repaint();
    }

    // learned how to use JButtons from Java Oracle:
    // https://docs.oracle.com/javase/8/docs/api/javax/swing/JButton.html
    // EFFECTS: makes buttons and initializes buttons panel
    public void initializeButtons() {
        buttonToGetRecommendation = makeButton("Receive a recommendation");
        buttonAddMovie = makeButton("Add Favourite Movie");
        buttonRemoveMovie = makeButton("Remove Movie");
        buttonSaveMovies = makeButton("Save favourites");
        buttonLoadMovies = makeButton("Load favourites");
        buttonToViewAll = makeButton("View all movies");
        buttonToQuit = makeButton("Quit program");


        buttonsPanel.add(buttonToGetRecommendation);
        buttonsPanel.add(buttonAddMovie);
        buttonsPanel.add(buttonRemoveMovie);
        buttonsPanel.add(buttonSaveMovies);
        buttonsPanel.add(buttonLoadMovies);
        buttonsPanel.add(buttonToViewAll);
        buttonsPanel.add(buttonToQuit);

        buttonAddMovie.addActionListener(this);
        buttonRemoveMovie.addActionListener(this);
        buttonSaveMovies.addActionListener(this);
        buttonLoadMovies.addActionListener(this);
        buttonToViewAll.addActionListener(this);
        buttonToGetRecommendation.addActionListener(this);
        buttonToQuit.addActionListener(this);
    }

    // EFFECTS: initializes each button
    private JButton makeButton(String phrase) {
        JButton newButton = new JButton(phrase);
        newButton.setBackground(Color.PINK);
        newButton.setFont(new Font("SansSerif", Font.BOLD, 17));
        return newButton;
    }

    // MODIFIES: this
    // EFFECTS: connects each button event with associated action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonAddMovie) {
            addMovie();
            doHomeScreen();
        } else if (e.getSource() == buttonRemoveMovie) {
            if (movies.getSize() != 0) {
                removeMovie();
            }
        } else if (e.getSource() == buttonSaveMovies) {
            saveFavourites();
        } else if (e.getSource() == buttonToViewAll) {
            viewAllMovies();
        } else if (e.getSource() == buttonLoadMovies) {
            loadFavourites();
        } else if (e.getSource() == buttonToGetRecommendation) {
            getRecommendation();
        } else if (e.getSource() == buttonToQuit) {
            System.exit(0);
        }
    }

    // learned to use Scroll Panes from Java Oracle:
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
    // EFFECTS: displays all movies in the database in scrollable form
    public void viewAllMovies() {
        JFrame database = new JFrame("All");
        String message = "\n Here are all the movies:  \n ";
        for (String movieName : allMovies.getMovieNames()) {
            message += "\n\n" + movieName;
        }

        JTextArea textArea = new JTextArea(15, 25);
        textArea.setEditable(false);
        textArea.setText(message);
        textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(database, scrollPane);
    }

    // EFFECTS: filters movie database by user's genre preference
    public void getRecommendation() {
        moviesPanel = new JPanel();
        moviesPanel.setSize(10, 10);
        JTextField genre = new JTextField(10);
        JTextField releaseDate = new JTextField(10);
        JLabel genreLabel = new JLabel("<html> Enter your preferred genre:<br/>"
                + "'action' <br/> 'horror'  <br/> 'romance' <br/>  'sci-fi' <html> ");
        JLabel dateLabel = new JLabel("<html> Enter your preferred release date: <br/> "
                + "'a' for before 1980 <br/>" + "'b' for 1980-1989 <br/>"
                + "'c' for 1990-1999 <br/>" + "'d' for 2000-2009 <br/>"
                + "'e' for 2010-2022 <html>");
        moviesPanel.add(genreLabel);
        moviesPanel.add(genre);
        moviesPanel.add(dateLabel);
        moviesPanel.add(releaseDate);

        doResponse(releaseDate, genre);
    }

    // EFFECTS: filters movie database by user's release date preference, then displays subset of movies
    public void doResponse(JTextField releaseDate, JTextField genre) {

        int answer = JOptionPane.showConfirmDialog(mainScreen, moviesPanel,
                "Preferences", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon);
        if (answer == JOptionPane.OK_OPTION) {
            if (releaseDate.getText().isEmpty() | genre.getText().isEmpty()) {
                JFrame empty = new JFrame("Empty");
                JOptionPane.showMessageDialog(empty, "Please input both preferences before continuing");
                getRecommendation();
            } else {
                allMovies = new MovieList();
                allMovies.addMovies();
                allMovies.filterGenre(genre.getText());
                ArrayList<String> finalRecs = allMovies.filterDate(releaseDate.getText());
                JFrame recs = new JFrame("recs");
                String recommendations = "\n Here are your movie recommendations:  \n ";
                for (String movie : finalRecs) {
                    recommendations += "\n\n" + movie;
                }
                JTextArea text = new JTextArea(recommendations);
                JOptionPane.showConfirmDialog(mainScreen, text, "Name", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }

    // EFFECTS: prompts user to input their favourite movie
    public void addMovie() {
        moviesPanel = new JPanel();
        JTextField movieInput = new JTextField(20);
        JLabel enterName = new JLabel("Enter movie name: ");
        moviesPanel.add(enterName);
        moviesPanel.add(movieInput);
        int answer = JOptionPane.showConfirmDialog(mainScreen, moviesPanel, "Add a movie",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
        doAnswer(answer, movieInput);
    }

    // MODIFIES: this
    // EFFECTS: adds user movie input into favourites and displays it on the movies panel
    public void doAnswer(int answer, JTextField movieInput) {
        if (answer == JOptionPane.OK_OPTION) {
            try {
                movies.addMovieToFavourites(movieInput.getText());
                JFrame frame = new JFrame();
                frame.setVisible(true);
                frame.setLayout(new BorderLayout());
                frame.setMinimumSize(new Dimension(400, 300));
                JLabel label = new JLabel(familyMovies);
                frame.add(label);

            } catch (NotInDatabaseException e) {
                JFrame notInDatabase = new JFrame("Not in Database");
                JOptionPane.showMessageDialog(notInDatabase, "\"This movie is not in our database. "
                        + "Please try again. If you need help choosing, "
                        + "you can choose to view all movies or receive a recommendation!\"\n");
            } catch (DuplicateException e) {
                JFrame duplicateMovie = new JFrame("Duplicate Movie");
                JOptionPane.showMessageDialog(duplicateMovie, "\"Duplicate Movie\"\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the movie that the user inputs from movie panel
    public void removeMovie() {
        moviesPanel = new JPanel();
        JTextField movieInput = new JTextField(20);
        JLabel enterName = new JLabel("Enter the name of movie to delete: ");
        moviesPanel.add(enterName);
        moviesPanel.add(movieInput);

        int answer = JOptionPane.showConfirmDialog(mainScreen, moviesPanel,
                "Remove Movie", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

        if (answer == JOptionPane.OK_OPTION) {
            if (!movies.removeMovieFromFavourites(movieInput.getText())) {
                JLabel notInFavourites = new JLabel("This movie is not in your list of favourites.");
                JOptionPane.showConfirmDialog(mainScreen, notInFavourites, "Saved", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, error);
            } else {
                doHomeScreen();
                JLabel removed = new JLabel("Movie removed!");
                JOptionPane.showConfirmDialog(mainScreen, removed, "Removed", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, icon);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads favourites list from file
    public void loadFavourites() {
        try {
            movies = jsonReader.read();
            saved = jsonReader.read();
            List<String> names = saved.namesOfMovies();
            String[] str = new String[names.size()];
            JList<String> favouriteMovies = new JList<>(names.toArray(str));

            mainScreen.removeAll();
            favouriteMovies.setFont(new Font("SansSerif", Font.BOLD, 17));
            mainScreen.add(favouriteMovies);

            revalidate();
            repaint();

            JLabel loaded = new JLabel("Movies loaded!");
            JOptionPane.showConfirmDialog(mainScreen, loaded, "Loaded", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, icon);

        } catch (IOException e) {
            JLabel unloadable = new JLabel("Unable to read from file: " + JSON_STORE);
            JOptionPane.showConfirmDialog(mainScreen, unloadable, "Can't load", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, error);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves favourites list to file
    public void saveFavourites() {
        try {
            jsonWriter.open();
            jsonWriter.write(movies);
            jsonWriter.close();
            JLabel saved = new JLabel("Movies saved!");
            JOptionPane.showConfirmDialog(mainScreen, saved, "Saved", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (FileNotFoundException e) {
            JLabel notFound = new JLabel("Can't save to " + JSON_STORE);
            JOptionPane.showConfirmDialog(mainScreen, notFound, "Can't Save", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, error);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // does nothing
    }

    // EFFECTS: when 0 is pressed, leads user to input recommendations; when 1 is pressed, user can view database
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_0) {
            getRecommendation();
        } else if (e.getKeyCode() == KeyEvent.VK_1) {
            viewAllMovies();
        }
    }

    @Override
    // does nothing
    public void keyReleased(KeyEvent e) {

    }
}
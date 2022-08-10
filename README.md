# *My Personal Project*

## A Movie Manager Application

Hi there! This application is a *movie generator* that helps 
viewers choose a movie to watch. Users have the option to answer questions about their
preferences that pop out in the beginning of the program. 
These are meant to filter out movies based on specifications such as
genre and release date. Additionally, they will be able
to view a database that contains over 70 movies for when they want to just browse around. They will be
able to view and add movies to their *favourites album*, which contains movies from my 
recommendation application that they particularly enjoyed.

## Who is this for? And why?
This application is meant for people who want to watch a movie but are unsure what to watch, or 
people who would like some new movie suggestions, which I'm **really** excited to share!
I think that being able to input personal preferences can help users find movies that reflect their 
own interests and help this application reach a wider audience. My interest comes from 
seeing how films in general reflect culture and are a significant way of connecting to our own 
experiences with people and situations in life. As someone who really values human connections and 
social relationships, I think that a movie generator can serve as a stepping stone for people 
to find a movie that doesn't just entertain but **also communicates** with them in some way! 


## A summary of **user stories** for my project:

- *As a user, I want to be able to save my favourite movies
  into a “favourite album”*
- *As a user, I want to be able to be able to remove movies from my favourite album*
- *As a user, I want to be able to input my movie preferences and receive a movie recommendation*
- *As a user, I want to be able to view a list of all the movies
  for when I don’t have a preference*
- *As a user, I want to be able to view the movies in my favourites album*
- *As a user, when I leave the main menu, I want to be reminded to save my 
   favourites list*
- *As a user, when I start the application, I want to be given the option to load my favourites list
   from file*

## Instructions for grader:
- You can generate the first required event by clicking on "Add Favourite Movie". Then, you will be 
prompted to input a movie that is checked against all the movies in my database. 
  - Note that users cannot add duplicate movies or movies not from the 
  database. There is an option to view all movies or get a movie recommendation if you need suggestions!
- To generate the second required event - this one uses keyboard -  you need to choose to press "i" , 
then input your preferences in each step. This will then give you a filtered list of movies based on your 
preferences. You can press "v" to view the full database. There are also buttons for these functions too.
- You can locate my visual component by clicking on any of these buttons: "Add Movie", "Save favourites",
"View all movies", or "Load favourites". Additionally, there is an
image popup after you enter a favourite movie.
- You can save the state of my application by clicking on "Save favourites".
- You can reload the state of my application by clicking on "Load Favourites".


## Phase 4: Task 2

Tue Aug 09 21:41:02 PDT 2022
<br /> Preferences have been inputted. Based on those preferences, Movie Recommender suggests:
Guardians of the Galaxy, Black Panther, Spider-Man: No Way Home, Avengers: Endgame

Tue Aug 09 21:41:22 PDT 2022
<br /> Movie: Spider-Man: No Way Home has been added to favourites!

Tue Aug 09 21:41:38 PDT 2022
<br /> Movie: Black Panther has been added to favourites!

Tue Aug 09 21:41:49 PDT 2022
<br /> Preferences have been inputted. Based on those preferences, Movie Recommender suggests:
The Bodyguard, Persuasion, Titanic, 10 Things I Hate About You

Tue Aug 09 21:41:57 PDT 2022
<br /> Movie: Persuasion has been added to favourites!

Tue Aug 09 21:42:09 PDT 2022
<br /> Movie: Black Panther has been removed from favourites!

Tue Aug 09 21:42:42 PDT 2022
<br /> Movie: A Quiet Place has been added to favourites!

Tue Aug 09 21:43:09 PDT 2022
<br /> Movie: Spider-Man: No Way Home has been removed from favourites!

Process finished with exit code 0


## Phase 4: Task 3

- If I had more time, I would make my code more robust and use exceptions in my remove function
instead of if statements - for example, when someone inputs a movie that is not in the favourites list
- If I had more time, I would make my Favourites class extend MovieList
- If I had more time, I would extract piece of code that shows the error and confirmation dialogs and make it a 
separate method to avoid duplicate code throughout MovieManagerUI
- If I had more time, I would remove two of the three associations between Movie and MovieList.
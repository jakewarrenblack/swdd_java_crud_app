

package com.company;

import com.mysql.cj.util.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    //The scanner object is used to read in user input. We've imported it above as a java.utils package.
    static Scanner keyboard;

    //The model acts as an intermediary between our Main and our MovieTableGateway.
    static Model model;

    public Main() {
    }

    public static void main(String[] args) {
        //Set to true to get the while loop going, then immediately set to false to not loop by default.
        //'Default' of the switch statements, however, resets this to true, so rather than throwing an error, the statement just returns to the menu.
        boolean restart = true;

        while(restart) {
        restart = false;
        //User could log in as a staff member or customer, there are different functions available to both.
        System.out.println("\n\nWelcome. Select user type:");
        System.out.println("1. Staff member.");
        System.out.println("2. Customer.");

        //This integer will be the command for our switch statement.
        int opt;
        //Calling on our scanner to read input. nextLine just expects a String.
        String line = keyboard.nextLine();
        //Having read in a String using nextLine, we parse an integer from it to use in our switch cases.
        opt = Integer.parseInt(line);

        //Do while wil run until its condition is met. At the bottom of the do{}, we've told it to run unless the input received was '3'.
        //In that case, we'll exit.
            do {
                //Nested switch statement. From here you choose whether to reveal the staff menu, or the user menu.
                switch (opt) {
                    //If the input received was '1'.
                    case 1:

                        System.out.println("\n\n** STAFF MENU **");
                        System.out.println("1. Create new Movie");
                        System.out.println("2. View all Movies");
                        System.out.println("3. Delete a Movie.");
                        System.out.println("4. Update a Movie.");
                        System.out.println("5. View movie by ID");
                        System.out.println("6. Edit movie column by ID");
                        System.out.println("7. View movie by title");
                        System.out.println("8. View movies by premiere date descending.");
                        System.out.println("9. Exit");
                        System.out.println();
                        System.out.print("** Enter option: **\n\n");

                        int opt2;
                        String line2 = keyboard.nextLine();
                        opt2 = Integer.parseInt(line2);

                        //A switch statement allows us to test our variables (opt2 in this case) against a list of cases.
                        //Eg below we tell the switch statement what to do when the case, or value or opt2, is 1.
                        switch (opt2) {
                            case 1:
                                //Creating a new instance of our Movie object.
                                //readMovie will receive user input to be used to construct Movie m.
                                Movie m = readMovie();
                                //Our new movie is returned up to here after execution.
                                //If model.addMovie(m) was successful:
                                //model.addMovie(m) returns true or false, depending on the result of our insertMovie function in the MovieTableGateway.
                                boolean created = model.addMovie(m);
                                if (created) {
                                    System.out.println("** Movie Added to the Database **");
                                } else {
                                    System.out.println("** Movie NOT Added to the Database **");
                                }
                                //Break the execution, and jump to the first line following our switch statement.
                                break;
                            case 2:
                                viewMovies();
                                break;
                            case 3:
                                deleteMovie();
                                break;
                            case 4:
                                updateMovie();
                                break;
                            case 5:
                                viewMovie();
                                break;
                            case 6:
                                updateByColumn();
                                break;
                            case 7:
                                viewMovieByTitle();
                                break;
                            case 8:
                                viewMoviesSortDate();

                            default:
                                //If anything but our assigned case values is pressed, show the menu again.
                                restart = true;
                        }

                        break;

                    case 2:
                        //Customer is only allowed to view movies.
                        System.out.println("\n\n** CUSTOMER MENU **");
                        System.out.println("1. View all Movies");
                        System.out.println("2. View movie by title");
                        System.out.println("3. View all movies sorted by premiere date descending.");
                        System.out.println("4. Exit");
                        System.out.println();
                        System.out.print("** Enter option: **\n\n");
                        int opt3;
                        String line3 = keyboard.nextLine();
                        opt3 = Integer.parseInt(line3);

                        switch (opt3) {
                            case 1:
                                viewMovies();
                                break;
                            case 2:
                                viewMovieByTitle();
                                break;
                            case 3:
                                viewMoviesSortDate();
                                break;
                            default:
                                restart = true;
                        }

                        break;

                    default:
                        //Restart the while loop
                        restart = true;
                }
            //Exit if user inputs 3.
            } while (opt != 3);
            System.out.println("Goodbye!");
        }
    }

    //When the user chooses to insert a movie, we instantiate a new Movie and start executing this function:
    //User input read in here is used to construct the new Movie.
    private static Movie readMovie() {
        //We pass a few different data types to our Scanner object here.
        System.out.print("Enter title : ");
        String title = keyboard.nextLine();
        System.out.print("Enter director : ");
        String director = keyboard.nextLine();
        System.out.print("Enter running time : ");
        float runningTime = keyboard.nextFloat();
        System.out.print("Enter age rating : ");
        int ageRating = keyboard.nextInt();
        keyboard.nextLine();
        System.out.print("Is the movie available in 3D? true or false : ");
        boolean is3d = keyboard.nextBoolean();
        keyboard.nextLine();
        System.out.print("Enter premiere date (YYYY/MM/DD) : ");
        String dateString = keyboard.nextLine();
        //Return the new Movie back up to where readMovie() was called.
        return new Movie(runningTime, title, director, ageRating, dateString, is3d);
    }
//The return type of 'getMovies' in our MovieTableGateway is a list. This function is called from Model in getMovies, which is also of list return type.
//When both run successfully, the list of Movies is returned here.
    private static void viewMovies() {
        //Instantiate an empty arraylist of Movies which calls on getMovies in the model, which in turn calls on getMovies in our MovieTableGateway.
        List<Movie> Movies = model.getMovies();
        System.out.println("** Printing All Movies **");
        //Iterator is imported as a java.util package. We use this to iterate over our Movies arraylist.
        Iterator var1 = Movies.iterator();

        //While our iterator still has another value to go,
        //Instantiate a Movie 'mo' as
        while(var1.hasNext()) {
            //Return the next element in our collection until var.1hasNext is false. We have then reached the end of the arraylist.
            Movie mo = (Movie)var1.next();
            //Print each movie our iterator sees to the screen.
            System.out.println(mo);
        }

        System.out.println("** Finished Printing All Movies **");
    }
    //This works exactly as viewMovies does, but the SQL statement has been modified to also include 'ORDER BY premiereDate DESC'.
    private static void viewMoviesSortDate() {
        List<Movie> Movies = model.getMoviesSortDate();
        System.out.println("** Printing All Movies sorted by date**");
        Iterator var1 = Movies.iterator();

        while(var1.hasNext()) {
            Movie mo = (Movie)var1.next();

            System.out.println(mo);
        }

        System.out.println("** Finished Printing All Movies sorted by date**");
    }

    //This function allows us to view a single movie.
    private static void viewMovie() {
        //Read in an integer to be used as our movie's ID.
        System.out.println("Enter the ID of the movie to view.");
        int id = Integer.parseInt(keyboard.nextLine());

        //Use a list again, but will only read in a single Movie this time. We pass the id we've collected from the user to the Model's getMovie function.
        List<Movie> singleMovie = model.getMovie(id);
        System.out.println("** Printing Movie **");

        //Just as in viewMovies, iterate over the arraylist until no values are left. The iterator will only have to run once here.
        Iterator var1 = singleMovie.iterator();

        System.out.println(var1.next());

        System.out.println("** Finished Printing Movie **");
    }

    //Works the same as viewMovie, but reads in a String as the title instead of an id.
    private static void viewMovieByTitle() {
        System.out.println("Enter the title of the movie to view.");
        String title = keyboard.nextLine();

        List<Movie> singleMovie = model.getMovieByTitle(title);
        System.out.println("** Printing Movie **");
        Iterator var1 = singleMovie.iterator();



            System.out.println(var1.next());

            System.out.println("** Finished Printing Movie **");

    }

    private static void deleteMovie() {
        System.out.print("Enter the ID of the movie to delete:");
        //Read an id to be passed to the model's deleteMovie function.
        int id = Integer.parseInt(keyboard.nextLine());

        //We'll also read a String to ask the user to confirm they want to delete the movie.
        System.out.println("Are you sure you want to delete this movie? Type yes or no.");

        String command = keyboard.nextLine();

        //Better to convert command to lowercase to avoid accounting for case.
        if(command.toLowerCase().equals("yes")){
            //If model.deleteMovie returned true.
            if (model.deleteMovie(id)) {
                System.out.println("\nMovie deleted");
            } else {
                System.out.println("\nMovie not deleted, check your database to see if a movie with this ID actually exists");
            }
        }else if(command.toLowerCase().equals("no")){
            System.out.println("Movie not deleted.");
        }else{
            System.out.println("Bad input. Movie not deleted.");
        }

    }


    private static void updateMovie(){
        System.out.println("Enter the ID of the movie to update.");
        int id = Integer.parseInt(keyboard.nextLine());

        System.out.println("**PLEASE FILL ALL FIELDS**");

        System.out.print("Enter title : ");
        String title = keyboard.nextLine();

        System.out.print("Enter director : ");
        String director = keyboard.nextLine();

        System.out.print("Enter age rating : ");
        int ageRating = Integer.parseInt(keyboard.nextLine());



        System.out.print("Enter running time : ");
        float runningTime = keyboard.nextFloat();
        keyboard.nextLine();

        System.out.print("Is the movie available in 3D? true or false : ");
        boolean is3d = keyboard.nextBoolean();

        keyboard.nextLine();
        System.out.print("Enter premiere date (YYYY/MM/DD) : ");

        //Inside MovieTableGateway, this is wrapped inside: Date.valueOf(dateString));
        //This allows MySql to recognise our dateString as a proper SQL date value.
        String dateString = keyboard.nextLine();


        //Send all the data the user has just given us to the Model's updateMovie() function
        if (model.updateMovie(id, runningTime, title, director, ageRating, dateString, is3d)) {
            //If updateMovie returns true
            System.out.println("\nMovie updated.");
        } else {
            System.out.println("\nMovie not updated, check your database to see if a movie with this ID actually exists");
        }

    }
    //This option redirects the user to a number of different functions with an if else statement.
    //I thought this would make more sense than having an option for each type of update cluttering the main menu.
    public static void updateByColumn(){
        System.out.println("Which column name are you updating?\n**Column options are:**\n1. director\n2. title \n3. age rating \n4. is3d \n5. premiere date \n6. runningTime");
        String column = keyboard.nextLine();

        if(column.equals("director") || column.equals("1")){
            System.out.println("Switching you to " + column + " update function...");
            System.out.println("****");
            updateDirector();
        }
        else if(column.equals("title") || column.equals("2")){
            System.out.println("Switching you to " + column + " update function...");
            System.out.println("****");
            updateTitle();
        }else if(column.equals("age rating") || column.toLowerCase().equals("agerating") || column.equals("3")){
            System.out.println("Switching you to " + column + " update function...");
            System.out.println("****");
            updateAgeRating();
        }else if(column.equals("is3d") || column.equals("4")){
            System.out.println("Switching you to " + column + " update function...");
            System.out.println("****");
            updateIs3d();
        }else if(column.equals("premiere date") || column.toLowerCase().equals("premieredate") || column.equals("5")){
            System.out.println("Switching you to " + column + " update function...");
            System.out.println("****");
            updatePremiereDate();
        }else if(column.equals("running time") || column.toLowerCase().equals("runningTime") || column.equals("6")){
            System.out.println("Switching you to " + column + " update function...");
            System.out.println("****");
            updateRunningTime();
        }
        else{
            System.out.println("Bad input. That column name may not exist. Please check your entry and try again.");
        }
    }

    //These work like a modular version of the updateMovie() function.
    //In each case, we read an ID for the Movie, as well as the data type for whatever column the user is updating.
    public static void updateTitle(){
        System.out.println("Enter the ID of the movie to update.");
        int id = Integer.parseInt(keyboard.nextLine());

        System.out.println("Enter updated title:");
        String title = keyboard.nextLine();

        if(model.updateTitle(id,title)){
            System.out.println("\nTitle updated.");
        }else{
            System.out.println("\nTitle not updated, check the database to see if a movie with this ID actually exists.");
        }
    }
    public static void updateDirector(){
        System.out.println("Enter the ID of the movie to update.");
        int id = Integer.parseInt(keyboard.nextLine());

        System.out.println("Enter updated director:");
        String director = keyboard.nextLine();

        if(model.updateDirector(id,director)){
            System.out.println("\nDirector updated.");
        }else{
            System.out.println("\nDirector not updated, check the database to see if a movie with this ID actually exists.");
        }
    }
    public static void updateAgeRating(){
        System.out.println("Enter the ID of the movie to update.");
        int id = Integer.parseInt(keyboard.nextLine());

        System.out.println("Enter updated ageRating:");
        int ageRating = Integer.parseInt(keyboard.next());

        if(model.updateAgeRating(id,ageRating)){
            System.out.println("\nAge rating updated.");
        }else{
            System.out.println("\nAge rating not updated, check the database to see if a movie with this ID actually exists.");
        }
    }
    public static void updateIs3d(){
        System.out.println("Enter the ID of the movie to update.");
        int id = Integer.parseInt(keyboard.nextLine());

        System.out.println("Enter updated 3D availability:");
        boolean is3d = keyboard.nextBoolean();

        if(model.updateIs3d(id,is3d)){
            System.out.println("\n3D availability updated.");
        }else{
            System.out.println("\n3D availability not updated, check the database to see if a movie with this ID actually exists.");
        }
    }
    public static void updatePremiereDate(){
        System.out.println("Enter the ID of the movie to update.");
        int id = Integer.parseInt(keyboard.nextLine());

        System.out.println("Enter updated premiere date:");
        String dateString = keyboard.nextLine();

        if(model.updatePremiereDate(id,dateString)){
            System.out.println("\nPremiere date updated.");
        }else{
            System.out.println("\nPremiere date not updated, check the database to see if a movie with this ID actually exists.");
        }
    }
    public static void updateRunningTime(){
        System.out.println("Enter the ID of the movie to update.");
        int id = Integer.parseInt(keyboard.nextLine());

        System.out.println("Enter updated premiere date availability:");
        float runningTime = keyboard.nextFloat();

        if(model.updateRunningTime(id,runningTime)){
            System.out.println("\nRunning time availability updated.");
        }else{
            System.out.println("\nRunning time not updated, check the database to see if a movie with this ID actually exists.");
        }
    }

    static {
        //Define our keyboard as an instance of the Scanner object, which will read an input stream from the user.
        keyboard = new Scanner(System.in);
        //Grab a reference to the Model.
        model = Model.getInstance();
    }
}

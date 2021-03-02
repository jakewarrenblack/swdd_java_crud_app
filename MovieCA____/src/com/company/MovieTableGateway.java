
package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Defining our column names from the database.
public class MovieTableGateway {
    private static final String TABLE_NAME = "movies";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_RUNNINGTIME = "runningTime";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DIRECTOR = "director";
    private static final String COLUMN_AGERATING = "ageRating";
    private static final String COLUMN_PREMIERE = "premiereDate";
    private static final String COLUMN_3D = "is3D";
    //Instantiate a connection object.
    private Connection mConnection;

    public MovieTableGateway(Connection connection) {
        this.mConnection = connection;
    }

    //A Movie is passed as a parameter to this method.
    public boolean insertMovie(Movie m) {
        //Our SQL insertion statement. The question marks are replaced in our prepared statement below.
        String query = "INSERT INTO movies (runningTime, title, director, ageRating, premiereDate, is3D) VALUES (?, ?, ?, ?, ?, ?)";

        //Try catch allows us to catch errors. If anything goes wrong, this statement will catch it and show us an error, rather than just crashing the execution.
        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query, 1);
            stmt.setFloat(1, m.getRunningTime());
            stmt.setString(2, m.getTitle());
            stmt.setString(3, m.getDirector());
            stmt.setInt(4, m.getAgeRating());
            stmt.setDate(5, m.getPremiereDate());
            stmt.setBoolean(6, m.getIs3D());
            int numRowsAffected = stmt.executeUpdate();
            //If one row is affected, which should always be the case:
            if (numRowsAffected == 1) {
                //Send true back to our Model, then back to our Main.
                return true;
            }
        } catch (SQLException var6) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : insertMovie(), Check the SQL you have created to see where your error is", var6);
        }

    return false;
}

//Return type of this method is an arraylist.
public List<Movie> getMovies() {
    List<Movie> movies = new ArrayList();
    String query = "SELECT * FROM movies";


    try {
        Statement stmt = this.mConnection.createStatement();
        //A ResultSet is a table of data which represents the data returned from our database.
        ResultSet rs = stmt.executeQuery(query); //We define our ResultSet as the result of our query.

        //This will loop through our ResultSet while there are still values that haven't been passed.
        while(rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String director = rs.getString("director");
            String premiereDate = rs.getString("premiereDate");
            int runningTime = rs.getInt("runningTime");
            int ageRating = rs.getInt("ageRating");
            boolean is3d = rs.getBoolean("is3D");
            //Create a new movie object for each result of our ResultSet, grab the values from the column in each case and drop them into our variables.
            Movie m = new Movie(id, runningTime, title, director, ageRating, premiereDate, is3d);
            //For each item in the ResultSet, we've created a Movie instance and now we'll append this onto our movies arraylist.
            movies.add(m);
        }
    } catch (SQLException var13) {
        Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : getMovies(), Check the SQL you have created to see where your error is", var13);
    }

    //When finished, return our movies arraylist to the Model, which will return the movies arraylist to the Main.
    return movies;
}
    //This method works exactly as the above, but with a different SQL statement to include 'ORDER BY premiereDate DESC'.
    //This will sort our movies ordered by their premiereDates from most recent to oldest.
    public List<Movie> getMoviesSortDate() {
        List<Movie> movies = new ArrayList();
        String query = "SELECT * FROM movies ORDER BY premiereDate DESC";


        try {
            Statement stmt = this.mConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String director = rs.getString("director");
                String premiereDate = rs.getString("premiereDate");
                int runningTime = rs.getInt("runningTime");
                int ageRating = rs.getInt("ageRating");
                boolean is3d = rs.getBoolean("is3D");
                Movie m = new Movie(id, runningTime, title, director, ageRating, premiereDate, is3d);
                movies.add(m);
            }
        } catch (SQLException var13) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : getMovies(), Check the SQL you have created to see where your error is", var13);
        }

        return movies;
    }

    //This method works similarly to our getMovies() method, but we pass an id into this function.
    public List<Movie> getMovieById(int _id) {
        //Another difference here is we're using a fixed size arraylist, since we'll only ever need one. I thought an ordinary arraylist might be a waste of space.
        List<Movie> singleMovie = Arrays.asList(new Movie[1]);
        //Our ID will be inserted in the preparedStatement.
        String query = "SELECT * FROM movies WHERE id= ?";

        try {

            PreparedStatement stmt = this.mConnection.prepareStatement(query);
            stmt.setInt(1, _id);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String director = rs.getString("director");
                    String premiereDate = rs.getString("premiereDate");
                    int runningTime = rs.getInt("runningTime");
                    int ageRating = rs.getInt("ageRating");
                    boolean is3d = rs.getBoolean("is3D");
                    Movie m = new Movie(id, runningTime, title, director, ageRating, premiereDate, is3d);
                    //Drop our movie into the first and only index of our singleMovie arraylist.
                    singleMovie.set(0,m);

                }
                return singleMovie;
        }
        catch (SQLException var13) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : getMovieById(), Check the SQL you have created to see where your error is", var13);
        }

        return singleMovie;
    }

    //Same as above, but we accept a String (title) as our parameter and run our SQL query using this instead.
    public List<Movie> getMovieByTitle(String _title) {
        List<Movie> singleMovie = Arrays.asList(new Movie[1]);
        String query = "SELECT * FROM movies WHERE title= ?";

        try {

            PreparedStatement stmt = this.mConnection.prepareStatement(query);
            stmt.setString(1, _title);
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String director = rs.getString("director");
                String premiereDate = rs.getString("premiereDate");
                int runningTime = rs.getInt("runningTime");
                int ageRating = rs.getInt("ageRating");
                boolean is3d = rs.getBoolean("is3D");
                Movie m = new Movie(id, runningTime, title, director, ageRating, premiereDate, is3d);
                singleMovie.set(0,m);

            }
            return singleMovie;
        }
        catch (SQLException var13) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : getMovieById(), Check the SQL you have created to see where your error is", var13);
        }

        return  singleMovie;
    }

    //Works similarly to retrieving a single movie, except we're performing an operation on the movie rather than just retrieving it.
    public boolean deleteMovie(int id) {
        String query = "DELETE FROM movies WHERE id= ?";

        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query);
            //Insert our ID
            stmt.setInt(1, id);
            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
            //The difference is here. We use stmt.executeUpdate() rather than stmt.executeQuery().
            int numRowsAffected = stmt.executeUpdate();
            //If one row is affected, which should always be the case, return true.
            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException var5) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : deleteMovie(), Check the SQL you have created to see where your error is", var5);
        }

        return false;
    }

    //This is a broad update statement.
    public boolean updateMovie(int id, float runningTime, String title, String director, int ageRating, String dateString, boolean is3d){

        String query="UPDATE movies SET runningTime = COALESCE(?, runningTime), title = COALESCE(NULLIF(?,''),title),director = COALESCE(NULLIF(?,''),director),ageRating = COALESCE(?,ageRating),premiereDate = COALESCE(?,premiereDate),is3D = COALESCE(?,is3D) WHERE id = ?";
        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query);;

            stmt.setFloat(1,runningTime);
            stmt.setString(2,title);
            stmt.setString(3,director);
            stmt.setInt(4,ageRating);
            stmt.setDate(5, Date.valueOf(dateString));
            stmt.setBoolean(6,is3d);
            stmt.setInt(7,id);

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
            int numRowsAffected = stmt.executeUpdate();
            if (numRowsAffected == 1) {
                return true;
            }
        } catch (SQLException var5) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : updateMovie(), Check the SQL you have created to see where your error is", var5);
        }

        return false;
    }

    public boolean updateTitle(int _id, String _title){
        String query="UPDATE movies SET title = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query);;
            stmt.setString(1,_title);
            stmt.setInt(2,_id);

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
            int numRowsAffected = stmt.executeUpdate();
            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException var5) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : updateTitle(), Check the SQL you have created to see where your error is", var5);
        }
        return false;
    }

    public boolean updateDirector(int _id, String _director){
        String query="UPDATE movies SET director = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query);;
            stmt.setString(1,_director);
            stmt.setInt(2,_id);

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
            int numRowsAffected = stmt.executeUpdate();
            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException var5) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : updateDirector(), Check the SQL you have created to see where your error is", var5);
        }
        return false;
    }

    public boolean updateAgeRating(int _id, int _ageRating){
        String query="UPDATE movies SET ageRating = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query);;
            stmt.setInt(1,_ageRating);
            stmt.setInt(2,_id);

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
            int numRowsAffected = stmt.executeUpdate();
            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException var5) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : updateDirector(), Check the SQL you have created to see where your error is", var5);
        }
        return false;
    }

    public boolean updateIs3d(int _id, boolean _is3d){
        String query="UPDATE movies SET is3d = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query);;
            stmt.setBoolean(1,_is3d);
            stmt.setInt(2,_id);

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
            int numRowsAffected = stmt.executeUpdate();
            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException var5) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : updateIs3d(), Check the SQL you have created to see where your error is", var5);
        }
        return false;
    }

    public boolean updateRunningTime(int _id, float _runningTime){
        String query="UPDATE movies SET runningTime = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query);;
            stmt.setFloat(1,_runningTime);
            stmt.setInt(2,_id);

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
            int numRowsAffected = stmt.executeUpdate();
            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException var5) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : updateIs3d(), Check the SQL you have created to see where your error is", var5);
        }
        return false;
    }

    public boolean updatePremiereDate(int _id, String _dateString){
        String query="UPDATE movies SET runningTime = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.mConnection.prepareStatement(query);;
            stmt.setDate(1, Date.valueOf(_dateString));
            stmt.setInt(2,_id);

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");
            int numRowsAffected = stmt.executeUpdate();
            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException var5) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in MovieTableGateway : updateIs3d(), Check the SQL you have created to see where your error is", var5);
        }
        return false;
    }






}

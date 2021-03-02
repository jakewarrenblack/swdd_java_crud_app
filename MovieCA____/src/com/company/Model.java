

package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Our model will act as an intermediary between the Main and the MovieTableGateway.
public class Model {
    private static Model instance = null;
    private List<Movie> Movies;
    private MovieTableGateway gateway;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }

        return instance;
    }

    private Model() {
        //Construct the model as an instance of our database connection.
        try {
            //attempt to make a connection.
            Connection conn = DBConnection.getInstance();
            this.gateway = new MovieTableGateway(conn);
        } catch (ClassNotFoundException var2) {
            //if the connection fails, post the SQLException.
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "Problem Connecting to the Database, have you added your JDBC Driver .jar file?", var2);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    //All of these functions are called from Main. They each call on their equivalents in MovieTableGateway,
    //returning a boolean or whatever data is being retrieved in each case.

    public List<Movie> getMovies() {
        return this.gateway.getMovies();
    }

    public List<Movie> getMoviesSortDate() {
        return this.gateway.getMoviesSortDate();
    }

    public List<Movie> getMovie(int id) {
        return this.gateway.getMovieById(id);
    }

    public List<Movie> getMovieByTitle(String title) {
        return this.gateway.getMovieByTitle(title);
    }

    public boolean addMovie(Movie m) {
        //We check the result of this in Main to tell the user whether or not the insert has succeeded.
        return this.gateway.insertMovie(m);
    }

    public boolean deleteMovie(int id) {
        return this.gateway.deleteMovie(id);
    }

    public boolean updateMovie(int id, float runningTime, String title, String director, int ageRating, String dateString, boolean is3d){
        return this.gateway.updateMovie(id,runningTime, title, director, ageRating, dateString, is3d);
    }

    public boolean updateTitle(int id, String title){
        return this.gateway.updateTitle(id,title);
    }

    public boolean updateDirector(int id, String director){
        return this.gateway.updateDirector(id,director);
    }

    public boolean updateAgeRating(int id, int ageRating){
        return this.gateway.updateAgeRating(id,ageRating);
    }

    public boolean updateIs3d(int id, boolean is3d){
        return this.gateway.updateIs3d(id,is3d);
    }

    public boolean updatePremiereDate(int id,String dateString){
        return this.gateway.updatePremiereDate(id,dateString);
    }
    public boolean updateRunningTime(int id,float runningTime){
        return this.gateway.updateRunningTime(id,runningTime);
    }
}

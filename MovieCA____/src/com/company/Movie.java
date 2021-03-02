
package com.company;

import java.sql.Date;

//Here we're defining our actual Movie class.
public class Movie {
    //The characteristics that make up our Movie.
    private int id;
    private float runningTime;
    private String title;
    private String director;
    private int ageRating;
    private Date premiereDate;
    private boolean is3D;

    //Default constructor with no ID.
    public Movie(float rt, String t, String d, int a, String dt, boolean is3dTemp) {
        this.runningTime = rt;
        this.title = t;
        this.director = d;
        this.ageRating = a;
        this.premiereDate = Date.valueOf(dt);
        this.is3D = is3dTemp;
    }

    //Default constructor with an ID.
    public Movie(int id, float rt, String t, String d, int a, String dt, boolean is3dTemp) {
        this.id = id;
        this.runningTime = rt;
        this.title = t;
        this.director = d;
        this.ageRating = a;
        this.premiereDate = Date.valueOf(dt);
        this.is3D = is3dTemp;
    }



    //Get and set methods for every variable in our Movie.
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRunningTime() {
        return this.runningTime;
    }

    public void setRunningTime(float runningTime) {
        this.runningTime = runningTime;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return this.director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAgeRating() {
        return this.ageRating;
    }

    public void setAgeRating(int ageRating) {
        this.ageRating = ageRating;
    }

    public void setPremiereDate(Date premiereDate) {
        this.premiereDate = premiereDate;
    }

    public Date getPremiereDate() {
        return this.premiereDate;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean getIs3D() {
        return this.is3D;
    }

    //A toString() 'utility' function which will return our Movie's characteristics as a String.
    public String toString() {
        return "Movie id = " + this.id + ", runningTime = " + this.runningTime + " hours, title = '" + this.title + "', director = '" + this.director + "', ageRating = " + this.ageRating + ", premiereDate = " + this.premiereDate + ", is3D = " + this.is3D;
    }
}

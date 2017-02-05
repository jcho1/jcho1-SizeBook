package com.example.jeff.jcho1_sizebook;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Class that implements records of user
 */

public class Record {

    private String date;
    private String name;
    private String comment;
    private Measurements measurements;

    public Record(String name, String date){
        this.date = date;
        this.name = name;
    }

    public Record(String name){
        // get today from date and change to preferred string format
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date today = new Date();
        this.date = dateFormat.format(today);
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Measurements getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Measurements measurements) {
        this.measurements = measurements;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() { return name + " | " + date; }

}

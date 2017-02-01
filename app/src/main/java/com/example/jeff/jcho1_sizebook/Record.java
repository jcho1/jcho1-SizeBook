package com.example.jeff.jcho1_sizebook;

import java.util.Date;

/**
 * Created by Jeff on 1/19/2017.
 */

public class Record {

    private Date date;
    private String name;
    private String comment;
    private Measurements measurements;

    public Record(String name, Date date){
        this.date = date;
        this.name = name;
    }

    public Record(String name){
        this.date = new Date();
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
}

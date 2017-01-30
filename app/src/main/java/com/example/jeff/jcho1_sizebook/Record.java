package com.example.jeff.jcho1_sizebook;

import java.util.Date;

/**
 * Created by Jeff on 1/19/2017.
 */

public class Record {

    private Date date;
    private String name;
    private float neck;
    private float bust;
    private float chest;
    private float waist;
    private float hip;
    private float inseam;
    private String comment;

    public Record(String name, Date date){
        this.date = date;
        this.name = name;
    }

    public Record(String name){
        this.date = new Date();
        this.name = name;
    }

    public float getNeck() {
        return neck;
    }

    public void setNeck(float neck) {
        this.neck = neck;
    }

    public float getBust() {
        return bust;
    }

    public void setBust(float bust) {
        this.bust = bust;
    }

    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getHip() {
        return hip;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }

    public float getInseam() {
        return inseam;
    }

    public void setInseam(float inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

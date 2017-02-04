package com.example.jeff.jcho1_sizebook;

import java.util.Date;

/**
 * Created by jcho1 on 1/31/17.
 */

public class Measurements{
    private float neck;
    private float bust;
    private float chest;
    private float waist;
    private float hip;
    private float inseam;

    public Measurements(){
        this.neck=0;
        this.bust=0;
        this.chest=0;
        this.waist=0;
        this.hip=0;
        this.inseam=0;
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
}

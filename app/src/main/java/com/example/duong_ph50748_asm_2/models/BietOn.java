package com.example.duong_ph50748_asm_2.models;

public class BietOn {
    private String loiBietOn;
    private int idbieton;

    public BietOn(String loiBietOn) {
        this.loiBietOn = loiBietOn;
    }

    public BietOn(){};

    public int getIdbieton() {
        return idbieton;
    }

    public void setIdbieton(int idbieton) {
        this.idbieton = idbieton;
    }

    public String getLoiBietOn() {
        return loiBietOn;
    }

    public void setLoiBietOn(String loiBietOn) {
        this.loiBietOn = loiBietOn;
    }
}

package com.mobisem.engineering.Models;

import java.util.Date;

public class MeditationModel {

    private int ID;
    private double result;


    public int getID() {
        return ID;
    }

    public MeditationModel setID(int ID) {
        this.ID = ID;
        return this;
    }

    public double getResult() {
        return result;
    }

    public MeditationModel setResult(double result) {
        this.result = result;
        return this;
    }
}

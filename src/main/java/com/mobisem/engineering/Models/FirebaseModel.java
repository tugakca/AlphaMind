package com.mobisem.engineering.Models;

import java.sql.Time;
import java.util.Date;

public class FirebaseModel {


    private int meditationID;
    private Date time;
    private String uuid;
     private Double base;
    private Double breathing;
    private Double mindless;
    private Double visulization;

    public Double getBase() {
        return base;
    }

    public FirebaseModel setBase(Double base) {
        this.base = base;
        return this;
    }

    public int getMeditationID() {
        return meditationID;
    }

    public FirebaseModel setMeditationID(int meditationID) {
        this.meditationID = meditationID;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public FirebaseModel setTime(Date time) {
        this.time = time;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public FirebaseModel setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Double getBreathing() {
        return breathing;
    }

    public FirebaseModel setBreathing(Double breathing) {
        this.breathing = breathing;
        return this;
    }

    public Double getMindless() {
        return mindless;
    }

    public FirebaseModel setMindless(Double mindless) {
        this.mindless = mindless;
        return this;
    }

    public Double getVisulization() {
        return visulization;
    }

    public FirebaseModel setVisualization(Double visulization) {
        this.visulization = visulization;
        return this;
    }
}

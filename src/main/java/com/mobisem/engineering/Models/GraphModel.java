package com.mobisem.engineering.Models;

public class GraphModel {

    private double base;
    private double breathing;
    private double mindless;
    private double visualization;


    public double getBase() {
        return base;
    }

    public GraphModel setBase(double base) {
        this.base = base;
        return this;
    }

    public double getBreathing() {
        return breathing;
    }

    public GraphModel setBreathing(double breathing) {
        this.breathing = breathing;
        return this;
    }

    public double getMindless() {
        return mindless;
    }

    public GraphModel setMindless(double mindless) {
        this.mindless = mindless;
        return this;
    }

    public double getVisualization() {
        return visualization;
    }

    public GraphModel setVisualization(double visualization) {
        this.visualization = visualization;
        return this;
    }
}

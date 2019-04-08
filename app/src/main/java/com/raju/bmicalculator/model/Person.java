package com.raju.bmicalculator.model;

import java.text.DecimalFormat;

public class Person {

    private double height, weight;

    public Person(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double calculateBMI() {

        return Double.parseDouble(new DecimalFormat("#.0").format(getWeight() / (getHeight() * getHeight())));
    }
}

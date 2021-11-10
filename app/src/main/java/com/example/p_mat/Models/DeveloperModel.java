package com.example.p_mat.Models;

public class DeveloperModel {
    String name,id;
    double match;

    public DeveloperModel(String name, String id, double match) {
        this.name = name;
        this.id = id;
        this.match = match;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getMatch() {
        return match;
    }
}

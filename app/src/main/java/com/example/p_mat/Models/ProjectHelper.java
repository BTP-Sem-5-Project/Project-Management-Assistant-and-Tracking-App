package com.example.p_mat.Models;

import java.util.List;

public class ProjectHelper {
    String Description;
    String Name;
    String projectManager;
    List<String> members;

    public ProjectHelper(String description, String name, String projectManager, List<String> members) {
        Description = description;
        Name = name;
        this.projectManager = projectManager;
        this.members = members;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}

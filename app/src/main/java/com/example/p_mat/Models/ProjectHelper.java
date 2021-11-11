package com.example.p_mat.Models;

import java.util.List;

public class ProjectHelper {
    String Description;
    String Name;
    String projectManager;
    List<String> members;
    String Organization;
    String GitHub;

    public ProjectHelper() {
    }

    public ProjectHelper(String description, String name, String projectManager, List<String> members, String Organization,String GitHub) {
        Description = description;
        Name = name;
        this.projectManager = projectManager;
        this.members = members;
        this.Organization = Organization;
        this.GitHub = GitHub;
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

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        Organization = organization;
    }

    public String getGitHub() {
        return GitHub;
    }

    public void setGitHub(String gitHub) {
        GitHub = gitHub;
    }
}

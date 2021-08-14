package com.example.p_mat.Models;

import java.util.List;

public class OrganizationHelper {
    String Name;
    String Rank;
    List<String> members;
    String CEO;
    String HR;
    List<List<String>> projects;

    public OrganizationHelper() {
    }

    public OrganizationHelper(String name, String rank, List<String> members, String CEO, String HR, List<List<String>> projects) {
        Name = name;
        Rank = rank;
        this.members = members;
        this.CEO = CEO;
        this.HR = HR;
        this.projects = projects;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getCEO() {
        return CEO;
    }

    public void setCEO(String CEO) {
        this.CEO = CEO;
    }

    public String getHR() {
        return HR;
    }

    public void setHR(String HR) {
        this.HR = HR;
    }

    public List<List<String>> getProjects() {
        return projects;
    }

    public void setProjects(List<List<String>> projects) {
        this.projects = projects;
    }
}

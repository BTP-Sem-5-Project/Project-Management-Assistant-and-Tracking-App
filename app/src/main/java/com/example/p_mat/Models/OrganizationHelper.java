package com.example.p_mat.Models;

import java.util.List;

public class OrganizationHelper {
    String Name;
    String CEO;
    String HR;
    List<String> members;
    List<String> projects;

    public OrganizationHelper() {
    }

    public OrganizationHelper(String name, String CEO, String HR, List<String> members, List<String> projects) {
        Name = name;
        this.CEO = CEO;
        this.HR = HR;
        this.members = members;
        this.projects = projects;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }
}

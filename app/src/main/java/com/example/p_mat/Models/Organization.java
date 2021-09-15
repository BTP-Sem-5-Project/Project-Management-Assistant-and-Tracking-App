package com.example.p_mat.Models;

import java.util.List;

public class Organization {
    public String name;
    public List<CEO> ceo;
    public List<HR> hr;
    public List<ProjectManager> projectManager;
    public List<Developer> developer;
    public List<Project> project;
    public List<PublicNotice> publicNotice;

    public Organization(){

    }

    public Organization(String name, List<CEO> ceo, List<HR> hr, List<ProjectManager> projectManager, List<Developer> developer, List<Project> project, List<PublicNotice> publicNotice) {
        this.name = name;
        this.ceo = ceo;
        this.hr = hr;
        this.projectManager = projectManager;
        this.developer = developer;
        this.project = project;
        this.publicNotice = publicNotice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CEO> getCeo() {
        return ceo;
    }

    public void setCeo(List<CEO> ceo) {
        this.ceo = ceo;
    }

    public List<HR> getHr() {
        return hr;
    }

    public void setHr(List<HR> hr) {
        this.hr = hr;
    }

    public List<ProjectManager> getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(List<ProjectManager> projectManager) {
        this.projectManager = projectManager;
    }

    public List<Developer> getDeveloper() {
        return developer;
    }

    public void setDeveloper(List<Developer> developer) {
        this.developer = developer;
    }

    public List<Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

    public List<PublicNotice> getPublicNotice() {
        return publicNotice;
    }

    public void setPublicNotice(List<PublicNotice> publicNotice) {
        this.publicNotice = publicNotice;
    }
}
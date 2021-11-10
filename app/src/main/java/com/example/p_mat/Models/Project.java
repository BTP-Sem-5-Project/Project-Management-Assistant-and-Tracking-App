package com.example.p_mat.Models;

import java.util.List;

public class Project{
    public String name;
    public String github;
    public List<Developer> developer;
    public List<Notice> notice;
    public List<Task> task;
    public ProjectManager projectManager;

    public Project(){

    }

    public Project(String name,String github, List<Developer> developer, List<Notice> notice, List<Task> task, ProjectManager projectManager) {
        this.name = name;
        this.github = github;
        this.developer = developer;
        this.notice = notice;
        this.task = task;
        this.projectManager = projectManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Developer> getDeveloper() {
        return developer;
    }

    public void setDeveloper(List<Developer> developer) {
        this.developer = developer;
    }

    public List<Notice> getNotice() {
        return notice;
    }

    public void setNotice(List<Notice> notice) {
        this.notice = notice;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }
}
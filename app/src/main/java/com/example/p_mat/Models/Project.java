package com.example.p_mat.Models;

import java.util.List;

public class Project{
    public String name;
    public Developer teamLead;
    public List<Developer> developer;
    public List<Notice> notice;
    public List<Task> task;

    public Project(){

    }

    public Project(String name, Developer teamLead, List<Developer> developer, List<Notice> notice, List<Task> task) {
        this.name = name;
        this.teamLead = teamLead;
        this.developer = developer;
        this.notice = notice;
        this.task = task;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Developer getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(Developer teamLead) {
        this.teamLead = teamLead;
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
}
package com.example.p_mat.Models;

public class NoticeHelper {
    String CreatedDate; // DDMMYYYY
    String CreatedTime; // HHMM
    String Title; // Not more than 25 characters
    String Description; // Not more than 60 characters
    String AssignedProject; // Assigned Email

    public NoticeHelper(){

    }

    public NoticeHelper(String createdDate, String createdTime, String title, String description, String assignedProject) {
        CreatedDate = createdDate;
        CreatedTime = createdTime;
        Title = title;
        Description = description;
        AssignedProject = assignedProject;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAssignedProject() {
        return AssignedProject;
    }

    public void setAssignedProject(String assignedProject) {
        AssignedProject = assignedProject;
    }
}

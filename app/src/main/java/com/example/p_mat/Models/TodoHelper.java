package com.example.p_mat.Models;

public class TodoHelper {
    String DeadlineDate; // DDMMYYYY
    String DeadlineTime; // HHMM
    String Title; // Not more than 25 characters
    String Description; // Not more than 60 characters
    Boolean Completed; // true or false
    String AssignedEmail; // Assigned Email

    public TodoHelper(){
        // empty constructor for error free firebase working
    }

    public TodoHelper(String deadlineDate, String deadlineTime, String title, String description, Boolean completed, String assignedEmail) {
        DeadlineDate = deadlineDate;
        DeadlineTime = deadlineTime;
        Title = title;
        Description = description;
        Completed = completed;
        AssignedEmail = assignedEmail;
    }

    public String getDeadlineDate() {
        return DeadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        DeadlineDate = deadlineDate;
    }

    public String getDeadlineTime() {
        return DeadlineTime;
    }

    public void setDeadlineTime(String deadlineTime) {
        DeadlineTime = deadlineTime;
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

    public Boolean getCompleted() {
        return Completed;
    }

    public void setCompleted(Boolean completed) {
        Completed = completed;
    }

    public String getAssignedEmail() {
        return AssignedEmail;
    }

    public void setAssignedEmail(String assignedEmail) {
        AssignedEmail = assignedEmail;
    }
}

package com.example.p_mat.Models;


public class Task{
    public String heading;
    public String content;
    public String createdBy;
    public String createdAt;
    public String state;

    public Task(){

    }

    public Task(String heading, String content, String createdBy, String createdAt, String state) {
        this.heading = heading;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.state = state;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
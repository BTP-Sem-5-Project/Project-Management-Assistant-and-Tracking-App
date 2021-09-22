package com.example.p_mat.Models;

public class PublicNotice{
    public String heading;
    public String content;
    public String createdBy;
    public String createdAt;
    public String deadline;

    public PublicNotice(){

    }

    public PublicNotice(String heading, String content, String createdBy, String createdAt, String deadline) {
        this.heading = heading;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.deadline = deadline;
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}

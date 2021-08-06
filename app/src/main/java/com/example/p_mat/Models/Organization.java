package com.example.p_mat.Models;

import java.util.List;

class CEO{
    public String name, id;

    public CEO(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class HR{
    public String name, id;

    public HR(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class ProjectManager{
    public String name, id;

    public ProjectManager(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Developer{
    public String name, id;

    public Developer(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

class Notice{
    public String heading;
    public String content;
    public String createdBy;
    public String createdAt;
    public String deadline;

    public Notice(String heading, String content, String createdBy, String createdAt, String deadline) {
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

class Task{
    public String heading;
    public String content;
    public String createdBy;
    public String createdAt;
    public String state;

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

class Project{
    public String name;
    public Developer teamLead;
    public List<Developer> developer;
    public List<Notice> notice;
    public List<Task> task;

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

class PublicNotice{
    public String heading;
    public String content;
    public String createdBy;
    public String createdAt;
    public String deadline;

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

public class Organization {
    public String name;
    public List<CEO> ceo;
    public List<HR> hr;
    public List<ProjectManager> projectManager;
    public List<Developer> developer;
    public List<Project> project;
    public List<PublicNotice> publicNotice;

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

package com.example.p_mat.Models;

import java.util.List;

public class User {
    public String id,name,github,email,phone,qualification,linkedin,password;
    public int experience;
    List<String> skills;
    Boolean available;

    public User() {
    }

    public User(String id, String name, String github, String email, String phone, String qualification, String linkedin, String password, int experience, List<String> skills, Boolean available) {
        this.id = id;
        this.name = name;
        this.github = github;
        this.email = email;
        this.phone = phone;
        this.qualification = qualification;
        this.linkedin = linkedin;
        this.password = password;
        this.experience = experience;
        this.skills = skills;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

}
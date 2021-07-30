package com.example.p_mat.Models;

import java.util.List;

public class User {
    public String id,name,github,email,resume,phone,address,qualification;
    public int experience;
    List<String> skills,achievements;

    public User() {
    }

    public User(String id,String name, String github, String email, String resume, String phone, String address, String qualification, int experience, List<String> skills, List<String> achievements) {
        this.id = id;
        this.name = name;
        this.github = github;
        this.email = email;
        this.resume = resume;
        this.phone = phone;
        this.address = address;
        this.qualification = qualification;
        this.experience = experience;
        this.skills = skills;
        this.achievements = achievements;
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

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
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

    public List<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<String> achievements) {
        this.achievements = achievements;
    }
}

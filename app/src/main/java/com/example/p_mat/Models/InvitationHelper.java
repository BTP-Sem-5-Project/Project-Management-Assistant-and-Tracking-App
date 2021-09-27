package com.example.p_mat.Models;

public class InvitationHelper {
    String Title;
    String Email;

    public InvitationHelper(){

    }
    public InvitationHelper(String title, String email) {
        Title = title;
        Email = email;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

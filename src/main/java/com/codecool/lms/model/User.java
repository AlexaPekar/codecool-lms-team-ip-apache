package com.codecool.lms.model;

public class User {

    private String name;
    private String email;
    private String password;
    private boolean connected;
    private GitHub github;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.connected = false;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public GitHub getGitHub() {
        return github;
    }

    public void setGitHub(GitHub gitHub) {
        this.github = gitHub;
    }
}
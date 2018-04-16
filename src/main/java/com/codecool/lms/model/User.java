package com.codecool.lms.model;

public class User {
    private final int id;
    private String name;
    private final String email;
    private String password;
    private boolean connected;
    private GitHub github;

    User(int id, String name, String email, String password) {
        this.id = id;
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
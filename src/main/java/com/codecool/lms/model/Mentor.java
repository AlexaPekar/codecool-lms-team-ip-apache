package com.codecool.lms.model;

public class Mentor extends User {

    private boolean connected;
    private GitHub github;


    public Mentor(String name, String email, String password) {
        super(name, email, password);
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

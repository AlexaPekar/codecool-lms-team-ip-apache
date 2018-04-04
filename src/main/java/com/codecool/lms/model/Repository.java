package com.codecool.lms.model;

public class Repository {
    private String html;
    private String name;
    private int stars;
    private int watchers;
    private String forks;

    public Repository(String html, String name, int stars, int watchers, String forks) {
        this.html = html;
        this.name = name;
        this.stars = stars;
        this.watchers = watchers;
        this.forks = forks;
    }

    public String getHtml() {
        return html;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    public int getWatchers() {
        return watchers;
    }

    public String getForks() {
        return forks;
    }
}

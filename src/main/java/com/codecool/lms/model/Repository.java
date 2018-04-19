package com.codecool.lms.model;

public class Repository {
    private final int id;
    private final String html;
    private final String name;
    private final int stars;
    private final int watchers;
    private final String forks;

    public Repository(int id, String html, String name, int stars, int watchers, String forks) {
        this.id = id;
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

    public int getId() {
        return id;
    }
}

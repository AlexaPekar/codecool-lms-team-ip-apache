package com.codecool.lms.model;

import java.util.List;

public class GitHub {
    private String avatar;
    private String html;
    private int repos;
    private int gists;
    private int followers;
    private int following;
    private String company;
    private String blog;
    private String location;
    private String created;
    private List<Repository> repositories;


    public GitHub(String avatar, String html, int repos, int gists, int followers, int following, String company, String blog, String location, String created, List<Repository> repositories) {
        this.avatar = avatar;
        this.html = html;
        this.repos = repos;
        this.gists = gists;
        this.followers = followers;
        this.following = following;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.created = created;
        this.repositories = repositories;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getHtml() {
        return html;
    }

    public int getRepos() {
        return repos;
    }

    public int getGists() {
        return gists;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getCompany() {
        return company;
    }

    public String getBlog() {
        return blog;
    }

    public String getLocation() {
        return location;
    }

    public String getCreated() {
        return created;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }
}

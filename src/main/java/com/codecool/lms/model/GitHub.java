package com.codecool.lms.model;

import java.util.List;

public class GitHub {
    private final String avatar;
    private final String html;
    private final int repos;
    private final int gists;
    private final int followers;
    private final int following;
    private final String company;
    private final String blog;
    private final String location;
    private final String created;
    private final List<Repository> repositories;


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

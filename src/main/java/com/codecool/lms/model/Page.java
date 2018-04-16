package com.codecool.lms.model;

public abstract class Page {

    private int id;
    private String title;
    private String content;
    private boolean published;


    Page(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.published = false;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isPublished() {
        return published;
    }

    public void publish() {
        this.published = true;
    }

    public void depublish() {
        this.published = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

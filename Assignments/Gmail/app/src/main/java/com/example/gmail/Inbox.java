package com.example.gmail;

public class Inbox {
    private String sender;
    private String title;
    private String content;
    private String time;
    private boolean favorite;

    public Inbox(String sender, String title, String content, String time, boolean favorite) {
        this.sender = sender;
        this.title = title;
        this.content = content;
        this.time = time;
        this.favorite = favorite;
    }

    public String getSender() {
        return sender;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

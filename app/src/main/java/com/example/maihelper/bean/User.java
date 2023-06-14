package com.example.maihelper.bean;

public class User {
    private String username;//用户名

    private String nickname;//昵称

    private String rating;//等级分
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public User(String username, String nickname, String rating) {
        this.username = username;
        this.nickname = nickname;
        this.rating = rating;
    }

}

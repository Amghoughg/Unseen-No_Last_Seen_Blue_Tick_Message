package com.example.unseenmessagesfha.Models;

import io.realm.RealmObject;

public class AllChat extends RealmObject {
    private String Title;
    private String DateTime;
    private byte[] Avatar;
    private String AppName;
    private int Counter;

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public int getCounter() {
        return Counter;
    }

    public void setCounter(int counter) {
        Counter = counter;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public byte[] getAvatar() {
        return Avatar;
    }

    public void setAvatar(byte[] avatar) {
        Avatar = avatar;
    }
}

package com.example.unseenmessagesfha.Models;


import io.realm.RealmObject;

public class Messages extends RealmObject {

    private String Title;
    private String Text;
    private String TimeDate;
    private String DayMonth;
    private byte[] bitmap;
    private String Time;
    private String AppName;

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Messages(String title, String text, String timeDate, String DayMonth, byte[] bitmap) {
        Title = title;
        Text = text;
        TimeDate = timeDate;
        this.DayMonth = DayMonth;
        this.bitmap = bitmap;
    }

    public Messages() {
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTimeDate() {
        return TimeDate;
    }

    public void setTimeDate(String timeDate) {
        TimeDate = timeDate;
    }

    public String getDayMonth() {
        return DayMonth;
    }

    public void setDayMonth(String dayMonth) {
        DayMonth = dayMonth;
    }
}

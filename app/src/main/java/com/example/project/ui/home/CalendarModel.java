package com.example.project.ui.home;

public class CalendarModel {

    private int day;
    private String date;
    private int state;

    public CalendarModel(int day, String date, int state) {
        this.day = day;
        this.date = date;
        this.state = state;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}

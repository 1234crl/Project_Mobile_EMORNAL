package com.example.project.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "News")
public class News implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String duration;
    private int idGambar;
    private String detailNews;

    // Constructor
    public News(String title, String description, String duration, int idGambar, String detailNews) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.idGambar = idGambar;
        this.detailNews = detailNews;
    }

    public String getDetailNews() {
        return detailNews;
    }

    public void setDetailNews(String detailNews) {
        this.detailNews = detailNews;
    }

    protected News(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        duration = in.readString();
        idGambar = in.readInt();
        detailNews = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getIdGambar() {
        return idGambar;
    }

    public void setIdGambar(int idGambar) {
        this.idGambar = idGambar;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(duration);
        parcel.writeInt(idGambar);
        parcel.writeString(detailNews);
    }
}


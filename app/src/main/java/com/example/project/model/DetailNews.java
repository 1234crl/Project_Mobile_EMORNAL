package com.example.project.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DetailNews", primaryKeys = {"id", "idNews"})
public class DetailNews implements Parcelable {

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "idNews")
    private int idNews;

    @ColumnInfo(name = "fullDescription")
    private String fullDescription;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "publishedDate")
    private String publishedDate;

    protected DetailNews(Parcel in) {
        id = in.readInt();
        idNews = in.readInt();
        fullDescription = in.readString();
        author = in.readString();
        publishedDate = in.readString();
    }

    public DetailNews(int id, int idNews, String fullDescription, String author, String publishedDate) {
        this.id = id;
        this.idNews = idNews;
        this.fullDescription = fullDescription;
        this.author = author;
        this.publishedDate = publishedDate;
    }

    public static final Creator<DetailNews> CREATOR = new Creator<DetailNews>() {
        @Override
        public DetailNews createFromParcel(Parcel in) {
            return new DetailNews(in);
        }

        @Override
        public DetailNews[] newArray(int size) {
            return new DetailNews[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idNews);
        dest.writeString(fullDescription);
        dest.writeString(author);
        dest.writeString(publishedDate);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
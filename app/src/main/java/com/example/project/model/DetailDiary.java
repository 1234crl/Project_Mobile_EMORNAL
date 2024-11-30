package com.example.project.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DetailDiary", primaryKeys = {"id", "idEmotions"})
public class DetailDiary implements Parcelable {

    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "idEmotions")
    private int idEmotions;
    @ColumnInfo(name = "jumlah")
    private int jumlah;
    @ColumnInfo(name = "status")
    private boolean status;

    protected DetailDiary(Parcel in) {
        id = in.readInt();
        idEmotions = in.readInt();
        jumlah = in.readInt();
        status = in.readByte() != 0;
    }

    public DetailDiary(int id, int idEmotions, int jumlah, boolean status) {
        this.id = id;
        this.idEmotions = idEmotions;
        this.jumlah = jumlah;
        this.status = status;
    }

    public static final Creator<DetailDiary> CREATOR = new Creator<DetailDiary>() {
        @Override
        public DetailDiary createFromParcel(Parcel in) {
            return new DetailDiary(in);
        }

        @Override
        public DetailDiary[] newArray(int size) {
            return new DetailDiary[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(jumlah);
        dest.writeInt(idEmotions);
        dest.writeByte((byte) (status ? 1 : 0));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getIdEmotions() {
        return idEmotions;
    }

    public void setIdEmotions(int idEmotions) {
        this.idEmotions = idEmotions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}


package com.example.project.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project.model.Diary;

import java.util.List;

@Dao
public interface DiaryDao {

    @Insert
    void insert(Diary diary);

    @Update
    void update(Diary diary);

    @Delete
    void delete(Diary diary);

    @Query("SELECT * FROM Diary ORDER BY id asc")
    LiveData<List<Diary>> getAll();
}
package com.example.project.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project.model.Emotions;

import java.util.List;

@Dao
public interface EmotionsDao {

    @Insert
    void insert(Emotions[] emotions);

    @Update
    void update(Emotions emotions);

    @Delete
    void delete(Emotions emotions);

    @Query("SELECT * FROM Emotions ORDER BY id asc")
    LiveData<List<Emotions>> getAll();

    @Query("SELECT COUNT(*) FROM Emotions")
    int getCount();
}

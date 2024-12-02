package com.example.project.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.project.model.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(News... news);

    @Query("SELECT * FROM News")
    LiveData<List<News>> getAllNews();

    @Query("SELECT COUNT(*) FROM News")
    int getCount();
}


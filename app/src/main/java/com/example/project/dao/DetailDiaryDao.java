package com.example.project.dao;//package com.example.projectdatabasemobile.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project.model.DetailDiary;
import com.example.project.model.Diary;
import com.example.project.model.Emotions;

import java.util.List;

@Dao
public interface DetailDiaryDao {
    @Insert
    void insert(DetailDiary detailDiary);

    @Update
    void update(DetailDiary detailDiary);

    @Delete
    void delete(DetailDiary detailDiary);

    @Query("SELECT * FROM DetailDiary WHERE id = :id")
    DetailDiary getById(int id);

    @Query("SELECT * FROM DetailDiary")
    LiveData<List<DetailDiary>> getAll();

    @Query("SELECT * FROM DetailDiary WHERE idEmotions = :idEmotions AND id = :idDiary")
    LiveData<List<DetailDiary>> getByDiaryAndEmotionId(int idEmotions, int idDiary);

    @Query("SELECT * FROM DetailDiary WHERE status = :status")
    LiveData<List<DetailDiary>> getByStatus(boolean status);

}

package com.example.project.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.project.dao.DiaryDao;
import com.example.project.database.EmornalRoomDatabase;
import com.example.project.model.Diary;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiaryRepository {
    private final DiaryDao diaryDao;

    private final ExecutorService executorService;

    public DiaryRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();

        EmornalRoomDatabase db = EmornalRoomDatabase.getInstance(application);
        diaryDao = db.diaryDao();
    }

    public LiveData<List<Diary>> getAll() {
        return diaryDao.getAll();
    }

    public void insert (final Diary diary) {executorService.execute(()->diaryDao.insert(diary));}
    public void delete (final Diary diary) {executorService.execute(()->diaryDao.delete(diary));}

    public void update (final Diary diary) {executorService.execute(()->diaryDao.update(diary));}

}

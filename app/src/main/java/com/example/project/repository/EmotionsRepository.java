package com.example.project.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.project.dao.EmotionsDao;
import com.example.project.database.EmornalRoomDatabase;
import com.example.project.model.Emotions;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmotionsRepository {

    private EmotionsDao emotionsDao;
    private ExecutorService executorService;

    public EmotionsRepository(Application application){
        executorService = Executors.newSingleThreadExecutor();

        EmornalRoomDatabase database = EmornalRoomDatabase.getInstance(application);
        emotionsDao = database.emotionsDao();
    }

    public LiveData<List<Emotions>> getAllEmotions() {
        return emotionsDao.getAll();
    }
}

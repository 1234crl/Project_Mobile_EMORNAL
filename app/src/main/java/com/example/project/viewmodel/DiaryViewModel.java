package com.example.project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project.dao.EmotionsDao;
import com.example.project.database.EmornalRoomDatabase;
import com.example.project.model.Diary;
import com.example.project.model.Emotions;
import com.example.project.repository.DiaryRepository;
import com.example.project.repository.EmotionsRepository;

import java.util.List;

public class DiaryViewModel extends AndroidViewModel {

    private DiaryRepository repository;
    public LiveData<List<Diary>> allDiary;

    public DiaryViewModel(@NonNull Application application) {
        super(application);
        EmotionsDao emotionsDao = EmornalRoomDatabase.getInstance(application).emotionsDao();
        repository = new DiaryRepository(application);
        allDiary = repository.getAll();
    }

    public void insert(Diary diary) { repository.insert(diary); }

    public void update(Diary diary) { repository.update(diary); }

    public void delete(Diary diary) { repository.delete(diary); }

}

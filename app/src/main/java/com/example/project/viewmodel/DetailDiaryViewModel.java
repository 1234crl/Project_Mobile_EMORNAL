package com.example.project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project.dao.EmotionsDao;
import com.example.project.database.EmornalRoomDatabase;
import com.example.project.model.DetailDiary;
import com.example.project.model.Emotions;
import com.example.project.repository.DetailDiaryRepository;
import com.example.project.repository.EmotionsRepository;

import java.util.List;

public class DetailDiaryViewModel extends AndroidViewModel {

    private DetailDiaryRepository repository;
    public LiveData<List<DetailDiary>> allDetailDiary;

    public DetailDiaryViewModel(@NonNull Application application) {
        super(application);
        EmotionsDao emotionsDao = EmornalRoomDatabase.getInstance(application).emotionsDao();
        repository = new DetailDiaryRepository(application);
        allDetailDiary = repository.getAll();
    }

    public void insert(DetailDiary detailDiary) { repository.insert(detailDiary); }

    public void update(DetailDiary detailDiary) { repository.update(detailDiary); }

    public void delete(DetailDiary detailDiary) { repository.delete(detailDiary); }
}

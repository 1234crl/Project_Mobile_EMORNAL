package com.example.project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project.dao.EmotionsDao;
import com.example.project.database.EmornalRoomDatabase;
import com.example.project.model.Emotions;
import com.example.project.repository.EmotionsRepository;

import java.util.List;

public class EmotionsViewModel extends AndroidViewModel {

    private EmotionsRepository repository;
    public LiveData<List<Emotions>> allEmotions;

    public EmotionsViewModel(@NonNull Application application) {
        super(application);
        EmotionsDao emotionsDao = EmornalRoomDatabase.getInstance(application).emotionsDao();
        repository = new EmotionsRepository(application);
        allEmotions = repository.getAllEmotions();
    }

    public LiveData<List<Emotions>> getAllEmotions() {
        return repository.getAllEmotions();
    }

}

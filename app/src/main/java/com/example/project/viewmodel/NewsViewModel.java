package com.example.project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project.dao.NewsDao;
import com.example.project.database.EmornalRoomDatabase;
import com.example.project.model.Emotions;
import com.example.project.model.News;
import com.example.project.repository.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository repository;
    public LiveData<List<News>> allNews;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        NewsDao newsDao= EmornalRoomDatabase.getInstance(application).newsDao();
        repository = new NewsRepository(application);
    }

    public LiveData<List<News>> getAllNews() {
        return repository.getAllNews();
    }


}

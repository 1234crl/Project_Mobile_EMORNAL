package com.example.project.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.project.dao.NewsDao;
import com.example.project.database.EmornalRoomDatabase;
import com.example.project.model.News;

import java.util.List;

public class NewsRepository {
    private final NewsDao newsDao;

    public NewsRepository(Application application) {
        // Inisialisasi database dan DAO
        EmornalRoomDatabase database = EmornalRoomDatabase.getInstance(application);
        newsDao = database.newsDao();
    }

    // Mendapatkan semua berita
    public LiveData<List<News>> getAllNews() {
//        return newsDao.getAllNews();
        List<News> newsList = newsDao.getAllNews().getValue();
        if (newsList != null) {
            Log.d("Database", "News count: " + newsList.size());
            for (News news : newsList) {
                Log.d("Database", "News: " + news.getTitle());
            }
        } else {
            Log.d("Database", "No news found in database!");
        }
        return newsDao.getAllNews();
    }
}

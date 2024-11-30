package com.example.project.database;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project.R;
import com.example.project.converter.Converter;
import com.example.project.dao.DetailDiaryDao;
import com.example.project.dao.DiaryDao;
import com.example.project.dao.EmotionsDao;
import com.example.project.model.DetailDiary;
import com.example.project.model.Diary;
import com.example.project.model.Emotions;

import java.util.Date;
import java.util.concurrent.Executors;
import androidx.room.TypeConverters;

@Database(entities = {Emotions.class, Diary.class, DetailDiary.class}, version = 5, exportSchema = false)
@TypeConverters(Converter.class)

public abstract class EmornalRoomDatabase extends RoomDatabase {
//  DB NAME
    private static final String DB_NAME = "EmornalRoomDatabase";


//DAO Diary
    public abstract DiaryDao diaryDao();

//DAO DetailDiary
    public abstract DetailDiaryDao detailDiaryDao();

//  All the DAO object
    public abstract EmotionsDao emotionsDao();
    //public abstract DiaryDao diaryDao();
    //public abstract DetailDiaryDao detailDiaryDao();

    private static volatile EmornalRoomDatabase INSTANCE;

    public static EmornalRoomDatabase getInstance(final Context context) {
        if(INSTANCE == null){
            synchronized (EmornalRoomDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        EmornalRoomDatabase.class,
                        DB_NAME)
                        .addCallback(emornalRoomDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }



    private static final Callback emornalRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                EmotionsDao dao = INSTANCE.emotionsDao();
                DiaryDao diaryDao = INSTANCE.diaryDao();

                Bitmap bitmap;
                Emotions[] initialEmotions = {
//                      Adding Emotions with each of their picture
                        new Emotions("Joy", R.mipmap.ic_joy_foreground),
                        new Emotions("Serenity",R.mipmap.ic_serenity_foreground),
                        new Emotions("Ecstasy",R.mipmap.ic_ecstacy_foreground),
                        new Emotions("Anger",R.mipmap.ic_anger_foreground),
                        new Emotions("Annoyance",R.mipmap.ic_annoyance_foreground),
                        new Emotions("Rage",R.mipmap.ic_rage_foreground),
                        new Emotions("Anticipation",R.mipmap.ic_anticipation_foreground ),
                        new Emotions("Admiration",R.mipmap.ic_admiration_foreground),
                        new Emotions("Interest",R.mipmap.ic_interest_foreground),
                        new Emotions("Depressed",R.mipmap.ic_depressed_foreground),
                        new Emotions("Insecure",R.mipmap.ic_insecure_foreground),
                        new Emotions("Overwhelmed",R.mipmap.ic_over_foreground),
                        new Emotions("Sad",R.mipmap.ic_sad_foreground),
                        new Emotions("Panic",R.mipmap.ic_panic_foreground),
                        new Emotions("Sick",R.mipmap.ic_sick_foreground),
                        new Emotions("Tired",R.mipmap.ic_tired_foreground)
                };

                dao.insert(initialEmotions);

                Diary initialDiary = new Diary(new Date());
                diaryDao.insert(initialDiary);

//                int count = dao.getCount();
//                Log.d("MyTag", "Jumlah data setelah insert: " + count);
            });
        }
    };
}

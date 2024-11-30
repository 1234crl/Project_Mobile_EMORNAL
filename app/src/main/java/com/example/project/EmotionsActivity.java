package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.databinding.ActivityEmotionsBinding;
import com.example.project.model.DetailDiary;
import com.example.project.model.Diary;
import com.example.project.model.Emotions;
import com.example.project.viewmodel.DetailDiaryViewModel;
import com.example.project.viewmodel.DiaryViewModel;
import com.example.project.viewmodel.EmotionsViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.net.HttpCookie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class EmotionsActivity extends AppCompatActivity {

    private EmotionAdapter emotionsAdapter;
    private EmotionsViewModel emotionsViewModel;

    private DiaryViewModel diaryViewModel;
    private DetailDiaryViewModel detailDiaryViewModel;

    private HashMap<Emotions, Integer> dictDetailDiary = new HashMap<>();

    private DetailDiary detailDiary;

    private DetailDiaryAdapter detailDiaryAdapter;

    private ActivityEmotionsBinding binding;

    private Date currentDate;


    //TESTING Bottom Sheet Dialog
    Button show;
    BottomSheetDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityEmotionsBinding.inflate(getLayoutInflater());

        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("SELECTED_DATE");

        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emotionsViewModel = new EmotionsViewModel(EmotionsActivity.this.getApplication());
        diaryViewModel = new DiaryViewModel(EmotionsActivity.this.getApplication());
        detailDiaryViewModel = new DetailDiaryViewModel(EmotionsActivity.this.getApplication());

        emotionsAdapter = new EmotionAdapter();
        detailDiaryAdapter = new DetailDiaryAdapter();

        //Click Listener
        emotionsAdapter.setOnItemClickListener(emotion -> {

            if (dictDetailDiary.get(emotion) != null) {
                int currentJumlah = dictDetailDiary.get(emotion);
                dictDetailDiary.put(emotion, currentJumlah + 1);
            } else {
                dictDetailDiary.put(emotion, 1);
            }

            String emotionName = emotion.getNama();
            Toast.makeText(this, "You have added: " + emotionName, Toast.LENGTH_SHORT).show();
        });


        //Menampilkan daftar Emotion dari Database
        emotionsViewModel.getAllEmotions().observe(this, emotions -> {


            emotionsAdapter.updateData(emotions); // Update EmotionAdapter
            detailDiaryAdapter.setEmotionList(emotions); // Update DetailDiaryAdapter dengan data Emotions
        });

//        EmotionAdapter adapter = new EmotionAdapter(list);
        binding.gridEmotions.setAdapter(emotionsAdapter);
        binding.gridEmotions.setLayoutManager(new GridLayoutManager(this,4));


        //BOTTOM Dialog
        dialog = new BottomSheetDialog(this);

        binding.btnCurrentEmotion.setOnClickListener(v -> showEmotions());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            currentDate = dateFormat.parse(selectedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        binding.btnShake.setOnClickListener(view -> {
            Diary diary = new Diary(currentDate);
            diaryViewModel.insert(diary);
            for (HashMap.Entry<Emotions, Integer> entry : dictDetailDiary.entrySet()) {
                Emotions emotions = entry.getKey(); // ID emotion
                Integer jumlah = entry.getValue();
                DetailDiary detailDiary = new DetailDiary(diary.getId(), emotions.getId(), jumlah, true);
                detailDiaryViewModel.insert(detailDiary);
            };
        });


        }

    private void showEmotions() {
        // Inflate dialog layout
        View dialogView = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet, null);

        // RecyclerView dalam dialog
        RecyclerView bottomSheetRecycler = dialogView.findViewById(R.id.grid_detail_diary);

        // Ubah dictDetailDiary menjadi List<DetailDiary>
        List<DetailDiary> detailDiaryList = new ArrayList<>();
        for (Emotions emotion : dictDetailDiary.keySet()) {
            int jumlah = dictDetailDiary.get(emotion);
            detailDiaryList.add(new DetailDiary(jumlah, emotion.getId(), jumlah,false));
        }

        // Set RecyclerView dengan adapter
//        DetailDiaryAdapter detailDiaryAdapter = new DetailDiaryAdapter();
        detailDiaryAdapter.updateData(detailDiaryList);
        bottomSheetRecycler.setAdapter(detailDiaryAdapter);



        // Atur layout manager untuk grid
        bottomSheetRecycler.setLayoutManager(new GridLayoutManager(this, 4));


        // Tombol close untuk menutup dialog
        Button closeBtn = dialogView.findViewById(R.id.btn_close);
        closeBtn.setOnClickListener(v -> dialog.dismiss());

        // Set dialog content dan tampilkan dialog
        dialog.setContentView(dialogView);
        dialog.show();
    }

    private void addEmotionIntoDiary(Emotions emotion) {
        if (dictDetailDiary.containsKey(emotion)) {
            dictDetailDiary.compute(emotion, (k, currentJumlah) -> currentJumlah + 1);
        } else {
            dictDetailDiary.put(emotion, 1);
        }
    }

    private void createDialog() {

        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet, null, false);
//        Button submit = view.findViewById(R.id.btn_submit);
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//                Toast.makeText(EmotionsActivity.this, "YAYYY", Toast.LENGTH_SHORT).show();
//            }
//        });

        dialog.setContentView(view);
    }




    public int insertDiary(Diary diary, DetailDiary detailDiary){
        try {
            diaryViewModel.insert(diary);
            detailDiaryViewModel.insert(detailDiary);

            return 1; // Sukses
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}


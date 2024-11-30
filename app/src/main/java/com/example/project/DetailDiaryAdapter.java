package com.example.project;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.model.DetailDiary;
import com.example.project.model.Emotions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailDiaryAdapter extends RecyclerView.Adapter<DetailDiaryAdapter.ViewHolder> {

    private List<DetailDiary> detailDiaryList = new ArrayList<>();

    //JOIN untuk mendapatkan gambarID Emotion dari DetailDiary
    private Map<Integer, Integer> emotionIdToImageMap = new HashMap<>();

    //JOIN untuk mendapatkan nama Emotion dari DetailDiary
    private Map<Integer, String> emotionIdToNameMap = new HashMap<>();



    public void updateData(List<DetailDiary> list) {
        detailDiaryList.clear();
        detailDiaryList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Inflate bottom sheet
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emotion_template, parent, false);
        return new ViewHolder(view);
    }


    public void setEmotionList(List<Emotions> emotionsList) {
        for (Emotions emotion : emotionsList) {
            emotionIdToImageMap.put(emotion.getId(), emotion.getIdGambar()); //Gambar
            emotionIdToNameMap.put(emotion.getId(), emotion.getNama()); //Nama
        }
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DetailDiary detailDiary = detailDiaryList.get(position);
        holder.emotionJumlah.setText(String.valueOf(detailDiary.getJumlah()));

        // Get gambarId dari emotionIdToImageMap
        int emotionId = detailDiary.getIdEmotions();
        String namaEmotion = emotionIdToNameMap.get(emotionId);
        Integer gambarId = emotionIdToImageMap.get(emotionId);

        if (namaEmotion != null) {
            holder.emotionName.setText(namaEmotion); // Atur nama emotion
        } else {
            holder.emotionName.setText("Unknown Emotion"); // Default nama
            Log.e("DetailDiaryAdapter", "Emotion name not found for ID: " + emotionId);
        }



        if (gambarId != null) {
            holder.emotionGambar.setImageResource(gambarId);
        } else {
            Log.e("DetailDiaryAdapter", "Emotion ID not found: " + emotionId);
            holder.emotionGambar.setImageResource(R.mipmap.ic_admiration_foreground); // Default image
        }

    }

    @Override
    public int getItemCount() {
        return detailDiaryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView emotionName;
        TextView emotionJumlah;
        ImageView emotionGambar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emotionName = itemView.findViewById(R.id.text_emotion_name);
            emotionJumlah = itemView.findViewById(R.id.text_jumlah);
            emotionGambar = itemView.findViewById(R.id.emotion_logo);
        }
    }
}


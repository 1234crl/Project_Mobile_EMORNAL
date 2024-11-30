package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.model.Emotions;

import java.util.ArrayList;
import java.util.List;

public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.EmotionsViewHolder> {

    private List<Emotions> emotionsList = new ArrayList<>();


    //ON CLICK LISTENER
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Emotions emotion);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public EmotionAdapter() {
    }

    public void updateData(List<Emotions> emotions) {
        this.emotionsList.clear(); // Hapus data lama
        if (emotions != null) {
            this.emotionsList.addAll(emotions); // Tambahkan data baru
        }
        notifyDataSetChanged();    }

    public static class EmotionsViewHolder extends RecyclerView.ViewHolder {
        public ImageView emotionImageView;
        public TextView emotionTextView;
        public TextView emotionJumlah;

        public EmotionsViewHolder(@NonNull View itemView) {
            super(itemView);
            emotionImageView = itemView.findViewById(R.id.emotion_logo);
            emotionTextView = itemView.findViewById(R.id.text_emotion_name);
            emotionJumlah = itemView.findViewById(R.id.text_jumlah);
        }}

    @NonNull
    @Override
    public EmotionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emotion_template
                , parent, false);
        return new EmotionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmotionsViewHolder holder, int position) {
        Emotions currentEmotion = emotionsList.get(position);
        holder.emotionImageView.setImageResource(currentEmotion.getIdGambar());
        holder.emotionTextView.setText(currentEmotion.getNama());
        holder.emotionJumlah.setText("");


        //Click Listener
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(currentEmotion);
            }
        });
    }

    @Override
    public int getItemCount() {
        return emotionsList.size();
    }

    public void setData(List<Emotions> emotions) {
        this.emotionsList = emotions;
        notifyDataSetChanged();
    }
}

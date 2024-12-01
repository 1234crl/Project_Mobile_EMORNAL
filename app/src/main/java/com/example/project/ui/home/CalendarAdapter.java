package com.example.project.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.databinding.CalendarDateLayoutBinding;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private ArrayList<CalendarModel> dateList;
    private DateItemClick dateItemClickListener;

    public CalendarAdapter(ArrayList<CalendarModel> dateList, DateItemClick dateItemClickListener) {
        this.dateList = dateList;
        this.dateItemClickListener = dateItemClickListener;
    }


    @NonNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.calendar_date_layout, parent, false);

        CalendarDateLayoutBinding binding = CalendarDateLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );

        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ViewHolder holder, int position) {
        holder.bind(dateList.get(position));
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView num;
        private View today;
        private ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.num);
            today = itemView.findViewById(R.id.today);
            icon = itemView.findViewById(R.id.icon);
        }

        @SuppressLint("ResourceAsColor")
        public void bind(CalendarModel calendarModel) {
            int day = calendarModel.getDay();
            Log.d("CalendarAdapter", "Day: " + calendarModel.getDay() + ", State: " + calendarModel.getState());

            if (day != 0){
                num.setText(String.valueOf(calendarModel.getDay()));
            }
            else {
                num.setText("");
                icon.setVisibility(View.INVISIBLE);
            }

            switch (calendarModel.getState()){
                case -1:
                    today.setVisibility(View.INVISIBLE);

                    break;
                case 0:
                    num.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.light_gray));
                    today.setVisibility(View.INVISIBLE);

                    break;
                case 1:
                    num.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
                    num.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_emornal_empty2));
                    today.setVisibility(View.VISIBLE);

                    break;
                case 2:
                    num.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
                    num.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_emornal_empty2));
                    today.setVisibility(View.VISIBLE);

                    itemView.setOnClickListener(view -> {
                        dateItemClickListener.onDateClick(calendarModel);
                    });

                    break;
                case 3:
                    num.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.white));
                    num.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_emornal_empty2));
                    today.setVisibility(View.INVISIBLE);
                    break;
            }

            num.setOnClickListener(view -> {
                Toast.makeText(view.getContext(), String.valueOf(day), Toast.LENGTH_SHORT).show();
            });
        }

    }
}

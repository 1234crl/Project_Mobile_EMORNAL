package com.example.project.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.project.R;

import java.time.LocalDate;

public class Helper {

    public Helper() {
    }

    public String getMonth(int month, Context context){
        if (month == 1){
            return context.getString(R.string.jan);
        }
        else if (month == 2){
            return context.getString(R.string.feb);
        }
        else if (month == 3){
            return context.getString(R.string.mar);
        }
        else if (month == 4){
            return context.getString(R.string.apr);
        }
        else if (month == 5){
            return context.getString(R.string.may);
        }
        else if (month == 6){
            return context.getString(R.string.jun);
        }
        else if (month == 7){
            return context.getString(R.string.jul);
        }
        else if (month == 8){
            return context.getString(R.string.aug);
        }
        else if (month == 9){
            return context.getString(R.string.sep);
        }
        else if (month == 10){
            return context.getString(R.string.oct);
        }
        else if (month == 11){
            return context.getString(R.string.nov);
        }
        else if (month == 12){
            return context.getString(R.string.dec);
        }
        return null;
    }

    @SuppressLint("NewApi")
    public LocalDate createDate(int day, int month, int year){
        return LocalDate.of(year, month, day);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int isItToday(LocalDate date) {
        LocalDate today = LocalDate.now(); // Current date without time
        if (date.isAfter(today)) {
            return 0; // The date is in the future
        } else if (date.isBefore(today)) {
            return 1; // The date is in the past
        }
        return 2; // The date is today
    }

}

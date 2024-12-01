package com.example.project.ui.home;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.project.databinding.FragmentHomeBinding;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements DateItemClick {

    private FragmentHomeBinding binding;
    private LocalDateTime currDate;
    private Helper myHelper;
    private CalendarAdapter calendarAdapter;

    public HomeFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        Log.d("HomeFragment", "onCreateView called");
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myHelper = new Helper();

        binding.rvCalendar.setLayoutManager(new GridLayoutManager(requireContext(),7));
        binding.rvCalendar.addItemDecoration(new CustomItemDecoration());
        binding.rvCalendar.setHasFixedSize(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            currDate = LocalDateTime.now();
        }

        setCalendar(currDate);

        binding.monthNavigationLeft.setOnClickListener( view1 -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                currDate = currDate.minusMonths(1);
                setCalendar(currDate);
                binding.monthYear.setText(myHelper.getMonth(currDate.getMonthValue(), getContext()) + ", " + currDate.getYear());
            }
        });

        binding.monthNavigationRight.setOnClickListener(view1 -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                currDate = currDate.plusMonths(1);
                setCalendar(currDate);
                binding.monthYear.setText(myHelper.getMonth(currDate.getMonthValue(), getContext()) + ", " + currDate.getYear());
            }
        });

    }

    private LocalDateTime date = null;
    private ArrayList<CalendarModel> dateList = null;
    @SuppressLint("NewApi")
    private void setCalendar(LocalDateTime currDate) {
        // Set the month and year in the calendar header
        binding.monthYear.setText(myHelper.getMonth(currDate.getMonthValue(), getContext()) + ", " + currDate.getYear());
        date = currDate.with(LocalTime.MIDNIGHT);

        // Get the number of days in the current month
        int numOfDaysInThisMonth = currDate.toLocalDate().lengthOfMonth();

        // Calculate the first day of the month and its day of the week
        LocalDate firstDateOfMonth = currDate.toLocalDate().withDayOfMonth(1);
        int dayOfWeek = firstDateOfMonth.getDayOfWeek().getValue(); // 1 = Monday, 7 = Sunday

        // Adjust dayOfWeek to align with a Sunday-start calendar (optional)
        if (dayOfWeek == 7) {
            dayOfWeek = 0;
        }

        // Clear or initialize the date list
        if (dateList == null) {
            dateList = new ArrayList<>();
        } else {
            dateList.clear();
        }

        // Add placeholders for empty days at the start of the month
        for (int i = 0; i < dayOfWeek; i++) {
            dateList.add(new CalendarModel(0, "", -1)); // Placeholder with no date
        }

        // Add actual dates for the current month
        for (int i = 1; i <= numOfDaysInThisMonth; i++) {
            LocalDate currentDate = firstDateOfMonth.withDayOfMonth(i);
            int isToday = myHelper.isItToday(currentDate);

            // Set the status for each date based on whether it is today, etc.
            int status;
            switch (isToday) {
                case 0: // Today
                    status = 3;
                    break;
                case 1: // Some other special day
                    status = 2;
                    break;
                default: // Regular day
                    status = 1;
                    break;
            }

            // Add the date to the list
            dateList.add(new CalendarModel(i, currentDate.toString(), status));
        }

        // Add placeholders for empty days at the end of the grid (optional)
        int totalCells = dateList.size();
        int remainingCells = totalCells % 7;
        if (remainingCells != 0) {
            for (int i = 0; i < (7 - remainingCells); i++) {
                dateList.add(new CalendarModel(0, "", -1)); // Placeholder
            }
        }

        // Set up the adapter and bind it to the RecyclerView
        if (!dateList.isEmpty()) {
            calendarAdapter = new CalendarAdapter(dateList, (DateItemClick) this);
            binding.rvCalendar.setAdapter(calendarAdapter);
        }
    }

//    @Override
//    public void onDateClick(CalendarModel calendarModel) {
//        Toast.makeText(this, "Date: " +calendarModel.getDate(), Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onDateClick(CalendarModel calendarModel) {
    }
}
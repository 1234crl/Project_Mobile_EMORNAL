package com.example.project.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.NewsAdapter;
import com.example.project.R;
import com.example.project.model.News;
import com.example.project.viewmodel.NewsViewModel;

public class DashboardFragment extends Fragment {

    private NewsAdapter newsAdapter;
    private NewsViewModel newsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Inisialisasi RecyclerView dan Adapter
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        newsAdapter = new NewsAdapter();
        recyclerView.setAdapter(newsAdapter);

        newsAdapter.setOnItemClickListener(this::openNewsDetail);

        // Inisialisasi ViewModel
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        // Observasi data LiveData dari ViewModel
        newsViewModel.getAllNews().observe(getViewLifecycleOwner(), newsList -> {
            // Perbarui data adapter
            newsAdapter.setNewsList(newsList);
            Log.d("DashboardFragment", "News List: " + newsList);
        });


        return view;
    }

    // Fungsi untuk membuka detail berita
    private void openNewsDetail(News news) {
        // Membuat instance NewsDetailFragment dan mengirim data berita
        DashboardDetailFragment newsDetailFragment = DashboardDetailFragment.newInstance(
                news.getTitle(),
                news.getDescription(),
                news.getIdGambar()
        );

        // Ganti fragment yang ada dengan NewsDetailFragment
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, newsDetailFragment)
                .addToBackStack(null)
                .commit();

        requireActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}

package com.example.project.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.project.R;

public class DashboardDetailFragment extends Fragment {

    // Deklarasi variabel untuk menampung data berita
    private String title;
    private String description;
    private int idGambar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout untuk fragment
        View view = inflater.inflate(R.layout.fragment_news_isi, container, false);

        if (getArguments() != null) {
            title = getArguments().getString("title");
            description = getArguments().getString("description");
            idGambar = getArguments().getInt("idgambar");
        }

        // Menampilkan data ke dalam TextView
        TextView titleView = view.findViewById(R.id.tvArticleTitle);
        TextView descriptionView = view.findViewById(R.id.tvArticleContent);
        ImageView logo = view.findViewById(R.id.ivArticleImage);

        // Set data pada TextView
        titleView.setText(title);
        descriptionView.setText(description);
        logo.setImageResource(idGambar);

        return view;
    }

    // Fungsi untuk membuat instance fragment dengan data
    public static DashboardDetailFragment newInstance(String title, String description, int idGambar) {
        DashboardDetailFragment fragment = new DashboardDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("description", description);
        args.putInt("idgambar", idGambar);
        fragment.setArguments(args);
        return fragment;
    }
}

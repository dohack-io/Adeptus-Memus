package com.example.plankr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class Statsfragment extends Fragment {


    View meineView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       meineView = inflater.inflate(R.layout.stats_layout, container, false);
        return meineView;
    }
}

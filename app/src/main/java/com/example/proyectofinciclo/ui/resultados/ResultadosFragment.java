package com.example.proyectofinciclo.ui.resultados;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.timelineprueba.TweetsAdapter;
import com.google.android.material.tabs.TabLayout;

import twitter4j.ResponseList;
import twitter4j.Status;

public class ResultadosFragment extends Fragment {

    String[] tweets;
    ResponseList<Status> statuses;
    private TweetsAdapter tweetsAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    public static Context context;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultados, container, false);
        ResultadosAdapter resultadosAdapter = new ResultadosAdapter(getActivity().getSupportFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(resultadosAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}


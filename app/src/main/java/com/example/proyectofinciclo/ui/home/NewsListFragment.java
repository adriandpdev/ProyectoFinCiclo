package com.example.proyectofinciclo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.ApiService;
import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.Services.ConnectionService;
import com.example.proyectofinciclo.models.news;
import com.example.proyectofinciclo.res.ResNews;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NewsListAdapter newsAdapter;
    private List<news> resultadoSQL;
    private LinearLayoutManager llm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_news);
        recyclerView.setHasFixedSize(true);
        resultadoSQL = null;

        ApiService apiService = ConnectionService.getApiService();
        Call<ResNews> call = apiService.getNews();
        call.enqueue(new Callback<ResNews>() {
            @Override
            public void onResponse(Call<ResNews> call, Response<ResNews> response) {
                if (response.code()==200) {
                    ResNews res = response.body();
                    if(res.getEstado()!=200){
                        donackbar("Code: " + response.code()+", Estado: "+res.getMensaje(), view );
                        return;
                    }else if(res.getEstado()==200){
                        newsAdapter = new NewsListAdapter(res.getNews(),getContext());
                        recyclerView.setAdapter(newsAdapter);
                        llm = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(llm);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResNews> call, Throwable t) {
                donackbar(t.getMessage(), view);
            }
        });

        return view;
    }
    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }

}
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
import com.example.proyectofinciclo.res.ResCalendario;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private ResulsHomeAdapter resulsAdapter;
    private LinearLayoutManager llm;
    private CardView btntw, btnig;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rvHomeResul);
        btnig = view.findViewById(R.id.cardViewig);
        btntw = view.findViewById(R.id.cardViewtw);

        recyclerView.setHasFixedSize(true);

        ApiService apiService = ConnectionService.getApiService();

        Call<ResNews> callnews = apiService.getNews();
        callnews.enqueue(new Callback<ResNews>() {
            @Override
            public void onResponse(Call<ResNews> call, Response<ResNews> response) {
                if (response.code()==200) {
                    ResNews res = response.body();
                    if(res.getEstado()!=200){
                        donackbar("Code: " + response.code()+", Estado: "+res.getMensaje(), view );
                        return;
                    }else if(res.getEstado()==200){
                        List<news> news = res.getNews();
                    }
                }else{
                    donackbar("Code: " + response.code()+", ERROR ", view );
                }
            }

            @Override
            public void onFailure(Call<ResNews> call, Throwable t) {
                donackbar(t.getMessage(), view);
            }
        });

        Call<ResCalendario> call = apiService.getCalendarioHome();
        call.enqueue(new Callback<ResCalendario>() {
            @Override
            public void onResponse(Call<ResCalendario> call, Response<ResCalendario> response) {
                if (response.code()==200) {
                    ResCalendario res = response.body();
                    if(res.getEstado()!=200){
                        donackbar("Code: " + response.code()+", Estado: "+res.getMensaje(), view );
                        return;
                    }else if(res.getEstado()==200){

                        resulsAdapter = new ResulsHomeAdapter(res.getPartidos(),getContext());
                        recyclerView.setAdapter(resulsAdapter);
                        llm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                        recyclerView.setLayoutManager(llm);
                    }
                }else{
                    donackbar("Code: " + response.code()+", ERROR ", view );
                }
            }

            @Override
            public void onFailure(Call<ResCalendario> call, Throwable t) {
                donackbar(t.getMessage(), view);
            }
        });
        btntw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twFragment fr=new twFragment();
                fr.setArguments(bn);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fr)
                        .addToBackStack(null)
                        .commit();

            }
        });
        btnig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                igFragment fr=new igFragment();
                fr.setArguments(bn);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment,fr)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }
}
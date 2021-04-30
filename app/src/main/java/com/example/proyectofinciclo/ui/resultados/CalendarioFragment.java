package com.example.proyectofinciclo.ui.resultados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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

public class CalendarioFragment extends Fragment {

    private CalendarioViewModel mViewModel;
    private RecyclerView recyclerView;
    private ResulsAdapter resulsAdapter;
    private LinearLayoutManager llm;

    public static CalendarioFragment newInstance() {
        return new CalendarioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        recyclerView = view.findViewById(R.id.rvResuls);
        recyclerView.setHasFixedSize(true);

        ApiService apiService = ConnectionService.getApiService();
        Call<ResCalendario> call = apiService.getCalendario();
        call.enqueue(new Callback<ResCalendario>() {
            @Override
            public void onResponse(Call<ResCalendario> call, Response<ResCalendario> response) {
                if (response.code()==200) {
                    ResCalendario res = response.body();
                    if(res.getEstado()!=200){
                        donackbar("Code: " + response.code()+", Estado: "+res.getMensaje(), view );
                        return;
                    }else if(res.getEstado()==200){
                        resulsAdapter = new ResulsAdapter(res.getPartidos(),getContext());
                        recyclerView.setAdapter(resulsAdapter);
                        llm = new LinearLayoutManager(getActivity());
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
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalendarioViewModel.class);
    }

    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }

}
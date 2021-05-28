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
import com.example.proyectofinciclo.res.ResClasi;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClasiFragment extends Fragment {

    private CalendarioViewModel mViewModel;
    private RecyclerView recyclerView;
    private ClasiAdapter clasiAdapter;
    private LinearLayoutManager llm;

    public static ClasiFragment newInstance() {
        return new ClasiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clasi, container, false);
        recyclerView = view.findViewById(R.id.rv_clasi);
        recyclerView.setHasFixedSize(true);

        ApiService apiService = ConnectionService.getApiService();
        Call<ResClasi> call = apiService.getClasi();
        call.enqueue(new Callback<ResClasi>() {
            @Override
            public void onResponse(Call<ResClasi> call, Response<ResClasi> response) {
                if (response.code()==200) {
                    ResClasi res = response.body();
                    if(res.getEstado()!=200){
                        donackbar("Code: " + response.code() +
                            ", Estado: "+res.getMensaje(), view );
                    }else if(res.getEstado()==200){
                        clasiAdapter = new ClasiAdapter(res.getEquipos(),getContext());
                        recyclerView.setAdapter(clasiAdapter);
                        llm = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(llm);
                    }
                }else{
                    donackbar("Code: " + response.code()+", ERROR ", view );
                }
            }

            @Override
            public void onFailure(Call<ResClasi> call, Throwable t) {
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
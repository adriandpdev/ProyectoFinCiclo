package com.example.proyectofinciclo.ui.plantilla;

import android.os.Bundle;
import android.util.Log;
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
import com.example.proyectofinciclo.models.jugador;
import com.example.proyectofinciclo.res.ResPlantilla;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlantillaFragment extends Fragment {

    private PlantillaViewModel mViewModel;
    private RecyclerView recyclerView;
    private PlantillaAdapter userAdapter;
    private List<jugador> resultadoSQL;
    private LinearLayoutManager llm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plantilla, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_jugadores);
        recyclerView.setHasFixedSize(true);
        resultadoSQL = null;

        ApiService apiService = ConnectionService.getApiService();
        Call<ResPlantilla> call = apiService.getPlantilla();
        Log.d("DATOS", "REALIZANDO PETICION");
        call.enqueue(new Callback<ResPlantilla>() {
            @Override
            public void onResponse(Call<ResPlantilla> call, Response<ResPlantilla> response) {
                if (response.code()==200) {
                    ResPlantilla res = response.body();
                    if(res.getEstado()!=200){
                        donackbar("Code: " + response.code()+", Estado: "+res.getMensaje(), view );
                        return;
                    }else if(res.getEstado()==200){

                        userAdapter = new PlantillaAdapter(res.getjugadores(),getContext());
                        recyclerView.setAdapter(userAdapter);
                        llm = new LinearLayoutManager(getActivity());
                        recyclerView.setLayoutManager(llm);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResPlantilla> call, Throwable t) {
                Log.d("DATOS", "FALLO");
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
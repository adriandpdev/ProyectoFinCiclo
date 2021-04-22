package com.example.proyectofinciclo.ui.calendario;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.models.resul;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
        View v = inflater.inflate(R.layout.fragment_calendario, container, false);
        List<resul> resultados = new ArrayList<resul>();
        try {
            InputStream archivo=getResources().openRawResource(R.raw.partidos);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(archivo));


            String linea;
            while((linea =bufferedReader.readLine())!= null){
                String[] linea2 = linea.split("/");
                String[] res = linea2[2].split("-");
                resultados.add(new resul(linea2[0],"","",linea2[1],linea2[2],"1"));
                Log.d("archivo", "onCreateView: "+linea);
            }
            archivo.close();
            bufferedReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream archivo=getResources().openRawResource(R.raw.resultados);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(archivo));


            String linea;
            while((linea =bufferedReader.readLine())!= null){
                String[] linea2 = linea.split("/");
                String[] res = linea2[2].split("-");
                resultados.add(new resul("",res[0],res[1],"","",""));
                Log.d("archivo", "onCreateView: "+linea);
            }
            archivo.close();
            bufferedReader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerView = v.findViewById(R.id.rvResuls);
        recyclerView.setHasFixedSize(true);
        resulsAdapter = new ResulsAdapter(resultados,getContext());
        recyclerView.setAdapter(resulsAdapter);
        llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalendarioViewModel.class);
        // TODO: Use the ViewModel
    }

}
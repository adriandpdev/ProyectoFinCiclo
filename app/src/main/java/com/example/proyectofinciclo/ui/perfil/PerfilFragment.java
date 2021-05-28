package com.example.proyectofinciclo.ui.perfil;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinciclo.LoginActivity;
import com.example.proyectofinciclo.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private EditText txtuser, txtemail;
    private TextView txtnum;
    private Button btnlogout;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        SharedPreferences prefe=getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);
        btnlogout = view.findViewById(R.id.btn_logout);
        txtuser= view.findViewById(R.id.edName);
        txtemail= view.findViewById(R.id.edMail);
        txtnum = view.findViewById(R.id.txtNum);

        btnlogout.setOnClickListener(v -> {
            cerrarsesion();
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            getActivity().finish();
        });
        txtuser.setText(prefe.getString("user","username"));
        txtnum.setText("Socio "+prefe.getString("socio","X"));
        txtemail.setText(prefe.getString("email","example@mail.com"));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
    }

    public void cerrarsesion(){
        SharedPreferences preferencias=getActivity().getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("user", "");
        editor.putString("pass", "");
        editor.commit();
    }

}

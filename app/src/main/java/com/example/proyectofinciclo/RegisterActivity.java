package com.example.proyectofinciclo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinciclo.Services.ConnectionService;
import com.example.proyectofinciclo.res.PetRegister;
import com.example.proyectofinciclo.res.ResUser;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private static Context context;
    public String user="", pass="";
    public Button btnlogin, btnregister;
    public EditText txtuser, txtpass, txtemail,txtsocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnlogin = findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(this);
        btnregister = findViewById(R.id.btn_register);
        btnregister.setOnClickListener(this);
        txtuser = findViewById(R.id.et_user);
        txtpass= findViewById(R.id.et_contrasena);
        txtemail = findViewById(R.id.et_email);
        txtsocio= findViewById(R.id.et_socio);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                if((!txtuser.getText().toString().equals(""))&&(!txtpass.getText().toString().equals(""))&&(!txtemail.getText().toString().equals(""))&&(!txtsocio.getText().toString().equals(""))) {
                    ApiService restClient = ConnectionService.getApiService();
                    PetRegister pRegister = new PetRegister(txtuser.getText().toString(), txtpass.getText().toString(),txtemail.getText().toString(), txtsocio.getText().toString());
                    Call<ResUser> call = restClient.getRegister(pRegister);
                    call.enqueue(new Callback<ResUser>() {
                        @Override
                        public void onResponse(Call<ResUser> call, Response<ResUser> response) {
                            if (response.code()==200) {
                                ResUser res = response.body();
                                if(res.getEstado()==200){
                                    guardarsesion();
                                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    donackbar("Code: " + response.code()+", Estado: "+res.getMensaje(), v);
                                    return;
                                }
                            }else{
                                donackbar("Error "+response.code(),v);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResUser> call, Throwable t) {
                            donackbar(t.getMessage(), v);
                        }
                    });
                }else{
                    donackbar("Rellena todos los campos", v);
                }
                break;
            case R.id.btn_login:
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }
    public void guardarsesion(){
        SharedPreferences preferencias=getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("user", txtuser.getText().toString());
        editor.putString("pass", txtpass.getText().toString());
        editor.putString("email", txtemail.getText().toString());
        editor.putString("socio", txtsocio.getText().toString());
        editor.commit();
    }

}
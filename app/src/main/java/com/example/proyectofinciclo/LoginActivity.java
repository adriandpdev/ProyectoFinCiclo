package com.example.proyectofinciclo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinciclo.Services.ConnectionService;
import com.example.proyectofinciclo.res.PetLogin;
import com.example.proyectofinciclo.res.ResUser;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static Context context;
    public String user="", pass="";
    public Button btnlogin;
    public EditText txtuser, txtpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin = findViewById(R.id.btn_iniciar_sesion);
        btnlogin.setOnClickListener(this);
        txtuser = findViewById(R.id.et_user);
        txtpass= findViewById(R.id.et_contrasena);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_iniciar_sesion:
                if((!txtuser.getText().toString().equals(""))&&(!txtpass.getText().toString().equals(""))) {
                    ApiService restClient = ConnectionService.getApiService();
                    PetLogin pLogin = new PetLogin(txtuser.getText().toString(), txtpass.getText().toString());
                    Call<ResUser> call = restClient.getLogin(pLogin);
                    call.enqueue(new Callback<ResUser>() {
                        @Override
                        public void onResponse(Call<ResUser> call, Response<ResUser> response) {
                            if (response.code()==200) {
                                ResUser res = response.body();
                                if(res.getEstado()==200){
                                    //guardarsesion();
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
            case R.id.btn_olvidepass:
                donackbar("Contacte con administración", v);
                    break;
            case R.id.btn_ir_crearCuenta:
                donackbar("Contacte con administración", v);
                break;
        }
    }
    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }


}
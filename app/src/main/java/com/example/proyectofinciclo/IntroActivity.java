package com.example.proyectofinciclo;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinciclo.Services.ConnectionService;
import com.example.proyectofinciclo.res.PetLogin;
import com.example.proyectofinciclo.res.ResUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroActivity extends AppCompatActivity {
    private VideoView video;
    private String user, pass;
    private int enc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isOnline()) {
            setContentView(R.layout.activity_intro);
            enc = 0;
            video = findViewById(R.id.videoView);
            String path = "android.resource://" + getPackageName() + "/" + R.raw.intro;
            video.setVideoURI(Uri.parse(path));
            video.start();
            enc = get_session();
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    do{
                        if(enc == 1){
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(IntroActivity.this).toBundle());
                            finish();
                        }else if(enc == 2){
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(IntroActivity.this).toBundle());
                            finish();
                        }
                        enc = get_session();
                    }while(enc == 0);
                }
            });
        } else {
            try {
                Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
                finish();
            } catch (Exception e) {
                Log.d("ERROR", "Show Dialog: " + e.getMessage());
            }
        }
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            return false;
        }
        return true;
    }

    public int get_session(){
        SharedPreferences ShPref=getSharedPreferences("session", Context.MODE_PRIVATE);
        user=ShPref.getString("user","");
        pass=ShPref.getString("pass","");
        Log.d("TAG", "get_session: "+user+" "+pass);
        if(user != "" && pass != ""){
            ApiService restClient = ConnectionService.getApiService();
            PetLogin pLogin = new PetLogin(user, pass);
            Call<ResUser> call = restClient.getLogin(pLogin);
            call.enqueue(new Callback<ResUser>() {
                @Override
                public void onResponse(Call<ResUser> call, Response<ResUser> response) {
                    if (response.code()==200) {
                        ResUser res = response.body();
                        if(res.getEstado()==200){
                            SharedPreferences.Editor editor=ShPref.edit();
                            editor.putString("socio", res.getUser().getSocio());
                            editor.putString("email", res.getUser().getEmail());
                            editor.commit();
                            enc = 1;
                        }else{
                            enc = 2;
                        }
                    }else{
                        enc = 2;
                    }
                }
                @Override
                public void onFailure(Call<ResUser> call, Throwable t) {
                    enc = 2;
                }
            });
        }else{
            enc = 2;
        }
        return enc;
    }
}

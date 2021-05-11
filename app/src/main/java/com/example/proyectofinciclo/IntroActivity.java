package com.example.proyectofinciclo;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    private VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isOnline()) {
            setContentView(R.layout.activity_intro);

            video = findViewById(R.id.videoView);
            String path = "android.resource://" + getPackageName() + "/" + R.raw.intro;
            video.setVideoURI(Uri.parse(path));
            video.start();
            video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class); //LoginActivity.class);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(IntroActivity.this).toBundle());
                    finish();
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
}

package com.example.proyectofinciclo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.timelineprueba.LoadImage;
import com.example.proyectofinciclo.webviewActivity;
import com.google.android.material.snackbar.Snackbar;

public class NewsDetailsActivity extends AppCompatActivity{
    public ImageView imgnews;
    public TextView tvtitle;
    public TextView tvdesc;
    public TextView tvdate;
    public Button btnweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news_details);
        Bundle data = this.getIntent().getExtras();
        if(data != null){
            String id = data.getString("id");
            String title = data.getString("title");
            String desc = data.getString("desc");
            String date = data.getString("date");
            String img = data.getString("img");

            imgnews = findViewById(R.id.ivNdetails);
            tvtitle = findViewById(R.id.txTitleNdetails);
            tvdesc = findViewById(R.id.txDescNdetails);
            tvdate = findViewById(R.id.txDateNdetails);
            btnweb = findViewById(R.id.btnNdetails);

            // Cargamos los datos
            tvtitle.setText(title);
            tvdesc.setText(desc);
            tvdate.setText(date);
            String imageHttpAddress = img;
            new LoadImage(imgnews).execute(imageHttpAddress);
            // Añadimos la función al hacer click al boton
            btnweb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), webviewActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }else{
            Log.d("TAG", "onCreateView: NULL");
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}
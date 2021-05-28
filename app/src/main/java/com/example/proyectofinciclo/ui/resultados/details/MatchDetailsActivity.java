package com.example.proyectofinciclo.ui.resultados.details;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.Services.LoadImage;
import com.google.android.material.snackbar.Snackbar;

public class MatchDetailsActivity extends AppCompatActivity {

    public CardView mCardView;
    public TextView tvTime,tvResul;
    public TextView txthome;
    public TextView txtaway;
    public ImageView awayimg,homeimg;
    public CardView cvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.fragment_details_match);
        mCardView = findViewById(R.id.cvResul);
        tvResul = findViewById(R.id.tvResul);
        tvTime = findViewById(R.id.tvTime);
        homeimg = findViewById(R.id.ivHomeTeam);
        awayimg = findViewById(R.id.ivAwayTeam);
        txthome = findViewById(R.id.tvHomeTeam);
        txtaway = findViewById(R.id.tvAwayTeam);
        cvTime = findViewById(R.id.cvTime);

        tvResul.setText(getIntent().getExtras().getString("resul"));
        tvTime.setText(getIntent().getExtras().getString("min"));
        txthome.setText(getIntent().getExtras().getString("loc"));
        txtaway.setText(getIntent().getExtras().getString("vis"));
        String imageHttpAddresshomeloc = getIntent().getExtras().getString("loc").replace(" ","");
        new LoadImage(homeimg).execute("https://res.adriandiarteprieto.tk/escudos/"+imageHttpAddresshomeloc+".png");
        String imageHttpAddresshomevis = getIntent().getExtras().getString("vis").replace(" ","");
        new LoadImage(awayimg).execute("https://res.adriandiarteprieto.tk/escudos/"+imageHttpAddresshomevis+".png");

        // Cargar resultado
        if(getIntent().getExtras().getString("resul").equals("null : null")){
            if(!getIntent().getExtras().getString("date").equals("0 : 00")){
                tvResul.setText(getIntent().getExtras().getString("date"));
                tvResul.setTextSize(20);
                tvResul.setTypeface(Typeface.DEFAULT);
            }else{
                tvResul.setVisibility(View.INVISIBLE);
            }
            tvResul.setText(getIntent().getExtras().getString("date"));
            tvTime.setText("Estadio "+getIntent().getExtras().getString("estadio"));
            tvTime.setTextColor(Color.BLACK);
            cvTime.setCardBackgroundColor(Color.TRANSPARENT);
            cvTime.setCardElevation(0);
        }else{
            tvResul.setText(getIntent().getExtras().getString("resul"));
            tvTime.setText(getIntent().getExtras().getString("min"));
        }

        /*
        MatchDetailsAdapter matchDetailsAdapter = new MatchDetailsAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(matchDetailsAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);*/

    }

    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
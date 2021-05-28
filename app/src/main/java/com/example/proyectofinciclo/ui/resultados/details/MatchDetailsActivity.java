package com.example.proyectofinciclo.ui.resultados.details;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.timelineprueba.LoadImage;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

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
            tvResul.setVisibility(View.INVISIBLE);
            tvTime.setText("Estadio "+getIntent().getExtras().getString("estadio"));
            tvTime.setTextColor(Color.BLACK);
            cvTime.setCardBackgroundColor(Color.TRANSPARENT);
            cvTime.setCardElevation(0);
        }else{
            tvResul.setText(getIntent().getExtras().getString("resul"));
            tvTime.setText(getIntent().getExtras().getString("min"));
        }

        MatchDetailsAdapter matchDetailsAdapter = new MatchDetailsAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(matchDetailsAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

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
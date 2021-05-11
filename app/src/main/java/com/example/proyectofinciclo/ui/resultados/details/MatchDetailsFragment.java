package com.example.proyectofinciclo.ui.resultados.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.timelineprueba.LoadImage;
import com.example.proyectofinciclo.ui.resultados.CalendarioViewModel;
import com.example.proyectofinciclo.ui.resultados.ResulsAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MatchDetailsFragment extends Fragment {

    private CalendarioViewModel mViewModel;
    private RecyclerView recyclerView;
    private ResulsAdapter resulsAdapter;
    private LinearLayoutManager llm;

    public CardView mCardView;
    public TextView tvTime,tvResul;
    public TextView txthome;
    public TextView txtaway;
    public ImageView awayimg,homeimg;

    public static MatchDetailsFragment newInstance() {
        return new MatchDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_match, container, false);

        mCardView = view.findViewById(R.id.cvResul);
        tvResul = view.findViewById(R.id.tvResul);
        tvTime = view.findViewById(R.id.tvTime);
        homeimg = view.findViewById(R.id.ivHomeTeam);
        awayimg = view.findViewById(R.id.ivAwayTeam);
        txthome = view.findViewById(R.id.tvHomeTeam);
        txtaway = view.findViewById(R.id.tvAwayTeam);

        tvResul.setText(getArguments().getString("resul"));
        tvTime.setText(getArguments().getString("min"));
        txthome.setText(getArguments().getString("loc"));
        txtaway.setText(getArguments().getString("vis"));
        String imageHttpAddresshomeloc = getArguments().getString("loc").replace(" ","");
        new LoadImage(homeimg).execute("https://res.adriandiarteprieto.tk/escudos/"+imageHttpAddresshomeloc+".png");
        String imageHttpAddresshomevis = getArguments().getString("vis").replace(" ","");
        new LoadImage(homeimg).execute("https://res.adriandiarteprieto.tk/escudos/"+imageHttpAddresshomevis+".png");

        MatchDetailsAdapter matchDetailsAdapter = new MatchDetailsAdapter(getActivity().getSupportFragmentManager());
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(matchDetailsAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalendarioViewModel.class);
    }

    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }

}
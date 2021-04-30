package com.example.proyectofinciclo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.timelineprueba.LoadImage;
import com.google.android.material.snackbar.Snackbar;


public class NewsDetailsFragment extends Fragment {

    public ImageView imgnews;
    public TextView tvtitle;
    public TextView tvdesc;
    public TextView tvdate;
    public Button btnweb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);
        Bundle data = this.getArguments();
        if(data != null){
            String id = data.getString("id");
            String title = data.getString("title");
            String desc = data.getString("desc");
            String date = data.getString("date");
            String img = data.getString("img");

            imgnews = view.findViewById(R.id.ivNdetails);
            tvtitle = view.findViewById(R.id.txTitleNdetails);
            tvdesc = view.findViewById(R.id.txDescNdetails);
            tvdate = view.findViewById(R.id.txDateNdetails);
            btnweb = view.findViewById(R.id.btnNdetails);

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
                        wvnewsFragment wnf = new wvnewsFragment();
                        Bundle data = new Bundle();
                        data.putString("id",id);
                        wnf.setArguments(data);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment,wnf)
                                .addToBackStack(null)
                                .commit();
                    }
                });
        }


        return view;
    }
    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }
}
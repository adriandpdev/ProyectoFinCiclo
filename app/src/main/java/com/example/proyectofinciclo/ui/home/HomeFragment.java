package com.example.proyectofinciclo.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.ApiService;
import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.Services.ConnectionService;
import com.example.proyectofinciclo.models.news;
import com.example.proyectofinciclo.res.ResCalendario;
import com.example.proyectofinciclo.res.ResNews;
import com.example.proyectofinciclo.Services.LoadImage;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private ResulsHomeAdapter resulsAdapter;
    private LinearLayoutManager llm;
    private CardView btntw, btnig;
    private Button btnnews;
    private HorizontalScrollView hsv;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        btnnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.newsListFragment);
            }
        });
        btntw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.twFragment);
            }
        });
        btnig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.igFragment);
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ConstraintLayout ll = view.findViewById(R.id.linearLayout2);
        ll.setVisibility(View.GONE);
        ProgressBar pgloading = view.findViewById(R.id.pgloading);

        recyclerView = view.findViewById(R.id.rvHomeResul);
        btnig = view.findViewById(R.id.cardViewig);
        btntw = view.findViewById(R.id.cardViewtw);
        btnnews = view.findViewById(R.id.btnnewlist);
        hsv = view.findViewById(R.id.horizontalScrollView);

        recyclerView.setHasFixedSize(true);
        ApiService apiService = ConnectionService.getApiService();
        Call<ResNews> callnews = apiService.getNews();
        callnews.enqueue(new Callback<ResNews>() {
            @Override
            public void onResponse(Call<ResNews> call, Response<ResNews> response) {
                if (response.code()==200) {
                    ResNews res = response.body();
                    if(res.getEstado()!=200){
                        donackbar("Code: " + response.code()+", Estado: "+res.getMensaje(), view );
                        return;
                    }else if(res.getEstado()==200){
                        SimpleDateFormat sf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
                        sf.setLenient(true);
                        List<news> news = res.getNews();
                        // Noticia 1
                        CardView cv1 = view.findViewById(R.id.cardView1);
                        ImageView img1 = view.findViewById(R.id.ivN1);
                        TextView title1 = view.findViewById(R.id.txTitleN1);
                        TextView date1 = view.findViewById(R.id.txDateN1);
                        String imageHttpAddressaway1 = news.get(0).getImg();
                        new LoadImage(img1).execute(imageHttpAddressaway1);
                        title1.setText(news.get(0).getTitle());
                        Date bddate1 = null;
                        String formateddate = null;
                        try {
                            bddate1 = sf.parse(news.get(0).getDate()+"+02:00");
                            String fecha = new SimpleDateFormat("EEEE, d 'de' MMMM",new Locale("es","ES")).format(bddate1);
                            formateddate = fecha.toUpperCase().charAt(0) + fecha.substring(1,fecha.length());
                            date1.setText(formateddate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String finalFormateddate = formateddate;
                        cv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle data = new Bundle();
                                data.putString("id",news.get(0).getId());
                                data.putString("title",news.get(0).getTitle());
                                data.putString("desc",news.get(0).getDesc());
                                data.putString("date", finalFormateddate);
                                data.putString("img",news.get(0).getImg());
                                Navigation.findNavController(view).navigate(R.id.newsDetailsFragment, data);
                            }
                        });

                        // Noticia 2
                        CardView cv2 = view.findViewById(R.id.cardView2);
                        ImageView img2 = view.findViewById(R.id.ivN2);
                        TextView title2 = view.findViewById(R.id.txTitleN2);
                        TextView date2 = view.findViewById(R.id.txDateN2);
                        String imageHttpAddressaway2 = news.get(1).getImg();
                        new LoadImage(img2).execute(imageHttpAddressaway2);
                        title2.setText(news.get(1).getTitle());
                        Date bddate2 = null;
                        String formateddate2 = null;
                        try {
                            bddate2 = sf.parse(news.get(1).getDate()+"+02:00");
                            String fecha = new SimpleDateFormat("EEEE, d 'de' MMMM",new Locale("es","ES")).format(bddate2);
                            formateddate2 =fecha.toUpperCase().charAt(0) + fecha.substring(1,fecha.length());
                            date2.setText(formateddate2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String finalFormateddate2 = formateddate2;
                        cv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle data = new Bundle();
                                data.putString("id",news.get(1).getId());
                                data.putString("title",news.get(1).getTitle());
                                data.putString("desc",news.get(1).getDesc());
                                data.putString("date", finalFormateddate2);
                                data.putString("img",news.get(1).getImg());
                                Navigation.findNavController(view).navigate(R.id.newsDetailsFragment, data);
                            }
                        });

                        // Noticia 3
                        CardView cv3 = view.findViewById(R.id.cardView3);
                        ImageView img3 = view.findViewById(R.id.ivN3);
                        TextView title3 = view.findViewById(R.id.txTitleN3);
                        TextView date3 = view.findViewById(R.id.txDateN3);
                        String imageHttpAddressaway3 = news.get(2).getImg();
                        new LoadImage(img3).execute(imageHttpAddressaway3);
                        title3.setText(news.get(2).getTitle());
                        Date bddate3 = null;
                        String formateddate3 = null;
                        try {
                            bddate3 = sf.parse(news.get(2).getDate()+"+02:00");
                            String fecha = new SimpleDateFormat("EEEE, d 'de' MMMM",new Locale("es","ES")).format(bddate3);
                            formateddate3 =fecha.toUpperCase().charAt(0) + fecha.substring(1,fecha.length());
                            date3.setText(formateddate3);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String finalFormateddate3 = formateddate3;
                        cv3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle data = new Bundle();
                                data.putString("id",news.get(1).getId());
                                data.putString("title",news.get(1).getTitle());
                                data.putString("desc",news.get(1).getDesc());
                                data.putString("date", finalFormateddate3);
                                data.putString("img",news.get(1).getImg());
                                Navigation.findNavController(view).navigate(R.id.newsDetailsFragment, data);
                            }
                        });

                        // Noticia 4
                        CardView cv4 = view.findViewById(R.id.cardView4);
                        ImageView img4 = view.findViewById(R.id.ivN4);
                        TextView title4 = view.findViewById(R.id.txTitleN4);
                        TextView date4 = view.findViewById(R.id.txDateN4);
                        String imageHttpAddressaway4 = news.get(3).getImg();
                        new LoadImage(img4).execute(imageHttpAddressaway4);
                        title4.setText(news.get(3).getTitle());
                        Date bddate4 = null;
                        String formateddate4 = null;
                        try {
                            bddate4 = sf.parse(news.get(3).getDate()+"+02:00");
                            String fecha = new SimpleDateFormat("EEEE, d 'de' MMMM",new Locale("es","ES")).format(bddate4);
                            formateddate4 =fecha.toUpperCase().charAt(0) + fecha.substring(1,fecha.length());
                            date4.setText(formateddate4);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String finalFormateddate4 = formateddate4;
                        cv4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle data = new Bundle();
                                data.putString("id",news.get(1).getId());
                                data.putString("title",news.get(1).getTitle());
                                data.putString("desc",news.get(1).getDesc());
                                data.putString("date", finalFormateddate4);
                                data.putString("img",news.get(1).getImg());
                                Navigation.findNavController(view).navigate(R.id.newsDetailsFragment, data);
                            }
                        });

                        // Noticia 5
                        CardView cv5 = view.findViewById(R.id.cardView5);
                        ImageView img5 = view.findViewById(R.id.ivN5);
                        TextView title5 = view.findViewById(R.id.txTitleN5);
                        TextView date5 = view.findViewById(R.id.txDateN5);
                        String imageHttpAddressaway5 = news.get(4).getImg();
                        new LoadImage(img5).execute(imageHttpAddressaway5);
                        title5.setText(news.get(4).getTitle());
                        Date bddate5 = null;
                        String formateddate5 = null;
                        try {
                            bddate5 = sf.parse(news.get(4).getDate()+"+02:00");
                            String fecha = new SimpleDateFormat("EEEE, d 'de' MMMM",new Locale("es","ES")).format(bddate5);
                            formateddate5 =fecha.toUpperCase().charAt(0) + fecha.substring(1,fecha.length());
                            date4.setText(formateddate5);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String finalFormateddate5 = formateddate5;
                        cv5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle data = new Bundle();
                                data.putString("id",news.get(1).getId());
                                data.putString("title",news.get(1).getTitle());
                                data.putString("desc",news.get(1).getDesc());
                                data.putString("date", finalFormateddate5);
                                data.putString("img",news.get(1).getImg());
                                Navigation.findNavController(view).navigate(R.id.newsDetailsFragment, data);
                            }
                        });

                    }
                }else{
                    donackbar("Code: " + response.code()+", ERROR ", view );
                }
            }

            @Override
            public void onFailure(Call<ResNews> call, Throwable t) {
                donackbar(t.getMessage(), view);
            }
        });

        Call<ResCalendario> call = apiService.getCalendarioHome();
        call.enqueue(new Callback<ResCalendario>() {
            @Override
            public void onResponse(Call<ResCalendario> call, Response<ResCalendario> response) {
                if (response.code()==200) {
                    ResCalendario res = response.body();
                    if(res.getEstado()!=200){
                        donackbar("Code: " + response.code()+", Estado: "+res.getMensaje(), view );
                        return;
                    }else if(res.getEstado()==200){

                        resulsAdapter = new ResulsHomeAdapter(res.getPartidos(),getContext());
                        recyclerView.setAdapter(resulsAdapter);
                        llm = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
                        recyclerView.setLayoutManager(llm);
                        hsv.smoothScrollTo(410, 0);
                    }
                }else{
                    donackbar("Code: " + response.code()+", ERROR ", view );
                }
            }

            @Override
            public void onFailure(Call<ResCalendario> call, Throwable t) {
                Log.d("ERROR", "onFailure: "+t.getMessage());
                donackbar(t.getMessage(), view);
            }
        });

        pgloading.setVisibility(View.GONE);
        ll.setVisibility(View.VISIBLE);
        return view;
    }
    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }

}
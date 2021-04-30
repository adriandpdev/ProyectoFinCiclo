package com.example.proyectofinciclo.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.ui.perfil.PerfilViewModel;
import com.google.android.material.snackbar.Snackbar;

public class wvnewsFragment extends Fragment {


    private PerfilViewModel mViewModel;
    private WebView webView;
    public String id;
    public static wvnewsFragment newInstance() {
        return new wvnewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        Bundle data = this.getArguments();
        if(data != null) {
            id = data.getString("id");
        }else{
            id = "";
        }
        webView = view.findViewById(R.id.webviewnews);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Log.d("TAG", "onCreateView: "+id);
        webView.loadUrl("https://www.unionistascf.com/?p="+id);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
    }

    public void donackbar(String mess, View v){
        Snackbar mSnackbar = Snackbar.make(v, mess, Snackbar.LENGTH_LONG);
        mSnackbar.show();
    }
}

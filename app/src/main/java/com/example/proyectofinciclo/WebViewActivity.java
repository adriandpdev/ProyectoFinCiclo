package com.example.proyectofinciclo;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinciclo.ui.perfil.PerfilViewModel;

public class WebViewActivity extends AppCompatActivity {

    private PerfilViewModel mViewModel;
    private WebView webView;
    private String id;
    public static WebViewActivity newInstance() {
        return new WebViewActivity();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tienda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle data = this.getIntent().getExtras();
        if(data != null) {
            id = data.getString("id");
        }else{
            id = "";
        }
        webView = findViewById(R.id.webviewshop);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.unionistascf.com/?p="+id);
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
package com.example.proyectofinciclo.apitwitter;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TwitterClient {
    // Datos principales
    private static final String REST_URL = "https://api.twitter.com/2";
    private static final String BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAG3eNQEAAAAAm83ZjLJafAfgeWAKal3fYzO6f3E%3DcVlqG7eOQXtZYpdH2RZ4HDLG0PFVkfnKZ41NxFoajKsCA4tj5E";

    // TAG para logs
    public static final String TAG = "TwitterClient";

    // Metodos disponibles para conectar con la api
    public static Response getTimeline(int id, long count) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(REST_URL+"/users/"+id+"/tweets?tweet.fields=created_at,referenced_tweets,entities&expansions=author_id,referenced_tweets.id.author_id&user.fields=created_at,profile_image_url&max_results="+count)
                    .method("GET", null)
                    .addHeader("Authorization", "Bearer "+BEARER_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}

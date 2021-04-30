package com.example.proyectofinciclo.timelineprueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.apitwitter.LoadTimeline;

import java.util.List;
import java.util.concurrent.ExecutionException;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;


public class TimeLinePrueba extends AppCompatActivity {
    public static final String SELECTED_STATUS = "SelectedStatus";
    public static final String STATUSES = "Statuses";
    String[] tweets;
    ResponseList<Status> statuses;
    private TweetsAdapter tweetsAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line_prueba);
        recyclerView = (RecyclerView) findViewById(R.id.RVtimeline);
        recyclerView.setHasFixedSize(true);

        List<Status> statuses = null;
        try {
            statuses = (List<Status>) new LoadTimelineBackground(this).execute().get();
            tweetsAdapter = new TweetsAdapter(statuses,context);
            recyclerView.setAdapter(tweetsAdapter);
            llm = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(llm);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    TwitterListener timelineListener = new TwitterAdapter() {

        @Override
        public void gotHomeTimeline(ResponseList<Status> statuses) {
            showTimeline(statuses);
        }

        @Override
        public void onException(TwitterException te, TwitterMethod method) {
            showError();
        }
    };
    private void showError() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "No se ha podido obtener el Timeline", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void showTimeline(ResponseList<Status> statuses) {
        // Cacheamos en memoria los Tweets
        this.statuses = statuses;

        // Creamos un array de Strings con el texto de los Status( Tweets )
        tweets = new String[statuses.size()];
        int counter = 0;
        for (Status status : statuses) {
            tweets[counter] = status.getText();
            counter++;
        }

        // Lo guardamos en un bundle, el cual le pasaremos al Fragment
        final Bundle bundle = new Bundle();
        bundle.putStringArray(TimelineFragment.TWEETS_KEY, tweets);

        // Debido a que el callback se está ejecutando en otro Thread distinto al Thread de UI, necesitamos mandar un mensaje
        // Al Thread de UI para poder actualizar la vista, para ello usamos el metodo runOnUiThread de la clase Activity
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //View progressBar = findViewById(R.id.progressBar);
                //progressBar.setVisibility(View.GONE);
                // Creamos el TimelineFragment
                Fragment fragment = new Fragment();
                // Añadimos el bundle con los tweets que hemos creado anteriormente
                fragment.setArguments(bundle);
                // Y lo insertamos en la vista contenedor
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, fragment)
                        .commit();
            }
        });
    }

    public static class PruebasTwitterFragment extends Fragment {

        String[] tweets;
        ResponseList<Status> statuses;
        private TweetsAdapter tweetsAdapter;
        private RecyclerView recyclerView;
        private LinearLayoutManager llm;
        public static Context context;

        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_time_line_prueba, container, false);
            recyclerView = view.findViewById(R.id.RVtimeline);
            recyclerView.setHasFixedSize(true);
            List<Status> statuses = null;
            try {
                statuses = new LoadTimeline(view.getContext()).execute().get();
                //statuses = new LoadTimelineBackground(view.getContext()).execute().get();
                tweetsAdapter = new TweetsAdapter(statuses,context);
                recyclerView.setAdapter(tweetsAdapter);
                llm = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(llm);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            return view;
        }
        TwitterListener timelineListener = new TwitterAdapter() {

            @Override
            public void gotHomeTimeline(ResponseList<Status> statuses) {
                showTimeline(statuses);
            }

            @Override
            public void onException(TwitterException te, TwitterMethod method) {
                showError();
            }
        };
        private void showError() {

        }
        private void showTimeline(ResponseList<Status> statuses) {
            // Cacheamos en memoria los Tweets
            this.statuses = statuses;

            // Creamos un array de Strings con el texto de los Status( Tweets )
            tweets = new String[statuses.size()];
            int counter = 0;
            for (Status status : statuses) {
                tweets[counter] = status.getText();
                counter++;
            }

            // Lo guardamos en un bundle, el cual le pasaremos al Fragment
            final Bundle bundle = new Bundle();
            bundle.putStringArray(TimelineFragment.TWEETS_KEY, tweets);

            // Debido a que el callback se está ejecutando en otro Thread distinto al Thread de UI, necesitamos mandar un mensaje
            // Al Thread de UI para poder actualizar la vista, para ello usamos el metodo runOnUiThread de la clase Activity

        }
    }

    public static class twitteredprueba {
        public twitteredprueba() {


        }

    }
}

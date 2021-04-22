package com.example.proyectofinciclo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import twitter4j.*;


public class TimelineTwitterFragment extends Fragment {

    public static final String SELECTED_STATUS = "SelectedStatus";
    public static final String STATUSES = "Statuses";
    String[] tweets;
    ResponseList<Status> statuses;
    View v;

    public TimelineTwitterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_timeline_twitter, container, false);
        Fragment fragment = getChildFragmentManager().findFragmentById(R.id.container);

        // En caso de que no haya ningún fragmento, obtenemos los Tweets del Timeline de la API de twtitter y manejamos el Callback
        if (fragment == null) {
            ApiTwitter.getInstance().getTimelineAsync(timelineListener); // => timelineListener
        } else {

        }
        return v;
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
        Toast.makeText(getContext(), "No se ha podido obtener el Timeline", Toast.LENGTH_LONG).show();
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
        bundle.putStringArray("tweets", tweets);

        // Debido a que el callback se está ejecutando en otro Thread distinto al Thread de UI, necesitamos mandar un mensaje
        // Al Thread de UI para poder actualizar la vista, para ello usamos el metodo runOnUiThread de la clase Activity
        /*MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                // Creamos el TimelineFragment
                Fragment fragment = new TimelineFragment();
                // Añadimos el bundle con los tweets que hemos creado anteriormente
                fragment.setArguments(bundle);
                // Y lo insertamos en la vista contenedor
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, fragment)
                        .commit();
            }
        });*/
    }
}

package com.example.proyectofinciclo.timelineprueba;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinciclo.R;
import com.example.proyectofinciclo.ui.perfil.PerfilViewModel;
import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.signature.TwitterCredentials;

import java.util.List;

public class pruebasTimelineFragment extends Fragment {

    private PerfilViewModel mViewModel;
    private TweetsAdapterHome tweetsAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager llm;
    public static pruebasTimelineFragment newInstance() {
        return new pruebasTimelineFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View view = inflater.inflate(R.layout.activity_time_line_prueba, container, false);

        TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken("958158007-aJuMnp0GbwQxMDsJe8Wysm5FvSnW6B09EuoqRCZ1")
                .accessTokenSecret("lmFfJziny7CmpDAStriKn2Y7OZLaeSFe6Lx3aUMFyQGwx")
                .apiKey("PV12m2GaCoNOobrMyjXfv0IL6")
                .apiSecretKey("HevDhheeV8YGb0xPACoBlsbiaaMB2v7WpjR5m5WjwIvvOz7HF5")
                .build());
        List<Tweet> tweets = twitterClient.getUserTimeline("1665264812",20);
        recyclerView = view.findViewById(R.id.RVtimeline);
        recyclerView.setHasFixedSize(true);

        tweetsAdapter = new TweetsAdapterHome(tweets,view.getContext());
        recyclerView.setAdapter(tweetsAdapter);
        llm = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(llm);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

    }

}
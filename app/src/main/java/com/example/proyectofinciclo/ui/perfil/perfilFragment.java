package com.example.proyectofinciclo.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinciclo.R;
import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.signature.TwitterCredentials;

import java.util.List;

public class perfilFragment extends Fragment {

    private PerfilViewModel mViewModel;

    public static perfilFragment newInstance() {
        return new perfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        TextView tx = view.findViewById(R.id.TEXTOPRUEBA);
        com.github.redouane59.twitter.TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken("958158007-aJuMnp0GbwQxMDsJe8Wysm5FvSnW6B09EuoqRCZ1")
                .accessTokenSecret("lmFfJziny7CmpDAStriKn2Y7OZLaeSFe6Lx3aUMFyQGwx")
                .apiKey("PV12m2GaCoNOobrMyjXfv0IL6")
                .apiSecretKey("HevDhheeV8YGb0xPACoBlsbiaaMB2v7WpjR5m5WjwIvvOz7HF5")
                .build());
        List<Tweet> tweets = twitterClient.getUserTimeline("1665264812",20);
        tx.setText(tweets.get(0).getText());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        // TODO: Use the ViewModel
    }

}
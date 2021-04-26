package com.example.proyectofinciclo.timelineprueba;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class LoadTimelineBackground extends AsyncTask<Void, Void, List<Status>> {

    private Context mContext;
    private String title;
    private ResponseList<twitter4j.Status> statuses;

    public LoadTimelineBackground (Context context) {
        this.mContext = context;
    }


    @Override
    protected List<twitter4j.Status> doInBackground(Void... voids) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("PV12m2GaCoNOobrMyjXfv0IL6")
                .setOAuthConsumerSecret("HevDhheeV8YGb0xPACoBlsbiaaMB2v7WpjR5m5WjwIvvOz7HF5")
                .setOAuthAccessToken("958158007-aJuMnp0GbwQxMDsJe8Wysm5FvSnW6B09EuoqRCZ1")
                .setOAuthAccessTokenSecret("lmFfJziny7CmpDAStriKn2Y7OZLaeSFe6Lx3aUMFyQGwx");
        // Bearer token
        // AAAAAAAAAAAAAAAAAAAAAG3eNQEAAAAA2h9UiLPEo4neluibsibBN8Cwmiw%3D0adp68s2Q2fLm1Y8aRRrf7d6Ty02bLge2Pg5J4N9a2pp0PFRjw
        Twitter twitter = new TwitterFactory(cb.build()).getInstance();
        try {
            statuses = null;
            try {
                statuses = twitter.getUserTimeline(1665264812);
                for (twitter4j.Status status : statuses) {
                    String url= "https://twitter.com/" + status.getUser().getScreenName() + "/status/"
                            + status.getId();
                }
                return statuses;

            } catch (TwitterException e) {
                Log.e("Error", "Exception: " + e.getMessage());
            }

        } catch (Exception e) {
            Log.e("Error", "Exception: " + e.getMessage());
        }

        return null;
    }

}

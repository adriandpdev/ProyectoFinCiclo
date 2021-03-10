package com.example.proyectofinciclo;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.TwitterListener;
import twitter4j.conf.ConfigurationBuilder;

public class ApiTwitter {

    private static ApiTwitter instance;

    private ApiTwitter() {

    }

    public static ApiTwitter getInstance() {
        if (instance == null) {
            instance = new ApiTwitter();
        }
        return instance;
    }

    private ConfigurationBuilder getConfiguration() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("qjWGbIhAjurvYLUF85BCwwDL7")
                .setOAuthConsumerSecret("Jrm7eZ9nfBigAkVxYqBgGlFjUiKPi71WwZ6Dtbhl5Y0vATR4sY")
                .setOAuthAccessToken("UWo1iyFMAvpT2RaC99zKpcvZPgxK7zCdPI7FvZGd")
                .setOAuthAccessTokenSecret("jM1Y5gRz9EKHZilXxPLqpaoJN29OY3qcRZ79bcwtMhtES");

        return cb;
    }

    public void getTimelineAsync(TwitterListener listener) {
        AsyncTwitterFactory factory = new AsyncTwitterFactory((getConfiguration().build()));
        AsyncTwitter asyncTwitter = factory.getInstance();
        asyncTwitter.addListener(listener);
        asyncTwitter.getHomeTimeline();
    }

}

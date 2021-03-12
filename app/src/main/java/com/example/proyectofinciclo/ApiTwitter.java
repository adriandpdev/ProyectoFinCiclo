package com.example.proyectofinciclo;

import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.logging.Handler;

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
                .setOAuthConsumerKey("PV12m2GaCoNOobrMyjXfv0IL6")
                .setOAuthConsumerSecret("HevDhheeV8YGb0xPACoBlsbiaaMB2v7WpjR5m5WjwIvvOz7HF5")
                .setOAuthAccessToken("958158007-aJuMnp0GbwQxMDsJe8Wysm5FvSnW6B09EuoqRCZ1")
                .setOAuthAccessTokenSecret("lmFfJziny7CmpDAStriKn2Y7OZLaeSFe6Lx3aUMFyQGwx");
        // Bearer token
        // AAAAAAAAAAAAAAAAAAAAAG3eNQEAAAAA2h9UiLPEo4neluibsibBN8Cwmiw%3D0adp68s2Q2fLm1Y8aRRrf7d6Ty02bLge2Pg5J4N9a2pp0PFRjw
        return cb;
    }

    public void getTimelineAsync(TwitterListener listener) {
        AsyncTwitterFactory factory = new AsyncTwitterFactory((getConfiguration().build()));
        AsyncTwitter asyncTwitter = factory.getInstance();
        asyncTwitter.addListener(listener);
        asyncTwitter.getHomeTimeline();
    }

}

package com.yucun.shuttlebus;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by yucunli on 2015-06-28.
 */
public final class ShuttlebusApp extends Application {

    @Override
    public void onCreate() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "giF8a6oUsrGc8hBGamKh31veeKJNyUKeSpneOfvI", "C1GTLyvouCEIDnkBmBRmT1mWtLDXRh6Ojfg8iSRl");
    }
}

package com.yucun.shuttlebus;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.yucun.shuttlebus.model.Monday;

/**
 * Created by yucunli on 2015-06-28.
 */
public final class ShuttlebusApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(Monday.class);
        Parse.initialize(this, "giF8a6oUsrGc8hBGamKh31veeKJNyUKeSpneOfvI", "C1GTLyvouCEIDnkBmBRmT1mWtLDXRh6Ojfg8iSRl");
    }
}

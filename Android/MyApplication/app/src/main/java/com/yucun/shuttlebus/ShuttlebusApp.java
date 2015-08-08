package com.yucun.shuttlebus;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.yucun.shuttlebus.model.Friday;
import com.yucun.shuttlebus.model.Info;
import com.yucun.shuttlebus.model.Monday;
import com.yucun.shuttlebus.model.Thursday;
import com.yucun.shuttlebus.model.Tuesday;
import com.yucun.shuttlebus.model.Wednesday;
import io.fabric.sdk.android.Fabric;

/**
 * Created by yucunli on 2015-06-28.
 */
public final class ShuttlebusApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        ParseObject.registerSubclass(Monday.class);
        ParseObject.registerSubclass(Tuesday.class);
        ParseObject.registerSubclass(Wednesday.class);
        ParseObject.registerSubclass(Thursday.class);
        ParseObject.registerSubclass(Friday.class);

        ParseObject.registerSubclass(Info.class);

        Parse.initialize(this, "giF8a6oUsrGc8hBGamKh31veeKJNyUKeSpneOfvI", "C1GTLyvouCEIDnkBmBRmT1mWtLDXRh6Ojfg8iSRl");

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }
}

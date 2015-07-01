package com.yucun.shuttlebus;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;
import com.yucun.shuttlebus.adapter.MondayListAdapter;
import com.yucun.shuttlebus.model.Monday;
import com.yucun.shuttlebus.service.LocationService;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class MainActivity extends ActionBarActivity {

    private static final long LOCATION_TIMEOUT_SECONDS = 20;

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.listview) ListView listview;

    MondayListAdapter mondayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        final LocationManager locationManager = (LocationManager) getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);
        final LocationService locationService = new LocationService(locationManager);


        // Get our current location.
        locationService.getLocation()
            .timeout(LOCATION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .map(new Func1<Location, String>() {
                @Override
                public String call(final Location location) {
                    final double longitude = location.getLongitude();
                    final double latitude = location.getLatitude();

                    return longitude + " " + latitude;
                }
            })
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<String>() {
                @Override
                public void onNext(String s) {
                    title.setText(s);
                }

                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }
            });

        // Set up the Parse query to use in the adapter
        ParseQueryAdapter.QueryFactory<Monday> factory = new ParseQueryAdapter.QueryFactory<Monday>() {
            public ParseQuery<Monday> create() {
                ParseQuery<Monday> query = Monday.getQuery();
                query.fromLocalDatastore();
                query.orderByAscending("time_order");
                query.whereEqualTo("campus", "sgw");
                return query;
            }
        };

        mondayListAdapter = new MondayListAdapter(this, factory);

        // Attach the query adapter to the view
        listview.setAdapter(mondayListAdapter);

        loadFromParse();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFromParse() {
        ParseQuery<Monday> query = Monday.getQuery();
        query.findInBackground(new FindCallback<Monday>() {
            public void done(List<Monday> todos, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Monday>) todos,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!isFinishing()) {
                                            mondayListAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("TodoListActivity",
                                                "Error pinning todos: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("TodoListActivity",
                            "loadFromParse: Error finding pinned todos: "
                                    + e.getMessage());
                }
            }
        });
    }
}

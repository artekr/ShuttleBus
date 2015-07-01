package com.yucun.shuttlebus;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;
import com.yucun.shuttlebus.adapter.MondayListAdapter;
import com.yucun.shuttlebus.adapter.WednesdayListAdapter;
import com.yucun.shuttlebus.model.Monday;
import com.yucun.shuttlebus.model.Wednesday;

import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.listview) ListView listview;

    MondayListAdapter mondayListAdapter;
    WednesdayListAdapter wednesdayListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
            case Calendar.MONDAY:
                // Set up the Parse query to use in the adapter
                ParseQueryAdapter.QueryFactory<Monday> mondayQueryFactory = new ParseQueryAdapter.QueryFactory<Monday>() {
                    public ParseQuery<Monday> create() {
                        ParseQuery<Monday> query = Monday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "sgw");
                        return query;
                    }
                };

                mondayListAdapter = new MondayListAdapter(this, mondayQueryFactory);

                // Attach the query adapter to the view
                listview.setAdapter(mondayListAdapter);

                loadMondayFromParse();
            case Calendar.TUESDAY:
            case Calendar.WEDNESDAY:
                // Set up the Parse query to use in the adapter
                ParseQueryAdapter.QueryFactory<Wednesday> wednesdayQueryFactory = new ParseQueryAdapter.QueryFactory<Wednesday>() {
                    public ParseQuery<Wednesday> create() {
                        ParseQuery<Wednesday> query = Wednesday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "sgw");
                        return query;
                    }
                };

                wednesdayListAdapter = new WednesdayListAdapter(this, wednesdayQueryFactory);

                // Attach the query adapter to the view
                listview.setAdapter(wednesdayListAdapter);

                loadWednesdayFromParse();
            case Calendar.THURSDAY:
            case Calendar.FRIDAY:
            case Calendar.SATURDAY:
        }

    }

    private void loadMondayFromParse() {
        ParseQuery<Monday> query = Monday.getQuery();
        query.findInBackground(new FindCallback<Monday>() {
            public void done(List<Monday> mondayList, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Monday>) mondayList,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!isFinishing()) {
                                            mondayListAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("MainActivity",
                                                "Error pinning mondays: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("MainActivity",
                            "loadMondayFromParse: Error finding pinned mondays: "
                                    + e.getMessage());
                }
            }
        });
    }

    private void loadWednesdayFromParse() {
        ParseQuery<Wednesday> query = Wednesday.getQuery();
        query.findInBackground(new FindCallback<Wednesday>() {
            public void done(List<Wednesday> wednesdayList, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Wednesday>) wednesdayList,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!isFinishing()) {
                                            wednesdayListAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("MainActivity",
                                                "Error pinning wednesdays: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("MainActivity",
                            "loadWednesdayFromParse: Error finding pinned wednesdays: "
                                    + e.getMessage());
                }
            }
        });
    }
}

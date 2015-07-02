package com.yucun.shuttlebus.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;
import com.yucun.shuttlebus.R;
import com.yucun.shuttlebus.adapter.MondayListAdapter;
import com.yucun.shuttlebus.adapter.ThursdayListAdapter;
import com.yucun.shuttlebus.adapter.WednesdayListAdapter;
import com.yucun.shuttlebus.model.Monday;
import com.yucun.shuttlebus.model.Thursday;
import com.yucun.shuttlebus.model.Wednesday;

import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yucunli on 2015-07-02.
 */
public class ScheduleFragment extends Fragment {

    @InjectView(R.id.sgw_listview) ListView sgw_listview;
    @InjectView(R.id.loyola_listview) ListView loyola_listview;

    MondayListAdapter mondayListAdapter;
    WednesdayListAdapter wednesdayListAdapter;
    ThursdayListAdapter thursdaySGWListAdapter;
    ThursdayListAdapter thursdayLoyolaListAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.inject(this, rootView);

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

                mondayListAdapter = new MondayListAdapter(getActivity(), mondayQueryFactory);

                // Attach the query adapter to the view
                sgw_listview.setAdapter(mondayListAdapter);

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

                wednesdayListAdapter = new WednesdayListAdapter(getActivity(), wednesdayQueryFactory);

                // Attach the query adapter to the view
                sgw_listview.setAdapter(wednesdayListAdapter);

                loadWednesdayFromParse();
            case Calendar.THURSDAY:
                // Set up the Parse query to use in the adapter
                ParseQueryAdapter.QueryFactory<Thursday>  thursdaySGWQueryFactory
                        = new ParseQueryAdapter.QueryFactory<Thursday>() {
                    public ParseQuery<Thursday> create() {
                        ParseQuery<Thursday> query = Thursday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "sgw");
                        return query;
                    }
                };

                ParseQueryAdapter.QueryFactory<Thursday>  thursdayLoyolaQueryFactory
                        = new ParseQueryAdapter.QueryFactory<Thursday>() {
                    public ParseQuery<Thursday> create() {
                        ParseQuery<Thursday> query = Thursday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "loyola");
                        return query;
                    }
                };

                thursdaySGWListAdapter = new ThursdayListAdapter(getActivity(), thursdaySGWQueryFactory);
                thursdayLoyolaListAdapter = new ThursdayListAdapter(getActivity(), thursdayLoyolaQueryFactory);

                // Attach the query adapter to the view
                sgw_listview.setAdapter(thursdaySGWListAdapter);
                loyola_listview.setAdapter(thursdayLoyolaListAdapter);

                loadThursdayFromParse();
            case Calendar.FRIDAY:
            case Calendar.SATURDAY:
        }

        return rootView;
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
                                        if (!getActivity().isFinishing()) {
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
                                        if (!getActivity().isFinishing()) {
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

    private void loadThursdayFromParse() {
        ParseQuery<Thursday> query = Thursday.getQuery();
        query.findInBackground(new FindCallback<Thursday>() {
            public void done(List<Thursday> thursdayList, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Thursday>) thursdayList,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!getActivity().isFinishing()) {
                                            thursdaySGWListAdapter.loadObjects();
                                            thursdayLoyolaListAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("MainActivity",
                                                "Error pinning thursday: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("MainActivity",
                            "loadWednesdayFromParse: Error finding pinned thursday: "
                                    + e.getMessage());
                }
            }
        });
    }
}

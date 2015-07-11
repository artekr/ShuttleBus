package com.yucun.shuttlebus.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;
import com.yucun.shuttlebus.InfoActivity;
import com.yucun.shuttlebus.R;
import com.yucun.shuttlebus.adapter.FridayListAdapter;
import com.yucun.shuttlebus.adapter.MondayListAdapter;
import com.yucun.shuttlebus.adapter.SaturdayListAdapter;
import com.yucun.shuttlebus.adapter.SundayListAdapter;
import com.yucun.shuttlebus.adapter.ThursdayListAdapter;
import com.yucun.shuttlebus.adapter.TuesdayListAdapter;
import com.yucun.shuttlebus.adapter.WednesdayListAdapter;
import com.yucun.shuttlebus.model.Friday;
import com.yucun.shuttlebus.model.Monday;
import com.yucun.shuttlebus.model.Saturday;
import com.yucun.shuttlebus.model.Sunday;
import com.yucun.shuttlebus.model.Thursday;
import com.yucun.shuttlebus.model.Tuesday;
import com.yucun.shuttlebus.model.Wednesday;
import com.yucun.shuttlebus.service.LocationService;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yucunli on 2015-07-02.
 */
public class ScheduleFragment extends Fragment {

    private static final long LOCATION_TIMEOUT_SECONDS = 20;

    private final Location LOYOLA_LOCATION = new Location("");
    private final Location SGW_LOCATION = new Location("");

    @InjectView(R.id.sgw_listview) ListView sgw_listview;
    @InjectView(R.id.loyola_listview) ListView loyola_listview;

    MondayListAdapter mondaySGWListAdapter;
    MondayListAdapter mondayLoyolaListAdapter;
    TuesdayListAdapter tuesdaySGWListAdapter;
    TuesdayListAdapter tuesdayLoyolaListAdapter;
    WednesdayListAdapter wednesdaySGWListAdapter;
    WednesdayListAdapter wednesdayLoyolaListAdapter;
    ThursdayListAdapter thursdaySGWListAdapter;
    ThursdayListAdapter thursdayLoyolaListAdapter;
    FridayListAdapter fridaySGWListAdapter;
    FridayListAdapter fridayLoyolaListAdapter;
    SaturdayListAdapter saturdaySGWListAdapter;
    SaturdayListAdapter saturdayLoyolaListAdapter;
    SundayListAdapter sundaySGWListAdapter;
    SundayListAdapter sundayLoyolaListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        LOYOLA_LOCATION.setLatitude(45.4580804);
        LOYOLA_LOCATION.setLongitude(-73.6388611);

        SGW_LOCATION.setLatitude(45.4970244);
        SGW_LOCATION.setLongitude(-73.5792617);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.inject(this, rootView);

        Calendar calendar = Calendar.getInstance();
        //int day = calendar.get(Calendar.DAY_OF_WEEK);
        int day = Calendar.MONDAY;
        switch (day) {
            case Calendar.SUNDAY:
                // Set up the Parse query to use in the adapter
                ParseQueryAdapter.QueryFactory<Sunday> sundaySGWQueryFactory = new ParseQueryAdapter.QueryFactory<Sunday>() {
                    public ParseQuery<Sunday> create() {
                        ParseQuery<Sunday> query = Sunday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "sgw");
                        return query;
                    }
                };

                ParseQueryAdapter.QueryFactory<Sunday> sundayLoyolaQueryFactory = new ParseQueryAdapter.QueryFactory<Sunday>() {
                    public ParseQuery<Sunday> create() {
                        ParseQuery<Sunday> query = Sunday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "loyola");
                        return query;
                    }
                };

                sundaySGWListAdapter = new SundayListAdapter(getActivity(), sundaySGWQueryFactory);
                sundayLoyolaListAdapter = new SundayListAdapter(getActivity(), sundayLoyolaQueryFactory);

                // Attach the query adapter to the view
                sgw_listview.setAdapter(sundaySGWListAdapter);
                loyola_listview.setAdapter(sundayLoyolaListAdapter);

                loadSundayFromParse();
                break;
            case Calendar.MONDAY:
                // Set up the Parse query to use in the adapter
                ParseQueryAdapter.QueryFactory<Monday> mondaySGWQueryFactory = new ParseQueryAdapter.QueryFactory<Monday>() {
                    public ParseQuery<Monday> create() {
                        ParseQuery<Monday> query = Monday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "sgw");
                        return query;
                    }
                };

                ParseQueryAdapter.QueryFactory<Monday> mondayLoyolaQueryFactory = new ParseQueryAdapter.QueryFactory<Monday>() {
                    public ParseQuery<Monday> create() {
                        ParseQuery<Monday> query = Monday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "loyola");
                        return query;
                    }
                };

                mondaySGWListAdapter = new MondayListAdapter(getActivity(), mondaySGWQueryFactory);
                mondayLoyolaListAdapter = new MondayListAdapter(getActivity(), mondayLoyolaQueryFactory);

                // Attach the query adapter to the view
                sgw_listview.setAdapter(mondaySGWListAdapter);
                loyola_listview.setAdapter(mondayLoyolaListAdapter);

                loadMondayFromParse();
                break;
            case Calendar.TUESDAY:
                // Set up the Parse query to use in the adapter
                ParseQueryAdapter.QueryFactory<Tuesday> tuesdaySGWQueryFactory = new ParseQueryAdapter.QueryFactory<Tuesday>() {
                    public ParseQuery<Tuesday> create() {
                        ParseQuery<Tuesday> query = Tuesday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "sgw");
                        return query;
                    }
                };

                ParseQueryAdapter.QueryFactory<Tuesday> tuesdayLoyolaQueryFactory = new ParseQueryAdapter.QueryFactory<Tuesday>() {
                    public ParseQuery<Tuesday> create() {
                        ParseQuery<Tuesday> query = Tuesday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "loyola");
                        return query;
                    }
                };

                tuesdaySGWListAdapter = new TuesdayListAdapter(getActivity(), tuesdaySGWQueryFactory);
                tuesdayLoyolaListAdapter = new TuesdayListAdapter(getActivity(), tuesdayLoyolaQueryFactory);

                // Attach the query adapter to the view
                sgw_listview.setAdapter(tuesdaySGWListAdapter);
                loyola_listview.setAdapter(tuesdayLoyolaListAdapter);

                loadTuesdayFromParse();
                break;
            case Calendar.WEDNESDAY:
                // Set up the Parse query to use in the adapter
                ParseQueryAdapter.QueryFactory<Wednesday> wednesdaySGWQueryFactory = new ParseQueryAdapter.QueryFactory<Wednesday>() {
                    public ParseQuery<Wednesday> create() {
                        ParseQuery<Wednesday> query = Wednesday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "sgw");
                        return query;
                    }
                };

                ParseQueryAdapter.QueryFactory<Wednesday> wednesdayLoyolaQueryFactory = new ParseQueryAdapter.QueryFactory<Wednesday>() {
                    public ParseQuery<Wednesday> create() {
                        ParseQuery<Wednesday> query = Wednesday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "loyola");
                        return query;
                    }
                };

                wednesdaySGWListAdapter = new WednesdayListAdapter(getActivity(), wednesdaySGWQueryFactory);
                wednesdayLoyolaListAdapter = new WednesdayListAdapter(getActivity(), wednesdayLoyolaQueryFactory);

                // Attach the query adapter to the view
                sgw_listview.setAdapter(wednesdaySGWListAdapter);
                loyola_listview.setAdapter(wednesdayLoyolaListAdapter);

                loadWednesdayFromParse();
                break;
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
                break;
            case Calendar.FRIDAY:
                // Set up the Parse query to use in the adapter
                ParseQueryAdapter.QueryFactory<Friday> fridaySGWQueryFactory = new ParseQueryAdapter.QueryFactory<Friday>() {
                    public ParseQuery<Friday> create() {
                        ParseQuery<Friday> query = Friday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "sgw");
                        return query;
                    }
                };

                ParseQueryAdapter.QueryFactory<Friday> fridayLoyolaQueryFactory = new ParseQueryAdapter.QueryFactory<Friday>() {
                    public ParseQuery<Friday> create() {
                        ParseQuery<Friday> query = Friday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "loyola");
                        return query;
                    }
                };

                fridaySGWListAdapter = new FridayListAdapter(getActivity(), fridaySGWQueryFactory);
                fridayLoyolaListAdapter = new FridayListAdapter(getActivity(), fridayLoyolaQueryFactory);

                // Attach the query adapter to the view
                sgw_listview.setAdapter(fridaySGWListAdapter);
                loyola_listview.setAdapter(fridayLoyolaListAdapter);

                loadFridayFromParse();
                break;
            case Calendar.SATURDAY:
                // Set up the Parse query to use in the adapter
                ParseQueryAdapter.QueryFactory<Saturday> saturdaySGWQueryFactory = new ParseQueryAdapter.QueryFactory<Saturday>() {
                    public ParseQuery<Saturday> create() {
                        ParseQuery<Saturday> query = Saturday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "sgw");
                        return query;
                    }
                };

                ParseQueryAdapter.QueryFactory<Saturday> saturdayLoyolaQueryFactory = new ParseQueryAdapter.QueryFactory<Saturday>() {
                    public ParseQuery<Saturday> create() {
                        ParseQuery<Saturday> query = Saturday.getQuery();
                        query.fromLocalDatastore();
                        query.orderByAscending("time_order");
                        query.whereEqualTo("campus", "loyola");
                        return query;
                    }
                };

                saturdaySGWListAdapter = new SaturdayListAdapter(getActivity(), saturdaySGWQueryFactory);
                saturdayLoyolaListAdapter = new SaturdayListAdapter(getActivity(), saturdayLoyolaQueryFactory);

                // Attach the query adapter to the view
                sgw_listview.setAdapter(saturdaySGWListAdapter);
                loyola_listview.setAdapter(saturdayLoyolaListAdapter);

                loadSaturdayFromParse();
                break;
        }

        final LocationManager locationManager = (LocationManager) getActivity().getApplicationContext()
                .getSystemService(Context.LOCATION_SERVICE);
        final LocationService locationService = new LocationService(locationManager);

        // Get our current location.
        locationService.getLocation()
                .timeout(LOCATION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .map(new Func1<Location, String>() {
                    @Override
                    public String call(final Location location) {
                        float distanceToSGW = location.distanceTo(SGW_LOCATION);
                        float distanceToLoyola = location.distanceTo(LOYOLA_LOCATION);

                        return distanceToSGW - distanceToLoyola >= 0 ? "loyola" : "sgw";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String campus) {
                        if (campus.equals("loyola")) {
                            sgw_listview.setVisibility(View.INVISIBLE);
                            loyola_listview.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.schedule_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sgw:
                sgw_listview.setVisibility(View.VISIBLE);
                loyola_listview.setVisibility(View.INVISIBLE);

                return true;
            case R.id.loyola:
                sgw_listview.setVisibility(View.INVISIBLE);
                loyola_listview.setVisibility(View.VISIBLE);

                return true;
            case R.id.info:
                Intent intent = new Intent();
                intent.setClass(getActivity(), InfoActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadSundayFromParse() {
        ParseQuery<Sunday> query = Sunday.getQuery();
        query.findInBackground(new FindCallback<Sunday>() {
            public void done(List<Sunday> sundayList, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Sunday>) sundayList,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!getActivity().isFinishing()) {
                                            sundaySGWListAdapter.loadObjects();
                                            sundayLoyolaListAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("MainActivity",
                                                "Error pinning sundays: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("MainActivity",
                            "loadSundayFromParse: Error finding pinned sundays: "
                                    + e.getMessage());
                }
            }
        });
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
                                            mondaySGWListAdapter.loadObjects();
                                            mondayLoyolaListAdapter.loadObjects();
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

    private void loadTuesdayFromParse() {
        ParseQuery<Tuesday> query = Tuesday.getQuery();
        query.findInBackground(new FindCallback<Tuesday>() {
            public void done(List<Tuesday> tuesdayList, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Tuesday>) tuesdayList,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!getActivity().isFinishing()) {
                                            tuesdaySGWListAdapter.loadObjects();
                                            tuesdayLoyolaListAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("MainActivity",
                                                "Error pinning tuesday: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("MainActivity",
                            "loadTuesdayFromParse: Error finding pinned tuesday: "
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
                                            wednesdaySGWListAdapter.loadObjects();
                                            wednesdayLoyolaListAdapter.loadObjects();
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

    private void loadFridayFromParse() {
        ParseQuery<Friday> query = Friday.getQuery();
        query.findInBackground(new FindCallback<Friday>() {
            public void done(List<Friday> fridayList, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Friday>) fridayList,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!getActivity().isFinishing()) {
                                            fridaySGWListAdapter.loadObjects();
                                            fridayLoyolaListAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("MainActivity",
                                                "Error pinning friday: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("MainActivity",
                            "loadFridayFromParse: Error finding pinned friday: "
                                    + e.getMessage());
                }
            }
        });
    }

    private void loadSaturdayFromParse() {
        ParseQuery<Saturday> query = Saturday.getQuery();
        query.findInBackground(new FindCallback<Saturday>() {
            public void done(List<Saturday> saturdayList, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground((List<Saturday>) saturdayList,
                            new SaveCallback() {
                                public void done(ParseException e) {
                                    if (e == null) {
                                        if (!getActivity().isFinishing()) {
                                            saturdaySGWListAdapter.loadObjects();
                                            saturdayLoyolaListAdapter.loadObjects();
                                        }
                                    } else {
                                        Log.i("MainActivity",
                                                "Error pinning saturday: "
                                                        + e.getMessage());
                                    }
                                }
                            });
                } else {
                    Log.i("MainActivity",
                            "loadSaturdayFromParse: Error finding pinned saturday: "
                                    + e.getMessage());
                }
            }
        });
    }
}

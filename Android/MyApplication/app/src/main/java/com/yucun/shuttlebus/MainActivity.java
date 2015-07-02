package com.yucun.shuttlebus;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.yucun.shuttlebus.fragment.ScheduleFragment;

import butterknife.ButterKnife;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ScheduleFragment())
                    .commit();
        }


    }

}

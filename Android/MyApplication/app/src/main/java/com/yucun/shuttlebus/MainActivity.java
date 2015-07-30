package com.yucun.shuttlebus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yucun.shuttlebus.fragment.ScheduleFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        // Set a toolbar to replace the action bar.
        toolbar.setTitle("Shuttle Bus");
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ScheduleFragment())
                    .commit();
        }

    }

}

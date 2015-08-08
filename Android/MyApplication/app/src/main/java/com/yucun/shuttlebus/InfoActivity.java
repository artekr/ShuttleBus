package com.yucun.shuttlebus;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.yucun.shuttlebus.model.Info;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yucunli on 2015-07-11.
 */
public class InfoActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar) Toolbar toolbar;
    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.content) TextView content;
    @InjectView(R.id.contact) TextView contact;
    @InjectView(R.id.version) TextView version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.inject(this);

        // Set a toolbar to replace the action bar.
        toolbar.setNavigationIcon(R.drawable.ic_ab_up_white);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        loadInfoDataFromLocal();
        loadInfoDataFromServer();

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
            version.setText("version: " + pInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return false;
    }

    private void loadInfoDataFromLocal() {
        ParseQuery<Info> query = Info.getQuery();
        query.fromLocalDatastore();
        query.orderByDescending("createdAt");
        query.getFirstInBackground(new GetCallback<Info>() {

            @Override
            public void done(Info info, ParseException e) {
                if (info == null) {
                    Log.d("Parse", "The getFirst request failed.");
                } else {
                    // got the most recently modified object... do something with it here
                    title.setText(info.getTitle());
                    content.setText(info.getContent() + info.getCreatedAt().toString());
                    contact.setText("Contact: " + info.getEmail());
                }
            }
        });
    }

    private void loadInfoDataFromServer() {
        ParseQuery<Info> query = Info.getQuery();
        query.orderByDescending("createdAt");
        query.getFirstInBackground(new GetCallback<Info>() {

            @Override
            public void done(Info info, ParseException e) {
                if (info == null) {
                    Log.d("Parse", "The getFirst request failed.");
                } else {
                    // got the most recently modified object... do something with it here
                    info.pinInBackground();
                    title.setText(info.getTitle());
                    content.setText(info.getContent() + info.getCreatedAt().toString());
                    contact.setText("Contact: " + info.getEmail());
                }
            }
        });
    }
}

package com.yucun.shuttlebus;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.ButterKnife;

/**
 * Created by yucunli on 2015-07-11.
 */
public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

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
}

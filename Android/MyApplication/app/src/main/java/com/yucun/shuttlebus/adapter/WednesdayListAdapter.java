package com.yucun.shuttlebus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.yucun.shuttlebus.R;
import com.yucun.shuttlebus.model.Monday;
import com.yucun.shuttlebus.model.Wednesday;

/**
 * Created by yucunli on 2015-07-01.
 */
public class WednesdayListAdapter extends ParseQueryAdapter<Wednesday> {

    private LayoutInflater inflater;

    public WednesdayListAdapter(Context context,
                             ParseQueryAdapter.QueryFactory<Wednesday> queryFactory) {
        super(context, queryFactory);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getItemView(Wednesday wednesday, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_day, parent, false);
            holder = new ViewHolder();
            holder.departTime = (TextView) view
                    .findViewById(R.id.departTime);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TextView departTime = holder.departTime;
        departTime.setText(wednesday.getDepart_time());

        return view;
    }

    private static class ViewHolder {
        TextView departTime;
    }
}

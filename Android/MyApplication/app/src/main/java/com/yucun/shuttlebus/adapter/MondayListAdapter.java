package com.yucun.shuttlebus.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.yucun.shuttlebus.R;
import com.yucun.shuttlebus.model.Monday;

/**
 * Created by yucunli on 2015-06-29.
 */
public class MondayListAdapter extends ParseQueryAdapter<Monday> {

    private LayoutInflater inflater;

    public MondayListAdapter(Context context,
                           ParseQueryAdapter.QueryFactory<Monday> queryFactory) {
        super(context, queryFactory);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getItemView(Monday monday, View view, ViewGroup parent) {
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
        departTime.setText(monday.getDepart_time());

        return view;
    }

    private static class ViewHolder {
        TextView departTime;
    }
}

package com.yucun.shuttlebus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.yucun.shuttlebus.R;
import com.yucun.shuttlebus.model.Day;
import com.yucun.shuttlebus.model.Tuesday;

/**
 * Created by yucunli on 2015-07-11.
 */
public class DayListAdapter<T extends Day> extends ParseQueryAdapter<T> {

    private LayoutInflater inflater;

    public DayListAdapter(Context context,
                          ParseQueryAdapter.QueryFactory<T> queryFactory){
        super(context, queryFactory);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getItemView(T day, View view, ViewGroup parent) {
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
        departTime.setText(day.getDepart_time());

        return view;
    }

    private static class ViewHolder {
        TextView departTime;
    }
}

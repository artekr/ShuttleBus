package com.yucun.shuttlebus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;
import com.yucun.shuttlebus.R;
import com.yucun.shuttlebus.model.Monday;
import com.yucun.shuttlebus.model.Thursday;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yucunli on 2015-07-02.
 */
public class ThursdayListAdapter extends ParseQueryAdapter<Thursday> {

    private LayoutInflater inflater;

    public ThursdayListAdapter(Context context,
                             ParseQueryAdapter.QueryFactory<Thursday> queryFactory) {
        super(context, queryFactory);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getItemView(Thursday thursday, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_day, parent, false);
            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        TextView departTime = holder.departTime;
        departTime.setText(thursday.getDepart_time());

        return view;
    }

    public static class ViewHolder {
        @InjectView(R.id.departTime)
        TextView departTime;

        public ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }
}

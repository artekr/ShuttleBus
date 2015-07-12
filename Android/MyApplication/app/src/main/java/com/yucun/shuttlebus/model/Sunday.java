package com.yucun.shuttlebus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by yucunli on 2015-07-01.
 */
@ParseClassName("Sunday")
public class Sunday extends Day {

    public static ParseQuery<Sunday> getQuery() {
        return ParseQuery.getQuery(Sunday.class);
    }

}

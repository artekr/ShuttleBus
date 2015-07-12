package com.yucun.shuttlebus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by yucunli on 2015-06-29.
 */
@ParseClassName("Monday")
public class Monday extends Day {

    public static ParseQuery<Monday> getQuery() {
        return ParseQuery.getQuery(Monday.class);
    }
}

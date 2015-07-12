package com.yucun.shuttlebus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by yucunli on 2015-07-01.
 */
@ParseClassName("Friday")
public class Friday extends Day {

    public static ParseQuery<Friday> getQuery() {
        return ParseQuery.getQuery(Friday.class);
    }
}

package com.yucun.shuttlebus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by yucunli on 2015-07-01.
 */
@ParseClassName("Saturday")
public class Saturday extends Day {

    public static ParseQuery<Saturday> getQuery() {
        return ParseQuery.getQuery(Saturday.class);
    }

}

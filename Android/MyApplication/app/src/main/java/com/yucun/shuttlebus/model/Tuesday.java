package com.yucun.shuttlebus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by yucunli on 2015-07-01.
 */
@ParseClassName("Tuesday")
public class Tuesday extends Day {

    public static ParseQuery<Tuesday> getQuery() {
        return ParseQuery.getQuery(Tuesday.class);
    }

}

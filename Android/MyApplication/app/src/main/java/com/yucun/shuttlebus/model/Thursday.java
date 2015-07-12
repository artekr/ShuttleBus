package com.yucun.shuttlebus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by yucunli on 2015-07-01.
 */
@ParseClassName("Thursday")
public class Thursday extends Day {

    public static ParseQuery<Thursday> getQuery() {
        return ParseQuery.getQuery(Thursday.class);
    }

}

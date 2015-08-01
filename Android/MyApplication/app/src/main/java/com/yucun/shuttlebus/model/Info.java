package com.yucun.shuttlebus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by yucunli on 2015-07-31.
 */
@ParseClassName("Info")
public class Info extends ParseObject {
    public String getContent() {
        return getString("content");
    }

    public String getTitle() {
        return getString("title");
    }

    public static ParseQuery<Info> getQuery() {
        return ParseQuery.getQuery(Info.class);
    }
}

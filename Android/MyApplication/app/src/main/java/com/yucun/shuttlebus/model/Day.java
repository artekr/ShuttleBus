package com.yucun.shuttlebus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by yucunli on 2015-07-12.
 */
public class Day extends ParseObject {
    public String getCampus() {
        return getString("campus");
    }


    public String getDepart_time() {
        return getString("depart_time");
    }


    public String getTime_order() {
        return getString("time_order");
    }

}

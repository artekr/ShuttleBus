package com.yucun.shuttlebus.model;

/**
 * Created by yucunli on 2015-06-28.
 */
public class DepartureTime {

    private String campus;
    private String departure_time;
    private int time_order;

    public String getCampus(){
        return this.campus;
    }

    public String getDeparture_time() {
        return this.departure_time;
    }

    public int getTime_order() {
        return this.time_order;
    }
}

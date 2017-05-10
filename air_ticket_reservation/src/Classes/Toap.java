/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Date;

/**
 *
 * @author Chaithika
 */
public class Toap {

    private String leg_no;
    private String airportID;
    private Date departure_time;
    private Date arrival_time;

    /**
     * @return the leg_no
     */
    public String getLeg_no() {
        return leg_no;
    }

    /**
     * @param leg_no the leg_no to set
     */
    public void setLeg_no(String leg_no) {
        this.leg_no = leg_no;
    }

    /**
     * @return the airportID
     */
    public String getAirportID() {
        return airportID;
    }

    /**
     * @param airportID the airportID to set
     */
    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    /**
     * @return the departure_time
     */
    public Date getDeparture_time() {
        return departure_time;
    }

    /**
     * @param departure_time the departure_time to set
     */
    public void setDeparture_time(Date departure_time) {
        this.departure_time = departure_time;
    }

    /**
     * @return the arrival_time
     */
    public Date getArrival_time() {
        return arrival_time;
    }

    /**
     * @param arrival_time the arrival_time to set
     */
    public void setArrival_time(Date arrival_time) {
        this.arrival_time = arrival_time;
    }

    
}

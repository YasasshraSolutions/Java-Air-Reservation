/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Chaithika
 */
public class Flight {
    private String flight_no;
    private int max_seats;
    private String airline_id;
    private boolean active;

    /**
     * @return the flight_no
     */
    public String getFlight_no() {
        return flight_no;
    }

    /**
     * @param flight_no the flight_no to set
     */
    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    /**
     * @return the max_seats
     */
    public int getMax_seats() {
        return max_seats;
    }

    /**
     * @param max_seats the max_seats to set
     */
    public void setMax_seats(int max_seats) {
        this.max_seats = max_seats;
    }

    /**
     * @return the airline_id
     */
    public String getAirline_id() {
        return airline_id;
    }

    /**
     * @param airline_id the airline_id to set
     */
    public void setAirline_id(String airline_id) {
        this.airline_id = airline_id;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}

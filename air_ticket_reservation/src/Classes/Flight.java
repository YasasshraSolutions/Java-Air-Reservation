/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Buddhi Aberatne
 */
public class Flight {

    private String flight_no = null;
    private int max_seats = 0;
    private String airline_ID = null;
    private boolean active = false;
    private Connection conn = null;
    public boolean exist = false;

    /**
     * Added default constructor
     */
    public Flight() {
        this.conn = DBConnect.connect();
    }

    /**
     * Constructor to auto load flight details from db
     *
     * @param flight_no
     */
    public Flight(String flight_no) {
        this.conn = DBConnect.connect();
        PreparedStatement pst = null;
        try {
            String sql = "SELECT * FROM `flight` WHERE `flight_no` = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, flight_no);
            ResultSet rs;
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst()) {
                return;
            }
            while (rs.next()) {
                this.flight_no = rs.getString("flight_no");
                this.max_seats = rs.getInt("max_seats");
                this.airline_ID = rs.getString("airline_ID");
                this.active = rs.getBoolean("active");
                this.exist = true;
            }
        } catch (SQLException e) {
            System.out.println("Error : while excicuting prepared statement");
            System.out.println(e);
        }
    }

    public boolean save() {
        try {
            String sql = "INSERT INTO `flight`(`flight_no`, `max_seats`, `airline_ID`, `active`) VALUES (?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.flight_no);
            pst.setInt(2, this.max_seats);
            pst.setString(3, this.airline_ID);
            pst.setBoolean(4, this.active);
            pst.executeUpdate();
            this.exist = true;
            return true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                try {
                    String sql = "UPDATE `flight` SET `max_seats`=?,`airline_ID`=?,`active`=? WHERE `flight_no` = ?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setInt(1, this.max_seats);
                    pst.setString(2, this.airline_ID);
                    pst.setBoolean(3, this.active);
                    pst.setString(4, this.flight_no);
                    pst.executeUpdate();
                    this.exist = true;
                    return true;

                } catch (SQLException e2) {
                    System.out.println("Error : while excicuting prepared statement");
                    return false;
                }
            }
            return false;
        }

    }

    public ResultSet getAll() {
        PreparedStatement pst;
        try {
            String sql = "SELECT * FROM flight";
            pst = this.conn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            System.out.println("Error : while excicuting prepared statement");
            System.out.println(e);
            return null;
        }
    }
  
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
        return airline_ID;
    }

    /**
     * @param airline_id the airline_id to set
     */
    public void setAirline_id(String airline_id) {
        this.airline_ID = airline_id;
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

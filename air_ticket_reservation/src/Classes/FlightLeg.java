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
import java.util.Date;

/**
 *
 * @author Buddhi Abeyratne
 */
public class FlightLeg {
    
    private int leg_no;
    private String leg_type = null;
    private String from_aID = null;
    private String to_aID = null;
    private Date departure_time = null;
    private Date arival_time = null;
    private String flight_no = null ;
    private Connection conn=null;
    
    
    public FlightLeg()
    {
      this.conn = DBConnect.connect();  
    }
    
    
    public FlightLeg(int leg_no)
    {
      this.conn = DBConnect.connect(); 
      PreparedStatement pst = null;
        try {
            String sql = "SELECT * FROM `flight_leg` WHERE `leg_no` = ?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,leg_no);
            ResultSet rs;
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst() ) {     
                return;
            } 
            while (rs.next()) {
                this.leg_no = rs.getInt("leg_no");
                this.leg_type = rs.getString("leg_type");
                this.from_aID = rs.getString("from_aID");
                this.to_aID = rs.getString("to_aID");
                this.departure_time = rs.getDate("departure_time");
                this.arival_time = rs.getDate("departure_time");
                this.flight_no = rs.getString("flight_no");
            }
        } catch (SQLException e) {
            System.out.println("Error : while excicuting prepared statement");
            System.out.println(e);
        }
    }
    
    /**
     * @return the leg_no
     */
    public int getLeg_no() {
        return leg_no;
    }

    /**
     * @param leg_no the leg_no to set
     */
    public void setLeg_no(int leg_no) {
        this.leg_no = leg_no;
    }

    /**
     * @return the leg_type
     */
    public String getLeg_type() {
        return leg_type;
    }

    /**
     * @param leg_type the leg_type to set
     */
    public void setLeg_type(String leg_type) {
        this.leg_type = leg_type;
    }

    /**
     * @return the from_aID
     */
    public String getFrom_aID() {
        return from_aID;
    }

    /**
     * @param from_aID the from_aID to set
     */
    public void setFrom_aID(String from_aID) {
        this.from_aID = from_aID;
    }

    /**
     * @return the to_aID
     */
    public String getTo_aID() {
        return to_aID;
    }

    /**
     * @param to_aID the to_aID to set
     */
    public void setTo_aID(String to_aID) {
        this.to_aID = to_aID;
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
     * @return the arival_time
     */
    public Date getArival_time() {
        return arival_time;
    }

    /**
     * @param arival_time the arival_time to set
     */
    public void setArival_time(Date arival_time) {
        this.arival_time = arival_time;
    }
    
    
    
}

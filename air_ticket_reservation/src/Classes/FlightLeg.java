/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.mysql.jdbc.Statement;
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
    public boolean exist  = false;
    
    /**
     * Default constructor
     */
    public FlightLeg()
    {
      this.conn = DBConnect.connect();  
    }
    
    /**
     * Constructor to auto load flight leg from db
     * @param leg_no : primary key of Flight leg
     */
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
                this.departure_time = rs.getTimestamp("departure_time");
                this.arival_time = rs.getTimestamp("arival_time");
                this.flight_no = rs.getString("flight_no");
                this.exist = true;
            }
        } catch (SQLException e) {
            System.out.println("Error : while excicuting prepared statement");
            System.out.println(e);
        }
    }
    
    
    
    public boolean save() {
        if(!this.exist){
        try {
            String sql = "INSERT INTO `flight_leg`(`leg_type`, `flight_no`, `from_aID`, `departure_time`, `to_aID`, `arival_time`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, this.leg_type);
            pst.setString(2, this.getFlight_no());
            pst.setString(3, this.from_aID);
            pst.setTimestamp(4, new java.sql.Timestamp(this.departure_time.getTime()));
            pst.setString(5, this.to_aID);
            pst.setTimestamp(6, new java.sql.Timestamp(this.arival_time.getTime()));
            pst.executeUpdate();
            this.exist = true;
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next())
            {
                this.leg_no = rs.getInt(1);
            }
            return true;
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062){
                this.exist = true;
                return this.save();
            }
            System.out.println(e);
            return false;
        }
        }else{
            try {
                    String sql = "UPDATE `flight_leg` SET `leg_type`=?,`flight_no`=?,`from_aID`=?,`departure_time`=?,`to_aID`=?,`arival_time`=? WHERE `leg_no`=?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, this.leg_type);
                    pst.setString(2, this.getFlight_no());
                    pst.setString(3, this.from_aID);
                    pst.setTimestamp(4, new java.sql.Timestamp(this.departure_time.getTime()));
                    pst.setString(5, this.to_aID);
                    pst.setTimestamp(6, new java.sql.Timestamp(this.arival_time.getTime()));
                    pst.setInt(7, this.leg_no);
                    pst.executeUpdate();
                    this.exist = true;
                    return true;
                } catch (SQLException e2) {
                    System.out.println("Error : while excicuting prepared statement");
                    return false;
                }
        }

    }
    public boolean remove(){
         try {
            String sql = "DELETE FROM `flight_leg` WHERE `leg_no` =?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, this.leg_no);
            pst.executeUpdate();
            this.exist = false;
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    public ResultSet getAll() {
        PreparedStatement pst;
        try {
            String sql = "SELECT * FROM `flight_leg`";
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
    
    
    
}

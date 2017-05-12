/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import Classes.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Manash Gurudeniya
 */
public class Airport {
    private String airportID = null;
    private String name = null;
    private String city = null;
    private String apstate = null;
    private String country = null;
    private boolean active = false;
    private Connection conn;
    public boolean exist = false;
    
    /**
     * default constructor
     */
    public Airport(){
        conn = DBConnect.connect();
    }
    
    /**
     * constructor with an ID
     */
    public Airport(String aid){
        conn = DBConnect.connect();
        PreparedStatement pst = null;
        try {
            String sql = "SELECT * FROM `airport` WHERE `airportID`=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, aid);
            ResultSet rs;
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst() ) {     
                return;
            }
            while(rs.next()){
                this.airportID = rs.getString("airportID");
                this.name = rs.getString("name");
                this.city = rs.getString("city");
                this.apstate = rs.getString("apstate");
                this.country = rs.getString("country");
                this.active = rs.getBoolean("active");
                this.exist = true;
            }
            
        } catch (SQLException e) {
            System.out.println("Error:"+e);
            
        }
    }
    
    /**
     * save method
     */
    public boolean save(){
        try {
            String sql = "INSERT INTO `airport`(`airportID`, `name`, `city`, `apstate`, `country`, `active`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.airportID);
            pst.setString(2, this.name);
            pst.setString(3, this.city);
            pst.setString(4, this.apstate);
            pst.setString(5, this.country);
            pst.setBoolean(6, this.active);
            pst.executeUpdate();
            this.exist = true;
            return true;
            
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062){
                try {
                    String sql = "UPDATE `airport` SET `airportID`=?,`name`=?,`city`=?,`apstate`=?,`country`=?,`active`=? WHERE `airportID` = ?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, this.airportID);
                    pst.setString(2, this.name);
                    pst.setString(3, this.city);
                    pst.setString(4, this.apstate);
                    pst.setString(5, this.country);
                    pst.setBoolean(6, this.active);
                    pst.setString(7, this.airportID);
                    pst.executeUpdate();
                    this.exist = true;
                    return true;
                } catch (SQLException e1) {
                    System.out.println("Error"+e1);
                    return false;
                }
            }
            return false;
        }
    }
    
    public ResultSet getALl()
    {
        PreparedStatement pst;
        try {
            String sql = "SELECT * FROM `airport`";
            pst=this.conn.prepareStatement(sql);
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
     * public method to deactivate
     */
    public void deactivate(){
        conn = DBConnect.connect();
        PreparedStatement pst = null;
        if(exist== true){
                this.active= false;
                this.save();
        }
        
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the apstate
     */
    public String getApstate() {
        return apstate;
    }

    /**
     * @param apstate the apstate to set
     */
    public void setApstate(String apstate) {
        this.apstate = apstate;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
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

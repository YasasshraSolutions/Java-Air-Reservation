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
/**
 *
 * @author Manash Gurudeniya
 */
public class Airport {
    private String airportID;
    private String name;
    private String city;
    private String apstate;
    private String country;
    private boolean active;
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
            
        } catch (Exception e) {
            System.out.println("Error:"+e);
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

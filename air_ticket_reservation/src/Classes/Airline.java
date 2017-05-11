/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;
import Classes.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Chaithika Stephen
 */
public class Airline {
    
    private String airline_ID=null;
    private String airline_name=null;
    private String origin=null;
    private boolean active=false;
    public boolean exist = false;
    private Connection conn =null;
    /**
     * Get object for a given id
     */
    public  Airline(String ID){
        this.conn = DBConnect.connect();
        PreparedStatement pst = null;
        try {
            String sql = "SELECT * FROM airline where airline_ID = ?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,ID);
            ResultSet rs;
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst() ) {     
                return;
            } 
            while (rs.next()) {
                    this.airline_ID = rs.getString("airline_ID");
                    this.airline_name = rs.getString("airline_name");
                    this.origin = rs.getString("origin");
                    this.active  = rs.getBoolean("active");
                    this.exist = true;
            }
        } catch (SQLException e) {
            System.out.println("Error : while excicuting prepared statement");
            System.out.println(e);
        }
    }
    
    /**
     * Default constructor
     */
    public Airline(){
        this.conn = DBConnect.connect();
    }
    /**
     * insert or update if not exist
     */
    public boolean save(){
        try {
            String sql="INSERT INTO `airline`(`airline_ID`, `airline_name`, `origin`, `active`) VALUES (?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.airline_ID);
            pst.setString(2, this.airline_name);
            pst.setString(3, this.origin);
            pst.setBoolean(4, this.active);
            pst.executeUpdate();
            this.exist = true;
            return true;
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062){
                try {
                        String sql="UPDATE `airline` SET `airline_name`=?,`origin`=?,`active`=? WHERE `airline_ID`=?";
                        PreparedStatement pst = conn.prepareStatement(sql);
                        pst.setString(1, this.airline_name);
                        pst.setString(2, this.origin);
                        pst.setBoolean(3, this.active);
                        pst.setString(4, this.airline_ID);
                        pst.executeUpdate();
                        this.exist = true;
                        return true; 
                    } catch (SQLException e2) {
                        System.out.println("Error : while excicuting prepared statement");
                        return false;
                    }
            }
            System.out.println(e);
            return false;
        }
    }
    
    /**
     * @return the airline_ID
     */
    public String getAirline_ID() {
        return airline_ID;
    }

    /**
     * @param airline_ID the airline_ID to set
     */
    public void setAirline_ID(String airline_ID) {
        this.airline_ID = airline_ID;
    }

    /**
     * @return the airline_name
     */
    public String getAirline_name() {
        return airline_name;
    }

    /**
     * @param airline_name the airline_name to set
     */
    public void setAirline_name(String airline_name) {
        this.airline_name = airline_name;
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
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

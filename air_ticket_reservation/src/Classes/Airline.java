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
/**
 *
 * @author Chaithika Stephen
 */
public class Airline {
    
    private String airline_ID;
    private String airline_name;
    private String origin;
    private boolean active;
    private Connection conn =null;
    /**
     * Get object for a given id
     */
    Airline(String ID){
        conn = DBConnect.connect();
        try {
            String sql = "SELECT * FROM airline where airline_ID=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, ID);
            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                    this.airline_ID = rs.getString("airline_ID");
                    this.airline_name = rs.getString("airline_name");
                    this.origin = rs.getString("origin");
                    this.active  = rs.getBoolean("active");
            }
        } catch (Exception e) {
            System.out.println("Error : could  not find object");
        }
    }
    
    /**
     * Default constructor
     */
    Airline(){
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

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
 * @author Chaithika Stephen
 */
public class Admin {
    private int admin_id;
    private String user_name = null;
    private String password = null;
    private int admin_level;
    private String name = null;
    private Connection conn = null;
    private boolean exist = false;

    /**
     * constructor with the id
     * @param adminid : admin id
     */
    public  Admin(int adminid){
        conn = DBConnect.connect();
        PreparedStatement pst;
        try {
            String sql = "SELECT * FROM `admin` WHERE `admin_id` = ?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,adminid);
            ResultSet rs;
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst() ) {     
                return;
            } 
            while (rs.next()) {
                    this.admin_id =rs.getInt("admin_id");
                    this.password = rs.getString("password");
                    this.user_name = rs.getString("user_name");
                    this.admin_level = rs.getInt("admin_level");
                    this.name = rs.getString("name");
                    this.exist = true;
            }
        } catch (SQLException e) {
            System.out.println("Error : while excicuting prepared statement");
            System.out.println(e);
            System.out.println(e.getErrorCode());
        }
    }
    
    /**
     * constructor with the id
     * @param username : user name of admin
     * @param pass : admin password 
     * @return  : boolian login details validity
     */
    public boolean adminLogin(String username,String pass){
        conn = DBConnect.connect();
        PreparedStatement pst;
        try {
            String sql = "SELECT * FROM `admin` WHERE `user_name` = ? AND `password` = ?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2, pass);
            ResultSet rs;
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst() ) {     
                return false;
            } 
            while (rs.next()) {
                    this.admin_id =rs.getInt("admin_id");
                    this.password = rs.getString("password");
                    this.user_name = rs.getString("user_name");
                    this.admin_level = rs.getInt("admin_level");
                    this.name = rs.getString("name");
                    this.exist = true;
                    return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error : while excicuting prepared statement");
            System.out.println(e);
            System.out.println(e.getErrorCode());
            return false;
        }
    }
    
    public boolean save(){
        try {
            String sql = "INSERT INTO `admin`(`user_name`, `password`, `admin_level`, `name`) VALUES (?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,this.user_name);
            pst.setString(2, this.password);
            pst.setInt(3,this.admin_level);
            pst.setString(4, this.name);
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if(rs.next())
            {
                this.admin_id = rs.getInt(1);
            }
            this.exist = true;
            return true;
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062){
                try {
                    String sql="UPDATE `admin` SET `user_name`=?,`password`=?,`admin_level`=?,`name`=? WHERE `admin_id`=?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1,this.user_name);
                    pst.setString(2, this.password);
                    pst.setInt(3,this.admin_level);
                    pst.setString(4, this.name);
                    pst.setInt(5,this.admin_id);
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

    
    /**
     * @return the admin_id
     */
    public int getAdmin_id() {
        return admin_id;
    }

    /**
     * @param admin_id the admin_id to set
     */
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the admin_level
     */
    public int getAdmin_level() {
        return admin_level;
    }

    /**
     * @param admin_level the admin_level to set
     */
    public void setAdmin_level(int admin_level) {
        this.admin_level = admin_level;
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
     * @return the exist
     */
    public boolean isExist() {
        return exist;
    }
    
}

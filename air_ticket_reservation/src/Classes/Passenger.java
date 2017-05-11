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
import java.util.Date;

/**
 *
 * @author Shaveen Dimasha
 */
public class Passenger {
    private String tel = null;
    private String paddress = null;
    private String fname = null;
    private String lname = null;
    private String pass_no = null;
    private String password = null;
    private Date dob = null;
    private boolean active = false;
    private Connection conn = null;
    public boolean exist = false;
    /**
     * default constructor
     */
    public Passenger(){
        conn = DBConnect.connect();
    }
    
    /**
     * constructor with the id
     */
    public  Passenger(String passNo){
        conn = DBConnect.connect();
        PreparedStatement pst = null;
        try {
            String sql = "SELECT * FROM `passenger` WHERE `pass_no`=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,passNo);
            ResultSet rs;
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst() ) {     
                return;
            } 
            while (rs.next()) {
                    this.tel = rs.getString("tel");
                    this.paddress = rs.getString("paddress");
                    this.fname = rs.getString("fname");
                    this.lname = rs.getString("lname");
                    this.pass_no = rs.getString("pass_no");
                    this.password = rs.getString("password");
                    this.dob = rs.getDate("dob");
                    this.active  = rs.getBoolean("active");
                    this.exist = true;
            }
        } catch (SQLException e) {
            System.out.println("Error : while excicuting prepared statement");
            System.out.println(e);
            System.out.println(e.getErrorCode());
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
     * public method save
     */
    public boolean save(){
        try {
            String sql = "INSERT INTO `passenger`(`tel`, `paddress`, `fname`, `lname`, `pass_no`, `password`, `dob`, `active`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, this.tel);
            pst.setString(2, this.paddress);
            pst.setString(3, this.fname);
            pst.setString(4, this.lname);
            pst.setString(5, this.pass_no);
            pst.setString(6, this.password);
            pst.setDate(7, new java.sql.Date(this.dob.getTime()));
            pst.setBoolean(8, this.active);
            pst.executeUpdate();
            this.exist = true;
            return true;
        } catch (SQLException e) {
            if(e.getErrorCode() == 1062){
                try {
                    String sql="UPDATE `passenger` SET `tel`=?,`paddress`=?,`fname`=?,`lname`=?,`pass_no`=?,`password`=?,`dob`=?,`active`=? WHERE `pass_no` = ?";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setString(1, this.tel);
                    pst.setString(2, this.paddress);
                    pst.setString(3, this.fname);
                    pst.setString(4, this.lname);
                    pst.setString(5, this.pass_no);
                    pst.setString(6, this.password);
                    pst.setDate(7, new java.sql.Date(this.dob.getTime()));
                    pst.setBoolean(8, this.active);
                    pst.setString(9, this.pass_no);
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
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the paddress
     */
    public String getPaddress() {
        return paddress;
    }

    /**
     * @param paddress the paddress to set
     */
    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the pass_no
     */
    public String getPass_no() {
        return pass_no;
    }

    /**
     * @param pass_no the pass_no to set
     */
    public void setPass_no(String pass_no) {
        this.pass_no = pass_no;
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
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
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

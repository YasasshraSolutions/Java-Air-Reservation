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
 * @author Chaithika
 */
public class Tickets {

    private int seat_no;
    private int class_;
    private String pass_no;
    private int leg_no;
    private Connection conn;
    private boolean exist = false;

    public Tickets() {
        conn = DBConnect.connect();
    }

    public Tickets(int psNo, String Plegno) {
        conn = DBConnect.connect();
        PreparedStatement pst = null;
        try {
            String sql = "SELECT * FROM `tickets` WHERE `seat_no`=? AND `leg_no`=?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, psNo);
            pst.setString(2, Plegno);
            ResultSet rs;
            rs = pst.executeQuery();
            if (!rs.isBeforeFirst()) {
                return;
            }
            while (rs.next()) {
                this.seat_no = rs.getInt("seat_no");
                this.pass_no = rs.getString("pass_no");
                this.leg_no = rs.getInt("leg_no");
                this.class_ = rs.getInt("class");
                this.exist = true;
            }

        } catch (SQLException e) {
            System.out.println("Error:" + e);

        }
    }

    private boolean delete() {
        try {
            String sql = "DELETE FROM `tickets` WHERE `seat_no` = ? AND `leg_no` = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, this.seat_no);
            pst.setInt(2, this.leg_no);
            pst.executeUpdate();
            this.exist = false;
            return true;
        } catch (SQLException e) {
            System.out.println("Error:" + e);
            return false;
        }

    }

    public boolean changeSeat(int pSeat) {

        if (this.delete()) {
            this.seat_no = pSeat;
            if (!this.save()) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * save method
     */
    public boolean save() {
        try {
            String sql = "INSERT INTO `tickets`(`seat_no`, `class`, `pass_no`, `leg_no`) VALUES (?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, this.seat_no);
            pst.setInt(2, this.class_);
            pst.setString(3, this.pass_no);
            pst.setInt(4, this.leg_no);
            pst.executeUpdate();
            this.exist = true;
            return true;

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                try {
                    String sql = "UPDATE `tickets` SET `class`=?,`pass_no`=? WHERE `seat_no` = ? AND `leg_no` =?";
                    PreparedStatement pst = conn.prepareStatement(sql);

                    pst.setInt(1, this.class_);
                    pst.setString(2, this.pass_no);
                    pst.setInt(3, this.seat_no);
                    pst.setInt(4, this.leg_no);
                    pst.executeUpdate();
                    this.exist = true;
                    return true;
                } catch (SQLException e1) {
                    System.out.println("Error" + e1);
                    return false;
                }
            }
            return false;
        }
    }

    public ResultSet getAll() {
        PreparedStatement pst;
        try {
            String sql = "SELECT * FROM `tickets`";
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
     * @return the seat_no
     */
    public int getSeat_no() {
        return seat_no;
    }

    /**
     * @param seat_no the seat_no to set
     */
    public void setSeat_no(int seat_no) {
        this.seat_no = seat_no;
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
     * @return the class_
     */
    public int getClass_() {
        return class_;
    }

    /**
     * @param class_ the class_ to set
     */
    public void setClass_(int class_) {
        this.class_ = class_;
    }

    /**
     * @return the exist
     */
    public boolean isExist() {
        return exist;
    }

}

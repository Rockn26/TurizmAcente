package com.Model;

import com.Helper.DBConnector;
import com.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservationInfo {
    private int id;
    private String c_name;
    private String c_phone;
    private String c_email;
    private String c_note;
    private String check_in;
    private String check_out;
    private int adultN;
    private int childN;
    private int price;
    private int room_id;

    public ReservationInfo() {
    }

    public ReservationInfo(int id, String c_name, String c_phone, String c_email, String c_note, String check_in, String check_out, int adultN, int childN, int price, int room_id) {
        this.id = id;
        this.c_name = c_name;
        this.c_phone = c_phone;
        this.c_email = c_email;
        this.c_note = c_note;
        this.check_in = check_in;
        this.check_out = check_out;
        this.adultN = adultN;
        this.childN = childN;
        this.price = price;
        this.room_id = room_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_phone() {
        return c_phone;
    }

    public void setC_phone(String c_phone) {
        this.c_phone = c_phone;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getC_note() {
        return c_note;
    }

    public void setC_note(String c_note) {
        this.c_note = c_note;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public int getAdultN() {
        return adultN;
    }

    public void setAdultN(int adultN) {
        this.adultN = adultN;
    }

    public int getChildN() {
        return childN;
    }

    public void setChildN(int childN) {
        this.childN = childN;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }


    public static boolean add(String c_name, String c_phone, String c_email, String c_note, String check_in, String check_out, int adultN, int childN, int price, int room_id) {
        String query = "INSERT INTO reservation_info (c_name,c_phone,c_email,c_note,check_in,check_out,adultN,childN,price,room_id) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, c_name);
            pr.setString(2, c_phone);
            pr.setString(3, c_email);
            pr.setString(4, c_note);
            pr.setString(5, check_in);
            pr.setString(6, check_out);
            pr.setInt(7, adultN);
            pr.setInt(8, childN);
            pr.setInt(9, price);
            pr.setInt(10, room_id);

            int response = pr.executeUpdate();

            if (response == -1) {
                Helper.showMsg("error");
            }
            return response != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<ReservationInfo> getList() {
        ArrayList<ReservationInfo> reservationInfo = new ArrayList<>();
        String query = "SELECT * FROM reservation_info";
        ReservationInfo obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = new ReservationInfo();
                obj.setId(rs.getInt("id"));
                obj.setC_name(rs.getString("c_name"));
                obj.setC_phone(rs.getString("c_phone"));
                obj.setC_email(rs.getString("c_email"));
                obj.setC_note(rs.getString("c_note"));
                obj.setCheck_in(rs.getString("check_in"));
                obj.setCheck_out(rs.getString("check_out"));
                obj.setAdultN(rs.getInt("adultN"));
                obj.setChildN(rs.getInt("childN"));
                obj.setPrice(rs.getInt("price"));
                obj.setRoom_id(rs.getInt("room_id"));
                reservationInfo.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationInfo;
    }


    public static boolean delete(int id){
        String query =  "DELETE FROM reservation_info WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static ReservationInfo getFetch(String c_email){
        ReservationInfo obj = null;
        String query = "SELECT * FROM reservation_info WHERE c_email =?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,c_email);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new ReservationInfo();
                obj.setId(rs.getInt("id"));
                obj.setC_name(rs.getString("c_name"));
                obj.setC_phone(rs.getString("c_phone"));
                obj.setC_email(rs.getString("c_email"));
                obj.setC_note(rs.getString("c_note"));
                obj.setCheck_in(rs.getString("check_in"));
                obj.setCheck_out(rs.getString("check_out"));
                obj.setAdultN(rs.getInt("adultN"));
                obj.setChildN(rs.getInt("childN"));
                obj.setPrice(rs.getInt("price"));
                obj.setRoom_id(rs.getInt("room_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static ArrayList<ReservationInfo> searchHotelList(String query) {
        ArrayList<ReservationInfo> reservationInfos = new ArrayList<>();
        ReservationInfo obj;

        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new ReservationInfo();
                obj.setId(rs.getInt("id"));
                obj.setC_name(rs.getString("c_name"));
                obj.setC_phone(rs.getString("c_phone"));
                obj.setC_email(rs.getString("c_email"));
                obj.setC_note(rs.getString("c_note"));
                obj.setCheck_in(rs.getString("check_in"));
                obj.setCheck_out(rs.getString("check_out"));
                obj.setAdultN(rs.getInt("adultN"));
                obj.setChildN(rs.getInt("childN"));
                obj.setPrice(rs.getInt("price"));
                obj.setRoom_id(rs.getInt("room_id"));
                reservationInfos.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationInfos;
    }


}

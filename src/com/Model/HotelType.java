package com.Model;

import com.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelType {
    private int id;
    private String type;
    private int hotel_id;

    private Hotel hotel;

    public HotelType(int id, String type, int hotel_id) {
        this.id = id;
        this.type = type;
        this.hotel_id = hotel_id;
        this.hotel = Hotel.getFetch(hotel_id);
    }

    public HotelType() {
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public static boolean add(String type, int hotel_id){
        String query = "INSERT INTO type_hotel(type,hotel_id) VALUES (?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,type);
            pr.setInt(2,hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<HotelType> getListByHotelID(int id) {
        ArrayList<HotelType> hotelTypesList = new ArrayList<>();
        HotelType obj;
        String query = "SELECT * FROM type_hotel WHERE hotel_id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                obj = new HotelType();
                obj.setId(rs.getInt("id"));
                obj.setType(rs.getString("type"));
                obj.setHotel_id(rs.getInt("hotel_id"));
                hotelTypesList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelTypesList;
    }

    public static HotelType getFetch(int id){
        HotelType obj = null;
        String query = "SELECT * FROM type_hotel WHERE id =?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new HotelType(rs.getInt("id"),rs.getString("type"),rs.getInt("hotel_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

}

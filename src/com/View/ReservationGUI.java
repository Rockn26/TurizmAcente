package com.View;

import com.Helper.Config;
import com.Helper.DBConnector;
import com.Helper.Helper;
import com.Model.Hotel;
import com.Model.ReservationInfo;
import com.Model.Room;
import com.Model.RoomProperties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_phone;
    private JTextField fld_hotel_roomType;
    private JTextArea txt_hotel_address;
    private JTextArea txt_hotel_properties;
    private JTextArea txt_hotel_roomPropert;
    private JTextField fld_adultN;
    private JTextField fld_childN;
    private JTextField fld_startDate;
    private JTextField fld_endDate;
    private JTextField fld_price;
    private JTextField fld_client_name;
    private JTextField fld_client_phone;
    private JTextField fld_client_mail;
    private JTextArea txt_client_rezNote;
    private JButton btn_rez_add;
    private JTextField fld_hotel_name;


    private final Room room;
    private int adultN = 0;
    private int childN = 0;
    private String startDate;
    private String endDate;

    private int total_price;

    public ReservationGUI(Room room, int adultN, int childN, String startDate, String endDate){
        this.room = room;
        this.adultN = adultN;
        this.childN = childN;
        this.startDate = startDate;
        this.endDate = endDate;

        add(wrapper);
        setSize(1100, 800);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        // Otelden odanın id sini alıyoruz.
        Hotel hotel = Hotel.getFetch(room.getHotel_id());
        // odanın id sine göre oda özelliklerini aldık
        RoomProperties roomProperties = RoomProperties.getFetch(room.getId());


        // Seçilen odanın özelliklerini getiriyoruz.
        fld_hotel_name.setText(hotel.getName());
        txt_hotel_address.setText(hotel.getAdress());
        fld_phone.setText(hotel.getPhone());
        txt_hotel_properties.setText(hotel.getProperty());
        fld_hotel_roomType.setText(room.getRoom_type());
        txt_hotel_roomPropert.setText(roomProperties.getProperty() + "\n" + roomProperties.getBed() + "\n" + roomProperties.getArea());
        fld_adultN.setText(Integer.toString(adultN));
        fld_childN.setText(Integer.toString(childN));
        fld_startDate.setText(startDate);
        fld_endDate.setText(endDate);


        SimpleDateFormat foratter = new SimpleDateFormat("dd/MM/yyyy");
        Date check_in = null;
        Date check_out = null;

        try {
            check_in = foratter.parse(startDate);
            check_out = foratter.parse(endDate);

        }catch (ParseException parseException){

        }

        // zaman farkı hesabı

        long days = (check_out.getTime() - check_in.getTime()) /(24 * 60 * 60 * 1000); // Format bu şekildeymiş.

        total_price = (int) (days * ((room.getAdult_price()* adultN) + (room.getChild_price()* childN)) );

        fld_price.setText(String.valueOf(total_price) + " Tl");


        btn_rez_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_client_name) || Helper.isFieldEmpty(fld_client_name) || Helper.isFieldEmpty(fld_client_mail) || Helper.isAreaEmpty(txt_client_rezNote)){
                Helper.showMsg("Lütfen tüm alanları doldurunuz");
            } else {
                String name = fld_client_name.getText();
                String phone = fld_client_phone.getText();
                String email = fld_client_mail.getText();
                String note = txt_client_rezNote.getText();
                int room_id = room.getId();
                if (ReservationInfo.add(name,phone,email,note,startDate,endDate,adultN,childN,total_price,room_id)){
                    Helper.showMsg("done");
                    int stokNumber = room.getStock() - 1;
                    updateStock(stokNumber,room_id);
                }
            }
        });
    }

    private static boolean updateStock(int stok, int id) {
        String query = "UPDATE room SET stock = ? WHERE id = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,stok);
            pr.setInt(2,id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}

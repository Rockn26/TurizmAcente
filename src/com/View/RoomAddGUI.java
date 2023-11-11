package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Helper.Item;
import com.Model.*;

import javax.swing.*;
import java.util.ArrayList;

public class RoomAddGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_left;
    private JPanel pnl_right;
    private JComboBox cmb_hotel_name;
    private JComboBox cmb_room_type;
    private JComboBox cmb_hotel_pensionType;
    private JComboBox cmb_hotel_season;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_room_bedTypeNum;
    private JTextField fld_room_area;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JButton btn_room_add;
    private JTextField fld_room_stock;
    private final Employee employee;
    private int addedRoom_id;


    public RoomAddGUI(Employee employee) {
        this.employee = employee;
        add(wrapper);
        setSize(700, 500);

        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        radioButton1.setText(Helper.roomProperty("1"));
        radioButton2.setText(Helper.roomProperty("2"));
        radioButton3.setText(Helper.roomProperty("3"));
        radioButton4.setText(Helper.roomProperty("4"));
        radioButton5.setText(Helper.roomProperty("5"));

        loadHotelNameCombo();
        loadHotelTypeCombo();
        loadSeasonCombo();

        // oda ekleme sayfasında seçilen odaya göre dinmik olarak pansiyon tipini getiren metod
        cmb_hotel_name.addActionListener(e -> {
            loadHotelTypeCombo();
            loadSeasonCombo();
            cmb_room_type.setSelectedIndex(0);
            cmb_hotel_season.setSelectedIndex(0);

        });


        btn_room_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_room_stock) || Helper.isFieldEmpty(fld_adult_price) || Helper.isFieldEmpty(fld_child_price) ||
                    Helper.isFieldEmpty(fld_room_bedTypeNum) || Helper.isFieldEmpty(fld_room_area) ||
                    cmb_room_type.getSelectedItem().toString().equals("") || cmb_hotel_pensionType.getSelectedItem() == null ||
                    cmb_hotel_season.getSelectedItem() == null || cmb_hotel_name == null ||
                    (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected() && !radioButton4.isSelected() && !radioButton5.isSelected())) {
                Helper.showMsg("fill");
            } else {
                String room_type = cmb_room_type.getSelectedItem().toString();
                int stock = Integer.parseInt(fld_room_stock.getText());
                int season_id = 0;
                int adult_price = Integer.parseInt(fld_adult_price.getText().toString());
                int child_price = Integer.parseInt(fld_child_price.getText().toString());
                Item hotelTypeItem = (Item) cmb_hotel_pensionType.getSelectedItem();
                int hotel_type_id = hotelTypeItem.getKey();
                Item hotelItem = (Item) cmb_hotel_name.getSelectedItem();
                int hotel_id = hotelItem.getKey();


                for (HotelSeason obj : HotelSeason.getListByHotelID(hotel_id)) { // season id yi çekmek için yazıldı
                    String season = obj.getSeason_start().toString() + " - " + obj.getSeason_end().toString();
                    if (season.equals(cmb_hotel_season.getSelectedItem().toString())) {
                        season_id = obj.getId();
                        break;
                    }
                }


                if (Room.add(room_type, stock, season_id, adult_price, child_price, hotel_type_id, hotel_id)) {
                    ArrayList<Room> roomList = Room.getList();
                    Room addedRoom = roomList.get(Room.getList().size() - 1); // son eklediğimiz o gelsin istiyoruz
                    addedRoom_id = addedRoom.getId();


                    // radiobutton doldurma işlemleri
                    String room_properties = "";
                    if (radioButton1.isSelected()) {
                        room_properties += radioButton1.getText();
                    }

                    if (radioButton2.isSelected()) {
                        room_properties += "\n" + radioButton2.getText();
                    }

                    if (radioButton3.isSelected()) {
                        room_properties += "\n" + radioButton3.getText();
                    }

                    if (radioButton4.isSelected()) {
                        room_properties += "\n" + radioButton4.getText();
                    }

                    if (radioButton5.isSelected()) {
                        room_properties += "\n" + radioButton5.getText();
                    }

                    RoomProperties.add(room_properties, addedRoom_id, fld_room_bedTypeNum.getText(), Integer.parseInt(fld_room_area.getText().toString()));
                    Helper.showMsg("done");
                    cmb_hotel_name.setSelectedIndex(0);
                    cmb_room_type.setSelectedIndex(0);
                    fld_room_stock.setText(null);
                    cmb_room_type.setSelectedIndex(0);
                    cmb_hotel_season.setSelectedIndex(0);
                    fld_adult_price.setText(null);
                    fld_child_price.setText(null);
                    fld_room_bedTypeNum.setText(null);
                    fld_room_area.setText(null);
                    radioButton1.setSelected(false);
                    radioButton2.setSelected(false);
                    radioButton3.setSelected(false);
                    radioButton4.setSelected(false);
                    radioButton5.setSelected(false);

                }


            }

        });
    }


    private void loadHotelNameCombo() {
        cmb_hotel_name.removeAllItems();
        cmb_hotel_name.addItem(new Item(0, null));
        for (Hotel obj : Hotel.getlist()) {
            cmb_hotel_name.addItem(new Item(obj.getId(), obj.getName()));
        }

    }


    // oda ekleme sayfasında seçilen otele göre pansiyon türlerini combo bax a aktaran metod
    private void loadHotelTypeCombo() {
        Item hotelItem = (Item) cmb_hotel_name.getSelectedItem();
        cmb_hotel_pensionType.removeAllItems();
        cmb_hotel_pensionType.addItem(new Item(0, null));
        for (HotelType obj : HotelType.getListByHotelID(hotelItem.getKey())) {

            cmb_hotel_pensionType.addItem(new Item(obj.getId(), obj.getType()));
        }

    }

    private void loadSeasonCombo() {
        Item hotelItem = (Item) cmb_hotel_name.getSelectedItem();
        cmb_hotel_season.removeAllItems();
        cmb_hotel_season.addItem(new Item(0, null));
        for (HotelSeason obj : HotelSeason.getListByHotelID(hotelItem.getKey())) {

            cmb_hotel_season.addItem(new Item(obj.getId(), (obj.getSeason_start() + " - " + obj.getSeason_end())));
        }

    }


}

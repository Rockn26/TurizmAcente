package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_bot;
    private JTabbedPane tab_admin;
    private JPanel pnl_bottom;
    private JScrollPane scrl_top;
    private JPanel pnl_left;
    private JPanel pnl_right;
    private JScrollPane scrl_left;
    private JScrollPane scrl_right;
    private JTable tbl_hotel_list;
    private JTable tbl_hotel_type;
    private JTable tbl_hotel_season;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JButton btn_hotel_add;
    private JPanel pnl_hotel_add;
    private JTable tbl_room_list;
    private JTextField fld_sh_hotelName;
    private JTextField fld_sh_startDate;
    private JTextField fld_sh_endDate;
    private JTextField fld_adultNumber;
    private JTextField fld_childNumber;
    private JButton btn_room_sh;
    private JTextField fld_room_id;
    private JButton btn_room_rezerv;
    private JButton btn_room_add;
    private JTable tbl_room_properties;
    private JScrollPane scrl_rezervasyon;
    private JTable tbl_rez_list;
    private JPanel pnl_search;
    private JTextField fld_sh_cEmail;
    private JButton btn_sh_mail;
    private JTextField fld_rez_id;
    private JButton btn_rez_delete;

    DefaultTableModel mdl_hotel_list;
    private Object[] row_hotel_list;
    DefaultTableModel mdl_hotel_type;
    private Object[] row_hotel_type;
    private int select_hotel_id;
    DefaultTableModel mdl_hotel_season;
    private Object[] row_hotel_season;
    DefaultTableModel mdl_room_list;
    private Object[] row_room_list;
    DefaultTableModel mdl_room_properties;
    private Object[] row_room_properties;
    private int select_room_id;
    private String check_in;
    private String check_out;
    private int reservation_room_id;
    DefaultTableModel mdl_reservation_list;
    private Object[] row_reservation_list;
    private int adult_number = 0;
    private int child_number = 0;
    private int rez_id;
    private int select_rez_row;


    private final Employee employee;

    public EmployeeGUI(Employee employee) {
        this.employee = employee;
        add(wrapper);
        setSize(1200, 800);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);




        // HotelList
        mdl_hotel_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_list = {"ID", "HOTEL ADI", "YILDIZ", "TESİS ÖZELLİKLERİ", "ADRES", "TELEFON", "EMAIL"};
        mdl_hotel_list.setColumnIdentifiers(col_hotel_list);
        row_hotel_list = new Object[col_hotel_list.length];
        loadHotelModel();
        tbl_hotel_list.setModel(mdl_hotel_list);
        tbl_hotel_list.getTableHeader().setReorderingAllowed(false);
        tbl_hotel_list.getColumnModel().getColumn(0).setMaxWidth(75);


        // pansiyon tiplerini ve sezonları listelemek için hotel idsini alma
        tbl_hotel_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                select_hotel_id = Integer.parseInt(tbl_hotel_list.getValueAt(tbl_hotel_list.getSelectedRow(), 0).toString());
            } catch (Exception exception) {

            }
            // Tıklandığında yüklensin.
            loadHotelTypeModel(select_hotel_id);
            loadHotelSeasonModel(select_hotel_id);

        });

        // ## HotelList


        // Hotel Type

        mdl_hotel_type = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_hotel_type = {"PANSİYON TİPLERİ"};
        mdl_hotel_type.setColumnIdentifiers(col_hotel_type);
        row_hotel_type = new Object[col_hotel_type.length];
        tbl_hotel_type.setModel(mdl_hotel_type);
        tbl_hotel_type.getTableHeader().setReorderingAllowed(false);

        // ## HotelType


        // otel yönetim sayfası otel ekle butonu
        btn_hotel_add.addActionListener(e -> {
            HotelAddGUI hotelAdd = new HotelAddGUI(employee);
            hotelAdd.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelModel();
                    tbl_hotel_list.getSelectionModel().clearSelection();
                }
            });
        });



        // Hotel Season

        mdl_hotel_season = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_hotel_season = {"DÖNEM BAŞLANGICI", "DÖNEM BİTİŞİ"};
        mdl_hotel_season.setColumnIdentifiers(col_hotel_season);
        row_hotel_season = new Object[col_hotel_season.length];
        tbl_hotel_season.setModel(mdl_hotel_season);
        tbl_hotel_season.getTableHeader().setReorderingAllowed(false);

        // ## HotelSeason



        // RoomList
        mdl_room_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_room_list = {"ID", "OTEL ADI", "ODA TİPİ", "STOK", "SEZON TARİHLERİ", "YETİŞKİN FİYATI", "ÇOCUK FİYATI", "PANSİYON TİPİ"};
        mdl_room_list.setColumnIdentifiers(col_room_list);
        row_room_list = new Object[col_room_list.length];
        loadRoomListModel();
        tbl_room_list.setModel(mdl_room_list);
        tbl_room_list.getTableHeader().setReorderingAllowed(false);
        tbl_room_list.getColumnModel().getColumn(0).setMaxWidth(75);


        // ## RoomList

        // Room Properties

        mdl_room_properties = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_room_properties = {"Oda Özellikleri", "Yatak Bilgisi", "Alan(m2)"};
        mdl_room_properties.setColumnIdentifiers(col_room_properties);
        row_room_properties = new Object[col_room_properties.length];
        tbl_room_properties.setModel(mdl_room_properties);
        tbl_room_properties.getTableHeader().setReorderingAllowed(false);

        // oda özelliklerini listelemek için tıklanınca oda id sini alma.
        tbl_room_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                select_room_id = Integer.parseInt(tbl_room_list.getValueAt(tbl_room_list.getSelectedRow(), 0).toString());
            } catch (Exception exception) {
            }
            fld_room_id.setText(Integer.toString(select_room_id));
            loadRoomPropertiesModel(select_room_id);
            // bu kısım olmazsa room id null dönüyor !!
            reservation_room_id = select_room_id;
            select_room_id = 0;
        });


        // ## RoomProperties


        // Employee panel çıkış butonu
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();

        });

        // oda yönetim sayfası oda ekleme butonu
        btn_room_add.addActionListener(e -> {
            RoomAddGUI roomAddGUI = new RoomAddGUI(employee);
            roomAddGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomListModel();
                    tbl_room_list.getSelectionModel().clearSelection();
                    ;
                    super.windowClosed(e);

                }
            });

        });

        // Oda arama bölgesi
        btn_room_sh.addActionListener(e -> {
            String regionHotelName = fld_sh_hotelName.getText();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            check_in = fld_sh_startDate.getText().trim();
            check_out = fld_sh_endDate.getText().trim();
            Date check_in_date = null;
            Date check_out_date = null;
            try {
                check_in_date = formatter.parse(check_in);
                check_out_date = formatter.parse(check_out);
            } catch (ParseException ex) {

            }

            String query = "SELECT * FROM hotel WHERE name LIKE '%{{name}}%' OR adress LIKE '%{{adress}}%'";
            query = query.replace("{{name}}", regionHotelName);
            query = query.replace("{{adress}}", regionHotelName);
            ArrayList<Hotel> searchingHotelList = Hotel.searchHotelList(query);

            ArrayList<Room> searchingRoomList = new ArrayList<>();

            if (Helper.isFieldEmpty(fld_sh_startDate) && Helper.isFieldEmpty(fld_sh_endDate) && Helper.isFieldEmpty(fld_sh_hotelName)) {
                loadRoomListModel();
            } else if (Helper.isFieldEmpty(fld_sh_startDate) && Helper.isFieldEmpty(fld_sh_endDate)) {
                for (Hotel hotel : searchingHotelList) {
                    Room obj = Room.getFetchByHotelID(hotel.getId());
                    searchingRoomList.add(obj);
                }
                if (searchingRoomList.isEmpty()) {
                    Helper.showMsg("Aradığınız kriterlere uygun oda bulunamadı");
                } else {
                    loadRoomListModel(searchingRoomList);
                }
            } else {
                for (Hotel obj : searchingHotelList) {
                    ArrayList<HotelSeason> searchingSeason = HotelSeason.getListByHotelID(obj.getId());
                    for (HotelSeason season : searchingSeason) {
                        String season_start = season.getSeason_start();
                        String season_end = season.getSeason_end();
                        Date season_start_date = null;
                        Date season_end_date = null;

                        try {
                            season_start_date = formatter.parse(season_start);
                            season_end_date = formatter.parse(season_end);
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }

                        if (season_start_date.before(check_in_date) && season_end_date.after(check_out_date)) {
                            Room room = Room.getFetchByHotelIDSeasonID(season.getId(), obj.getId());
                            if (room != null) {
                                searchingRoomList.add(room);
                            }

                        }
                    }
                }

                if (searchingRoomList.isEmpty()) {
                    Helper.showMsg("Aradığınız kriterlere uygun oda bulunamadı");
                } else {
                    loadRoomListModel(searchingRoomList);
                }
            }
            fld_sh_hotelName.setText(null);
        });



        // rezervasyon bilgileri tablosu kodları

        btn_room_rezerv.addActionListener(e -> {
            // Burada boşluktan ziyade id ye tıklandı mı yani fld_room_id tıklanınca geldi mi sorgusu yapıyoruz ilk tarafta
            if (Helper.isFieldEmpty(fld_room_id) || Helper.isFieldEmpty(fld_sh_startDate) || Helper.isFieldEmpty(fld_sh_endDate) || Helper.isFieldEmpty(fld_adultNumber) || Helper.isFieldEmpty(fld_childNumber)) {
                Helper.showMsg("Buradaki tüm kısımlar eksiksiz doldurulmalıdır!");
            } else {
                Room room = Room.getFetch(reservation_room_id);
                adult_number = Integer.parseInt(fld_adultNumber.getText());
                child_number = Integer.parseInt(fld_childNumber.getText());
                check_in = fld_sh_startDate.getText().trim();
                check_out = fld_sh_endDate.getText().trim();

                ReservationGUI reservationGUI = new ReservationGUI(room, adult_number, child_number, check_in, check_out);

                // sayfayı kapattığımda güncellensin
                reservationGUI.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        fld_sh_startDate.setText(null);
                        fld_sh_endDate.setText(null);
                        fld_adultNumber.setText(null);
                        fld_childNumber.setText(null);
                        fld_room_id.setText(String.valueOf(0));
                        super.windowClosed(e);
                        loadRoomListModel();
                        loadReservationModel();

                    }
                });

            }
        });


        // Reservation List
        mdl_reservation_list = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0) {
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_reservation_list = {"ID", "AD SOYAD", "TELEFON", "MAİL", "NOTU", "HOTEL ADI", "ODA TİPİ", "GİRİŞ TARİHİ", "ÇIKIŞ TARİHİ", "YETİŞKİN SAYISI", "ÇOCUK SAYISI", "TOPLAM ÜCRET"};
        mdl_reservation_list.setColumnIdentifiers(col_reservation_list);
        row_reservation_list = new Object[col_reservation_list.length];
        loadReservationModel();
        tbl_rez_list.setModel(mdl_reservation_list);
        tbl_rez_list.getTableHeader().setReorderingAllowed(false);
        tbl_rez_list.getColumnModel().getColumn(0).setMaxWidth(75);

        // Tabloyu dinliyoruz id almak için
        tbl_rez_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                select_rez_row = Integer.parseInt(tbl_rez_list.getValueAt(tbl_rez_list.getSelectedRow(), 0).toString());
            } catch (Exception exception) {

            }
            fld_rez_id.setText(Integer.toString(select_rez_row));
        });

        // ## Reservation List


        // Rez silme işlemleri
        btn_rez_delete.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_rez_id)) {
                Helper.showMsg("Lütfen bir id ye tıklayınız");
            } else {
                if (Helper.confirm("sure")) {
                    rez_id = Integer.parseInt(fld_rez_id.getText());
                    if (ReservationInfo.delete(rez_id)) {
                        Helper.showMsg("done");
                        loadReservationModel();
                        fld_rez_id.setText(null);
                    }
                } else {
                    Helper.showMsg("error");
                }
            }
        });

        // özel rez mail arama
        btn_sh_mail.addActionListener(e -> {
            String mail = fld_sh_cEmail.getText();

            String query = "SELECT * FROM reservation_info WHERE c_email LIKE '%{{c_email}}%'";
            query = query.replace("{{c_email}}", mail);

            ArrayList<ReservationInfo> searchRez = ReservationInfo.searchHotelList(query);
            loadReservationModel(searchRez);

        });
    }

    private void loadReservationModel(ArrayList<ReservationInfo> searchRez) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rez_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        if (searchRez == null) {
            Helper.showMsg("Aradığınız kriterde uygun oda bulunamadı");
        } else {
            for (ReservationInfo obj : searchRez) {
                i = 0;
                row_reservation_list[i++] = obj.getId();
                row_reservation_list[i++] = obj.getC_name();
                row_reservation_list[i++] = obj.getC_phone();
                row_reservation_list[i++] = obj.getC_email();
                row_reservation_list[i++] = obj.getC_note();
                row_reservation_list[i++] = Hotel.getFetch(Room.getFetch(obj.getRoom_id()).getHotel_id()).getName();
                row_reservation_list[i++] = Room.getFetch(obj.getRoom_id()).getRoom_type();
                row_reservation_list[i++] = obj.getCheck_in();
                row_reservation_list[i++] = obj.getCheck_out();
                row_reservation_list[i++] = obj.getAdultN();
                row_reservation_list[i++] = obj.getChildN();
                row_reservation_list[i++] = obj.getPrice();
                mdl_reservation_list.addRow(row_reservation_list);
            }
        }
    }

    private void loadReservationModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rez_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (ReservationInfo obj : ReservationInfo.getList()) {
            i = 0;
            row_reservation_list[i++] = obj.getId();
            row_reservation_list[i++] = obj.getC_name();
            row_reservation_list[i++] = obj.getC_phone();
            row_reservation_list[i++] = obj.getC_email();
            row_reservation_list[i++] = obj.getC_note();
            row_reservation_list[i++] = Hotel.getFetch(Room.getFetch(obj.getRoom_id()).getHotel_id()).getName();
            row_reservation_list[i++] = Room.getFetch(obj.getRoom_id()).getRoom_type();
            row_reservation_list[i++] = obj.getCheck_in();
            row_reservation_list[i++] = obj.getCheck_out();
            row_reservation_list[i++] = obj.getAdultN();
            row_reservation_list[i++] = obj.getChildN();
            row_reservation_list[i++] = obj.getPrice();
            mdl_reservation_list.addRow(row_reservation_list);
        }
    }


    private void loadHotelModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Hotel obj : Hotel.getlist()) {
            i = 0;
            row_hotel_list[i++] = obj.getId();
            row_hotel_list[i++] = obj.getName();
            row_hotel_list[i++] = obj.getStar();
            row_hotel_list[i++] = obj.getProperty();
            row_hotel_list[i++] = obj.getAdress();
            row_hotel_list[i++] = obj.getPhone();
            row_hotel_list[i++] = obj.getEmail();
            mdl_hotel_list.addRow(row_hotel_list);
        }
    }

    private void loadHotelTypeModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_type.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (HotelType obj : HotelType.getListByHotelID(id)) {
            i = 0;
            row_hotel_type[i++] = obj.getType();
            mdl_hotel_type.addRow(row_hotel_type);
        }
    }

    private void loadHotelSeasonModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel_season.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (HotelSeason obj : HotelSeason.getListByHotelID(id)) {
            i = 0;
            row_hotel_season[i++] = obj.getSeason_start();
            row_hotel_season[i++] = obj.getSeason_end();
            mdl_hotel_season.addRow(row_hotel_season);
        }

    }

    private void loadRoomListModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (Room obj : Room.getList()) {
            i = 0;
            row_room_list[i++] = obj.getId();
            row_room_list[i++] = Hotel.getFetch(obj.getHotel_id()).getName();
            row_room_list[i++] = obj.getRoom_type();
            row_room_list[i++] = obj.getStock();
            row_room_list[i++] = HotelSeason.getFetch(obj.getSeason_id()).getSeason_start() + " - " + HotelSeason.getFetch(obj.getSeason_id()).getSeason_end();
            row_room_list[i++] = obj.getAdult_price();
            row_room_list[i++] = obj.getChild_price();
            row_room_list[i++] = HotelType.getFetch(obj.getHotel_type_id()).getType();
            mdl_room_list.addRow(row_room_list);
        }
    }

    private void loadRoomListModel(ArrayList<Room> list) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_list.getModel();
        clearModel.setRowCount(0);
        int i;
        if (list == null) {
            Helper.showMsg("Aradığınız kriterde uygun oda bulunamadı");
        } else {
            for (Room obj : list) {
                i = 0;
                row_room_list[i++] = obj.getId();
                row_room_list[i++] = Hotel.getFetch(obj.getHotel_id()).getName();
                row_room_list[i++] = obj.getRoom_type();
                row_room_list[i++] = obj.getStock();
                row_room_list[i++] = HotelSeason.getFetch(obj.getSeason_id()).getSeason_start() + " - " + HotelSeason.getFetch(obj.getSeason_id()).getSeason_end();
                row_room_list[i++] = obj.getAdult_price();
                row_room_list[i++] = obj.getChild_price();
                row_room_list[i++] = HotelType.getFetch(obj.getHotel_type_id()).getType();
                mdl_room_list.addRow(row_room_list);
            }
        }
    }


    private void loadRoomPropertiesModel(int id) {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_room_properties.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (RoomProperties obj : RoomProperties.getListByRoomID(id)) {
            i = 0;
            row_room_properties[i++] = obj.getProperty();
            row_room_properties[i++] = obj.getBed();
            row_room_properties[i++] = obj.getArea();
            mdl_room_properties.addRow(row_room_properties);
        }
    }


}

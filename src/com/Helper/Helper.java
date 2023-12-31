package com.Helper;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Helper {

    public static void setLayout() {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }

    public static int screenCenterPoint(String axis, Dimension size) {
        int point;
        switch (axis) {
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    public static boolean isAreaEmpty(JTextArea area){
        return area.getText().trim().isEmpty();
    }

    public static void showMsg(String str) {
        optionPaneTR();
        String msg;
        String title;
        switch (str) {
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz!";
                title = "Hata!";
                break;
            case "done":
                msg = "İşlem başarılı!";
                title = "Sonuç";
                break;
            case "error":
                msg = "Bir hata oluştu!";
                title = "Hata!";
                break;
            default:
                msg = str;
                title = "Mesaj";
        }

        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        optionPaneTR();
        String msg;
        switch (str) {
            case "sure":
                msg = "Bu işlemi gerçekleştirmek istediğinize emin misiniz?";
                break;
            default:
                msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Son Kararın mı?", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void optionPaneTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }

    public static String hotelType(String number){
        String type = "";
        switch (number){
            case "1":
                type = "Ultra Her Şey Dahil";
                break;
            case "2":
                type = "Her Şey Dahil";
                break;
            case "3":
                type = "Oda Kahvaltı";
                break;
            case "4":
                type = "Tam Pansiyon";
                break;
            case "5":
                type = "Yarım Pansiyon";
                break;
            case "6":
                type = "Sadece Yatak";
                break;
            case "7":
                type = "Alkol Hariç Full credit";
                break;
        }
        return type;
    }

    public static String roomProperty(String number){
        String property ="";
        switch (number){
            case "1":
                property = "Televizyon";
                break;
            case "2":
                property = "Minibar";
                break;
            case "3":
                property = "Oyun Konsolu";
                break;
            case "4":
                property = "Kasa";
                break;
            case "5":
                property = "Projeksiyon";
                break;
        }
        return property;
    }


}

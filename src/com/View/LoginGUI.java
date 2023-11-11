package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Admin;
import com.Model.Employee;
import com.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JLabel lbl_uname;
    private JTextField fld_uname;
    private JLabel lbl_pass;
    private JTextField fld_pass;
    private JButton btn_giris;

    public LoginGUI() {

        add(wrapper);
        setSize(500, 700);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);


        //login işlemler
        btn_giris.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_uname) || Helper.isFieldEmpty(fld_pass)){
                Helper.showMsg("fill");
            }else {
                User u = User.getFetch(fld_uname.getText(),fld_pass.getText());
                if (u==null){
                    Helper.showMsg("Kullanıcı bulunamadı");
                }
                else {
                    switch (u.getType()){
                        case "admin":
                            AdminGUI admin = new AdminGUI((Admin) u);
                            break;
                        case "employee":
                            EmployeeGUI employeeGUI = new EmployeeGUI((Employee) u);
                            break;

                    }
                    dispose();
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI l = new LoginGUI();
    }
}








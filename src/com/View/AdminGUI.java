package com.View;

import com.Helper.Config;
import com.Helper.Helper;
import com.Model.Admin;
import com.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_top;
    private JPanel pnl_bottom;
    private JLabel fld_welcome;
    private JScrollPane srcl_admin;
    private JTable tbl_admin;
    private JPanel pnl_admin;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JButton btn_user_add;
    private JComboBox cmb_user_type;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
    private JTabbedPane tb_Kullanıcılar;
    private JButton btn_logout;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private final Admin admin;

    public AdminGUI(Admin admin){
        this.admin = admin;
        add(wrapper);
        setSize(1000, 700);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                    if (column == 0) {
                        return false;
                    }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        loadModel();
        tbl_admin.setModel(mdl_user_list);
        tbl_admin.getTableHeader().setReorderingAllowed(false);
        tbl_admin.getColumnModel().getColumn(0).setMaxWidth(75);


        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI l = new LoginGUI();

        });

        btn_user_add.addActionListener(e -> {
            if (Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname) || Helper.isFieldEmpty(fld_user_pass)){
            Helper.showMsg("fill");
            }
            else {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if (User.add(name,uname,pass,type)){
                    Helper.showMsg("done");
                    loadModel();
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);
                }
            }

        });
    }

    private void loadModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tbl_admin.getModel();
        clearModel.setRowCount(0);
        int i = 0;
        for (User obj : User.getList()){
            i = 0;
            row_user_list[i++] = obj.getId();
            row_user_list[i++] = obj.getName();
            row_user_list[i++] = obj.getUname();
            row_user_list[i++] = obj.getPass();
            row_user_list[i++] = obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

}

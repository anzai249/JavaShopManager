import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SysManager {
    private JPanel ShopManWin;
    private JButton OKButton;
    private JPasswordField passwordField1;
    private JLabel xiugaimima;
    private JLabel hellolable;
    private JLabel Shopman;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton shopAdd;
    private JButton shopDel;
    private JLabel shopName;
    private JPanel SysManWin;
    private JLabel vipMax;
    private JLabel duiHuanBiLi;
    private JLabel zheKouBiLi;
    private JButton searchBtn;
    private JTextField vipMaxField;
    static Connection con;
    static Statement sql;
    static int res;
    static ResultSet res_;
    public SysManager(String userName) {
        JFrame frame = new JFrame("SystemManager");
        frame.setContentPane(SysManWin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        hellolable.setText("��ã�"+userName);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String changePswd = ("\'"+passwordField1.getText()+"\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "UPDATE users " +
                            "SET Pswd = "+changePswd+" WHERE userName = "+userName+"";
                    System.out.println(sqlsent);
                    res = sql.executeUpdate(sqlsent);
                    JOptionPane.showMessageDialog(null, "���������޸ĳɹ���");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        shopAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //����������̵갴ť
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String shopName_ = ("\'"+textField1.getText()+ "\'");
                String vipMax_ = ("\'"+vipMaxField.getText()+ "\'");
                String duiHuanBiLi_ = ("\'"+textField2.getText()+ "\'");
                String zheKouBiLi_ = ("\'"+textField3.getText()+ "\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "INSERT INTO shops " +
                            "VALUES ("+shopName_+", "+vipMax_+", "+duiHuanBiLi_+", "+zheKouBiLi_+")";
                    System.out.println(sqlsent);
                    res = sql.executeUpdate(sqlsent);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "����������ӳɹ���");
            }
        });
        shopDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //������ɾ���̵갴ť
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String shopName_ = ("\'" + textField1.getText() + "\'");
                if (shopName_.equals("")) {
                    JOptionPane.showMessageDialog(null, "�㶼û���̵����֣��᲻���ã�", "������", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        sql = con.createStatement();
                        String sqlsent = "DELETE FROM shops WHERE shopName = " + shopName_ + "";
                        System.out.println(sqlsent);
                        res = sql.executeUpdate(sqlsent);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "ɾ���ˡ�");
                }
            }
        });
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //��ѯ��ť
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String shopName_ = ("\'" + textField1.getText() + "\'");
                if (shopName_.equals("")) {
                    JOptionPane.showMessageDialog(null, "�㶼û���̵����֣��᲻���ã�", "������", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        sql = con.createStatement();
                        String sqlsent = "SELECT VIPmaxPoint, duiHuanBiLi, zheKouBiLi FROM shops WHERE shopName = " + shopName_ + "";
                        System.out.println(sqlsent);
                        res_ = sql.executeQuery(sqlsent);
                        res_.next();
                        String VIPmaxPoint = res_.getString("VIPmaxPoint");
                        String duiHuanBiLi = res_.getString("duiHuanBiLi");
                        String zheKouBiLi = res_.getString("zheKouBiLi");
                        vipMaxField.setText(VIPmaxPoint);
                        textField2.setText(duiHuanBiLi);
                        textField3.setText(zheKouBiLi);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}

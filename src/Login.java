import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JTextField textField1;
    private JButton RegBtn;
    private JButton LginBtn;
    private JLabel PswdText;
    private JLabel UsnmText;
    private JPanel lginWindow;
    private JComboBox comboBox1;
    private JLabel JveseText;
    private JPasswordField textField2;
    static Connection con;
    static Statement sql;
    static int res;
    static ResultSet res_;

    public Login() {
        initialize();
    }
    public void initialize() {

        JFrame frame = new JFrame("Login");
        frame.setContentPane(lginWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        RegBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String userName_ = ("\'"+textField1.getText()+ "\'");
                String pswd = ("\'"+textField2.getText()+ "\'");
                int position = 0;
                if (comboBox1.getSelectedItem() == "系统管理员") {
                    position = 0;
                } else {
                    position = 1;
                }
                try {
                    sql = con.createStatement();
                    String sqlsent = "INSERT INTO users " +
                            "VALUES ("+userName_+", "+pswd+", "+position+")";
                    System.out.println(sqlsent);
                    res = sql.executeUpdate(sqlsent);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "注册成功，身份为"+comboBox1.getSelectedItem());
            }
        });
        LginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String inUserName=null;
                String inPswd=null;
                int inPos= 0;
                String userName_ = ("\'"+textField1.getText()+ "\'");
                String pswd = ("\'"+textField2.getText()+ "\'");
                int position = 0;
                if (comboBox1.getSelectedItem() == "系统管理员") {
                    position = 0;
                } else {
                    position = 1;
                }
                try {
                    sql = con.createStatement();
                    res_ = sql.executeQuery("select * from users where userName="+userName_);
                    while(res_.next()) {
                        inUserName=String.valueOf(res_.getString("userName"));
                        inPswd=String.valueOf(res_.getString("Pswd"));
                        inPos = Integer.valueOf(String.valueOf(res_.getInt("position")));
                    }
                    if(!(userName_.equals("\'"+inUserName+"\'"))){
                        JOptionPane.showMessageDialog(null, "查无此人","出错了",JOptionPane.ERROR_MESSAGE);
                    }
                    else if(!(pswd.equals("\'"+inPswd+"\'"))){
                        JOptionPane.showMessageDialog(null, "密码错误","出错了",JOptionPane.ERROR_MESSAGE);
                    }
                    else if((userName_.equals("\'"+inUserName+"\'"))&& (position != inPos)) {
                        JOptionPane.showMessageDialog(null, "身份与账号不匹配","出错了",JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "登录成功", "成功了",JOptionPane.PLAIN_MESSAGE);
                        if(position == 0) {
                            JOptionPane.showMessageDialog(null, "你是系统管理员","好！",JOptionPane.PLAIN_MESSAGE);
                            new SysManager(userName_);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "你是店铺管理员","好！",JOptionPane.PLAIN_MESSAGE);
                            new ShopManager(userName_);
                        }
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }
    public void setData(Login data) {
    }

    public void getData(Login data) {
    }

    public boolean isModified(Login data) {
        return false;
    }
}
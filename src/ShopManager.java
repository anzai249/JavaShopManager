import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopManager {
    private JTree tree1;
    private JButton OKButton;
    private JPasswordField passwordField1;
    private JPanel ShopManWin;
    private JLabel xiugaimima;
    private JLabel hellolable;
    private JTextField cusNameField;
    private JPanel custumerMan;
    private JButton AddButton1;
    private JButton SearchButton1;
    private JButton DelButton1;
    private JTextField cusPointField;
    private JLabel cusName;
    private JLabel cusPoint;
    private JPanel giftMan;
    private JTextField giftNameField;
    private JTextField giftValueField;
    private JButton AddButton;
    private JButton SearchButton;
    private JButton DelButton;
    private JLabel giftName;
    private JLabel giftValue;
    private JPanel VIPMan;
    private JTextField textField2;
    private JButton AddButton2;
    private JButton DelButton2;
    private JLabel VIPName;
    private JButton refreshbutton1;
    static Connection con;
    static Statement sql;
    static int res;
    static ResultSet res_;
    public ShopManager(String userName) {
        JFrame frame = new JFrame("ShopManager");
        frame.setContentPane(ShopManWin);
        frame.setSize(700,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        hellolable.setText("你好，"+userName);
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
                    JOptionPane.showMessageDialog(null, "奥利给，修改成功！");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        AddButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加会员
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String VIPName_ = ("\'"+textField2.getText()+"\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "INSERT INTO vips " +
                            "VALUES ("+VIPName_+")";
                    System.out.println(sqlsent);
                    res = sql.executeUpdate(sqlsent);
                    JOptionPane.showMessageDialog(null, "添加成功！");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        DelButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除会员
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String VIPName_ = ("\'"+textField2.getText()+"\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "DELETE FROM vips WHERE VIPName = " + VIPName_ + "";
                    System.out.println(sqlsent);
                    res = sql.executeUpdate(sqlsent);
                    JOptionPane.showMessageDialog(null, "删除成功！");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加礼品
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String giftName_ = ("\'"+giftNameField.getText()+"\'");
                String giftValue_ = ("\'"+giftValueField.getText()+"\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "INSERT INTO gifts " +
                            "VALUES ("+giftName_+", "+giftValue_+")";
                    System.out.println(sqlsent);
                    res = sql.executeUpdate(sqlsent);
                    JOptionPane.showMessageDialog(null, "添加成功！");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        SearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //查询礼品
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String giftName_ = ("\'"+giftNameField.getText()+"\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "SELECT giftValue FROM gifts WHERE giftName = " + giftName_ + "";
                    System.out.println(sqlsent);
                    res_ = sql.executeQuery(sqlsent);
                    res_.next();
                    String giftValue_ = res_.getString("giftValue");
                    giftValueField.setText(giftValue_);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        DelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除礼品
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String giftName_ = ("\'"+giftNameField.getText()+"\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "DELETE FROM gifts WHERE giftName = " + giftName_ + "";
                    System.out.println(sqlsent);
                    res = sql.executeUpdate(sqlsent);
                    JOptionPane.showMessageDialog(null, "删除成功！");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        SearchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //查询顾客
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String cusName_ = ("\'"+cusNameField.getText()+"\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "SELECT Points FROM custumers WHERE custumerName = " + cusName_ + "";
                    System.out.println(sqlsent);
                    res_ = sql.executeQuery(sqlsent);
                    res_.next();
                    String points_ = res_.getString("points");
                    cusPointField.setText(points_);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        AddButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加顾客
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String cusName_ = ("\'"+cusNameField.getText()+"\'");
                String points_ = ("\'"+cusPointField.getText()+"\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "INSERT INTO custumers " +
                            "VALUES ("+cusName_+", "+points_+")";
                    System.out.println(sqlsent);
                    res = sql.executeUpdate(sqlsent);
                    JOptionPane.showMessageDialog(null, "添加成功！");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        DelButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除顾客
                JDBCConnection s = new JDBCConnection();
                con = s.getConnection();
                String cusName_ = ("\'"+cusNameField.getText()+"\'");
                try {
                    sql = con.createStatement();
                    String sqlsent = "DELETE FROM custumers WHERE custumerName = " + cusName_ + "";
                    System.out.println(sqlsent);
                    res = sql.executeUpdate(sqlsent);
                    JOptionPane.showMessageDialog(null, "删除成功！");
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        refreshbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //刷新
                frame.dispose();
                new ShopManager(userName);
            }
        });
    }

    public void setTree1(JTree tree1) {
        this.tree1 = tree1;
    }

    private void createUIComponents() {
        JDBCConnection s = new JDBCConnection();
        con = s.getConnection();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("商店管理员管理");
        String Items[][]=new String[3][];
        String title[][] = new String[3][];
        Items[0]=new String[]{};
        Items[1]=new String[]{};
        Items[2]=new String[]{};
        String manageNames[]={"顾客","礼品","会员"};
        try {
            sql = con.createStatement();
            String sqlsent = "SELECT custumerName FROM custumers";
            System.out.println(sqlsent);
            res_ = sql.executeQuery(sqlsent);
            List<String> list = new ArrayList<>();
            while(res_.next()){
                String cusName_  = res_.getString("custumerName");
                list.add(cusName_);
                List<String> titleList = new ArrayList<String>();
                titleList.addAll(Arrays.asList(Items[0]));
                titleList.addAll(list);
                title[0] = titleList.toArray(new String[titleList.size()]);
            }
            res_.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            sql = con.createStatement();
            String sqlsent = "SELECT giftName FROM gifts";
            System.out.println(sqlsent);
            res_ = sql.executeQuery(sqlsent);
            List<String> list = new ArrayList<>();
            while(res_.next()){
                String cusName_  = res_.getString("giftName");

                list.add(cusName_);
                List<String> titleList = new ArrayList<String>();
                titleList.addAll(Arrays.asList(Items[0]));
                titleList.addAll(list);
                title[1] = titleList.toArray(new String[titleList.size()]);
            }
            res_.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            sql = con.createStatement();
            String sqlsent = "SELECT VIPName FROM vips";
            System.out.println(sqlsent);
            res_ = sql.executeQuery(sqlsent);
            List<String> list = new ArrayList<>();
            while(res_.next()){
                String cusName_  = res_.getString("VIPName");
                list.add(cusName_);
                List<String> titleList = new ArrayList<String>();
                titleList.addAll(Arrays.asList(Items[0]));
                titleList.addAll(list);
                title[2] = titleList.toArray(new String[titleList.size()]);
            }
            res_.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        DefaultMutableTreeNode node=null;
        DefaultMutableTreeNode childNode=null;
        int length=0;
        for(int i=0;i<3;i++)
        {
            length=title[i].length;
            node=new DefaultMutableTreeNode(manageNames[i]);
            for (int j=0;j<length;j++)
            {
                childNode=new DefaultMutableTreeNode(title[i][j]);
                node.add(childNode);
            }
            root.add(node);
        }
        tree1 = new JTree(root);
        tree1.updateUI();
    }
}
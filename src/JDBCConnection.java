import java.sql.*;

public class JDBCConnection {
    Connection con;
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            con=DriverManager.getConnection("jdbc:mysql://localhost/jifenSystem","root","123456");
            System.out.println("Successful");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }
}
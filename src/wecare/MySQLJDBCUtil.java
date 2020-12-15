package wecare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mysqltutorial.org
 */
public class MySQLJDBCUtil {

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
    try {
    // db parameters
    String url       = "jdbc:mysql://localhost:3306/userdetails";
    String user      = "root";
    String password  = "12345678";
    conn = DriverManager.getConnection(url, user, password);	
    } 
    catch(SQLException e) {
   System.out.println("Error connecting to the database");
    } 
        return conn;
    }
}
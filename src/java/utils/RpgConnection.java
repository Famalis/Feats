/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sergi_000
 */
public abstract class RpgConnection {
    
    private static String url = "jdbc:mysql://famalis.no-ip.biz:3306/rpg?useUnicode=true&characterEncoding=utf8";
    private static String login = "rpg";
    private static String pass  = "A9A6pq5AXPXEsbDZ";
    
    
    
    public static Connection getConnection() {
         try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Connection c = null;
        
        try {
            c = DriverManager.getConnection(
                    url, login, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return c;
    }
    
    public static boolean closeConnection(Connection c) {
        
        try {
            c.close();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        
        return true;
     
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyschool.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matheus
 */
public class DBConnection {

    Connection conn = null;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/orangeDatabase?autoReconnect=true&useSSL=false";
    String user = "root";
    String pwd = "root";

    private static DBConnection instance = null;

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = (Connection) DriverManager.getConnection(url, user, pwd); // TODO Auto-generated catch block
            if (conn != null) {
                System.out.println("Connected");
            }
        }
        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close(); // TODO Auto-generated catch block
        conn = null;
    }
}

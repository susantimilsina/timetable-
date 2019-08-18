package timetable;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class DB_Connect {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = "jdbc:mysql://localhost/timetable";
    public Connection conn = null;
    public void connectDatabase() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn =  DriverManager.getConnection(
                    CONN_STRING, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DB_Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void closeDBConnection(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DB_Connect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


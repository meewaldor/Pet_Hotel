/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DBHelper implements Serializable{
    public static Connection makeConnection() throws ClassNotFoundException, SQLException{
        //1. load driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. create connection String
        String url = "jdbc:sqlserver:"
                + "//localhost:1433"
                + ";databaseName=PetHotel";
        //3. open connection
        Connection con = DriverManager.getConnection(url,"sa","12345");
        return con;
    }
}

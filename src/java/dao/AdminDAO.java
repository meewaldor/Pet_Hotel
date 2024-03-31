/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.AdminDTO;
import dto.CustomerDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBHelper;

/**
 *
 * @author Admin
 */
public class AdminDAO {

    public AdminDTO checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        AdminDTO acc = null;
        try {
            //1. connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select [staffID], [email], [password], name, dob, img, status,role,phone "
                        + "FROM [dbo].[Staff] "
                        + "WHERE [email] = ? "
                        + "AND [password] = ? "
                        + "AND [status] = 1";
                //3. create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result   
                if (rs.next()) {
                    String id = rs.getString("staffID");
                    String name = rs.getString("name");
                    String img = rs.getString("img");
                    boolean status = rs.getBoolean("status");
                    Date dob = rs.getDate("dob");
                    String role = rs.getString("role");
                    String phone = rs.getString("phone");
                    acc = new AdminDTO(id, name, email, password, status, dob, img, role, phone);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return acc;
    }

    public boolean updateProfile(String id, String name, String phone, Date dob, String img) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Staff]\n"
                        + " SET   [name] = ?\n"
                        + "      ,[phone] = ?\n"
                        + "      ,[dob] = ?\n"
                        + "      ,[img] = ?\n"
                        + " WHERE [staffID] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, name);
                stm.setString(2, phone);
                stm.setDate(3, dob);
                stm.setString(4, img);
                stm.setString(5, id);

                result = stm.executeUpdate();

                if (result != 0) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public AdminDTO searchAdminById(String id) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [staffID]\n"
                    + "      ,[email]\n"
                    + "      ,[password]\n"
                    + "      ,[name]\n"
                    + "      ,[phone]\n"
                    + "      ,[role]\n"
                    + "      ,[img]\n"
                    + "      ,[status]\n"
                    + "      ,[dob]\n"
                    + "  FROM [dbo].[Staff]\n"
                    + "  WHERE [staffID] = ?";
            st = con.prepareStatement(sql);
            st.setString(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                AdminDTO c = new AdminDTO(rs.getString("customerID"), rs.getString("name"),
                        rs.getString("email"), rs.getString("password"), rs.getBoolean("status"),
                        rs.getDate("dob"), rs.getString("img"), rs.getString("role"), rs.getString("phone"));
                return c;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.CustomerDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBHelper;

/**
 *
 * @author Admin
 */
public class CustomerDAO {

    public CustomerDTO checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO acc = null;
        try {
            //1. connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select [customerID], [email], [password], firstName, lastName, phone, point, member, img, status "
                        + "FROM [dbo].[Customer] "
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
                    String customerID = rs.getString("customerID");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String phone = rs.getString("phone");
                    int point = rs.getInt("point");
                    String member = rs.getString("member");
                    String img = rs.getString("img");
                    boolean status = rs.getBoolean("status");
                    acc = new CustomerDTO(customerID, email, password, firstName, lastName, phone, point, member, img, status);
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

    public CustomerDTO checkToken(String token) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDTO acc = null;
        try {
            //1. connect DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "select [customerID],[email], firstName, lastName, phone, point, member, img, status "
                        + "FROM [dbo].[Customer] "
                        + "WHERE [token] = ? "
                        + "AND [status] = 1";
                //3. create SQL Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, token);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result   
                if (rs.next()) {
                    String customerID = rs.getString("customerID");
                    String email = rs.getString("email");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String phone = rs.getString("phone");
                    int point = rs.getInt("point");
                    String member = rs.getString("member");
                    String img = rs.getString("img");
                    boolean status = rs.getBoolean("status");
                    acc = new CustomerDTO(customerID,email, firstName, lastName, phone, point, member, img, status);
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

    public ArrayList<CustomerDTO> searchCustomerByEmail(String email) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<CustomerDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            String sql = "select * from customer "
                    + "where email like ? and status = 1";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + email + "%");
            rs = st.executeQuery();
            while (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"), rs.getString("img"), rs.getBoolean("status"));
                list.add(c);
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
        return list;
    }

    public boolean isExisted(String email) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select [email] from [dbo].[Customer] where  [email] = ? and status = 1";
                st = con.prepareStatement(sql);
                st.setString(1, email);
                rs = st.executeQuery();
                if (rs.next()) {
                    return true;
                }
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
        return false;
    }

    public boolean insertCustomer(String email, String firstName, String lastName, String password, String phone) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Customer]\n"
                        + "           ([CustomerID]\n"
                        + "           ,[email]\n"
                        + "           ,[password]\n"
                        + "           ,[firstName]\n"
                        + "           ,[lastName]\n"
                        + "           ,[phone]\n"
                        + "           ,[point]\n"
                        + "           ,[member]\n"
                        + "           ,[img]\n"
                        + "           ,[status])\n"
                        + "    VALUES (?,?,?,?,?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, this.createCustomerId());
                stm.setString(2, email);
                stm.setString(3, password);
                stm.setString(4, firstName);
                stm.setString(5, lastName);
                stm.setString(6, phone);
                stm.setInt(7, 0);
                stm.setString(8, "Basic");
                stm.setString(9, "CC_C3-scaled-e1695366218592");
                stm.setBoolean(10, true);

                rs = stm.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean insertCustomerToken(String token, String firstName, String lastName) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Customer]\n"
                        + "           ([CustomerID]\n"
                        + "           ,[firstName]\n"
                        + "           ,[lastName]\n"
                        + "           ,[point]\n"
                        + "           ,[member]\n"
                        + "           ,[img]\n"
                        + "           ,[status]\n"
                        + "           ,[token])\n"
                        + "    VALUES (?,?,?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, this.createCustomerId());
                stm.setString(2, firstName);
                stm.setString(3, lastName);
                stm.setInt(4, 0);
                stm.setString(5, "Basic");
                stm.setString(6, "CC_C3-scaled-e1695366218592");
                stm.setBoolean(7, true);
                stm.setString(8,token);

                rs = stm.executeUpdate();
                if (rs != 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public String createCustomerId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newCustomerId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from Customer";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newCustomerId = String.format("C%06d", count + 1);
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
        return newCustomerId;
    }

    public ArrayList<CustomerDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<CustomerDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "select * from customer where status = 1 ";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"), rs.getString("img"),
                        rs.getBoolean("status"));
                list.add(c);
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
        return list;
    }

    public CustomerDTO searchCustomerById(String customerId) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [customerID]\n"
                    + "      ,[email]\n"
                    + "      ,[password]\n"
                    + "      ,[firstname]\n"
                    + "      ,[lastname]\n"
                    + "      ,[phone]\n"
                    + "      ,[point]\n"
                    + "      ,[member]\n"
                    + "      ,[img]\n"
                    + "      ,[status]\n"
                    + "  FROM [dbo].[Customer]\n"
                    + "  WHERE [customerID] = ?";
            st = con.prepareStatement(sql);
            st.setString(1, customerId);
            rs = st.executeQuery();
            if (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerID"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"),
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"), rs.getString("img"),
                        rs.getBoolean("status"));
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

    public boolean updateCustomer(CustomerDTO s)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Customer]\n"
                        + " SET   [firstName] = ?\n"
                        + "      ,[lastName] = ?\n"
                        + "      ,[phone] = ?\n"
                        + "      ,[img] = ?\n"
                        + " WHERE [customerID] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, s.getFirstName());
                stm.setString(2, s.getLastName());
                stm.setString(3, s.getPhone());
                stm.setString(4, s.getImg());
                stm.setString(5, s.getCustomerID());

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

    public boolean deleteCustomer(String customerId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Customer]\n"
                        + "   SET [status] = ?\n"
                        + " WHERE [customerID] = ?";

                stm = con.prepareStatement(sql);

                stm.setBoolean(1, false);
                stm.setString(2, customerId);

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

    public ArrayList<CustomerDTO> searchCustomerByName(String name) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<CustomerDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            String sql = "select * from customer "
                    + "where [Fullname] like ? and status = 1";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            rs = st.executeQuery();
            while (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"), rs.getString("img"), rs.getBoolean("status"));
                list.add(c);
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
        return list;
    }

    public CustomerDTO searchCustomerByPhone(String phone) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<CustomerDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            String sql = "select * from customer "
                    + "where phone = ? and status = 1";
            st = con.prepareStatement(sql);
            st.setString(1, phone);
            rs = st.executeQuery();
            if (rs.next()) {
                CustomerDTO c = new CustomerDTO(rs.getString("customerId"), rs.getString("email"),
                        rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("phone"), rs.getInt("point"), rs.getString("member"), rs.getString("img"), rs.getBoolean("status"));
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.OrderDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TreeMap;
import utils.DBHelper;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    public ArrayList<OrderDTO> getAll() throws ClassNotFoundException, SQLException {

        ArrayList<OrderDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        CustomerDAO cDAO = new CustomerDAO();
        VoucherDAO vDAO = new VoucherDAO();
        OrderBoardingDetailDAO obDAO = new OrderBoardingDetailDAO();
        OrderServiceDetailDAO osDAO = new OrderServiceDetailDAO();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [OrderID],\n"
                    + "       [createdDate],\n"
                    + "       [status],\n"
                    + "       [total],\n"
                    + "       [voucherID],\n"
                    + "       [customerID]\n"
                    + "FROM [dbo].[Order]\n"
                    + "ORDER BY CASE status\n"
                    + "    WHEN 'pending' THEN 1\n"
                    + "    WHEN 'confirm' THEN 2\n"
                    + "    WHEN 'complete' THEN 3\n"
                    + "    WHEN 'cancel' THEN 4\n"
                    + "    ELSE 5\n"
                    + "  END, [createdDate] DESC";

            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                ResultSet rs2 = null;
                sql = "select [time],[status]\n"
                        + "from [dbo].[OrderStatusRecords]\n"
                        + "where [OrderID] = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, rs.getString("orderID"));
                rs2 = stm.executeQuery();
                TreeMap<Timestamp, String> records = new TreeMap<>();
                while (rs2.next()) {
                    records.put(rs2.getTimestamp("time"), rs2.getString("status"));
                }
                OrderDTO order = new OrderDTO(rs.getString("orderID"), rs.getTimestamp("createdDate"), rs.getString("status"),
                        rs.getDouble("total"), vDAO.searchVoucherById(rs.getString("voucherID")),
                        cDAO.searchCustomerById(rs.getString("customerID")), obDAO.getOrderBoardingDetailById(rs.getString("orderID")),
                        osDAO.getOrderServiceDetailById(rs.getString("orderID")), records);
                list.add(order);
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
        return list;
    }

    public boolean insertOrder(Date createdDate, int status, double total, String voucherId, String customerId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        String orderId = createOrderId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Order]\n"
                        + "           ([OrderID]\n"
                        + "           ,[createdDate]\n"
                        + "           ,[status]\n"
                        + "           ,[total]\n"
                        + "           ,[voucherID]\n"
                        + "           ,[customerID])"
                        + "     VALUES (?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, orderId);
                stm.setDate(2, createdDate);
                stm.setInt(3, status);
                stm.setDouble(4, total);
                stm.setString(5, voucherId);
                stm.setString(6, customerId);

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

    public String createOrderId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newOrderId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from [Order]";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newOrderId = String.format("BK%06d", count + 1);
                }
            }
            if (newOrderId != null) {
                return newOrderId;
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
        return null;
    }

    public OrderDTO searchOrderById(String orderId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        VoucherDAO vDAO = new VoucherDAO();
        CustomerDAO cDAO = new CustomerDAO();
        OrderBoardingDetailDAO obDAO = new OrderBoardingDetailDAO();
        OrderServiceDetailDAO osDAO = new OrderServiceDetailDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + "      ,[createdDate]\n"
                        + "      ,[status]\n"
                        + "      ,[total]\n"
                        + "      ,[voucherID]\n"
                        + "      ,[customerID]\n"
                        + "  FROM [dbo].[Order]"
                        + "  where [OrderID] = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ResultSet rs2 = null;
                    sql = "select [time],[status]\n"
                            + "from [dbo].[OrderStatusRecords]\n"
                            + "where [OrderID] = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, rs.getString("orderID"));
                    rs2 = stm.executeQuery();
                    TreeMap<Timestamp, String> records = new TreeMap<>();
                    while (rs2.next()) {
                        records.put(rs2.getTimestamp("time"), rs2.getString("status"));
                    }
                    OrderDTO order = new OrderDTO(rs.getString("orderID"), rs.getTimestamp("createdDate"), rs.getString("status"),
                            rs.getDouble("total"), vDAO.searchVoucherById(rs.getString("voucherID")),
                            cDAO.searchCustomerById(rs.getString("customerID")), obDAO.getOrderBoardingDetailById(rs.getString("orderID")),
                            osDAO.getOrderServiceDetailById(rs.getString("orderID")), records);
                    return order;
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
        return null;
    }

    public boolean updateOrder(OrderDTO o)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Order]\n"
                        + "   SET [createdDate] = ?\n"
                        + "      ,[status] = ?\n"
                        + "      ,[total] = ?\n"
                        + "      ,[voucherID] = ?\n"
                        + "      ,[customerID] = ?"
                        + " WHERE [orderID] = ?";

                stm = con.prepareStatement(sql);

                stm.setTimestamp(1, o.getCreatedDate());
                stm.setString(2, o.getStatus());
                stm.setDouble(3, o.getTotal());
                stm.setString(4, o.getVoucher().getVoucherId());
                stm.setString(5, o.getCustomer().getCustomerID());
                stm.setString(6, o.getOrderId());

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

    public ArrayList<OrderDTO> searchOrderByStatus(String status) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        VoucherDAO vDAO = new VoucherDAO();
        CustomerDAO cDAO = new CustomerDAO();
        ArrayList<OrderDTO> list = new ArrayList();
        OrderBoardingDetailDAO obDAO = new OrderBoardingDetailDAO();
        OrderServiceDetailDAO osDAO = new OrderServiceDetailDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + "      ,[createdDate]\n"
                        + "      ,[status]\n"
                        + "      ,[total]\n"
                        + "      ,[voucherID]\n"
                        + "      ,[customerID]\n"
                        + "  FROM [dbo].[Order]"
                        + "  where [status] = ?\n"
                        + "  order by [createdDate] desc";

                stm = con.prepareStatement(sql);
                stm.setString(1, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ResultSet rs2 = null;
                    sql = "select [time],[status]\n"
                            + "from [dbo].[OrderStatusRecords]\n"
                            + "where [OrderID] = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, rs.getString("orderID"));
                    rs2 = stm.executeQuery();
                    TreeMap<Timestamp, String> records = new TreeMap<>();
                    while (rs2.next()) {
                        records.put(rs2.getTimestamp("time"), rs2.getString("status"));
                    }
                    OrderDTO order = new OrderDTO(rs.getString("orderID"), rs.getTimestamp("createdDate"), rs.getString("status"),
                            rs.getDouble("total"), vDAO.searchVoucherById(rs.getString("voucherID")),
                            cDAO.searchCustomerById(rs.getString("customerID")), obDAO.getOrderBoardingDetailById(rs.getString("orderID")),
                            osDAO.getOrderServiceDetailById(rs.getString("orderID")), records);
                    list.add(order);
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
        return list;
    }

    public ArrayList<OrderDTO> searchOrderByCustomerID(String customerID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        VoucherDAO vDAO = new VoucherDAO();
        CustomerDAO cDAO = new CustomerDAO();
        ArrayList<OrderDTO> list = new ArrayList();
        OrderBoardingDetailDAO obDAO = new OrderBoardingDetailDAO();
        OrderServiceDetailDAO osDAO = new OrderServiceDetailDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + "      ,[createdDate]\n"
                        + "      ,[status]\n"
                        + "      ,[total]\n"
                        + "      ,[voucherID]\n"
                        + "      ,[customerID]\n"
                        + "  FROM [dbo].[Order]"
                        + "  where [customerID] = ?\n"
                        + "ORDER BY CASE status\n"
                        + "    WHEN 'pending' THEN 1\n"
                        + "    WHEN 'confirm' THEN 2\n"
                        + "    WHEN 'complete' THEN 3\n"
                        + "    WHEN 'cancel' THEN 4\n"
                        + "    ELSE 5\n"
                        + "  END, [createdDate] DESC";

                stm = con.prepareStatement(sql);
                stm.setString(1, customerID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ResultSet rs2;
                    sql = "select [time],[status]\n"
                            + "from [dbo].[OrderStatusRecords]\n"
                            + "where [OrderID] = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, rs.getString("orderID"));
                    rs2 = stm.executeQuery();
                    TreeMap<Timestamp, String> records = new TreeMap<>();
                    while (rs2.next()) {
                        records.put(rs2.getTimestamp("time"), rs2.getString("status"));
                    }
                    OrderDTO order = new OrderDTO(rs.getString("orderID"), rs.getTimestamp("createdDate"), rs.getString("status"),
                            rs.getDouble("total"), vDAO.searchVoucherById(rs.getString("voucherID")),
                            cDAO.searchCustomerById(rs.getString("customerID")), obDAO.getOrderBoardingDetailById(rs.getString("orderID")),
                            osDAO.getOrderServiceDetailById(rs.getString("orderID")), records);
                    list.add(order);
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
        return list;
    }

    public ArrayList<OrderDTO> searchOrderByCustomerIdAndStatus(String customerID, String status) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        VoucherDAO vDAO = new VoucherDAO();
        CustomerDAO cDAO = new CustomerDAO();
        ArrayList<OrderDTO> list = new ArrayList();
        OrderBoardingDetailDAO obDAO = new OrderBoardingDetailDAO();
        OrderServiceDetailDAO osDAO = new OrderServiceDetailDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [OrderID]\n"
                        + "      ,[createdDate]\n"
                        + "      ,[status]\n"
                        + "      ,[total]\n"
                        + "      ,[voucherID]\n"
                        + "      ,[customerID]\n"
                        + "  FROM [dbo].[Order]"
                        + "  where [status] = ? and [customerID]=?\n"
                        + "  order by [createdDate] desc";

                stm = con.prepareStatement(sql);
                stm.setString(1, status);
                stm.setString(2, customerID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ResultSet rs2 = null;
                    sql = "select [time],[status]\n"
                            + "from [dbo].[OrderStatusRecords]\n"
                            + "where [OrderID] = ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, rs.getString("orderID"));
                    rs2 = stm.executeQuery();
                    TreeMap<Timestamp, String> records = new TreeMap<>();
                    while (rs2.next()) {
                        records.put(rs2.getTimestamp("time"), rs2.getString("status"));
                    }
                    OrderDTO order = new OrderDTO(rs.getString("orderID"), rs.getTimestamp("createdDate"), rs.getString("status"),
                            rs.getDouble("total"), vDAO.searchVoucherById(rs.getString("voucherID")),
                            cDAO.searchCustomerById(rs.getString("customerID")), obDAO.getOrderBoardingDetailById(rs.getString("orderID")),
                            osDAO.getOrderServiceDetailById(rs.getString("orderID")), records);
                    list.add(order);
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
        return list;
    }

    public boolean updateOrderStatus(String orderId, String status) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "exec update_order_status\n "
                        + "?,?,?";

                stm = con.prepareStatement(sql);

                stm.setString(1, orderId);
                stm.setString(2, status);
                stm.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

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

}

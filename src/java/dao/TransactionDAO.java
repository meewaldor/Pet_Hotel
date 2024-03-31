/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.BoardingDTO;
import dto.CustomerDTO;
import dto.FeedbackDTO;
import dto.TransactionDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.DBHelper;

/**
 *
 * @author Admin
 */
public class TransactionDAO {

    OrderDAO oDao = new OrderDAO();

    public boolean insertTransaction(String transactionId, Timestamp createdTime, double value, String orderId, String roomId, String petId,
            Date checkInDate, Date checkOutDate, double unitPrice, String orderStatus, double totalBill, String voucherId,
            String customerId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "EXEC insertTransaction "
                        + "     @transactionId = ?, \n"
                        + "	@createdTime = ?, \n"
                        + "	@value = ?, \n"
                        + "	@orderid = ?,\n"
                        + "	@roomId = ?,\n"
                        + "	@petId = ?, \n"
                        + "	@checkInDate = ?, \n"
                        + "	@checkOutDate = ?, \n"
                        + "	@unitPrice = ?, \n"
                        + "	@orderStatus = ?, \n"
                        + "	@totalBill = ?,\n"
                        + "	@voucherId = ?, \n"
                        + "	@customerId = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, transactionId);
                stm.setTimestamp(2, createdTime);
                stm.setDouble(3, value);
                stm.setString(4, orderId);
                stm.setString(5, roomId);
                stm.setString(6, petId);
                stm.setDate(7, checkInDate);
                stm.setDate(8, checkOutDate);
                stm.setDouble(9, unitPrice);
                stm.setString(10, orderStatus);
                stm.setDouble(11, totalBill);
                stm.setString(12, voucherId);
                stm.setString(13, customerId);

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

    public ArrayList<TransactionDTO> getAll(int pageIndex) throws ClassNotFoundException, SQLException {
        ArrayList<TransactionDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT *, ROW_NUMBER() OVER (ORDER BY [createdTime] desc) AS RowNumber\n"
                    + "    FROM [dbo].[Transaction]\n"
                    + ") AS Subquery\n"
                    + "WHERE RowNumber BETWEEN ? AND ?";
            st = con.prepareStatement(sql);
            st.setInt(1,(pageIndex-1)*10);
            st.setInt(2,pageIndex*10);
            rs = st.executeQuery();
            while (rs.next()) {
                CustomerDTO customer = oDao.searchOrderById(rs.getString("orderID")).getCustomer();
                TransactionDTO transaction = new TransactionDTO(rs.getString("transactionID"), oDao.searchOrderById(rs.getString("orderID")),
                        customer, rs.getTimestamp("createdTime"), rs.getDouble("value"));

                list.add(transaction);
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

    public TransactionDTO searchTransactionById(String searchValue) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "select * \n"
                    + "from [dbo].[Transaction]\n"
                    + "where [transactionID] = ?";
//            String sql = "select * from [dbo].[boarding]";
            st = con.prepareStatement(sql);
            st.setString(1, searchValue);
            rs = st.executeQuery();
            if (rs.next()) {
                CustomerDTO customer = oDao.searchOrderById(rs.getString("orderID")).getCustomer();
                TransactionDTO transaction = new TransactionDTO(rs.getString("transactionID"), oDao.searchOrderById(rs.getString("orderID")),
                        customer, rs.getTimestamp("createdTime"), rs.getDouble("value"));

                return transaction;
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

    public ArrayList<TransactionDTO> searchTransactionByOrderId(String searchValue) throws ClassNotFoundException, SQLException {
        ArrayList<TransactionDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "select * \n"
                    + "from [dbo].[Transaction]\n"
                    + "where [orderID] = ?";
            st = con.prepareStatement(sql);
            st.setString(1, searchValue);
            rs = st.executeQuery();
            while (rs.next()) {
                CustomerDTO customer = oDao.searchOrderById(rs.getString("orderID")).getCustomer();
                TransactionDTO transaction = new TransactionDTO(rs.getString("transactionID"), oDao.searchOrderById(rs.getString("orderID")),
                        customer, rs.getTimestamp("createdTime"), rs.getDouble("value"));

                list.add(transaction);
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

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.OrderBoardingDetailDTO;
import dto.RoomDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBHelper;

/**
 *
 * @author Admin
 */
public class OrderBoardingDetailDAO {

    public OrderBoardingDetailDTO getOrderBoardingDetailById(String orderId) throws SQLException, ClassNotFoundException {
        OrderBoardingDetailDTO result = null;

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        RoomDAO rDAO = new RoomDAO();
        PetDAO pDAO = new PetDAO();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [orderID]\n"
                        + "      ,[roomID]\n"
                        + "      ,[petID]\n"
                        + "      ,[checkInDate]\n"
                        + "      ,[checkOutDate]\n"
                        + "      ,[unit_price]\n"
                        + "      ,[total_price]\n"
                        + "      ,[feedback]\n"
                        + "  FROM [dbo].[Order_Boarding_Detail]"
                        + "  where [OrderID] = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    OrderBoardingDetailDTO b = new OrderBoardingDetailDTO(orderId, rDAO.searchRoomById(rs.getString("roomID")),
                            pDAO.searchPetById(rs.getString("petId")), rs.getDate("checkInDate"), rs.getDate("checkOutDate"),
                            rs.getDouble("unit_price"), rs.getDouble("total_price"),rs.getString("feedback"));
                    result = b;
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
        return result;
    }

    public boolean sendFeedback(String orderID, String roomID, String content) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update [dbo].[Order_Boarding_Detail] \n"
                        + "set [feedback] = ?\n"
                        + "where [orderID] = ? and [roomID] = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, content);
                stm.setString(2, orderID);
                stm.setString(3, roomID);
                int row = stm.executeUpdate();
                if (row > 0) {
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

}

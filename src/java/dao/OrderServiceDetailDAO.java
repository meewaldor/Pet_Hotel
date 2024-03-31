/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.OrderServiceDetailDTO;
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
public class OrderServiceDetailDAO {

    public ArrayList<OrderServiceDetailDTO> getOrderServiceDetailById(String orderId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ServiceDAO sDAO = new ServiceDAO();
        PetDAO pDAO = new PetDAO();
        ArrayList<OrderServiceDetailDTO> list = new ArrayList<>();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [orderID]\n"
                        + "      ,[serviceID]\n"
                        + "      ,[petID]\n"
                        + "      ,[time]\n"
                        + "      ,[unit_price]\n"
                        + "  FROM [dbo].[Order_Service_Detail]"
                        + "  where [OrderID] = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, orderId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    OrderServiceDetailDTO b = new OrderServiceDetailDTO(orderId, sDAO.searchServiceById(rs.getString("serviceId")),
                            pDAO.searchPetById(rs.getString("petId")),rs.getTimestamp("time"), rs.getDouble("unit_price"));
                    list.add(b);
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

}

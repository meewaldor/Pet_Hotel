/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.BoardingDTO;
import dto.FeedbackDTO;
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
public class FeedbackDAO {

    public ArrayList<FeedbackDTO> getFeebackByBoardingID(String boardingID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<FeedbackDTO> feedbacks = new ArrayList<>();
        CustomerDAO cusDao = new CustomerDAO();

        try {
            con = DBHelper.makeConnection();
            String sql = "select b.boardingID,d.customerID,d.OrderID,[feedback],[reply_feedback]\n"
                    + "from [dbo].[Order_Boarding_Detail] a, [dbo].[Boarding] b, [dbo].[Room] c , [dbo].[Order] d\n"
                    + "where a.[orderID] = d.OrderID and a.roomID = c.roomID and b.boardingID = c.boardingID and b.boardingID = ?";
//            String sql = "select * from [dbo].[boarding]";
            st = con.prepareStatement(sql);
            st.setString(1,boardingID);
            rs = st.executeQuery();
            while (rs.next()) {
                FeedbackDTO f = new FeedbackDTO(cusDao.searchCustomerById(rs.getString("customerID")), rs.getString("OrderID"), rs.getString("boardingID"),
                        rs.getString("feedback"), rs.getString("reply_feedback"));
                feedbacks.add(f);
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
        return feedbacks;
    }
}

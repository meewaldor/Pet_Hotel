/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.BoardingDTO;
import dto.FeedbackDTO;
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
public class BoardingDAO {

    public ArrayList<BoardingDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<BoardingDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<FeedbackDTO> feedbacks = new ArrayList<>();
        FeedbackDAO fDao = new FeedbackDAO();

        try {
            con = DBHelper.makeConnection();
            String sql = "select a.* , \n"
                    + "(select top 1 [price] from [dbo].[Boarding_Price] b \n"
                    + "where a.boardingId = b.boardingId  \n"
                    + "order by [date] desc) as 'price'\n"
                    + "from [dbo].[boarding] a where status = 1";
//            String sql = "select * from [dbo].[boarding]";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                String[] description = rs.getString("description").split("#");
                feedbacks = fDao.getFeebackByBoardingID(rs.getString("boardingId"));
                BoardingDTO c = new BoardingDTO(rs.getString("boardingId"), rs.getString("name"),
                        rs.getDouble("rate"), description, rs.getString("img"),
                        rs.getDouble("length"), rs.getDouble("height"), rs.getDouble("width"), rs.getDouble("maxWeight"),
                        rs.getBoolean("status"), rs.getFloat("price"),feedbacks);
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

    public BoardingDTO insertBoarding(String name, String description, String img,
            double length, double height, double width, double maxWeight, int price) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean status = true;
        BoardingDTO b = null;
        String boardingId = this.createBoardingId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "EXEC insert_boarding ?,?,?,?,?,?,?,?,?,?";

                stm = con.prepareStatement(sql);

                stm.setString(1, boardingId);
                stm.setString(2, name);
                stm.setString(3, description);
                stm.setString(4, img);
                stm.setDouble(5, length);
                stm.setDouble(6, height);
                stm.setDouble(7, width);
                stm.setDouble(8, maxWeight);
                stm.setBoolean(9, status);
                stm.setFloat(10, price);

                b = new BoardingDTO(boardingId, name, 0, description.split("#"), img, length, height, width, maxWeight, status, price);

                stm.executeUpdate();
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
        return b;
    }

    private String createBoardingId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newBoardingId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from Boarding";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newBoardingId = String.format("B%03d", count + 1);
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
        return newBoardingId;
    }

    public boolean updateBoarding(BoardingDTO s)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "EXEC update_Boarding\n"
                        + "    @boardingId = ?,\n"
                        + "    @name = ?,\n"
                        + "    @rate = ?,\n"
                        + "    @description = ?,\n"
                        + "    @img = ?,\n"
                        + "    @length = ?,\n"
                        + "    @height = ?,\n"
                        + "    @width = ?,\n"
                        + "    @maxWeight = ?,\n"
                        + "    @status = ?,\n"
                        + "    @price = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, s.getBoardingId());
                stm.setString(2, s.getName());
                stm.setDouble(3, s.getRate());
                stm.setString(4, convertArrayToString(s.getDescription()));
                stm.setString(5, s.getImg());
                stm.setDouble(6, s.getLength());
                stm.setDouble(7, s.getHeight());
                stm.setDouble(8, s.getWidth());
                stm.setDouble(9, s.getMaxWeight());
                stm.setBoolean(10, s.isStatus());
                stm.setFloat(11, s.getPrice());

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

    public String convertArrayToString(String[] x) {
        String rs = "";

        for (String string : x) {
            rs += string + "#";
        }
        return rs;
    }

    public BoardingDTO searchBoardingById(String boardingId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select a.* , \n"
                        + "(select top 1 [price] from [dbo].[Boarding_Price] b \n"
                        + "where a.boardingId = b.boardingId  \n"
                        + "order by [date] desc) as 'price'\n"
                        + "from [dbo].[boarding] a\n"
                        + "where a.boardingId = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, boardingId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    BoardingDTO b = new BoardingDTO(boardingId, rs.getString("name"), rs.getDouble("rate"),
                            rs.getString("description").split("#"), rs.getString("img"), rs.getDouble("length"),
                            rs.getDouble("height"), rs.getDouble("width"),
                            rs.getDouble("maxWeight"), rs.getBoolean("status"), rs.getFloat("price"));
                    return b;

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

    public boolean deleteBoarding(String boardingId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[boarding]\n"
                        + "   SET [status] = ?\n"
                        + " WHERE [boardingId] = ?";

                stm = con.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setString(2, boardingId);
                if (stm.executeUpdate() != 0) {
                    result = true;
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

    public ArrayList<BoardingDTO> searchBoardingByName(String name) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ArrayList<BoardingDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select a.* , \n"
                        + "(select top 1 [price] from [dbo].[Boarding_Price] b \n"
                        + "where a.boardingId = b.boardingId  \n"
                        + "order by [date] desc) as 'price'\n"
                        + "from [dbo].[boarding] a\n"
                        + "where a.name like ? and status = 1";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    BoardingDTO b = new BoardingDTO(rs.getString("boardingId"), rs.getString("name"), rs.getDouble("rate"),
                            rs.getString("description").split("#"), rs.getString("img"), rs.getDouble("length"), rs.getDouble("height"), rs.getDouble("width"),
                            rs.getDouble("maxWeight"), rs.getBoolean("status"), rs.getFloat("price"));
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

    public ArrayList<BoardingDTO> getBoardingsAvailable(Date checkin, Date checkout) throws ClassNotFoundException, SQLException {
        ArrayList<BoardingDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        BoardingDAO bDAO = new BoardingDAO();
        try {
            con = DBHelper.makeConnection();
            String sql = "select [boardingID], [name], [img] \n"
                    + "from [dbo].[Boarding]\n"
                    + "where [boardingID] in (\n"
                    + "select [boardingID] \n"
                    + "from [dbo].[Room] \n"
                    + "where [roomID] not in \n"
                    + "(select [roomID]\n"
                    + "from [dbo].[Order_Boarding_Detail] \n"
                    + "where ([checkInDate] < ? and [checkOutDate] > ?)\n"
                    + "or ([checkInDate] < ? and [checkOutDate] > ?)\n"
                    + "or ([checkInDate] >= ? and [checkOutDate] <= ?)))";

            st = con.prepareStatement(sql);
            st.setDate(1, checkin);
            st.setDate(2, checkin);
            st.setDate(3, checkout);
            st.setDate(4, checkout);
            st.setDate(5, checkin);
            st.setDate(6, checkout);
            rs = st.executeQuery();
            while (rs.next()) {
                BoardingDTO b = new BoardingDTO(rs.getString("boardingID"), rs.getString("name"), rs.getString("img"));
                list.add(b);
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
    
    public RoomDTO getRoomAvailableByBoardingId(String boardingId, Date checkin, Date checkout) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        RoomDAO rDAO = new RoomDAO();
        BoardingDAO bDAO = new BoardingDAO();
        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [boardingID], [roomID]\n"
                    + "FROM [dbo].[Room]\n"
                    + "WHERE [roomID] NOT IN (\n"
                    + "    SELECT [roomID]\n"
                    + "    FROM [dbo].[Order_Boarding_Detail]\n"
                    + "    WHERE ([checkInDate] < ? AND [checkOutDate] > ?)\n"
                    + "        OR ([checkInDate] < ? AND [checkOutDate] > ?)\n"
                    + "        OR ([checkInDate] >= ? AND [checkOutDate] <= ?)\n"
                    + ") AND [boardingID] = ?";

            st = con.prepareStatement(sql);
            st.setDate(1, checkin);
            st.setDate(2, checkin);
            st.setDate(3, checkout);
            st.setDate(4, checkout);
            st.setDate(5, checkin);
            st.setDate(6, checkout);
            st.setString(7, boardingId);
            rs = st.executeQuery();
            if (rs.next()) {
                RoomDTO room = new RoomDTO(rs.getString("roomID"), searchBoardingById(boardingId), true);
                return room;
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

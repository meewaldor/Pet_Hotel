/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.VoucherDTO;
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
public class VoucherDAO {

    public ArrayList<VoucherDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<VoucherDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [voucherID]\n"
                    + "      ,[fromDate]\n"
                    + "      ,[toDate]\n"
                    + "      ,[value]\n"
                    + "  FROM [dbo].[Voucher]";

            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                VoucherDTO v = new VoucherDTO(rs.getString("voucherID"), rs.getDate("fromDate"),
                        rs.getDate("toDate"), rs.getDouble("value"));
                list.add(v);
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

    public boolean insertVoucher(Date fromDate, Date toDate, double value) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        String voucherId = createVoucherId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Voucher]\n"
                        + "           ([voucherID]\n"
                        + "           ,[fromDate]\n"
                        + "           ,[toDate]\n"
                        + "           ,[value])\n"
                        + "     VALUES (?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, voucherId);
                stm.setDate(2, fromDate);
                stm.setDate(3, toDate);
                stm.setDouble(4, value);

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

    public String createVoucherId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newVoucherId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from Voucher";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newVoucherId = String.format("V%03d", count + 1);
                }
            }
            if (newVoucherId != null) {
                return newVoucherId;
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

    public VoucherDTO searchVoucherById(String voucherId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [voucherID]\n"
                        + "      ,[fromDate]\n"
                        + "      ,[toDate]\n"
                        + "      ,[value]\n"
                        + "  FROM [dbo].[Voucher]"
                        + "  where voucherID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, voucherId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    VoucherDTO b = new VoucherDTO(voucherId, rs.getDate("fromDate"), rs.getDate("toDate"), rs.getDouble("value"));
                    if (b != null) {
                        return b;
                    }
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

    public boolean updateVoucher(VoucherDTO v)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Voucher]\n"
                        + "   SET [fromDate] = ?\n"
                        + "      ,[toDate] = ?\n"
                        + "      ,[value] = ?\n"
                        + " WHERE [voucherID] = ?";

                stm = con.prepareStatement(sql);

                stm.setDate(1, v.getFromDate());
                stm.setDate(2, v.getToDate());
                stm.setDouble(3, v.getValue());
                stm.setString(4, v.getVoucherId());

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
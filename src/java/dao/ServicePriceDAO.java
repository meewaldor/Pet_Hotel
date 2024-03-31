/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.ServicePriceDTO;
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
public class ServicePriceDAO {

    public ArrayList<ServicePriceDTO> searchServicePriceById(String serviceId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ArrayList<ServicePriceDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select d.*\n"
                        + "from [dbo].[Service_Price] d,\n"
                        + "(SELECT [serviceID],max([date]) as 'date',[type],[weight]\n"
                        + "  FROM [dbo].[Service_Price]\n"
                        + "  where [serviceID] = ?\n"
                        + "  group by [serviceID],[type],[weight]) c\n"
                        + "where d.serviceID = c.serviceID and d.type = c.type and d.weight = c.weight and d.date = c.date and d.status = 1\n"
                        + "order by d.type, d.weight";

                stm = con.prepareStatement(sql);
                stm.setString(1, serviceId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    ServicePriceDTO b = new ServicePriceDTO(serviceId, rs.getString("type"),
                            rs.getDouble("weight"), rs.getFloat("price"), rs.getBoolean("status"));
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

    public boolean createServicePrice(String serviceID, String petType, float petWeight,int price) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "exec insert_service_price ?,?,?,?";
                stm = con.prepareStatement(sql);

                stm.setString(1, serviceID);
                stm.setString(2, petType);
                stm.setFloat(3, petWeight);
                stm.setFloat(4, price);

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

    public boolean deleteAllServiceDetail(String id) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update [dbo].[Service_Price]\n"
                        + "set [status] = 0\n"
                        + "where [serviceID] = ?";
                stm = con.prepareStatement(sql);

                stm.setString(1, id);

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

    public boolean deleteServicePrice(String serviceId, String type, String weight) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update [dbo].[Service_Price]\n"
                        + "set [status] = 0\n"
                        + "where [serviceID] = ? and [type] = ? and [weight] = ?";
                stm = con.prepareStatement(sql);

                stm.setString(1, serviceId);
                stm.setString(2, type);
                stm.setString(3, weight);

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



}

package dao;

import dto.ServiceDTO;
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
public class ServiceDAO {

    public ArrayList<ServiceDTO> getAll() throws ClassNotFoundException, SQLException {
        ArrayList<ServiceDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [serviceID]\n"
                        + "      ,[name]\n"
                        + "      ,[rate]\n"
                        + "      ,[description]\n"
                        + "      ,[img]\n"
                        + "      ,[status]\n"
                        + "  FROM [dbo].[Service]\n"
                        + "  WHERE [status] = 1";

                st = con.prepareStatement(sql);
                rs = st.executeQuery();
                while (rs.next()) {
                    ServiceDTO c = new ServiceDTO(rs.getString("serviceID"), rs.getString("name"),
                            rs.getDouble("rate"), rs.getString("description"), rs.getString("img"), rs.getBoolean("status"));
                    list.add(c);
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
        return list;
    }

    public boolean insertService(String name, String description, String img) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        String serviceId = createServiceId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Service]\n"
                        + "           ([serviceID]\n"
                        + "           ,[name]\n"
                        + "           ,[rate]\n"
                        + "           ,[description]\n"
                        + "           ,[img]\n"
                        + "           ,[status])\n"
                        + "     VALUES (?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, serviceId);
                stm.setString(2, name);
                stm.setDouble(3, 0);
                stm.setString(4, description);
                stm.setString(5, img);
                stm.setBoolean(6, true);

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

    public String createServiceId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newServiceId = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from Service";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newServiceId = String.format("S%03d", count + 1);
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
        return newServiceId;
    }

    public void deleteService(String serviceId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Service]\n"
                        + "   SET [status] = ?\n"
                        + " WHERE serviceID = ?";

                stm = con.prepareStatement(sql);
                stm.setBoolean(1, false);
                stm.setString(2, serviceId);
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
    }

    public boolean updateService(ServiceDTO s)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Service]\n"
                        + "   SET [name] = ?\n"
                        + "      ,[rate] = ?\n"
                        + "      ,[description] = ?\n"
                        + "      ,[img] = ?\n"
                        + "      ,[status] = ?\n"
                        + " WHERE [serviceID] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, s.getName());
                stm.setDouble(2, s.getRate());
                stm.setString(3, s.getDescription());
                stm.setString(4, s.getImg());
                stm.setBoolean(5, s.isStatus());
                stm.setString(6, s.getServiceId());

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

    public ServiceDTO searchServiceById(String serviceId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [serviceID]\n"
                        + "      ,[name]\n"
                        + "      ,[rate]\n"
                        + "      ,[description]\n"
                        + "      ,[img]\n"
                        + "      ,[status]\n"
                        + "  FROM [dbo].[Service]\n"
                        + "  where serviceID = ? and status = 1";

                stm = con.prepareStatement(sql);
                stm.setString(1, serviceId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ServiceDTO b = new ServiceDTO(serviceId, rs.getString("name"), rs.getDouble("rate"),
                            rs.getString("description"), rs.getString("img"), rs.getBoolean("status"));
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
    
    public ArrayList<ServiceDTO> searchServiceByIdV2(String serviceId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ArrayList<ServiceDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [serviceID]\n"
                        + "      ,[name]\n"
                        + "      ,[rate]\n"
                        + "      ,[description]\n"
                        + "      ,[img]\n"
                        + "      ,[status]\n"
                        + "  FROM [dbo].[Service]\n"
                        + "  where serviceID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, serviceId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ServiceDTO b = new ServiceDTO(serviceId, rs.getString("name"), rs.getDouble("rate"),
                            rs.getString("description"), rs.getString("img"), rs.getBoolean("status"));
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

    public ArrayList<ServiceDTO> searchServiceByName(String name) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        ArrayList<ServiceDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT [serviceID]\n"
                        + "      ,[name]\n"
                        + "      ,[rate]\n"
                        + "      ,[description]\n"
                        + "      ,[img]\n"
                        + "      ,[status]\n"
                        + "  FROM [dbo].[Service]\n"
                        + "  where name like ? and status = 1";

                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                while (rs.next()) {

                    ServiceDTO b = new ServiceDTO(rs.getString("serviceId"), rs.getString("name"), rs.getDouble("rate"),
                            rs.getString("description"), rs.getString("img"), rs.getBoolean("status"));
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

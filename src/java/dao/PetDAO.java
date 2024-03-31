package dao;

import dto.CustomerDTO;
import dto.PetDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DBHelper;

public class PetDAO {

    CustomerDAO cusDao = new CustomerDAO();

    public ArrayList<PetDTO> getAll(int pageIndex) throws ClassNotFoundException, SQLException {

        ArrayList<PetDTO> list = new ArrayList();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT *\n"
                    + "FROM (\n"
                    + "    SELECT *, ROW_NUMBER() OVER (ORDER BY [petID]) AS RowNumber\n"
                    + "    FROM [dbo].[Pet]\n"
                    + ") AS Subquery\n"
                    + "WHERE RowNumber BETWEEN ? AND ?";

            st = con.prepareStatement(sql);
            st.setInt(1,(pageIndex-1)*10);
            st.setInt(2,pageIndex*10);
            rs = st.executeQuery();
            while (rs.next()) {
                PetDTO p = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), cusDao.searchCustomerById(rs.getString("customerId")), rs.getString("type"),
                        rs.getDouble("weight"), rs.getString("gender"), rs.getBoolean("vaccinated"),
                        rs.getBoolean("status"), rs.getString("img"));
                list.add(p);
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

    public boolean insertPet(String name, Date dob, String customerId, String type,
            double weight, String gender, boolean vaccinated) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        int rs;
        String petId = createPetId();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO [dbo].[Pet]\n"
                        + "           ([petID]\n"
                        + "           ,[name]\n"
                        + "           ,[dob]\n"
                        + "           ,[customerID]\n"
                        + "           ,[type]\n"
                        + "           ,[weight]\n"
                        + "           ,[gender]\n"
                        + "           ,[vaccinated]\n"
                        + "           ,[status])"
                        + "     VALUES (?,?,?,?,?,?,?,?,?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, petId);
                stm.setString(2, name);
                stm.setDate(3, dob);
                stm.setString(4, customerId);
                stm.setString(5, type);
                stm.setDouble(6, weight);
                stm.setString(7, gender);
                stm.setBoolean(8, vaccinated);
                stm.setBoolean(9, true);

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

    public String createPetId() throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int count;
        String newPetId = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(*) as recordCount from Pet";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();

                if (rs.next()) {
                    count = rs.getInt("recordCount");
                    newPetId = String.format("P%06d", count + 1);
                }
            }
            if (newPetId != null) {
                return newPetId;
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

    public PetDTO searchPetById(String petId) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [petID]\n"
                    + "      ,[name]\n"
                    + "      ,[dob]\n"
                    + "      ,[customerID]\n"
                    + "      ,[type]\n"
                    + "      ,[weight]\n"
                    + "      ,[gender]\n"
                    + "      ,[vaccinated]\n"
                    + "      ,[status]\n"
                    + "      ,[img]\n"
                    + "  FROM [dbo].[Pet]"
                    + "  WHERE [petID] = ? and status = 1";
            st = con.prepareStatement(sql);
            st.setString(1, petId);
            rs = st.executeQuery();
            if (rs.next()) {
                PetDTO c = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), cusDao.searchCustomerById(rs.getString("customerId")), rs.getString("type"),
                        rs.getDouble("weight"), rs.getString("gender"), rs.getBoolean("vaccinated"),
                        rs.getBoolean("status"), rs.getString("img"));
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

    public boolean updatePet(PetDTO p)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Pet]\n"
                        + "   SET [petID] = ?\n"
                        + "      ,[name] = ?\n"
                        + "      ,[dob] = ?\n"
                        + "      ,[customerID] = ?\n"
                        + "      ,[type] = ?\n"
                        + "      ,[weight] = ?\n"
                        + "      ,[gender] = ?\n"
                        + "      ,[vaccinated] = ?\n"
                        + "      ,[status] = ?"
                        + " WHERE [PetID] = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, p.getPetId());
                stm.setString(2, p.getName());
                stm.setDate(3, p.getDob());
                stm.setString(4, p.getCustomer().getCustomerID());
                stm.setString(5, p.getType());
                stm.setDouble(6, p.getWeight());
                stm.setString(7, p.getGender());
                stm.setBoolean(8, p.isVaccinated());
                stm.setBoolean(9, p.isStatus());
                stm.setString(10, p.getPetId());

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

    public boolean deletePet(String petId)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;
        boolean status = false;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Pet]\n"
                        + "   SET [status] = ?\n"
                        + " WHERE [petID] = ?";

                stm = con.prepareStatement(sql);

                stm.setBoolean(1, status);
                stm.setString(2, petId);

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

    public ArrayList<PetDTO> getPetByCusId(String customerId) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<PetDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [petID]\n"
                    + "      ,[name]\n"
                    + "      ,[dob]\n"
                    + "      ,[customerID]\n"
                    + "      ,[type]\n"
                    + "      ,[weight]\n"
                    + "      ,[gender]\n"
                    + "      ,[vaccinated]\n"
                    + "      ,[status]\n"
                    + "      ,[img]\n"
                    + "  FROM [dbo].[Pet]"
                    + "  WHERE [customerID] = ? and status = 1";
            st = con.prepareStatement(sql);
            st.setString(1, customerId);
            rs = st.executeQuery();
            while (rs.next()) {
                PetDTO c = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), cusDao.searchCustomerById(rs.getString("customerId")), rs.getString("type"),
                        rs.getDouble("weight"), rs.getString("gender"), rs.getBoolean("vaccinated"),
                        rs.getBoolean("status"), rs.getString("img"));
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

    public ArrayList<PetDTO> searchPetByName(String name) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<PetDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [petID]\n"
                    + "      ,[name]\n"
                    + "      ,[dob]\n"
                    + "      ,[customerID]\n"
                    + "      ,[type]\n"
                    + "      ,[weight]\n"
                    + "      ,[gender]\n"
                    + "      ,[vaccinated]\n"
                    + "      ,[status]\n"
                    + "      ,[img]\n"
                    + "  FROM [dbo].[Pet]"
                    + "  WHERE [name] like ?";
            st = con.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            rs = st.executeQuery();
            while (rs.next()) {
                PetDTO c = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), cusDao.searchCustomerById(rs.getString("customerId")), rs.getString("type"),
                        rs.getDouble("weight"), rs.getString("gender"), rs.getBoolean("vaccinated"),
                        rs.getBoolean("status"), rs.getString("img"));
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

    public ArrayList<PetDTO> searchPetByType(String typeFilter) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<PetDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [petID]\n"
                    + "      ,[name]\n"
                    + "      ,[dob]\n"
                    + "      ,[customerID]\n"
                    + "      ,[type]\n"
                    + "      ,[weight]\n"
                    + "      ,[gender]\n"
                    + "      ,[vaccinated]\n"
                    + "      ,[status]\n"
                    + "      ,[img]\n"
                    + "  FROM [dbo].[Pet]"
                    + "  WHERE [type] = ? and status = 1";
            st = con.prepareStatement(sql);
            st.setString(1, typeFilter);
            rs = st.executeQuery();
            while (rs.next()) {
                PetDTO c = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), cusDao.searchCustomerById(rs.getString("customerId")), rs.getString("type"),
                        rs.getDouble("weight"), rs.getString("gender"), rs.getBoolean("vaccinated"),
                        rs.getBoolean("status"), rs.getString("img"));
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

    public ArrayList<PetDTO> searchPetByCustomerID(String cusID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<PetDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [petID]\n"
                    + "      ,[name]\n"
                    + "      ,[dob]\n"
                    + "      ,[customerID]\n"
                    + "      ,[type]\n"
                    + "      ,[weight]\n"
                    + "      ,[gender]\n"
                    + "      ,[vaccinated]\n"
                    + "      ,[status]\n"
                    + "      ,[img]\n"
                    + "  FROM [dbo].[Pet]"
                    + "  WHERE [customerID] = ? and status = 1";
            st = con.prepareStatement(sql);

            st.setString(1, cusID);
            rs = st.executeQuery();
            while (rs.next()) {
                PetDTO c = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), cusDao.searchCustomerById(rs.getString("customerId")), rs.getString("type"),
                        rs.getDouble("weight"), rs.getString("gender"), rs.getBoolean("vaccinated"),
                        rs.getBoolean("status"), rs.getString("img"));
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

    public boolean updatePetWeight(String id, float weight) throws ClassNotFoundException, SQLException {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stm = null;
        int result = 0;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE [dbo].[Pet]\n"
                        + "   SET [weight] = ?\n"
                        + " WHERE [PetID] = ?";

                stm = con.prepareStatement(sql);

                stm.setFloat(1, weight);
                stm.setString(2, id);

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

    public ArrayList<PetDTO> getPetByNameAndCusId(String customerID, String searchValue) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<PetDTO> list = new ArrayList();

        try {
            con = DBHelper.makeConnection();
            String sql = "SELECT [petID]\n"
                    + "      ,[name]\n"
                    + "      ,[dob]\n"
                    + "      ,[customerID]\n"
                    + "      ,[type]\n"
                    + "      ,[weight]\n"
                    + "      ,[gender]\n"
                    + "      ,[vaccinated]\n"
                    + "      ,[status]\n"
                    + "      ,[img]\n"
                    + "  FROM [dbo].[Pet]"
                    + "  WHERE [customerID] = ? and status = 1 and [name] like ?";
            st = con.prepareStatement(sql);

            st.setString(1, customerID);
            st.setString(2, "%" + searchValue + "%");
            rs = st.executeQuery();
            while (rs.next()) {
                PetDTO c = new PetDTO(rs.getString("petId"), rs.getString("name"),
                        rs.getDate("dob"), cusDao.searchCustomerById(rs.getString("customerId")), rs.getString("type"),
                        rs.getDouble("weight"), rs.getString("gender"), rs.getBoolean("vaccinated"),
                        rs.getBoolean("status"), rs.getString("img"));
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
}

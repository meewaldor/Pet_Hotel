/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package customerController;

import dao.CustomerDAO;
import dao.PetDAO;
import dto.CustomerDTO;
import dto.PetDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class UpdatePetUserViewServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("petId");
        PetDAO dao = new PetDAO();

        try {
            PetDTO p = dao.searchPetById(id);
            request.setAttribute("pet", p);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher("ReadPetByCustomerServlet").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String petId = request.getParameter("petId");
        String name = request.getParameter("petName");
        Date dob = Date.valueOf(request.getParameter("petdob"));
        String type = request.getParameter("type");
        double weight = Double.parseDouble(request.getParameter("weight"));
        String gender = request.getParameter("gender");
        boolean vaccinated = Boolean.parseBoolean(request.getParameter("vaccinated"));
        String img = request.getParameter("petimg");

        HttpSession session = request.getSession();
        CustomerDTO customer = (CustomerDTO) session.getAttribute("user");

        try {
            PetDAO dao = new PetDAO();
            CustomerDAO cDao = new CustomerDAO();
            if (customer != null) {
                PetDTO p = new PetDTO(petId, name, dob, customer, type, weight, gender, vaccinated, true, img);
                boolean result = dao.updatePet(p);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect("ReadPetByCustomer");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

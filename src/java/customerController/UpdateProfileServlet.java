/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package customerController;

import dao.CustomerDAO;
import dto.CustomerDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import validate.RegistrationInsertErr;

/**
 *
 * @author Admin
 */
public class UpdateProfileServlet extends HttpServlet {

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
        String customerId = request.getParameter("customerId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String img = request.getParameter("img");
        String phone = request.getParameter("phone");
        RegistrationInsertErr errors = new RegistrationInsertErr();

        try {
            boolean error = false;
            if (!phone.matches("^\\d{10}$")) {
                errors.setPhoneNotValid("Phone is not valid!");
                error = true;
            }
            if (!error) {
                CustomerDAO dao = new CustomerDAO();
                CustomerDTO c = new CustomerDTO(customerId, firstName, lastName, phone, img);
                boolean result = dao.updateCustomer(c);
                if (result) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", dao.searchCustomerById(customerId));
                    request.setAttribute("msg", "Successfully updated!");
                } else {
                    request.setAttribute("msg", "Update fail! Please try again!");
                }
            } else {
                request.setAttribute("msg",errors);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect("profile.jsp");
        }
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
        processRequest(request, response);
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
        processRequest(request, response);
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

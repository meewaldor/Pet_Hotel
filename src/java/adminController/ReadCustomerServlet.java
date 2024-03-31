/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dao.CustomerDAO;
import dto.BoardingDTO;
import dto.CustomerDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ReadCustomerServlet extends HttpServlet {

    private final String CUSTOMER_LIST_PAGE = "customer_list.jsp";

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
        String url = CUSTOMER_LIST_PAGE;
        String searchValue = request.getParameter("searchValue");
        String type = request.getParameter("type");
        ArrayList<CustomerDTO> list = new ArrayList<>();
        try {
            CustomerDAO dao = new CustomerDAO();
            CustomerDTO cus;
            if (searchValue != null && !searchValue.trim().isEmpty()) {
                if (type.equals("ID")) {
                    cus = dao.searchCustomerById(searchValue.trim());
                    if (cus != null) {
                        list.add(cus);
                    }
                } else if (type.equals("Name")) {
                    list = dao.searchCustomerByName(searchValue.trim());
                } else if (type.equals("Phone")) {
                    cus = dao.searchCustomerByPhone(searchValue.trim());
                    if (cus != null) {
                        list.add(cus);
                    }
                } else if (type.equals("Email")) {
                    list = dao.searchCustomerByEmail(searchValue.trim());
                }
            } else {
                list = dao.getAll();
            }
            request.setAttribute("customerList", list);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dao.CustomerDAO;
import dao.OrderDAO;
import dto.AdminDTO;
import dto.CustomerDTO;
import dto.OrderDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ReadOrderServlet extends HttpServlet {

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

        String searchValue = request.getParameter("searchValue");
        String type = request.getParameter("type");
        String statusFilter = request.getParameter("statusFilter");

        HttpSession session = request.getSession();
        AdminDTO admin = (AdminDTO) session.getAttribute("admin");
        String url = "login.jsp";

        ArrayList<OrderDTO> list = new ArrayList<>();
        try {
            OrderDAO dao = new OrderDAO();
            CustomerDAO cusDao = new CustomerDAO();
            if (admin != null) {
                if (searchValue != null && !searchValue.trim().isEmpty() && type.equals("oID")) {
                    OrderDTO o = dao.searchOrderById(searchValue);
                    if (o != null) {
                        list.add(o);
                    }
                } else if (searchValue != null && !searchValue.trim().isEmpty() && type.equals("cusID")) {
                    list = dao.searchOrderByCustomerID(searchValue);
                } else if (searchValue != null && !searchValue.trim().isEmpty() && type.equals("phone")) {
                    CustomerDTO cus = cusDao.searchCustomerByPhone(searchValue);
                    if (cus != null) {
                        String cusId = cus.getCustomerID();
                        list = dao.searchOrderByCustomerID(cusId);
                    }
                } else if (statusFilter != null && !statusFilter.equals("All")) {
                    list = dao.searchOrderByStatus(statusFilter);
                } else {
                    list = dao.getAll();
                }
                request.setAttribute("orderList", list);
                url = "order_list.jsp";
            }
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

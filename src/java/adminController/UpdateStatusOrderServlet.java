/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package adminController;

import dao.OrderDAO;
import dto.AdminDTO;
import dto.CustomerDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class UpdateStatusOrderServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String orderId = request.getParameter("OrderID");
        String status = request.getParameter("status");
        String searchValue = request.getParameter("searchValue");
        String type = request.getParameter("type");
        String statusFilter = request.getParameter("statusFilter");
        HttpSession session = request.getSession();
        CustomerDTO customer = (CustomerDTO) session.getAttribute("user");
        AdminDTO admin = (AdminDTO) session.getAttribute(("admin"));
        String url;
        if (customer != null) {
            url = "ReadOrderByCustomer";
        } else {
            url = "ReadOrder";
        }
        if (searchValue!= null && !searchValue.trim().isEmpty())
            url += ("?searchValue="+searchValue);
        if (type!= null && !type.trim().isEmpty())
            url += ("&type="+type);
        if (statusFilter!= null && !statusFilter.trim().isEmpty())
            url += ("?statusFilter="+statusFilter);
        try {
            OrderDAO dao = new OrderDAO();
            boolean result = dao.updateOrderStatus(orderId,status);
            if (!result) {
                request.setAttribute("msg", "Something wrong! Please try again!");
            }            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }finally {
            response.sendRedirect(url);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dao.BoardingDAO;
import dao.TransactionDAO;
import dto.BoardingDTO;
import dto.TransactionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ReadTransactionServlet extends HttpServlet {

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
        String url = "transaction_list.jsp";
        String searchValue = request.getParameter("searchValue");
        String type = request.getParameter("type");
        String pageIndex_raw = request.getParameter("page");
        int pageIndex = 1;
        if (pageIndex_raw != null) {
            pageIndex = Integer.parseInt(pageIndex_raw);
        }
        ArrayList<TransactionDTO> list = new ArrayList<>();
        try {
            TransactionDAO dao = new TransactionDAO();
            if (searchValue != null && !searchValue.trim().isEmpty() && type.equals("transactionID")) {
                TransactionDTO b = dao.searchTransactionById(searchValue);
                if (b != null) {
                    list.add(b);
                }
            } else if (searchValue != null && !searchValue.trim().isEmpty() && type.equals("orderID")) {
                list = dao.searchTransactionByOrderId(searchValue);
            } else {
                list = dao.getAll(pageIndex);
            }
            request.setAttribute("transactionList", list);
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

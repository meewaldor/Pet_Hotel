/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dao.BoardingDAO;
import dto.BoardingDTO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class CreateBoardingServlet extends HttpServlet {

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
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String img = "picture/"+request.getParameter("img");
        String length_raw = request.getParameter("length");
        String height_raw = request.getParameter("height");
        String width_raw = request.getParameter("width");
        String maxWeight_raw = request.getParameter("maxWeight");
        String price_raw = request.getParameter("price");
        String searchValue = request.getParameter("searchValue");
        String type = request.getParameter("type");
        // Path
        String url = ("ReadBoarding");
        if (searchValue!=null && !searchValue.trim().isEmpty())
            url += ("?searchValue="+searchValue+"&type="+type);
        try {

            double length = Double.parseDouble(length_raw);
            double height = Double.parseDouble(height_raw);
            double width = Double.parseDouble(width_raw);
            double maxWeight = Double.parseDouble(maxWeight_raw);
            int price = Integer.parseInt(price_raw);

            BoardingDAO dao = new BoardingDAO();
            BoardingDTO b = dao.insertBoarding(name, description, img, length, height, width, maxWeight,price);
            if (b != null)
                request.setAttribute("msg", "Succefully add a new boarding!");
            else
                request.setAttribute("msg", "Fail!Please try again!");
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            response.sendRedirect(url);
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

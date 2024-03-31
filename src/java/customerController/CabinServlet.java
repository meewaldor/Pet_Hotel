/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package customerController;

import dao.BoardingDAO;
import dao.PetDAO;
import dao.ServiceDAO;
import dto.BoardingDTO;
import dto.CustomerDTO;
import dto.PetDTO;
import dto.ServiceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CabinServlet extends HttpServlet {

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
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        CustomerDTO customer = (CustomerDTO) session.getAttribute("user");
        try {
//            Get boarding by ID
            BoardingDAO dao = new BoardingDAO();
            BoardingDTO b = dao.searchBoardingById(id);
//            Get customer's pets
            PetDAO pDao = new PetDAO();
            ArrayList<PetDTO> petList = pDao.getPetByCusId(customer.getCustomerID());
//            Get add-ons
            ServiceDAO sDao = new ServiceDAO();
            ArrayList<ServiceDTO> serviceList = sDao.getAll();

            // Get check in and check out date and amount
            double totalBill = 0;
            String checkInDate = request.getParameter("checkin");
            String checkOutDate = request.getParameter("checkout");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date checkIn = sdf.parse(checkInDate);
            Date checkOut = sdf.parse(checkOutDate);

            long difference = (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
            double totalPrice = difference * (b.getPrice());
            totalBill += totalPrice;

            session.setAttribute("checkIn", checkInDate);
            session.setAttribute("checkOut", checkOutDate);
            session.setAttribute("difference", difference);
            session.setAttribute("totalBill", totalBill);
            session.setAttribute("totalPrice", totalPrice);

            if (b != null) {
                request.setAttribute("boarding", b);
            }
            if (!petList.isEmpty()) {
                request.setAttribute("petList", petList);
            }
            if (!serviceList.isEmpty()) {
                request.setAttribute("serviceList", serviceList);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(CabinServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher("cabin.jsp").forward(request, response);
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

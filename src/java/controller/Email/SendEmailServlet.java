/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Email;

import dto.BoardingDTO;
import dto.CustomerDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class SendEmailServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        CustomerDTO cus = (CustomerDTO) session.getAttribute("user");
        
        String orderID = (String) session.getAttribute("orderID");

        String customerEmail = cus.getEmail();
        BoardingDTO b = (BoardingDTO) session.getAttribute("boarding");
        String checkin = (String) session.getAttribute("checkIn");
        String checkout = (String) session.getAttribute("checkOut");
        Double total = (Double) session.getAttribute("totalBill");
        Timestamp paymentTime = (Timestamp) session.getAttribute("paymentTime");
        
        String subject = "[NekoHee] Transfer successful";
        String msg = "Hi,"+cus.getFirstName()+" "+cus.getLastName()+"\n"
                + "\n"
                + "Thank you for using our service.\n"
                + "\n"
                + "Your booking's information:\n"
                + "Booking's ID: " + orderID +"\n"
                + b.getName() + "\n"
                + "Check in: " + checkin + " - Check out: " + checkout + "\n"
                + "Amount: " + total + "VND\n"
                + "Transaction time: " + paymentTime + "\n"
                + "\n"
                + "Best regards,\n"
                + "NekoHee";

        Mailer.send(customerEmail, subject, msg);

        // Huy session
        session.removeAttribute("checkIn");
        session.removeAttribute("checkOut");
        session.removeAttribute("boarding");
        session.removeAttribute("difference");
        session.removeAttribute("totalPrice");
        session.removeAttribute("totalBill");
        session.removeAttribute("petId");
        session.removeAttribute("orderID");

        response.sendRedirect("Home");
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

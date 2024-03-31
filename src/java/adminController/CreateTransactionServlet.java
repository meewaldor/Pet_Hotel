/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dao.BoardingDAO;
import dao.OrderDAO;
import dao.RoomDAO;
import dao.TransactionDAO;
import dto.BoardingDTO;
import dto.CustomerDTO;
import dto.PetDTO;
import dto.RoomDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServlet;

/**
 *
 * @author Admin
 */
public class CreateTransactionServlet extends HttpServlet {

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

        RoomDAO rDAO = new RoomDAO();
        BoardingDAO bDAO = new BoardingDAO();
        OrderDAO oDAO = new OrderDAO();
        TransactionDAO tDAO = new TransactionDAO();
        HttpSession session = request.getSession();

        try {
            //Get data from vnpay - Transaction table
            String transactionCode = request.getParameter("vnp_TxnRef");
            String totalMoney_raw = request.getParameter("vnp_Amount");
            double totalMoney = Double.parseDouble(totalMoney_raw);
            String paymentTime_raw = request.getParameter("vnp_PayDate");
            
            Timestamp paymentTime = convertStringToTimeStamp(paymentTime_raw);
            session.setAttribute("paymentTime", paymentTime);
            String orderId = oDAO.createOrderId();
            

            // Get information of boarding detail
            String checkin_raw = (String) session.getAttribute("checkIn");
            String checkout_raw = (String) session.getAttribute("checkOut");
            Date checkInDate = Date.valueOf(checkin_raw);
            Date checkOutDate = Date.valueOf(checkout_raw);
            
            // Get information of boarding
            BoardingDTO b = (BoardingDTO) session.getAttribute("boarding");
            double unit_price = b.getPrice();
            long dateDifferent = dateDifference(checkInDate, checkOutDate);
            double totalPrice = unit_price * dateDifferent;
            RoomDTO room = bDAO.getRoomAvailableByBoardingId(b.getBoardingId(), checkInDate, checkOutDate);
            String roomId = room.getRoomId();
            
            // Get information of customer
            CustomerDTO customer = (CustomerDTO) session.getAttribute("user");
            String customerId = customer.getCustomerID();
            
            // Get information of pet
            String petId = (String) session.getAttribute("petId");


            boolean success = tDAO.insertTransaction(transactionCode, paymentTime,
                    totalMoney, orderId, roomId , petId,
                    checkInDate, checkOutDate, unit_price, "Pending", totalPrice, null, customerId);
            
            session.setAttribute("orderID", orderId);

        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher("SendEmailServlet").forward(request, response);
            //response.sendRedirect("home.jsp");
        }

    }

    public static Timestamp convertStringToTimeStamp(String timeStampString) throws ParseException {
        String pattern = "yyyyMMddHHmmss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timeStampString));
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }

    public static long dateDifference(Date firstDate, Date secondDate) {
        long diff = 0;

        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return diff;
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

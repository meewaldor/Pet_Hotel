/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CustomerDAO;
import dto.CustomerDTO;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
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
public class RegistrationServlet extends HttpServlet {

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
        RegistrationInsertErr errors = new RegistrationInsertErr();
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String phone = request.getParameter("phone");
        CustomerDAO cDao = new CustomerDAO();
        String url =  "registration.jsp";
        try {
            boolean error = false;
            if (email.isEmpty()) {
                error = true;
                errors.setEmailIsEmpty("Email must be filled!!!");
            }
            if (firstName.isEmpty()) {
                error = true;
                errors.setFirstNameIsEmpty("Firstname must be filled!!!");
            }
            if (lastName.isEmpty()) {
                error = true;
                errors.setLastNameIsEmpty("Lastname must be filled!!!");
            }
            if (password.isEmpty()) {
                error = true;
                errors.setPasswordIsEmpty("Password must be filled!!!");
            }
            if (cDao.isExisted(email)) {
                error = true;
                errors.setEmailIsExisted(email + " is existed!!!");
            }
            if(!confirmPassword.equals(password)){
                error = true;
                errors.setConfirmPasswordNotValid("Confirm password does not match password!!!");
            }
            if (cDao.isExisted(email)) {
                error = true;
                errors.setEmailIsExisted(email+" already existed!");
            }
            if (!phone.matches("^\\d{10}$")) {
                error = true;
                errors.setPhoneNotValid("Phone is not valid!");
            }
            if (!error) {
                cDao.insertCustomer(email, firstName, lastName, password, phone);
                request.setAttribute("msg", "Successfully sign up");
                url = "login.jsp";
            }
            else {
                request.setAttribute("error", errors);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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

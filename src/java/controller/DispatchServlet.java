/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String HOME_CONTROLLER = "HomeServlet";

    private final String HOME_PAGE = "home.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final String REGISTRATION_PAGE = "registration.jsp";
    private final String PROFILE_PAGE = "profile.jsp";
    private final String ADMIN_PROFILE_PAGE = "admin_profile.jsp";
    private final String UPDATE_PASSWORD_PAGE = "updatePassword.jsp";
    private final String BOOKING_PAGE = "booking_list.jsp"; 
    
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String REGISTRATION_CONTROLLER = "RegistrationServlet";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    
    private final String CUSTOMERS_LIST_CONTROLLER = "ReadCustomerServlet";
    private final String SERVICES_LIST_CONTROLLER = "ReadServiceServlet";
    private final String BOARDINGS_LIST_CONTROLLER = "ReadBoardingServlet";
    private final String ORDERS_LIST_CONTROLLER = "ReadOrderServlet";
    private final String CUSTOMER_ORDERS_LIST_CONTROLLER = "ReadOrderByCustomerServlet";
    private final String ORDER_DETAILS_CONTROLLER = "ReadOrderDetailsServlet";
    private final String PET_LIST_CONTROLLER = "ReadPetServlet";
    private final String READ_SERVICE_PRICE = "ReadServicePriceServlet";
    private final String CUSTOMER_PET_LIST_CONTROLLER = "ReadPetByCustomerServlet";
    
    private final String DELETE_SERVICE_CONTROLLER = "DeleteServiceServlet";
    private final String DELETE_SERVICE_PRICE_CONTROLLER = "DeleteServicePriceServlet";
    private final String DELETE_BOARDING_CONTROLLER = "DeleteBoardingServlet";
    private final String DELETE_CUSTOMER_CONTROLLER = "DeleteCustomerServlet";
    private final String DELETE_ORDER_CONTROLLER = "DeleteOrderServlet";
    private final String DELETE_PET_CONTROLLER = "DeletePetServlet";
    
    private final String CREATE_BOARDING_CONTROLLER = "CreateBoardingServlet";
    private final String CREATE_SERVICE_CONTROLLER = "CreateServiceServlet";
    private final String CREATE_PET_CONTROLLER = "CreatePetServlet";
    private final String ADMIN_CREATE_PET_CONTROLLER = "CreatePetAdminServlet";
    private final String CREATE_ORDER_CONTROLLER = "CreateOrderServlet";
    private final String CREATE_SERVICE_PRICE_CONTROLLER = "CreateServicePriceServlet";
    
    private final String UPDATE_BOARDING_CONTROLLER = "UpdateBoardingServlet";
    private final String UPDATE_ORDER_CONTROLLER = "UpdateOrderServlet";
    private final String UPDATE_SERVICE_CONTROLLER = "UpdateServiceServlet";
    private final String UPDATE_PET_CONTROLLER = "UpdatePetServlet";
    private final String UPDATE_CUSTOMER_CONTROLLER = "UpdateCustomerServlet";
    private final String UPDATE_PROFILE_CONTROLLER = "UpdateProfileServlet";
    private final String UPDATE_STAFF_PROFILE_CONTROLLER = "UpdateStaffProfileServlet";
    private final String UPDATE_STATUS_ORDER_CONTROLLER = "UpdateStatusOrderServlet";
           
    private final String SEARCH_BOARDING_CONTROLLER = "SearchBoardingServlet";    
    private final String CABIN_CONTROLLER ="CabinServlet";   
    private final String DISPLAY_SERVICE_ADD_FORM = "DisplayServiceAddFormServlet";

//   private final String NULL_CONTROLLER = "NullServlet";
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
        String url = HOME_CONTROLLER;
        //which button did user click?
        String button = request.getParameter("btAction");
        try {
            if (button != null) {
                switch (button) {
                    case "Login":
                        url = LOGIN_CONTROLLER;
                        break;
                    case "loginPage":
                        url = LOGIN_PAGE;
                        break;
                    case "Registration":
                        url = REGISTRATION_CONTROLLER;
                        break;
                    case "RegistrationPage":
                        url = REGISTRATION_PAGE;
                        break;
                    case "home":
                        url = HOME_PAGE;
                        break;
                    case "Logout":
                        url = LOGOUT_CONTROLLER;
                        break;
                    case "ReadCustomers":
                        url = CUSTOMERS_LIST_CONTROLLER;
                        break;
                    case "ReadServices":
                        url = SERVICES_LIST_CONTROLLER;
                        break;
                    case "ReadBoardings":
                        url = BOARDINGS_LIST_CONTROLLER;
                        break;
                    case "ReadOrders":
                        url = ORDERS_LIST_CONTROLLER;
                        break;
                    case "ReadPets":
                        url = PET_LIST_CONTROLLER;
                        break;
                    case "ReadOrderDetails":
                        url = ORDER_DETAILS_CONTROLLER;
                        break;
                    case "DeleteService":
                        url = DELETE_SERVICE_CONTROLLER;
                        break;
                    case "DeleteCustomer":
                        url = DELETE_CUSTOMER_CONTROLLER;
                        break;
                    case "DeleteBoarding":
                        url = DELETE_BOARDING_CONTROLLER;
                        break;
                    case "DeleteOrder":
                        url = DELETE_ORDER_CONTROLLER;
                        break;
                    case "DeletePet":
                        url = DELETE_PET_CONTROLLER;
                        break;
                    case "CreateBoarding":
                        url = CREATE_BOARDING_CONTROLLER;
                        break;
                    case "CreateService":
                        url = CREATE_SERVICE_CONTROLLER;
                        break;
                    case "Add Pet":
                        url = CREATE_PET_CONTROLLER;
                        break;
                    case "CreateOrder":
                        url = CREATE_ORDER_CONTROLLER;
                        break;
                    case "UpdateBoarding":
                        url = UPDATE_BOARDING_CONTROLLER;
                        break;
                    case "UpdateService":
                        url = UPDATE_SERVICE_CONTROLLER;
                        break;
                    case "Profile":
                        url = PROFILE_PAGE;
                        break;
                    case "Pet":
                        url = CUSTOMER_PET_LIST_CONTROLLER;
                        break;
                    case "UpdatePassword":
                        url = UPDATE_PASSWORD_PAGE;
                        break;
                    case "UpdateCustomer":
                        url = UPDATE_CUSTOMER_CONTROLLER;
                        break;
                    case "SEARCH":
                        url = SEARCH_BOARDING_CONTROLLER;
                        break;
                    case "cabin":
                        url = CABIN_CONTROLLER;
                        break;
                    case "ReadServicePrice":
                        url = READ_SERVICE_PRICE;
                        break;
                    case "DisplayServiceAddForm":
                        url = DISPLAY_SERVICE_ADD_FORM;
                        break;
                    case "UpdateProfile":
                        url = UPDATE_PROFILE_CONTROLLER;
                        break;
                    case "UpdateStatusOrder":
                        url = UPDATE_STATUS_ORDER_CONTROLLER;
                        break;
                    case "AddServicePrice":
                        url = CREATE_SERVICE_PRICE_CONTROLLER;
                        break;
                    case "StatusFilter":
                        url = ORDERS_LIST_CONTROLLER;
                        break;
                    case "ReadOrdersByCustomer":
                        url = CUSTOMER_ORDERS_LIST_CONTROLLER;
                        break;
                    case "AdminProfile":
                        url = ADMIN_PROFILE_PAGE;
                        break;
                    case "UpdateStaffProfile":
                        url = UPDATE_STAFF_PROFILE_CONTROLLER;
                        break;
                    case "DeleteServicePrice":
                        url = DELETE_SERVICE_PRICE_CONTROLLER;
                        break;
                    case "UpdatePet":
                        url = UPDATE_PET_CONTROLLER;
                        break;
                    case "AdminCreatePet":
                        url = ADMIN_CREATE_PET_CONTROLLER;
                        break;
                }
            }
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

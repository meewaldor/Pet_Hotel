/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dao.CustomerDAO;
import dao.PetDAO;
import dto.AdminDTO;
import dto.CustomerDTO;
import dto.PetDTO;
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
public class ReadPetServlet extends HttpServlet {

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
        //get param
        String searchValue = request.getParameter("searchValue");
        String type = request.getParameter("type");
        String typeFilter = request.getParameter("typeFilter");
        String pageIndex_raw = request.getParameter("page");
        int pageIndex = 1;
        if (pageIndex_raw != null) {
            pageIndex = Integer.parseInt(pageIndex_raw);
        }
        
        HttpSession session = request.getSession();
        AdminDTO admin = (AdminDTO) session.getAttribute("admin");
        
        String url = "login.jsp";
        try {
            PetDAO dao = new PetDAO();
            ArrayList<PetDTO> list = new ArrayList<>();
            if (admin != null) {
                if (searchValue != null && !searchValue.trim().isEmpty()) {
                    if (type.equals("ID")) {
                        PetDTO pet = dao.searchPetById(searchValue);
                        if (pet != null) {
                            list.add(pet);
                        }
                    } else if (type.equals("Name")) {
                        list = dao.searchPetByName(searchValue);
                    } else if (type.equals("Phone")) {
                        CustomerDAO cusDao = new CustomerDAO();
                        CustomerDTO cus = cusDao.searchCustomerByPhone(searchValue);
                        if (cus != null) {
                            list = dao.searchPetByCustomerID(cus.getCustomerID());
                        }
                    }
                } else if (typeFilter != null && !typeFilter.equals("All")) {
                    list = dao.searchPetByType(typeFilter);
                } else {
                    list = dao.getAll(pageIndex);
                }
                request.setAttribute("data", list);
                url = "pet_list.jsp";
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

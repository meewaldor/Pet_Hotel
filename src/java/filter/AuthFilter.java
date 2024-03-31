/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import dto.AdminDTO;
import dto.CustomerDTO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class AuthFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    private final List<String> guest;
    private final List<String> customer;
    private final List<String> staff;
    private final List<String> tails;
    

    public AuthFilter() {
        // List of tail
        tails = new ArrayList<>();
        tails.add(".css");
        tails.add(".js");
        tails.add(".png");
        tails.add(".jpg");
        // List of pages guest can visit without authentication
        guest = new ArrayList<>();
        guest.add("");
        guest.add("login.jsp");
        guest.add("login.jsp");
        guest.add("registration.jsp");
        guest.add("home.jsp");
        guest.add("service.jsp");
        guest.add("boarding.jsp");
        guest.add("header.jsp");
        guest.add("footer.html");
        guest.add("contact.jsp");
        
        guest.add("Home");
        guest.add("Login");
        guest.add("Logout");
        guest.add("Registration");
        guest.add("SearchBoarding");
        guest.add("GoogleSignInServlet");
        
        
        // List of pages users with customer role can visit
        customer = new ArrayList<>();
        customer.add("cabin.jsp");
        customer.add("booking_list.jsp");
        customer.add("booking_detail.jsp");
        customer.add("myPet.jsp");
        customer.add("payment.jsp");
        customer.add("profile.jsp");
        customer.add("updatePassword.jsp");
        customer.add("profile.jsp");
        
        customer.add("Cabin");
        customer.add("CreatePet");
        customer.add("ReadOrderByCustomer");
        customer.add("ReadOrderDetails");
        customer.add("UpdatePetUserView");
        customer.add("UpdateProfile");
        customer.add("ReadPetByCustomer");
        customer.add("PaymentWithVnpay");
        customer.add("CreateTransaction");
        customer.add("UpdateStatusOrder");
        customer.add("DeletePet");
        customer.add("SendFeedback");
        customer.add("SearchBoarding");
        
        // List of pages users with admin role can visit
        staff = new ArrayList<>();
        staff.add("boarding_list.jsp");
        staff.add("service_list.jsp");
        staff.add("order_list.jsp");
        staff.add("pet_list.jsp");
        staff.add("customer_list.jsp");
        staff.add("new_boarding.jsp");
        staff.add("new_pet.jsp");
        staff.add("new_service.jsp");
        staff.add("update_boarding.jsp");
        staff.add("update_customer.jsp");
        staff.add("update_service.jsp");
        
        staff.add("ReadCustomer");
        staff.add("ReadBoarding");
        staff.add("ReadOrder");
        staff.add("ReadPet");
        staff.add("ReadService");
        staff.add("ReadPetByCustomer");
        staff.add("ReadServicePrice");
        staff.add("ReadTransaction");
        staff.add("CreateBoarding");
        staff.add("CreatePetAdmin");
        staff.add("CreateServicePrice");
        staff.add("CreateService");
        staff.add("DeleteBoarding");
        staff.add("DeleteCustomer");
        staff.add("DeletePet");
        staff.add("DeleteServicePrice");
        staff.add("DeleteService");
        staff.add("UpdateBoarding");
        staff.add("UpdateService");
        staff.add("UpdatePet");
        staff.add("UpdateCustomer");
        staff.add("UpdateStaffProfile");
        staff.add("UpdateStatusOrder");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("AuthFilter:doFilter()");
        }

//        doBeforeProcessing(request, response);

        Throwable problem = null;

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String login_page = "login.jsp";
        HttpSession session = req.getSession();
        CustomerDTO cus = (CustomerDTO) session.getAttribute("user");
        AdminDTO admin = (AdminDTO) session.getAttribute("admin");
        
        int lastIndexOfDot = uri.lastIndexOf(".");
        int lastIndexOfSlash = uri.lastIndexOf("/");
        int indexOfQuestion = uri.lastIndexOf("?");
        
        try {
            String resource;
            if (indexOfQuestion>=0)
                resource = uri.substring(lastIndexOfSlash + 1,indexOfQuestion);
            else 
                resource = uri.substring(lastIndexOfSlash + 1);
            if (admin != null) {
                // role admin
                if (guest.contains(resource) || lastIndexOfDot >=0 || staff.contains(resource) )
                    chain.doFilter(request, response);
                else
                    res.sendRedirect(login_page);
            } 
            if (cus != null){
                // role customer
                if (guest.contains(resource) || lastIndexOfDot >=0 || customer.contains(resource))
                    chain.doFilter(request, response);
                else
                    res.sendRedirect(login_page);
            }
            if (admin == null && cus == null) {
                // chua dang nhap
                if (guest.contains(resource) || lastIndexOfDot >=0) 
                    chain.doFilter(request, response);
                else
                    res.sendRedirect(login_page);
            }

        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

//        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}

/**
 * RegisterInsurer.java; Registers an Insurer user on the system by retrieving
 * the entered information from the .jsp register page, using it to create the
 * relevant objects for an Insurer user and passing them to the
 * DatabaseController to add to the database.
 */
package Servlet;

import Controller.UserController;
import Model.Insurer;
import Model.RiskThreshold;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterInsurer", urlPatterns = {"/RegisterInsurer"})
public class RegisterInsurer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            UserController userCon = new UserController();
            
            //retrieve entered data
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String title = request.getParameter("title");
            String firstName = request.getParameter("firstname");
            String surname = request.getParameter("surname");
            String address1 = request.getParameter("address1");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String postcode = request.getParameter("postcode");
            double minPrice = Double.parseDouble(request.getParameter("minPrice"));
            double maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
            String gadgetCondition = request.getParameter("condition");
            String gadgetType = request.getParameter("type"); 
            String typeOfCover = request.getParameter("typeOfCover");
            int coverPeriod = Integer.parseInt(request.getParameter("coverPeriod"));

            //capitalize first letters of names
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
            
            //create ArrayList of accepted conditions
            ArrayList<String> gadgetConditions = new ArrayList<>();
            gadgetConditions.add(gadgetCondition);//add condition to ArrayList

            //create RiskThreshold object with given information
            RiskThreshold riskT = new RiskThreshold(gadgetType, minPrice, maxPrice, typeOfCover, coverPeriod, gadgetConditions, username);
            
            //create ArrayList of RiskThresholds, as Insurer can have more than one
            ArrayList<RiskThreshold> riskThresholds = new ArrayList<>();
            riskThresholds.add(riskT);//add RiskThreshold object to list
            //create Insurer object with given information
            Insurer insurer = new Insurer("N", riskThresholds, username, password, email, title, 
                    firstName, surname, address1, street, city, postcode, "Insurer");
            
            userCon.addInsurer(insurer, riskThresholds);//pass to DatabaseController to add to database

             //send email to insurer confirming registration & give login details
            String email_subject ="Welcome to SureInsure";
            String email_msg = "<b><p>Welcome to SureInsure " + insurer.getfName() + " " + insurer.getsName() + "!</p></b>" 
                    +" <p>Thank you for registering with Sure Insure. Please find your login details below.</p>"
                    +"<p>Username: " + insurer.getUsername() +"<br/> Password: " + insurer.getPassword()+" </p>"
                    + "<p>We hope you find the insurance you are looking for."
                    + " Do not hesitate to contact us should you have any queries and we will respond within 24 hours.</p>"
                    + "<p>Best wishes, <br/> SureInsure Team";
            
            userCon.sendEmail_ConfirmingRegistration(insurer.getEmail(), "", email_subject, email_msg);
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterInsurer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterInsurer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RegisterInsurer.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterInsurer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterInsurer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RegisterInsurer.class.getName()).log(Level.SEVERE, null, ex);
        }
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

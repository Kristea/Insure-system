/**
 * RegisterInsured.java; Registers an Insured user on the system by retrieving
 * the entered information from the .jsp register page, using it to create the
 * relevant objects for an Insured user and passing them to the
 * DatabaseController to add to the database.
 */
package Servlet;

import Controller.InsuranceController;
import Controller.UserController;
import Model.Gadget;
import Model.InsuranceRequirement;
import Model.Insured;
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

@WebServlet(name = "RegisterInsured", urlPatterns = {"/RegisterInsured"})
public class RegisterInsured extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            InsuranceController insCon = new InsuranceController();
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
            String date = request.getParameter("dob");
            String gadgetType = request.getParameter("type");
            String gadgetMake = request.getParameter("make");
            String gadgetModel = request.getParameter("model");
            int gadgetAge = Integer.parseInt(request.getParameter("age"));
            String gadgetCondition = request.getParameter("condition");
            String environment = request.getParameter("env");
            String typeOfCover = request.getParameter("typeOfCover");
            int coverPeriod = Integer.parseInt(request.getParameter("coverPeriod"));
            String sr = request.getParameter("showRequirement");

            //change gadget make values 
            if(gadgetMake.contains("Samsung-p") ||gadgetMake.contains("Samsung-t") ){
                gadgetMake = "Samsung";
            }
            else if(gadgetMake.contains("Asus-l") ||gadgetMake.contains("Asus-t") ){
                gadgetMake = "Asus";
            }
            else if(gadgetMake.contains("Acer-l") ||gadgetMake.contains("Acer-t") ){
                gadgetMake = "Acer";
            }
            
            //capitalize first letters of names
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
            
            //checking if user entered yes or no & making a boolean to reflect answer
            Boolean showRequirement = null;
            if (sr.contains("true")) {
                showRequirement = true;
            } else if (sr.contains("false")) {
                showRequirement = false;
            }

            //split up and convert DOB to int
            String year = date.substring(0, 4);
            String month = date.substring(5, 7);
            String day = date.substring(8, 10);

            int dayDOB = Integer.parseInt(day);
            int monthDOB = Integer.parseInt(month);
            int yearDOB = Integer.parseInt(year);
            
            //method to get gadget id from database;
            String gadgetID = insCon.getGadgetID(gadgetType, gadgetMake, gadgetModel);
            double gadgetPrice = insCon.getGadgetPrice(gadgetID);
            Gadget gadget = new Gadget(gadgetID, gadgetType, gadgetMake, gadgetModel, gadgetPrice);

            //create InsuranceRequirement
            InsuranceRequirement insReq = new InsuranceRequirement(gadget, typeOfCover, coverPeriod, false,
                    gadgetAge, gadgetCondition, environment, username, showRequirement);
            ArrayList<InsuranceRequirement> insReqList = new ArrayList<>();
            insReqList.add(insReq);//add it to ArrayList of InsuranceRequirements for this Insured user

            //create Insured object
            Insured insured = new Insured(dayDOB, monthDOB, yearDOB, insReqList, username, password, email, title, firstName, surname, address1, street, city,
                    postcode, "Insured");
            
            userCon.addInsured(insured, gadget);//pass to DatabaseController to add to database

            //send email to insured user confirming registration & give login details
            String email_subject ="Welcome to SureInsure";
            String email_msg = "<b><p>Welcome to SureInsure " + insured.getfName() + " " + insured.getsName() + "!</p></b>" 
                    +" <p>Thank you for registering with SureInsure. Please find your login details below.</p>"
                    +"<p>Username: " + insured.getUsername() +"<br/> Password: " + insured.getPassword()+" </p>"
                    + "<p>We hope you find the insurance you are looking for."
                    + " Do not hesitate to contact us should you have any queries and we will respond within 24 hours.</p>"
                    + "<p>Best wishes, <br/> SureInsure Team";
            
            userCon.sendEmail_ConfirmingRegistration(insured.getEmail(), "", email_subject, email_msg);
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
            Logger.getLogger(RegisterInsured.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterInsured.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RegisterInsured.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegisterInsured.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterInsured.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(RegisterInsured.class.getName()).log(Level.SEVERE, null, ex);
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

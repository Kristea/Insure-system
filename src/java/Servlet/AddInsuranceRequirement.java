/**
 * AddInsuranceRequirement.java; Retrieves all the attributes from
 * AddInsuranceRequirement.jsp in order to create a new InsuranceRequirement for
 * the Insured user currently in session.
 */
package Servlet;

import Controller.InsuranceController;
import Model.Gadget;
import Model.InsuranceRequirement;
import Model.Insured;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddInsuranceRequirement", urlPatterns = {"/AddInsuranceRequirement"})
public class AddInsuranceRequirement extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //creating a session, if one does not already exist
            HttpSession session = request.getSession(true);
            InsuranceController insCon = new InsuranceController();

            String gadgetType = request.getParameter("type");
            String gadgetMake = request.getParameter("make");
            String gadgetModel = request.getParameter("model");
            int gadgetAge = Integer.parseInt(request.getParameter("age"));
            String gadgetCondition = request.getParameter("condition");
            String environment = request.getParameter("env");
            String typeOfCover = request.getParameter("typeOfCover");
            int coverPeriod = Integer.parseInt(request.getParameter("coverPeriod"));
            String sr = request.getParameter("showRequirement");
            String insuredUsername = request.getParameter("insuredUsername");

            //checking if user entered yes or no & making a boolean to reflect answer
            Boolean showRequirement = null;
            if (sr.contains("true")) {
                showRequirement = true;
            } else if (sr.contains("false")) {
                showRequirement = false;
            }
            //getting gadget information to create a Gadget object
            String gadgetID = insCon.getGadgetID(gadgetType, gadgetMake, gadgetModel);
            double gadgetPrice = insCon.getGadgetPrice(gadgetID);
            Gadget gadget = new Gadget(gadgetID, gadgetType, gadgetMake, gadgetModel, gadgetPrice);

            //create InsuranceRequirement object
            InsuranceRequirement insReq = new InsuranceRequirement(gadget, typeOfCover, coverPeriod, false,
                    gadgetAge, gadgetCondition, environment, insuredUsername, showRequirement);

            //get the insured logged into the session
            Insured insured = (Insured) session.getAttribute("insured");
            insured.getInsReq().add(insReq);//add the new insurance requirement to their list

            //add InsuranceRequirement to the database
            insCon.addInsuranceRequirement(insReq);
            insCon.addToLink1Table(insuredUsername, insReq.getInsReq_id());

            //re-direct to their profile page
            request.getRequestDispatcher("ProfilePageInsured.jsp").forward(request, response);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddInsuranceRequirement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddInsuranceRequirement.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddInsuranceRequirement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddInsuranceRequirement.class.getName()).log(Level.SEVERE, null, ex);
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

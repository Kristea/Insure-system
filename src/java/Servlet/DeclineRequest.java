/**
 * DeclineRequest.java; Called upon an Insurer rejecting an Insured users
 * request for insurance. Deals with updating the database and the users
 * involved lists of Requests objects.
 */
package Servlet;

import Controller.DatabaseController;
import Controller.InsuranceController;
import Controller.UserController;
import Model.Insured;
import Model.Insurer;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DeclineRequest", urlPatterns = {"/DeclineRequest"})
public class DeclineRequest extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //creating a session, if one does not already exist
            HttpSession session = request.getSession(true);
            InsuranceController insCon = new InsuranceController();
            UserController userCon = new UserController();

            //attributes from jsp
            String insuredEmail = request.getParameter("insuredEmail");
            String insuredUsername = request.getParameter("insuredUsername");
            String insurerEmail = request.getParameter("insurerEmail");
            //getting Insurer logged in the session
            Insurer insurer = (Insurer) session.getAttribute("insurer");
            //getting the insured user involved
            Insured insured = userCon.getInsured(insuredUsername);
            String reqID = request.getParameter("reqID");
            String comment = request.getParameter("comment");

            //Request involved to update its status
            Request req = insCon.getOneRequest(reqID);
            req.setStatus(Request.Status.CANCELLED);
            req.setComment(comment);
            //update status in the database

            insCon.updateRequest_Status(req);

            //update status for each user involved
            for (Request r : insurer.getRequests()) {
                if (r.getAgreement_id().contains(reqID)) {
                    r.setStatus(Request.Status.CANCELLED);
                }
            }

            for (Request r : insured.getRequests()) {
                if (r.getAgreement_id().contains(reqID)) {
                    r.setStatus(Request.Status.CANCELLED);
                }
            }

            //sending email to insured to notify insurer has declined request
            String val = String.format("%.02f", req.getInsReq().getValue());//formating price to 2decimal places
            String email_subject = "Insurance Request Declined ref: " + req.getInsReq().getInsReq_id();
            String email_msg = "<p>Dear " + req.getInsured().getfName() + " " + req.getInsured().getsName() + ",</p>"
                    + req.getInsurer().getTitle() + " " + req.getInsurer().getfName() + " " + req.getInsurer().getsName()
                    + " has declined your insurance request (Request ID: " + req.getAgreement_id() + "). </p><br/>"
                    + " <p>Reason for Declining : " + req.getComment() + " </p>"
                    + "<p><b>Requested Insurance Requirement</b></p>"
                    + "<p>Gadget Type: " + req.getInsReq().getGadget().getType() + "<br/>"
                    + "Gadget Make: " + req.getInsReq().getGadget().getMake() + "<br/>"
                    + "Gadget Model: " + req.getInsReq().getGadget().getModel() + "<br/>"
                    + "Gadget Age: " + req.getInsReq().getGadget_age() + " months<br/>"
                    + "Gadget Value: £" + val + "<br/>"
                    + "Gadget Condition: " + req.getInsReq().getCondition() + "<br/>"
                    + "Environment Where Gadget is Used: " + req.getInsReq().getEnvironment() + "<br/></p>"
                    + "<p>Type of Cover Required: " + req.getInsReq().getTypeOfCover() + "<br/>"
                    + "Cover Period: " + req.getInsReq().getCoverPeriod() + " months<br/></p>"
                    + " <p>Please remember to give " + req.getInsurer().getfName() + " feedback on your Overview Page for this request.</p>"
                    + "<p>Do not hesitate to contact us should you have any queries and we will respond within 24 hours.</p>"
                    + "<p>Best wishes, <br/> SureInsure Team";

            String email_msg2 = "<p>Dear " + req.getInsurer().getfName() + " " + req.getInsurer().getsName() + ",</p>"
                    + " you have declined "
                    + req.getInsured().getTitle() + " " + req.getInsured().getfName() + " " + req.getInsured().getsName()
                    + "'s insurance request (Request ID: " + req.getAgreement_id() + "). </p><br/>"
                    + "<p>For details of this request please refer to your Overview Page</p>"
                    + "<p>Please remember to give " + req.getInsured().getfName() + " feedback on your Overview Page for this request.</p>"
                    + "<p>Do not hesitate to contact us should you have any queries and we will respond within 24 hours.</p>"
                    + "<p>Best wishes, <br/> SureInsure Team";

            insCon.sendEmail_RequestDeclinedtToInsured(insuredEmail, insurerEmail, email_subject, email_msg);
            insCon.sendEmail_RequestDeclinedtToInsurer(insuredEmail, insurerEmail, email_subject, email_msg2);
            //re-direct to the profile page insurer
            request.getRequestDispatcher("ProfilePageInsurer.jsp").forward(request, response);
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
            Logger.getLogger(DeclineRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeclineRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(DeclineRequest.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DeclineRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DeclineRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(DeclineRequest.class.getName()).log(Level.SEVERE, null, ex);
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

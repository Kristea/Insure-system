/**
 * ConfirmAgreement.java; Called upon an Insurer confirming an insurance
 * request. Retrieves all the attributes required to create an Agreement object
 * from OverviewPage_Insurer.jsp and creates an Agreement.
 */
package Servlet;

import Controller.InsuranceController;
import Controller.UserController;
import Model.Agreement;
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

@WebServlet(name = "ConfirmAgreement", urlPatterns = {"/ConfirmAgreement"})
public class ConfirmAgreement extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //creating a session, if one does not already exist
            HttpSession session = request.getSession(true);

            InsuranceController insCon = new InsuranceController();
            UserController userCon = new UserController();

            //required attributes
            String insuredEmail = request.getParameter("insuredEmail");
            String insuredUsername = request.getParameter("insuredUsername");
            String insurerEmail = request.getParameter("insurerEmail");
            String insReq = request.getParameter("insReq");
            String reqID = request.getParameter("reqID");
            String gadgetType = request.getParameter("gadgetType");
            double agreedVal = Double.parseDouble(request.getParameter("agreedVal"));
            String typeOfCover = request.getParameter("typeOfCover");
            int coverPeriod = Integer.parseInt(request.getParameter("coverPeriod"));
            String gadgetCondition = request.getParameter("condition");

            //getting insurer currently logged in
            Insurer insurer = (Insurer) session.getAttribute("insurer");
            Insured insured = userCon.getInsured(insuredUsername);//getting the insured user involved
            //creating Agreement object
            Agreement agreement = new Agreement(reqID, insured, insurer, insReq, gadgetType, agreedVal, typeOfCover, coverPeriod,
                    gadgetCondition, Agreement.Status.AGREED, false, false);
            //add Agreement to the database
            insCon.addAgreement(agreement);

            //the the Request from which the agreement derived from to update its status for each user
            Request req = insCon.getOneRequest(reqID);
            req.setStatus(Request.Status.AGREED);
            insCon.updateRequest_Status(req);

            insurer.getAgreements().add(agreement);
            for (Request r : insurer.getRequests()) {
                if (r.getAgreement_id().contains(reqID)) {
                    r.setStatus(Request.Status.AGREED);
                }
            }

            insured.getAgreements().add(agreement);
            for (Request r : insured.getRequests()) {
                if (r.getAgreement_id().contains(reqID)) {
                    r.setStatus(Request.Status.AGREED);
                }
            }
            //formatting prices to 2 decimal places
              String val = String.format("%.02f", req.getInsReq().getValue());
              String aVal = String.format("%.02f", agreement.getAgreedVal());
                 
            //sending email to insured to notify that insurer has accepted the request
            String email_subject = "Insurance Request Accepted ref: "  + req.getInsReq().getInsReq_id();
            String email_msg = "<p>Dear " + req.getInsured().getfName() + " " + req.getInsured().getsName() + ",</p>"
                    + req.getInsurer().getTitle() + " " + req.getInsurer().getfName() + " " + req.getInsurer().getsName()
                    + " has accepted your insurance request (Request ID: " + req.getAgreement_id() + "). Please see the full insurance Agreement below. </p><br/>"
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
                    + "<p><b>Agreement Terms </b></p>"
                    + "<p>Agreement ID: " + agreement.getAgreement_id() + "<br/>"
                    + "Insurer: " + agreement.getInsurer().getTitle() + " " + agreement.getInsurer().getfName() + " " + agreement.getInsurer().getsName() + "<br/>"
                    + "Gadget Type: " + agreement.getGadgetType() + "<br/>"
                    + "Value Insured: £" + aVal + "<br/>"
                    + "Type Of Cover: " + agreement.getTypeOfCover() + "<br/>"
                    + "Cover Period: " + agreement.getTypeOfCover() + " months<br/>"
                    + "Condition of Gadget Accepted: " + agreement.getGadgetCondition() + "<br/>"
                    +" <p>Please remember to give " + req.getInsurer().getfName() + " feedback on your Overview Page for this agreement.</p>"
                    + "<p>Should you have any queries regarding your insurance do not hesitate to contact us and we will respond within 24 hours.</p>"
                    + "<p>Best wishes, <br/> SureInsure Team";
            
               String email_msg2 = "<p>Dear " + req.getInsurer().getfName() + " " + req.getInsurer().getsName() + ",</p>"
                    + " you have accepted "
                    + req.getInsured().getTitle() + " " + req.getInsured().getfName() + " " + req.getInsured().getsName()
                    + "'s insurance request (Request ID: " + req.getAgreement_id() + "). </p><br/>"
                    + "<p>For details of this request please refer to your Overview Page</p>"
                    + "<p>Please remember to give " + req.getInsured().getfName() + " feedback on your Overview Page for this request.</p>"
                    + "<p>Do not hesitate to contact us should you have any queries and we will respond within 24 hours.</p>"
                    + "<p>Best wishes, <br/> SureInsure Team";
            insCon.sendEmail_AgreementToInsured(insuredEmail, insurerEmail, email_subject, email_msg);
            insCon.sendEmail_AgreementToInsurer(insuredEmail, insurerEmail, email_subject, email_msg);

            //re-direct to their profile page.
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
            Logger.getLogger(ConfirmAgreement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmAgreement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ConfirmAgreement.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConfirmAgreement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConfirmAgreement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(ConfirmAgreement.class.getName()).log(Level.SEVERE, null, ex);
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

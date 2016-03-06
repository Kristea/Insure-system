/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controller.DatabaseController;
import Controller.InsuranceController;
import Controller.UserController;
import Model.Insured;
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

/**
 *
 * @author user
 */
@WebServlet(name = "SendRequest", urlPatterns = {"/SendRequest"})
public class SendRequest extends HttpServlet {

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
            throws ServletException, IOException, MessagingException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession(true);

            InsuranceController insCon = new InsuranceController();
            UserController userCon = new UserController();
            DatabaseController db = new DatabaseController();
            //generating email
            String insuredEmail = request.getParameter("insuredEmail");
            String insurerEmail = request.getParameter("insurerEmail");
            String request_rtid = request.getParameter("request_rtid");
            String subject = "Insurance Request - ref: " + request_rtid;
            String email_msg;
            String insurerUsername = request.getParameter("insurerUsername");
            String insuredUsername = request.getParameter("insuredUsername");
            String insReqID = request.getParameter("insReqID");
            String isPMatch = request.getParameter("perfect_match");
         
            boolean isPerfectMatch = false;
            if (isPMatch.contains("true")) {
                isPerfectMatch = true;
            }

            insCon.updateNotif("Y", insurerUsername);
            Request req = insCon.newRequest(insurerUsername, insuredUsername, insReqID, request_rtid, isPerfectMatch,"", false, false);

            Insured insured = (Insured) session.getAttribute("insured");
            insured.getRequests().add(req);

            String val = String.format("%.02f", req.getInsReq().getValue());//format gadget value to 2 decimal places
            email_msg = "<p>Dear " + req.getInsurer().getfName() + " " + req.getInsurer().getsName() + ",</p>"
                    + req.getInsured().getTitle() + " " + req.getInsured().getfName() + " " + req.getInsured().getsName()
                    + " has sent you an insurance request. This request refers to your risk threshold " + req.getRiskT().getRiskT_id() + ". Please see their insurance requirements below. </p><br/>"
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
                    + "<p>Login to your account on SureInsure to respond to this request. Please note any negotiations regarding terms of insurance"
                    + " are done outside of the system and you as the potential insurer have the responsibility to inform us of any changes that are made to your requirements by logging"
                    + " in to your account.</p>"
                    + "<p>Do not hesitate to contact us should you have any queries and we will respond within 24 hours.</p>"
                    + "<p>Best wishes, <br/> SureInsure Team";

            insCon.sendEmal_RequestInfoInsurer(insurerEmail, insuredEmail, subject, email_msg);//send email
            insCon.addRequest(req);

            request.getRequestDispatcher("ProfilePageInsured.jsp").forward(request, response);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SendRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendRequest at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        } catch (MessagingException ex) {
            Logger.getLogger(SendRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SendRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SendRequest.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (MessagingException ex) {
            Logger.getLogger(SendRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SendRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SendRequest.class.getName()).log(Level.SEVERE, null, ex);
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

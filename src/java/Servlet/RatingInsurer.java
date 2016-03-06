


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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet(name = "RatingInsurer", urlPatterns = {"/RatingInsurer"})
public class RatingInsurer extends HttpServlet {


    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            InsuranceController insCon = new InsuranceController();
            UserController userCon = new UserController();
            
            int newRating = Integer.parseInt(request.getParameter("insurerRating"));
            String reqid = request.getParameter("reqID");
            
            Insured insured = (Insured) session.getAttribute("insured");
            Request req = null;
            for (Request r : insured.getRequests()) {
                if (r.getAgreement_id().contains(reqid)) {
                    req = r;
                }
            }
            for (Agreement a : insured.getAgreements()) {
                if (a.getAgreement_id().contains(reqid)) {
                    a.setInsurerRated(true);
                }
            }
            req.setInsurerRated(true);
            req.getInsurer().calcRating(newRating);
            userCon.updateRating(req, "Insurer");
            insCon.updateRequest_Feedback(req, "Insurer");
            insCon.updatAgreement_Feedback(req, "Insurer");
            request.getRequestDispatcher("OverviewPage_Insured.jsp").forward(request, response);
            
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
            Logger.getLogger(RatingInsurer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RatingInsurer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RatingInsurer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RatingInsurer.class.getName()).log(Level.SEVERE, null, ex);
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

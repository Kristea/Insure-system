package Servlet;

import Controller.InsuranceController;
import Controller.UserController;
import Model.Agreement;
import Model.Insurer;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RatingInsured", urlPatterns = {"/RatingInsured"})
public class RatingInsured extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            InsuranceController insCon = new InsuranceController();
            UserController userCon = new UserController();
            
            int newRating = Integer.parseInt(request.getParameter("insuredRating"));
            String reqid = request.getParameter("reqID");
            
            Insurer insurer = (Insurer) session.getAttribute("insurer");
            Request req = null;
            for (Request r : insurer.getRequests()) {
                if (r.getAgreement_id().contains(reqid)) {
                    req = r;
                }
            }
            for (Agreement a : insurer.getAgreements()) {
                if (a.getAgreement_id().contains(reqid)) {
                    a.setInsuredRated(true);
                }
            }
            req.setInsuredRated(true);
            req.getInsured().calcRating(newRating);
            userCon.updateRating(req, "Insured");
            insCon.updateRequest_Feedback(req, "Insured");
            insCon.updatAgreement_Feedback(req, "Insured");
            request.getRequestDispatcher("OverviewPage_Insurer.jsp").forward(request, response);
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
            Logger.getLogger(RatingInsured.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RatingInsured.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RatingInsured.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RatingInsured.class.getName()).log(Level.SEVERE, null, ex);
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

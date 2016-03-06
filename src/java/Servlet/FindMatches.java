/**
 * FindMatches.java; Retrieves the gadget type, type of cover, cover period and
 * value in order to match against the RiskThresholds of Insurers. Retrieves a
 * list of perfect matches and closest & close matches from the database should
 * no perfect matches be available.
 */
package Servlet;

import Controller.InsuranceController;
import Model.Insurer;
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

@WebServlet(name = "FindMatches", urlPatterns = {"/FindMatches"})
public class FindMatches extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //creating a session, if one does not already exist
            HttpSession session = request.getSession(true);
            InsuranceController insController = new InsuranceController();

            //attributes
            String gadgetType = request.getParameter("type");
            String typeOfCover = request.getParameter("typeOfCover");
            int coverPeriod = Integer.parseInt(request.getParameter("coverPeriod"));
            double value = Double.parseDouble(request.getParameter("value"));
            String insReqID = request.getParameter("insReqID");

            //get perfect matches insurers & their risk thresholds ids
            ArrayList<Insurer> perfectMatches = insController.getPerfectMatches(gadgetType, typeOfCover, coverPeriod, value, request);
            ArrayList<String> perfectMatches_rtid = insController.getMatchingRiskThresholdsIDs(perfectMatches, request);

            //create closest matches & their risk threshold ids
            ArrayList<Insurer> closestMatches = new ArrayList<Insurer>();
            ArrayList<String> closestMatches_rtid = new ArrayList<String>();

            //if perfect matches comes back empty, find closest matches
            if (perfectMatches.isEmpty() == true) {
                closestMatches = insController.getClosestMatches(gadgetType, typeOfCover, value, request);
                closestMatches_rtid = insController.getMatchingRiskThresholdsIDs(closestMatches, request);
            }

             //create close matches & their risk threshold ids
            ArrayList<Insurer> closeMatches = new ArrayList<Insurer>();
            ArrayList<String> closeMatches_rtid = new ArrayList<String>();
            
            //if no perfect & closest matches find close matches
            if (perfectMatches.isEmpty() == true && closestMatches.isEmpty() == true) {
                closeMatches = insController.getCloseMatches(gadgetType, value, request);
                closeMatches_rtid = insController.getMatchingRiskThresholdsIDs(closeMatches, request);
            }

            //set the lists to be accessed in the jsp
            session.setAttribute("perfectMatches", perfectMatches);
            session.setAttribute("perfectMatching_RTID", perfectMatches_rtid);
            session.setAttribute("closestMatches", closestMatches);
            session.setAttribute("closestMatching_rtid", closestMatches_rtid);
            session.setAttribute("closeMatches", closeMatches);
            session.setAttribute("closeMatching_rtid", closeMatches_rtid);
            session.setAttribute("insReqID", insReqID);
            //re-direct to matches page
            request.getRequestDispatcher("Matches.jsp").forward(request, response);
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
            Logger.getLogger(FindMatches.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FindMatches.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FindMatches.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FindMatches.class.getName()).log(Level.SEVERE, null, ex);
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

/**
 * ViewProfiles.java; Given a username from the .jsp page, retrieves the 
 * profile information of the user with that username to display to another
 * .jsp page.
 */
package Servlet;

import Controller.DatabaseController;
import Controller.UserController;
import Model.InsuranceRequirement;
import Model.Insured;
import Model.Insurer;
import Model.RiskThreshold;
import Model.User;
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

@WebServlet(name = "ViewProfile", urlPatterns = {"/ViewProfiles"})
public class ViewProfiles extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            
            UserController userCon = new UserController();
            DatabaseController db = new DatabaseController();
            User user = new User();
            
            //retrieve wanted username of user to view profile
            String username = request.getParameter("username");
            String role = db.getRole(username);//get the role of the user
            
            if(role.contains("Insured")){//checking which role the user has to get the correct profile
                userCon.viewProfileInsured(username, request);
                Insured anInsured = new Insured();
                ArrayList<InsuranceRequirement> requirementsList = userCon.viewInsuranceRequirements(username, request);
                session.setAttribute("user", user);
                session.setAttribute("insured", anInsured);
                session.setAttribute("irList", requirementsList);
                request.getRequestDispatcher("ViewProfile_Insured.jsp").forward(request, response);
            }else if(role.contains("Insurer")){
                userCon.viewProfileInsurer(username, request);
                Insurer insurer = new Insurer();
                ArrayList<RiskThreshold> riskThresholds = userCon.viewRiskThresholds(username, request);
                session.setAttribute("insurer", insurer);
                session.setAttribute("rList", riskThresholds);
                request.getRequestDispatcher("ViewProfile_Insurer.jsp").forward(request, response);
            }
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
            Logger.getLogger(ViewProfiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewProfiles.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ViewProfiles.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewProfiles.class.getName()).log(Level.SEVERE, null, ex);
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

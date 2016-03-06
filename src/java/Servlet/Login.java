/**
 * Login.java; Logs a user into the system by checking retrieving the given
 * username & password to validate it. Upon validation the system checks which
 * role the user has and retrieves the appropriate profile information for the
 * particular user, dispatching the information to the relevant .jsp Page
 */
package Servlet;

import Controller.DatabaseController;
import Controller.InsuranceController;
import Controller.UserController;
import Model.Agreement;
import Model.InsuranceRequirement;
import Model.Insured;
import Model.Insurer;
import Model.Request;
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

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //creating a session, if one does not already exist
            HttpSession session = request.getSession(true);

            UserController userCon = new UserController();
            DatabaseController db = new DatabaseController();
            InsuranceController insCon = new InsuranceController();
            User user = new User();
            
            //retrieving getUsersProfileInformation parameters
            String aUsername = request.getParameter("username");
            String aPassword = request.getParameter("password");
            
            //check if user details are correct, if not do not sign in
            if (userCon.login(aUsername, aPassword) == false) {
                request.setAttribute("errorMessage", "Invalid Username or Password");
                request.getRequestDispatcher("LoginPage.jsp").forward(request, response);
            } else {//else get their profile information
                userCon.getUsersProfileInformation(aUsername, aPassword, request);
                String role = db.getRole(aUsername);//retrieving role

                if (role.contains("Insured")) {//checking which role the user has to get the correct profile
                    Insured anInsured = (Insured) request.getAttribute("insured");
                    ArrayList<Request> requests = insCon.getRequests(aUsername);
                    ArrayList<Agreement> agreements = insCon.getAgreements(aUsername);
                    ArrayList<InsuranceRequirement> requirementsList = userCon.viewInsuranceRequirements(aUsername, request);
                    anInsured.setInsReq(requirementsList);
                    anInsured.setRequests(requests);
                    anInsured.setAgreements(agreements);
                    session.setAttribute("user", user);
                    session.setAttribute("insured", anInsured);
                    session.setAttribute("irList", requirementsList);
                    session.setAttribute("requestsList", requests);
                    session.setAttribute("agreementsList", agreements);
                    request.getRequestDispatcher("ProfilePageInsured.jsp").forward(request, response);
                } else if (role.contains("Insurer")) {
                    Insurer anInsurer = (Insurer) request.getAttribute("insurer");
                    ArrayList<Request> requests = insCon.getRequests(aUsername);
                    ArrayList<Agreement> agreements = insCon.getAgreements(aUsername);
                    ArrayList<RiskThreshold> riskThresholds = userCon.viewRiskThresholds(aUsername, request);
                    anInsurer.setRiskThresholds(riskThresholds);
                    anInsurer.setRequests(requests);
                    anInsurer.setAgreements(agreements);

                    session.setAttribute("user", user);
                    session.setAttribute("insurer", anInsurer);
                    session.setAttribute("rList", riskThresholds);
                    session.setAttribute("requestsList", requests);
                    session.setAttribute("agreementsList", agreements);
                    request.getRequestDispatcher("ProfilePageInsurer.jsp").forward(request, response);
                }
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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

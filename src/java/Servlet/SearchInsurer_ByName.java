/**
 * SearchInsurer_ByName.java; Requests to search database for Insurer users
 * with the first name and surname patterns the user entered in the .jsp page
 * and retrieves a list of all found Insurer users.
 */
package Servlet;

import Controller.UserController;
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

@WebServlet(name = "SearchInsurer_ByName", urlPatterns = {"/SearchInsurer_ByName"})
public class SearchInsurer_ByName extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            UserController userCon = new UserController();

            //retrieve query search attributes from .jsp page
            String name = request.getParameter("name");

            //split name into firstname & surname when it finds the space
            String[] searchQuery = name.split("\\s+");
            
            String surname;
            String firstname;
            //if size = 1, only searching by firstname
            if (searchQuery.length == 1) {
                firstname = searchQuery[0];
                surname = "";
                //capitalize first letter, as all names in database have a capitol letter
                firstname = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
            } else {//else searching by both names
                firstname = searchQuery[0];
                surname = searchQuery[1];
                  //capitalize first letter, as all names in database have a capitol letter
                firstname = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
                surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
            }
            
            //create list of Insured users with Insured list returned 
            ArrayList<Insurer> insurers = userCon.searchInsurer_ByName(firstname, surname, request);

            session.setAttribute("insurers", insurers);
            request.getRequestDispatcher("SearchResult_Insurer.jsp").forward(request, response);
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SearchInsurer_ByName.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SearchInsurer_ByName.class.getName()).log(Level.SEVERE, null, ex);
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

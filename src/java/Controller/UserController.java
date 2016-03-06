/**
 * UserController; deals with processing requests made from the user by
 * interacting with the model components and the DatabaseController
 */
package Controller;

import Model.Gadget;
import Model.InsuranceRequirement;
import Model.Insured;
import Model.Insurer;
import Model.Request;
import Model.RiskThreshold;
import Model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class UserController {

    /**
     * Given an Insured & Gadget objects, pass them to addInsured() in the
     * DatabaseController to add the appropriate information to the database.
     *
     * @param insured : insured user to be added to the database.
     * @param gadget : gagdet associated with the insured user.
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ServletException
     */
    public void addInsured(Insured insured, Gadget gadget) throws SQLException, ClassNotFoundException, ServletException {
        DatabaseController db = new DatabaseController();
        db.addInsured(insured, gadget);
    }

    /**
     * Given an Insurer object & ArrayList of RiskThresholds pass them to
     * addInsurer() in the DatabaseController to add the appropriate information
     * to the database.
     *
     * @param insurer : Insurer to be added to the database.
     * @param rtList : RiskThresholds associated with the Insurer.
     * @throws SQLException
     * @throws ServletException
     * @throws ClassNotFoundException
     */
    public void addInsurer(Insurer insurer, ArrayList<RiskThreshold> rtList) throws SQLException, ServletException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        db.addInsurer(insurer, rtList);
    }

    /**
     * Given a username and password strings, get the role of the user and call
     * the appropriate to get user profile.
     *
     * @param uName :user's username
     * @param pWord :user's password
     * @param request : sessions request
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void getUsersProfileInformation(String uName, String pWord, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        String role = db.getRole(uName);

        if (role.contains("Insured")) {
            viewProfileInsured(uName, request);
        } else if (role.contains("Insurer")) {
            viewProfileInsurer(uName, request);
        }
    }

    /**
     * Given a username & password pass them to login() in the
     * DatabaseController to check if the user with given details exists.
     *
     * @param uName : users username
     * @param password : users password
     * @return :true if user details are correct, false otherwise.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean login(String uName, String password) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        boolean correctLogin = false;
        correctLogin = db.login(uName, password);
        return correctLogin;
    }

    /**
     * Retrieve a User object by calling getUser() in DatabaseController in
     * order to use the information of the object to display profile in jsp.
     *
     * @param uName :user's username used to get the wanted user information
     * @param request
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void viewProfile(String uName, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        User user = db.getUser(uName);
        request.setAttribute("user", user);
    }

    /**
     * Retrieve an Insurer object by calling getInsurer() in the
     * DatabaseController in order to use the information of the object to
     * display profile in jsp
     *
     * @param uName :user's username used to get the wanted user information
     * @param request
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void viewProfileInsurer(String uName, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        Insurer insurer = db.getInsurer(uName);
        request.setAttribute("insurer", insurer);
    }

    /**
     * Retrieve an Insured object by calling getInsured() in the
     * DatabaseController in order to use the information of the object to
     * display profile in jsp
     *
     * @param uName :user's username used to get the wanted user information
     * @param request
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void viewProfileInsured(String uName, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        Insured insured = db.getInsured(uName);

        request.setAttribute("insured", insured);
    }

    /**
     * Retrieve an ArrayList of InsuranceRequirement objects by calling
     * getInsuranceRequirements() in the DatabaseController in order to use the
     * information of the object to display in insured profile in jsp
     *
     * @param uName : user's username used to get the wanted user information
     * @param request
     * @return : ArrayList of InsuranceRequirements of wanted insured user
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList viewInsuranceRequirements(String uName, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        ArrayList<InsuranceRequirement> irList = db.getInsuranceRequirements(uName);
        request.setAttribute("irList", irList);
        return irList;
    }

    /**
     * Retrieve an ArrayList of RiskThreshold objects by calling
     * getRiskThresholds() in the DatabaseController in order to use the
     * information of the object to display in insurer profile in jsp
     *
     * @param uName :user's username used to get the wanted user information
     * @param request
     * @return : ArrayList of RiskThresholds of wanted insurer user
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList viewRiskThresholds(String uName, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        ArrayList<RiskThreshold> riskThresholds = db.getRiskThresholds(uName);

        request.setAttribute("rList", riskThresholds);
        return riskThresholds;
    }

    /**
     * Retrieve an ArrayList of RiskThreshold conditions by calling
     * getGadgetsConditions_Insurer() in the DatabaseController in order to use
     * the information of the object to display in insurer profile in jsp. Each
     * RiskThreshold can have more than one condition of gadget allowed which is
     * chosen by the insurer.
     *
     * @param rt_id :RiskThreshold ID, used to retrieved the correct conditions
     * @param request
     * @return : ArrayList of Strings contains all the conditions for specific
     * RiskThreshold.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList viewRiskThresholdConditions(String rt_id, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        ArrayList<String> gadgetConditions = db.getGadgetsConditions_Insurer(rt_id);

        request.setAttribute("conList", gadgetConditions);
        return gadgetConditions;
    }

    /**
     * Retrieves a list of Users by calling getAllInsurerUsers_ByName() in the
     * DatabaseController passing it the given parameters. The returned User
     * ArrayList will contain Insurers whose firstname contains the pattern
     * given in fname & surname contains the pattern given in sname. For each
     * user, get the insurer information by calling getInsurer() in
     * DatabaseController which returns an Insurer object from the passed in
     * username.
     *
     * @param fname : search pattern for firstname
     * @param sname : search pattern for surname
     * @param request
     * @return : ArrayList of Insurers which have been found
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Insurer> searchInsurer_ByName(String fname, String sname, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        ArrayList<User> users = db.getAllInsurerUsers_ByName(fname, sname);
        ArrayList<Insurer> insurers = new ArrayList<Insurer>();
        //for each user, using the username get the Insurer object; add it to the list of insurers
        for (User u : users) {
            Insurer insurer = db.getInsurer(u.getUsername());
            insurers.add(insurer);
        }
        request.setAttribute("insurers", insurers);
        return insurers;
    }

    /**
     * Retrieves a list of Users by calling getAllInsuredUsers_ByName() in the
     * DatabaseController passing it the given parameters. The returned User
     * ArrayList will contain Insureds whose firstname contains the pattern
     * given in fname & surname contains the pattern given in sname. For each
     * user, get the insured information by calling getInsured() in
     * DatabaseController which returns an Insured object from the passed in
     * username.
     *
     * @param fname : search pattern for firstname
     * @param sname : search pattern for surname
     * @param request
     * @return : ArrayList of Insureds which have been found
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Insured> searchInsured_ByName(String fname, String sname, HttpServletRequest request) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        ArrayList<User> users = db.getAllInsuredUsers_ByName(fname, sname);
        ArrayList<Insured> insureds = new ArrayList<>();
        //for each user, using the username get the Insurer object; add it to the list of insurers
        for (User u : users) {
            Insured insured = db.getInsured(u.getUsername());
            insureds.add(insured);
        }
        request.setAttribute("insureds", insureds);
        return insureds;
    }

    /**
     * Retrieves a list of Users by calling getAllInsurerUsers() in the
     * DatabaseController. The returned User ArrayList will contain Insurers.
     * For each user, get the insurer information by calling getInsurer() in
     * DatabaseController which returns an Insurer object from the passed in
     * username.
     *
     * @param request
     * @return : ArrayList of Insurers which have been found
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Insurer> searchInsurers_all(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        ArrayList<User> users = db.getAllInsurerUsers();
        ArrayList<Insurer> insurers = new ArrayList<Insurer>();
        for (User u : users) {
            Insurer insurer = db.getInsurer(u.getUsername());
            insurers.add(insurer);
        }
        request.setAttribute("insurers", insurers);
        return insurers;
    }

    /**
     * Retrieves a list of Users by calling getAllInsuredUsers() in the
     * DatabaseController. The returned User ArrayList will contain Insured
     * users. For each user, get the insured information by calling getInsured()
     * in DatabaseController which returns an Insured object from the passed in
     * username.
     *
     * @param request
     * @return : ArrayList of Insurers which have been found
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Insured> searchInsureds_all(HttpServletRequest request) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        ArrayList<User> users = db.getAllInsuredUsers();
        ArrayList<Insured> insureds = new ArrayList<Insured>();
        for (User u : users) {
            Insured insured = db.getInsured(u.getUsername());
            insureds.add(insured);
        }
        request.setAttribute("insureds", insureds);
        return insureds;
    }

    /**
     * Given a username passes it to getInsured() in the DatabaseController to
     * retrieved the Insured user with the given username.
     *
     * @param username : given username
     * @return : Insured user with the given username
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Insured getInsured(String username) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        Insured insured = db.getInsured(username);
        return insured;
    }

    /**
     * Given a username passes it to getInsurer() in the DatabaseController to
     * retrieved the Insurer user with the given username.
     *
     * @param username : given username
     * @return : Insured user with the given username
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Insurer getInsurer(String username) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        Insurer insurer = db.getInsurer(username);
        return insurer;
    }

    /**
     * Given the required fields, sends an Email to a user confirming
     * registration & detailing their login details
     *
     * @param toEmail : email of user
     * @param from : from email; the system sureinsure15@gmail.com
     * @param subject : subject of the email
     * @param msg : message body of the email
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendEmail_ConfirmingRegistration(String toEmail, String from, String subject, String msg) throws AddressException, MessagingException {
        toEmail = "claudiasfc18@gmail.com"; //for testing purposes
        from = "sureinsure15@gmail.com";
        try {
            final String fromEmail = from; //senders email
            final String password = "sureinsure123"; // correct password for gmail of sender

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setContent(msg, "text/html");
            message.setSubject(subject);
            // message.setText(msg);
            Transport.send(message);
        } catch (Exception ex) {
            System.out.println("SENDING EMAIL FAILED");
            System.out.println(ex);
        }
    }

     /**
     * Given the required fields, sends an Email to a user confirming
     * registration & detailing their login details
     *
     * @param toEmail : email of user
     * @param from : from email; the system sureinsure15@gmail.com
     * @param subject : subject of the email
     * @param msg : message body of the email
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendEmail_FeedbackReminder(String toEmail, String from, String subject, String msg) throws AddressException, MessagingException {
        toEmail = "claudiasfc18@gmail.com"; //for testing purposes
        from = "sureinsure15@gmail.com";
        try {
            final String fromEmail = from; //senders email
            final String password = "sureinsure123"; // correct password for gmail of sender

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
            props.put("mail.smtp.port", "587"); //TLS Port
            props.put("mail.smtp.auth", "true"); //enable authentication
            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

            //create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                //override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setContent(msg, "text/html");
            message.setSubject(subject);
            // message.setText(msg);
            Transport.send(message);
        } catch (Exception ex) {
            System.out.println("SENDING EMAIL FAILED");
            System.out.println(ex);
        }
    }

     public void updateRating(Request req, String role) throws SQLException, ClassNotFoundException {
          DatabaseController db = new DatabaseController();
          db.updateRating(req, role);    
     }
     
     
     
}

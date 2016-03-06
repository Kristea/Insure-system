/**
 * InsuranceController; deals with processing data relevant to insurance
 * requests made from the user by interacting with the model components and the
 * DatabaseController.
 */
package Controller;

import Model.Agreement;
import Model.InsuranceRequirement;
import Model.Insured;
import Model.Insurer;
import Model.Request;
import Model.RiskThreshold;
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

public class InsuranceController {

    /**
     * Given a type, make and model pass attributes to getGadgetID() in the
     * DatabaseController to retrieve the Gadget ID with those attributes.
     *
     * @param type : type of gadget
     * @param make : make of gadget
     * @param model : model of gadget
     * @return : String, gadgetID
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getGadgetID(String type, String make, String model) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        String id = db.getGadgetID(type, make, model);
        return id;
    }

    /**
     * Given an ID of a gadget, pass attribute to getGadgetPrice() in
     * DatabaseController to retrieve the gadget price from the database.
     *
     * @param gadgetID : id of gadget to get price for
     * @return : the price of the gadget
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public double getGadgetPrice(String gadgetID) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        double price = db.getGadgetPrice(gadgetID);
        return price;
    }

    /**
     * Given an InsuranceRequirement object pass it to addInsuranceRequirement()
     * in the DatabaseController to add it to the database.
     *
     * @param insReq : InsuranceRequirement to be added to the database.
     * @throws ServletException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addInsuranceRequirement(InsuranceRequirement insReq) throws ServletException, SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        db.addInsuranceRequirement(insReq);
    }

    /**
     * Given an Insured username and ID of an InsuranceRequirement pass
     * attributes to addToLink1Table() DatabaseController to add them to the
     * database table Link1. Link1 table serves as link between an Insured user
     * and an InsuranceRequirment.
     *
     * @param insuredUsername : username of Insured user
     * @param ir_id : id of an InsuranceRequirement.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ServletException
     */
    public void addToLink1Table(String insuredUsername, String ir_id) throws ClassNotFoundException, SQLException, ServletException {
        DatabaseController db = new DatabaseController();
        db.addToLink1Table(insuredUsername, ir_id);
    }

    /**
     * Given a RiskThreshold object, pass it to addRiskThreshold in the
     * DatabaseController to add it to the RiskThreshold table.
     *
     * @param riskt : RiskThreshold to be added to the database.
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ServletException
     */
    public void addRiskThreshold(RiskThreshold riskt) throws SQLException, ClassNotFoundException, ServletException {
        DatabaseController db = new DatabaseController();
        db.addRiskThreshold(riskt);
    }

    /**
     * Given an Insurers username and ID of a RiskThreshold pass attributed to
     * addToLink2Table() in DatabaseController to add them to the Link2 table in
     * the database. Link2 table serves as link between an Insurer and a Risk
     * Threshold.
     *
     * @param insurerUsername : username of an Insurer
     * @param rt_id : ID of a Risk Threshold
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ServletException
     */
    public void addToLink2Table(String insurerUsername, String rt_id) throws ClassNotFoundException, SQLException, ServletException {
        DatabaseController db = new DatabaseController();
        db.addToLink2Table(insurerUsername, rt_id);
    }

    /**
     * NOTE: Part of Matching Algorithm Retrieve a list of Insurers which match
     * Given a gadget type, type of cover, cover period and value, call
     * getPerfectMatches() in the DatabaseController passing the given
     * parameters in order to retrieve a list of insurers where the
     * RiskThreshold parameters match the given parameters
     *
     * @param gadgetType : insureds gadget type
     * @param typeOfCover : insureds type of cover
     * @param coverPeriod : insureds cover period
     * @param value : value of insureds gadget to be insured
     * @param request
     * @return :ArrayList of Insurer objects which match the wanted parameters
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Insurer> getPerfectMatches(String gadgetType, String typeOfCover, int coverPeriod, double value, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        ArrayList<Insurer> perfectMatches = db.getPerfectMatches(gadgetType, typeOfCover, coverPeriod, value);
        request.setAttribute("perfectMatches", perfectMatches);
        return perfectMatches;
    }

    /**
     * NOTE: Part of Matching Algorithm. Retrieve a list of Insurers which
     * match. Given a gadget type, type of cover, cover period and value, call
     * getClosestMatches() in the DatabaseController passing the given
     * parameters in order to retrieve a list of insurers where the
     * RiskThreshold parameters match the given parameters
     *
     * @param gadgetType : insureds gadget type
     * @param typeOfCover : insureds type of cover
     * @param value : value of insureds gadget to be insured
     * @param request
     * @return :ArrayList of Insurer objects which match the wanted parameters
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Insurer> getClosestMatches(String gadgetType, String typeOfCover, double value, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        ArrayList<Insurer> closestMatches = db.getClosestMatches(gadgetType, typeOfCover, value);
        request.setAttribute("closesteMatches", closestMatches);
        return closestMatches;
    }

    /**
     * NOTE: Part of Matching Algorithm. Retrieve a list of Insurers which
     * match. Given a gadget type, type of cover, cover period and value, call
     * getCloseMatches() in the DatabaseController passing the given parameters
     * in order to retrieve a list of insurers where the RiskThreshold
     * parameters match the given parameters
     *
     * @param gadgetType : insureds gadget type
     * @param value : value of insureds gadget to be insured
     * @param request
     * @return :ArrayList of Insurer objects which match the wanted parameters
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Insurer> getCloseMatches(String gadgetType, double value, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        ArrayList<Insurer> closeMatches = db.getCloseMatches(gadgetType, value);
        request.setAttribute("closesteMatches", closeMatches);
        return closeMatches;
    }

    /**
     * NOTE: Part of Matching Algorithm. Given a list of matched insurers, call
     * getMatchingRiskThresholds() in DatabaseController passing the given list
     * in order to retrieve a list of all the RiskThreshold ids relevant to the
     * matched insurer
     *
     * @param insurers : ArrayList of Insurers from which RiskThreshold ids are
     * required.
     * @param request
     * @return : ArrayList of Strings containing relevant RiskThreshold ids.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<String> getMatchingRiskThresholdsIDs(ArrayList<Insurer> insurers, HttpServletRequest request) throws SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        ArrayList<String> perfectMatches_rtid = db.getMatchingRiskThresholds(insurers);
        request.setAttribute("perfectMatching_RTID", perfectMatches_rtid);
        return perfectMatches_rtid;
    }

    /**
     * Given the required attributes construct a Request object & add it to the
     * appropriate Requests list of the users involved.
     *
     * @param insurerUsername : username of Insurer involved in Request
     * @param insuredUsername : username of Insured involved in Request
     * @param insReqID : ID of Insurance Requirement in this Request
     * @param riskTID : ID of Risk Threshold in this Request
     * @param isPerfectMatch : boolean which indicates if this request was a
     * perfect match of not.
     * @return Request object.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Request newRequest(String insurerUsername, String insuredUsername, String insReqID, String riskTID, boolean isPerfectMatch,
            String comment, boolean insuredRated, boolean insurerRated) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        UserController userCon = new UserController();

        Insurer insurer = userCon.getInsurer(insurerUsername);
        RiskThreshold riskT = null;
        ArrayList<RiskThreshold> rts = insurer.getRiskThresholds();
        for (RiskThreshold rt : rts) {
            if (rt.getRiskT_id().contains(riskTID)) {
                riskT = rt;
            }
        }

        InsuranceRequirement insReq = null;
        Insured insured = userCon.getInsured(insuredUsername);
        ArrayList<InsuranceRequirement> insReqs = insured.getInsReq();
        for (InsuranceRequirement ir : insReqs) {
            if (ir.getInsReq_id().contains(insReqID)) {
                insReq = ir;
            }
        }
        Request request = new Request(insured, insurer, insReq, riskT, isPerfectMatch, comment, insuredRated, insurerRated);
        insurer.getRequests().add(request);
        insured.getRequests().add(request);

        return request;
    }

    /**
     * Given a value ('Y' or 'N') and a username pass fields to updateNotif() in
     * the DatabaseController to update the attribute Notification for the
     * appropriate user in the database.
     *
     * @param val : value ('Y' or 'N') to update to
     * @param username : username of user to update the Notification for.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updateNotif(String val, String username) throws SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        db.updateNotif(val, username);
    }

    /**
     * Given a Request, pass it to addRequest() in the DatabaseController to add
     * it to the Request table in the database.
     *
     * @param req Request to be added to the database.
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ServletException
     */
    public void addRequest(Request req) throws ClassNotFoundException, SQLException, ServletException {
        DatabaseController db = new DatabaseController();
        db.addRequest(req);
    }

    /**
     * Given a username of a user, pass it to getRequests() in the
     * DatabaseController to retrieved a list of all the Requests from the
     * database for a user with the given username.
     *
     * @param username : username of user to retrieved Requests for.
     * @return : an ArrayList of Requests
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Request> getRequests(String username) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        ArrayList<Request> requests = db.getRequests(username);
        return requests;
    }

    /**
     * Given a Request ID pass it to getRequest() in the DatabaseController to
     * retrieve one request with the given ID from the database.
     *
     * @param reqID : ID of request to retrieve
     * @return : a Request object
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Request getOneRequest(String reqID) throws SQLException, ClassNotFoundException {
        DatabaseController db = new DatabaseController();
        Request req = db.getRequest(reqID);
        return req;
    }

    /**
     * Given a Request object pass it to updateRequest_Status() in the
     * DatabaseController to update the status attribute in the Request table in
     * the database for the given Request.
     *
     * @param req : Request to update the Status for.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateRequest_Status(Request req) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        db.updateRequest_Status(req);
    }

    /**
     * Given a Agreement object pass it to updateAgreement_Status() in the
     * DatabaseController to update the status attribute in the Agreement table
     * in the database for the given Agreement.
     *
     * @param agreement : agreement to update the Status for.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateAgreement_Status(Agreement agreement) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        db.updateAgreement_Status(agreement);
    }

    /**
     * Given the required fields, sends an Email to the Insurer involved in a
     * request to notify them that an Insured user has requested insurance from
     * them. NOTE: only works with GMAIL.
     *
     * @param toEmail : email of insurer
     * @param from : from email; the system sureinsure15@gmail.com
     * @param subject : subject of the email
     * @param msg : message body of the email
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendEmal_RequestInfoInsurer(String toEmail, String from, String subject, String msg) throws AddressException, MessagingException {
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
     * Given the required fields, sends an Email to the Insured involved in a
     * request to notify them that an Insurer user has accepted their insurance
     * request. NOTE: only works with GMAIL.
     *
     * @param toEmail : email of insured
     * @param from : from email; the system sureinsure15@gmail.com
     * @param subject : subject of the email
     * @param msg : message body of the email
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendEmail_AgreementToInsured(String toEmail, String from, String subject, String msg) throws AddressException, MessagingException {
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
     * Given the required fields, sends an Email to the Insured involved in a
     * request to notify them that they have accepted the request & remind to give feedback
     * NOTE: only works with GMAIL.
     *
     * @param toEmail : email of insured
     * @param from : from email; the system sureinsure15@gmail.com
     * @param subject : subject of the email
     * @param msg : message body of the email
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendEmail_AgreementToInsurer(String toEmail, String from, String subject, String msg) throws AddressException, MessagingException {
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
     * Given the required fields, sends an Email to the Insured involved in a
     * request to notify them that an Insurer user has declined their insurance
     * request. NOTE: only works with GMAIL.
     *
     * @param toEmail : email of insured
     * @param from : from email; the system sureinsure15@gmail.com
     * @param subject : subject of the email
     * @param msg : message body of the email
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendEmail_RequestDeclinedtToInsured(String toEmail, String from, String subject, String msg) throws AddressException, MessagingException {
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
     * Given the required fields, sends an Email to the Insurer involved in a
     * request to notify them that they have declined a request NOTE: only works
     * with GMAIL.
     *
     * @param toEmail : email of insurer
     * @param from : from email; the system sureinsure15@gmail.com
     * @param subject : subject of the email
     * @param msg : message body of the email
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendEmail_RequestDeclinedtToInsurer(String toEmail, String from, String subject, String msg) throws AddressException, MessagingException {
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
     * Given an Agreement object, pass it to addAgreement() in the
     * DatabaseController to add the agreement to the Agreement table in the
     * database.
     *
     * @param agreement Agreement to be added to the database.
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ServletException
     */
    public void addAgreement(Agreement agreement) throws SQLException, ClassNotFoundException, ServletException {
        DatabaseController db = new DatabaseController();
        db.addAgreement(agreement);
    }

    /**
     * Given a username pass it to getAgreements() in the DatabaseController
     * which retrieves all the agreements associated with the user with the
     * given username.
     *
     * @param username : username of user to get agreements for.
     * @return : ArrayList og Agreement objects
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Agreement> getAgreements(String username) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        ArrayList<Agreement> agreemeents = db.getAgreements(username);
        return agreemeents;
    }

    public void updateRequest_Feedback(Request req, String role) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        db.updateRating_Feedback(req, role);
    }

    public void updatAgreement_Feedback(Request req, String role) throws ClassNotFoundException, SQLException {
        DatabaseController db = new DatabaseController();
        db.updateAgreement_Feedback(req, role);
    }

}

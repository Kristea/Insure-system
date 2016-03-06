/**
 * Database Controller; deals with persisting and retrieving information to the
 * actual external database
 */
package Controller;

import Model.Agreement;
import Model.Gadget;
import Model.InsuranceRequirement;
import Model.Insured;
import Model.Insurer;
import Model.Request;
import Model.RiskThreshold;
import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;

public class DatabaseController {

    /**
     * Connects to the database
     *
     * @return a database connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection connectDB()
            throws ClassNotFoundException, SQLException {
        String jdbcDriver = "org.postgresql.Driver";
        Class.forName(jdbcDriver);
//        String jdbcUrl = "jdbc:postgresql:postgres";
//        String username = "student";
//        String password = "dbpassword";
       // return (DriverManager.getConnection(jdbcUrl, username, password));
          return (DriverManager.getConnection("jdbc:postgresql://localhost:5432/SureInsure", "postgres", "password"));
    }

    /**
     * Given a username & password, checks the database to see if given details
     * exists
     *
     * @param uName :given username
     * @param password : given password
     * @return : true if user with given details exists, false otherwise
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public boolean login(String uName, String password) throws ClassNotFoundException, SQLException {
        boolean correctDetails = false;//store retrieved value, true or false
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();

        //query returns true or false
        String checkDetails = "SELECT EXISTS(SELECT 1 FROM Users WHERE Username ='" + uName + "' AND Password = '" + password + "') AS Exists";
        ResultSet rs = stm.executeQuery(checkDetails);
        while (rs.next()) {
            correctDetails = rs.getBoolean("Exists");
        }

        return correctDetails;
    }

    /**
     * Retrieves a user from the database using a given username
     *
     * @param uName : username used in query to search database
     * @return : a User object, created with retrieved information
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public User getUser(String uName) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();

        String getProfile;
        getProfile = "SELECT * FROM Users WHERE Username = '" + uName + "'";
        //user attributes
        String username = null;
        String password = null;
        String title = null;
        String email = null;
        String fName = null;
        String sName = null;
        String addressLine1 = null;
        String street = null;
        String city = null;
        String postcode = null;
        String role = null;
        double rating = 0;
        int ratingCount = 0;
        ResultSet resultSet = stm.executeQuery(getProfile);

        while (resultSet.next()) {
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            title = resultSet.getString("title");
            email = resultSet.getString("email");
            fName = resultSet.getString("fName");
            sName = resultSet.getString("sName");
            addressLine1 = resultSet.getString("address1");
            street = resultSet.getString("street");
            city = resultSet.getString("city");
            postcode = resultSet.getString("postcode");
            role = resultSet.getString("role");
            rating = Double.parseDouble(resultSet.getString("rating"));
            ratingCount = Integer.parseInt(resultSet.getString("RatingCount"));
        }

        User u = new User(username, password, email, title, fName, sName, addressLine1, street, city, postcode, role);
        //set rating & ratingCount as they are not passed into the constructor
        u.setRating(rating);
        u.setRatingCount(ratingCount);
        connection.close();
        return u;
    }

    /**
     * Retrieves an insured from the database using a given username
     *
     * @param uName : username used in query to search database
     * @return : an Insured object, created with retrieved information
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Insured getInsured(String uName) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getProfile;
        getProfile = "SELECT * FROM Users WHERE Username = '" + uName + "'";
        //user attributes
        String username = null;
        String password = null;
        String title = null;
        String email = null;
        String fName = null;
        String sName = null;
        String addressLine1 = null;
        String street = null;
        String city = null;
        String postcode = null;
        String role = null;
        double rating = 0;
        int ratingCount = 0;

        //get user details
        ResultSet resultSet = stm.executeQuery(getProfile);
        while (resultSet.next()) {
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            title = resultSet.getString("title");
            email = resultSet.getString("email");
            fName = resultSet.getString("fName");
            sName = resultSet.getString("sName");
            addressLine1 = resultSet.getString("address1");
            street = resultSet.getString("street");
            city = resultSet.getString("city");
            postcode = resultSet.getString("postcode");
            role = resultSet.getString("role");
            rating = Double.parseDouble(resultSet.getString("rating"));
            ratingCount = Integer.parseInt(resultSet.getString("RatingCount"));
        }

        String getInsured = "SELECT * FROM Insured WHERE Username = '" + uName + "'";
        int DayDOB = 0;
        int MonthDOB = 0;
        int YearDOB = 0;

        //get insured details
        ResultSet resultSet2 = stm.executeQuery(getInsured);
        while (resultSet2.next()) {
            DayDOB = resultSet2.getInt("dayDOB");
            MonthDOB = resultSet2.getInt("monthDOB");
            YearDOB = resultSet2.getInt("yearDOB");
        }
        //get list of insurance requirements for an Insured user
        ArrayList<InsuranceRequirement> insReq = getInsuranceRequirements(uName);

        Insured ins = new Insured(DayDOB, MonthDOB, YearDOB, insReq, username, password, email, title, fName, sName, addressLine1, street, city, postcode, role);
        //set rating & ratingCount to values retrived from database
        ins.setRating(rating);
        ins.setRatingCount(ratingCount);
        connection.close();
        return ins;
    }

    /**
     * Retrieves a list of InsuranceRequirements for an insured user given a
     * username
     *
     * @param uName : username used in query to search database
     * @return : an ArrayList of InsuranceRequirements, created with retrieved
     * information
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList getInsuranceRequirements(String uName)
            throws ClassNotFoundException, SQLException {
        ArrayList<InsuranceRequirement> insReq = new ArrayList();
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        int gadget_age;
        String condition;
        double value;
        String environment;

        String getRequirements = "SELECT * FROM InsuranceRequirement JOIN Link1 "
                + "ON InsuranceRequirement.IR_ID = Link1.IR_ID "
                + "WHERE Link1.Insured_Username = '" + uName + "'";

        String insReq_id;
        String gadgetId;
        int coverPeriod;
        Gadget gadget;
        String typeOfCover;
        boolean isSelected = false;
        String isselected;
        boolean show = true;

        ResultSet resultSet = stm.executeQuery(getRequirements);
        while (resultSet.next()) {
            insReq_id = resultSet.getString("ir_id");
            gadgetId = resultSet.getString("gadgetid");
            typeOfCover = resultSet.getString("typeOfCover");
            coverPeriod = resultSet.getInt("coverPeriod");
            isselected = resultSet.getString("isSelected");
            gadget_age = resultSet.getInt("GadgetAge");
            condition = resultSet.getString("condition");
            value = resultSet.getInt("value");
            environment = resultSet.getString("environment");
            show = resultSet.getBoolean("ShowRequirement");

            gadget = getOneGadget(gadgetId);

            if (isselected.contains("true")) {
                isSelected = true;
            } else if (isselected.contains("false")) {
                isSelected = false;
            }

            InsuranceRequirement tmp = new InsuranceRequirement(gadget, typeOfCover, coverPeriod, isSelected, gadget_age, condition, environment, uName, show);
            tmp.setInsReq_id(insReq_id);
            tmp.setValue(value);
            insReq.add(tmp);
        }
        connection.close();
        return insReq;
    }

    /**
     * Retrieves one gadget from the database given a gadget id to search
     *
     * @param gadgetID : gadgetID to find wanted gadget
     * @return : a Gadget object, created with retrieved information
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Gadget getOneGadget(String gadgetID) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();

        String getGadget = "SELECT * FROM Gadget WHERE GadgetID ='" + gadgetID + "'";

        String gadgetId = null;
        String type = null;
        String make = null;
        String model = null;
        int price = 0;

        ResultSet resultSet = stm.executeQuery(getGadget);

        while (resultSet.next()) {
            gadgetId = resultSet.getString("gadgetID");
            type = resultSet.getString("type");
            make = resultSet.getString("make");
            model = resultSet.getString("model");
            price = resultSet.getInt("price");

        }
        Gadget g = new Gadget(gadgetId, type, make, model, price);
        connection.close();
        return g;
    }

    /**
     * Retrieves an insurer from the database using a given username
     *
     * @param uName : username used in query to search database
     * @return : an Insurer object, created with retrieved information
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Insurer getInsurer(String uName) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();

        String getProfile;
        getProfile = "SELECT * FROM Users WHERE Username = '" + uName + "'";
        //user attributes
        String username = null;
        String password = null;
        String title = null;
        String email = null;
        String fName = null;
        String sName = null;
        String addressLine1 = null;
        String street = null;
        String city = null;
        String postcode = null;
        String role = null;
        double rating = 0;
        int ratingCount = 0;

        //getting User information for Insurer
        ResultSet resultSet = stm.executeQuery(getProfile);
        while (resultSet.next()) {
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            title = resultSet.getString("title");
            email = resultSet.getString("email");
            fName = resultSet.getString("fName");
            sName = resultSet.getString("sName");
            addressLine1 = resultSet.getString("address1");
            street = resultSet.getString("street");
            city = resultSet.getString("city");
            postcode = resultSet.getString("postcode");
            role = resultSet.getString("role");
            rating = Double.parseDouble(resultSet.getString("rating"));
            ratingCount = Integer.parseInt(resultSet.getString("RatingCount"));
        }

        //getting information for Insurer
        String getInsurer = "SELECT * FROM Insurer WHERE Username = '" + uName + "'";

        String notif = null;
        ArrayList<RiskThreshold> riskThresholds;

        ResultSet resultSet2 = stm.executeQuery(getInsurer);
        while (resultSet2.next()) {
            notif = resultSet2.getString("notification");
        }

        riskThresholds = getRiskThresholds(uName);//get RiskThreshold for Insured
        Insurer insurer = new Insurer(notif, riskThresholds, username, password, email, title, fName, sName, addressLine1, street, city, postcode, role);
        //set rating & ratingcount as they arent passed in contructor
        insurer.setRating(rating);
        insurer.setRatingCount(ratingCount);
        connection.close();
        return insurer;
    }

    /**
     * Retrieves each RiskThreshold belonging to an Insurer and creates a list
     *
     * @param uName : username of Insurer; used to retrieved wanted
     * RiskThresholds
     * @return : ArrayList of RiskThreshold objects, created with retrieved
     * information
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<RiskThreshold> getRiskThresholds(String uName) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getRT;
        getRT = "SELECT * FROM RiskThreshold JOIN Link2 ON RiskThreshold.RT_ID = Link2.RT_ID WHERE Link2.Insurer_Username ='" + uName + "'";

        //attributes of a RiskThreshold
        String rtID, gadgetType;
        int minPrice, maxPrice;
        String typeOfCover;
        int coverPeriod;
        ArrayList<String> gadgetConditions;

        ArrayList<RiskThreshold> riskThresholds = new ArrayList<>();

        ResultSet resultSet = stm.executeQuery(getRT);
        while (resultSet.next()) {
            rtID = resultSet.getString("rt_id");
            gadgetType = resultSet.getString("GadgetType");
            minPrice = resultSet.getInt("minPrice");
            maxPrice = resultSet.getInt("maxPrice");
            typeOfCover = resultSet.getString("typeOfCover");
            coverPeriod = resultSet.getInt("coverPeriod");

            gadgetConditions = getGadgetsConditions_Insurer(rtID);
            RiskThreshold rt = new RiskThreshold(gadgetType, minPrice, maxPrice, typeOfCover, coverPeriod, gadgetConditions, uName);
            riskThresholds.add(rt);
        }
        connection.close();
        return riskThresholds;
    }

    /**
     * Retrieves all the gadget condition relevant to a RiskThreshold for a
     * particular Insurer. One RiskThreshold can have more than one condition of
     * gadget. (i.e an Insurer is also able to accept to insure a gadget of
     * various different conditions)
     *
     * @param rt_id : RiskThreshold ID used to find relevant gadget conditions
     * @return : ArrayLis of Strings contains all the relevant gadget conditions
     * for a wanted RiskThreshold
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> getGadgetsConditions_Insurer(String rt_id) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();

        String getConditions = "SELECT Gadget_Condition FROM Link3 WHERE RT_ID ='" + rt_id + "'";
        ResultSet resultSet = stm.executeQuery(getConditions);

        String condition;
        ArrayList<String> gadgetConditions = new ArrayList<>();
        while (resultSet.next()) {
            condition = resultSet.getString("Gadget_Condition");
            gadgetConditions.add(condition);
        }
        connection.close();
        return gadgetConditions;
    }

    /**
     * Retrieve the role of a user from database given a username
     *
     * @param uName : username used to find the user in order to get their role
     * @return : String role of User
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getRole(String uName) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getRole = "SELECT Role FROM Users WHERE Username ='" + uName + "'";

        ResultSet resultSet = stm.executeQuery(getRole);
        String role = null;
        while (resultSet.next()) {
            role = resultSet.getString("role");
        }
        connection.close();
        return role;
    }

    /**
     * Given the gadget type, make and model, gets the gadgetID from the
     * database table Gadget
     *
     * @param type : type of gadget
     * @param make : make of gadget
     * @param model : model of gadget
     * @return : gadget ID
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getGadgetID(String type, String make, String model) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getGadgetID = "SELECT GadgetID FROM Gadget WHERE Type ='" + type + "' AND Make ='" + make + "' AND Model ='" + model + "'";

        ResultSet resultSet = stm.executeQuery(getGadgetID);
        String id = null;
        while (resultSet.next()) {
            id = resultSet.getString("GadgetID");
        }
        connection.close();
        return id;
    }

    /**
     * Using the given gadgetID, gets the price of a Gadget from the Database
     * table Gadget
     *
     * @param gadgetID : used to retrieve correct price
     * @return : price of gadget
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public double getGadgetPrice(String gadgetID) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getGadgetPrice = "SELECT Price FROM Gadget WHERE GadgetID ='" + gadgetID + "'";

        ResultSet resultSet = stm.executeQuery(getGadgetPrice);
        double price = 0;
        while (resultSet.next()) {
            price = resultSet.getDouble("price");
        }
        connection.close();
        return price;
    }

    /**
     * Adds a new Insured user to the database
     *
     * @param insured : Insured user to be added
     * @param gadget : Gadget to be associated with Insured user
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ServletException
     */
    public void addInsured(Insured insured, Gadget gadget) throws SQLException, ClassNotFoundException, ServletException {
        Connection con = DatabaseController.connectDB();
        //add to User table
        PreparedStatement ps = con.prepareStatement("INSERT INTO Users VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        try {
            ps.setString(1, insured.getUsername());
            ps.setString(2, insured.getPassword());
            ps.setString(3, insured.getEmail());
            ps.setString(4, insured.getTitle());
            ps.setString(5, insured.getfName());
            ps.setString(6, insured.getsName());
            ps.setString(7, insured.getAddressLine1());
            ps.setString(8, insured.getStreet());
            ps.setString(9, insured.getCity());
            ps.setString(10, insured.getPostcode());
            ps.setString(11, insured.getRole());
            ps.setDouble(12, insured.getRating());
            ps.setInt(13, insured.getRatingCount());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new ServletException("Persist Problem into Users Table", e);
        }

        //add to Insured table
        PreparedStatement ps1 = con.prepareStatement("INSERT INTO Insured VALUES(?,?,?,?)");
        try {
            ps1.setString(1, insured.getUsername());
            ps1.setInt(2, insured.getDayDOB());
            ps1.setInt(3, insured.getMonthDOB());
            ps1.setInt(4, insured.getYearDOB());
            ps1.executeUpdate();
        } catch (Exception e) {
            throw new ServletException("Persist Problem into Insured Table", e);
        }

        for (InsuranceRequirement insReq : insured.getInsReq()) {
            addInsuranceRequirement(insReq);//add each InsuranceRequirement to database
            //add to the Link1 table, which links the Insured & InsuranceRequirement
            PreparedStatement ps2 = con.prepareStatement("INSERT INTO Link1 VALUES (?,?)");
            try {
                ps2.setString(1, insured.getUsername());
                ps2.setString(2, insReq.getInsReq_id());
                ps2.executeUpdate();
            } catch (Exception e) {
                throw new ServletException("Persist Problem into Link1 Table", e);
            }
        }
    }

    /**
     * Adds one InsuranceRequirement to the database table InsuranceRequirement
     *
     * @param insReq : InsuranceRequirement to be added
     * @throws ServletException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addInsuranceRequirement(InsuranceRequirement insReq) throws ServletException, SQLException, ClassNotFoundException {
        Connection con = DatabaseController.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO InsuranceRequirement VALUES(?,?,?,?,?,?,?,?,?,?)");
        try {
            ps.setString(1, insReq.getInsReq_id());
            ps.setString(2, insReq.getGadget().getGadget_id());
            ps.setString(3, insReq.getTypeOfCover());
            ps.setInt(4, insReq.getCoverPeriod());
            ps.setBoolean(5, insReq.isIsSelected());
            ps.setInt(6, insReq.getGadget_age());
            ps.setString(7, insReq.getCondition());
            ps.setDouble(8, insReq.getValue());
            ps.setString(9, insReq.getEnvironment());
            ps.setBoolean(10, insReq.isShow());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new ServletException("Persist Problem into InsuranceRequirement Table", e);
        }

    }

    /**
     * Adds to Link1 table an Insured user's username and the ID of an Insurance
     * Requirement. Link1 table links an Insured user & an InsuranceRequirement
     *
     * @param insuredUsername : insured user's username
     * @param ir_id : ID of Insurance Requirement
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ServletException
     */
    public void addToLink1Table(String insuredUsername, String ir_id) throws ClassNotFoundException, SQLException, ServletException {
        Connection con = DatabaseController.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Link1 VALUES(?,?)");

        try {
            ps.setString(1, insuredUsername);
            ps.setString(2, ir_id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new ServletException("Persist Problem into Link1 Table", e);
        }
    }

    /**
     * Adds to Link2 table an Insurer user's username & the ID of a
     * RiskThreshold. Link2 table links an Insurer to a RiskThreshold
     *
     * @param insurerUsername : Insurer username
     * @param rt_id : ID of RiskThreshold
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ServletException
     */
    public void addToLink2Table(String insurerUsername, String rt_id) throws ClassNotFoundException, SQLException, ServletException {
        Connection con = DatabaseController.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Link2 VALUES(?,?)");

        try {
            ps.setString(1, insurerUsername);
            ps.setString(2, rt_id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new ServletException("Persist Problem into Link2 Table", e);
        }
    }

    /**
     * Adds one Insurer to the database table Insurer & their list of
     * RiskThresholds. Also add both the username & RiskThreshold ID to the
     * Link2 table which serves a link between the two.
     *
     * @param insurer : Insurer user to be added
     * @param rtList : Insurers RiskThreshold list
     * @throws SQLException
     * @throws ServletException
     * @throws ClassNotFoundException
     */
    public void addInsurer(Insurer insurer, ArrayList<RiskThreshold> rtList) throws SQLException, ServletException, ClassNotFoundException {
        Connection con = DatabaseController.connectDB();
        //add to users table
        PreparedStatement ps = con.prepareStatement("INSERT INTO Users VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        try {
            ps.setString(1, insurer.getUsername());
            ps.setString(2, insurer.getPassword());
            ps.setString(3, insurer.getEmail());
            ps.setString(4, insurer.getTitle());
            ps.setString(5, insurer.getfName());
            ps.setString(6, insurer.getsName());
            ps.setString(7, insurer.getAddressLine1());
            ps.setString(8, insurer.getStreet());
            ps.setString(9, insurer.getCity());
            ps.setString(10, insurer.getPostcode());
            ps.setString(11, insurer.getRole());
            ps.setDouble(12, insurer.getRating());
            ps.setInt(13, insurer.getRatingCount());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new ServletException("Persist Problem into Users Table", e);
        }

        //add to insurer table
        PreparedStatement ps1 = con.prepareStatement("INSERT INTO Insurer VALUES(?,?)");
        try {
            ps1.setString(1, insurer.getUsername());
            ps1.setString(2, insurer.getNotification());
            ps1.executeUpdate();
        } catch (Exception e) {
            throw new ServletException("Persist Problem into Insurer Table", e);
        }

        //add each RiskThreshold by passing it to the method to do it & add to Link 2 table
        for (RiskThreshold rt : rtList) {
            addRiskThreshold(rt);
            PreparedStatement ps2 = con.prepareStatement("INSERT INTO Link2 VALUES(?,?)");
            try {
                ps2.setString(1, insurer.getUsername());
                ps2.setString(2, rt.getRiskT_id());
                ps2.executeUpdate();
            } catch (Exception e) {
                throw new ServletException("Persist Problem into Link2 Table", e);
            }
        }
        con.close();
    }

    /**
     * Add a single RiskThreshold to the database table RiskThreshold. For each
     * RiskThreshold, get each condition relevant and add the RiskThreshold ID &
     * each condition to table Link3. Link3 table in the database serves as Link
     * between each RiskThreshold and their accepted conditions.
     *
     * @param riskT : RiskThreshold to be added
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ServletException
     */
    public void addRiskThreshold(RiskThreshold riskT) throws SQLException, ClassNotFoundException, ServletException {
        Connection con = DatabaseController.connectDB();

        //add a RiskThreshold
        PreparedStatement ps = con.prepareStatement("INSERT INTO RiskThreshold VALUES(?,?,?,?,?,?)");
        try {
            ps.setString(1, riskT.getRiskT_id());
            ps.setString(2, riskT.getGadgetType());
            ps.setDouble(3, riskT.getMinPrice());
            ps.setDouble(4, riskT.getMaxPrice());
            ps.setString(5, riskT.getTypeOfCover());
            ps.setInt(6, riskT.getCoverPeriod());
            ps.executeUpdate();

            //adding the RiskThreshold ID & each condition the insurer wants into Link3
            for (String condition : riskT.getGadgetConditions()) {
                PreparedStatement ps1 = con.prepareStatement("INSERT INTO Link3 VALUES(?,?)");
                try {
                    ps1.setString(1, riskT.getRiskT_id());
                    ps1.setString(2, condition);
                    ps1.executeUpdate();
                } catch (Exception e) {
                    throw new ServletException("Persist Problem into Link3 Table", e);
                }
            }
        } catch (SQLException | ServletException e) {
            throw new ServletException("Persist Problem into Risk Threshold Table", e);
        }
    }

    /**
     * NOTE: Part of Matching Algorithm Retrieve a list of Insurers which match
     * the passed criteria.
     *
     * @param gadgetType : gadget type insurer must insure
     * @param typeOfCover :type of cover insurer must insure
     * @param coverPeriod :cover period insurer must insure
     * @param value : value must be between the Insurers accepted MinPrice &
     * MaxPrice.
     * @return : ArrayList of Insurer objects constructed from retrieved
     * information.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Insurer> getPerfectMatches(String gadgetType, String typeOfCover, int coverPeriod, double value) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getPerfectMatch = "SELECT Insurer.Username FROM (Insurer LEFT JOIN Link2 ON Insurer.Username = Link2.Insurer_Username) "
                + "LEFT JOIN RiskThreshold ON RiskThreshold.RT_ID = Link2.RT_ID "
                + "WHERE RiskThreshold.GadgetType = '" + gadgetType + "' "
                + "AND RiskThreshold.TypeOfCover = '" + typeOfCover + "' "
                + "AND RiskThreshold.CoverPeriod = '" + coverPeriod + "' "
                + "AND RiskThreshold.MinPrice <= " + value + " AND RiskThreshold.MaxPrice >= " + value + "";

        String username;//insurers username
        ArrayList<Insurer> perfectMatches = new ArrayList<Insurer>();

        ResultSet resultSet = stm.executeQuery(getPerfectMatch);
        while (resultSet.next()) {
            username = resultSet.getString("Username");
            Insurer insurer = getInsurer(username);//get insurers full information & create new object
            perfectMatches.add(insurer);//add insurer to list of perfect matches
        }
        connection.close();
        return perfectMatches;
    }

    /**
     * NOTE: Part of Matching Algorithm Retrieve a list of Insurers which
     * matched all the passed criteria. Occurs only when there are no perfect
     * matches retrieved.
     *
     * @param gadgetType : gadget type insurer must insure
     * @param typeOfCover : typeOfCover insurer must insure
     * @param value : value must be between the Insurers accepted MinPrice &
     * MaxPrice.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Insurer> getClosestMatches(String gadgetType, String typeOfCover, double value) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getClosestMatch = "SELECT Insurer.Username FROM (Insurer LEFT JOIN Link2 ON Insurer.Username = Link2.Insurer_Username) "
                + "LEFT JOIN RiskThreshold ON RiskThreshold.RT_ID = Link2.RT_ID "
                + "WHERE RiskThreshold.GadgetType = '" + gadgetType + "' "
                + "AND RiskThreshold.TypeOfCover = '" + typeOfCover + "' "
                + "AND RiskThreshold.MinPrice <= " + value + " AND RiskThreshold.MaxPrice >= " + value + "";

        String username;//insurers username
        ArrayList<Insurer> closestMatches = new ArrayList<Insurer>();

        ResultSet resultSet = stm.executeQuery(getClosestMatch);
        while (resultSet.next()) {
            username = resultSet.getString("Username");
            Insurer insurer = getInsurer(username); //get insurers full information & create new object
            closestMatches.add(insurer);//add insurer to closest match list
        }
        connection.close();
        return closestMatches;
    }

    /**
     * NOTE: Part of Matching Algorithm. Retrieve a list of Insurers which
     * matches the gadgetType given or is between the Insurers accepted MinPrice
     * & MaxPrice. Occurs only when there are no perfect & closest matches
     * retrieved.
     *
     * @param gadgetType : gadget type insurer must insure
     * @param value : value must be between the Insurers accepted MinPrice &
     * MaxPrice.
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<Insurer> getCloseMatches(String gadgetType, double value) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getCloseMatch = "SELECT Insurer.Username FROM (Insurer LEFT JOIN Link2 ON Insurer.Username = Link2.Insurer_Username) "
                + "LEFT JOIN RiskThreshold ON RiskThreshold.RT_ID = Link2.RT_ID "
                + "WHERE RiskThreshold.GadgetType = '" + gadgetType + "' "
                + "AND RiskThreshold.MinPrice <= " + value + " AND RiskThreshold.MaxPrice >= " + value + "";

        String username;//insurers username
        ArrayList<Insurer> closeMatches = new ArrayList<Insurer>();
        ResultSet resultSet = stm.executeQuery(getCloseMatch);
        while (resultSet.next()) {
            username = resultSet.getString("Username");
            Insurer insurer = getInsurer(username);//get insurers full information & create new object
            closeMatches.add(insurer);//add insurer to close matches list
        }
        connection.close();
        return closeMatches;
    }

    /**
     * NOTE: Part of Matching Algorithm. Given a list of matched insurers, for
     * each insurer retrieve the relevant risk threshold ids to the match.
     *
     * @param matchInsurer : List of Insurers which have been matched
     * @return : String array of all the relevant RiskThreshold id's
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList<String> getMatchingRiskThresholds(ArrayList<Insurer> matchInsurer) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        ArrayList<String> matchRTID = new ArrayList<String>();

        for (Insurer insurer : matchInsurer) {
            String match_RTID = "SELECT Link2.RT_ID FROM Link2 where Link2.Insurer_Username = '" + insurer.getUsername() + "'";
            String rtid;
            ResultSet resultSet = stm.executeQuery(match_RTID);
            while (resultSet.next()) {
                rtid = resultSet.getString("RT_ID");
                matchRTID.add(rtid);
            }
        }
        connection.close();
        return matchRTID;
    }

    /**
     * Retrieve all the users which are Insurers from the database
     *
     * @return : an ArrayList of Users objects which have as a role Insurer
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<User> getAllInsurerUsers() throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getProfile;
        getProfile = "SELECT * FROM Users WHERE Role = 'Insurer'";

        String username, password, title, email, fName, sName, addressLine1, street, city, postcode, role;
        double rating;
        int ratingCount;

        ArrayList<User> users = new ArrayList<>();
        ResultSet resultSet = stm.executeQuery(getProfile);
        while (resultSet.next()) {
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            title = resultSet.getString("title");
            email = resultSet.getString("email");
            fName = resultSet.getString("fName");
            sName = resultSet.getString("sName");
            addressLine1 = resultSet.getString("address1");
            street = resultSet.getString("street");
            city = resultSet.getString("city");
            postcode = resultSet.getString("postcode");
            role = resultSet.getString("role");
            rating = Double.parseDouble(resultSet.getString("rating"));
            ratingCount = Integer.parseInt(resultSet.getString("RatingCount"));

            //create user with retrieved attributes
            User u = new User(username, password, title, email, fName, sName, addressLine1, street, city, postcode, role);
            //set rating & ratingCount as they are not passed in the constructor
            u.setRating(rating);
            u.setRatingCount(ratingCount);
            users.add(u);//add to users list
        }
        return users;
    }

    /**
     * Retrieve all the users which are Insurers that contain the passed fields
     * fname in their Firstname and sname in their Surname. fname & sname can be
     * full names of partialy completed.
     *
     * @param fname : search pattern for firstname
     * @param sname : search pattern for surname
     * @return : ArrayList of Users containing the wanted pattern in their names
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<User> getAllInsurerUsers_ByName(String fname, String sname) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getProfile;

        if (sname == null) {//if no surname is given, search only on the first name
            getProfile = "SELECT * FROM Users WHERE Role = 'Insurer' AND fname LIKE '%" + fname + "%'";
        } else {//else user both first name & surname to search users
            getProfile = "SELECT * FROM Users WHERE fname LIKE '%" + fname + "%' AND sname LIKE '%" + sname + "%' AND Role = 'Insurer'";
        }

        String username, password, title, email, fName, sName, addressLine1, street, city, postcode, role;
        double rating;
        int ratingCount;
        ArrayList<User> users = new ArrayList<>();

        ResultSet resultSet = stm.executeQuery(getProfile);
        while (resultSet.next()) {
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            title = resultSet.getString("title");
            email = resultSet.getString("email");
            fName = resultSet.getString("fName");
            sName = resultSet.getString("sName");
            addressLine1 = resultSet.getString("address1");
            street = resultSet.getString("street");
            city = resultSet.getString("city");
            postcode = resultSet.getString("postcode");
            role = resultSet.getString("role");
            rating = Double.parseDouble(resultSet.getString("rating"));
            ratingCount = Integer.parseInt(resultSet.getString("RatingCount"));

            //create user with retrieved attributes
            User u = new User(username, password, title, email, fName, sName, addressLine1, street, city, postcode, role);
            //set rating & rating count as they are not passed to the constructor
            u.setRating(rating);
            u.setRatingCount(ratingCount);

            users.add(u);//add to list of found users
        }
        return users;
    }

    /**
     * Retrieve all the users which are Insured from the database
     *
     * @return : an ArrayList of Users objects which have as a role Insured
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<User> getAllInsuredUsers() throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getProfile = "SELECT * FROM Users WHERE Role = 'Insured'";

        String username, password, title, email, fName, sName, addressLine1, street, city, postcode, role;
        double rating;
        int ratingCount;
        ArrayList<User> users = new ArrayList<>();

        ResultSet resultSet = stm.executeQuery(getProfile);
        while (resultSet.next()) {
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            title = resultSet.getString("title");
            email = resultSet.getString("email");
            fName = resultSet.getString("fName");
            sName = resultSet.getString("sName");
            addressLine1 = resultSet.getString("address1");
            street = resultSet.getString("street");
            city = resultSet.getString("city");
            postcode = resultSet.getString("postcode");
            role = resultSet.getString("role");
            rating = Double.parseDouble(resultSet.getString("rating"));
            ratingCount = Integer.parseInt(resultSet.getString("RatingCount"));

            //create user with retrieved attributes
            User u = new User(username, password, title, email, fName, sName, addressLine1, street, city, postcode, role);
            //set rating & ratingCount as they are not passed into the constructor
            u.setRating(rating);
            u.setRatingCount(ratingCount);
            users.add(u);//add to list of found users
        }
        return users;
    }

    /**
     * Retrieve all the users which are Insureds that contain the passed fields
     * fname in their Firstname and sname in their Surname.
     *
     * @param fname : search pattern for firstname
     * @param sname : search pattern for surname
     * @return : ArrayList of Users containing the wanted pattern in their names
     * and have as a role Insured.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<User> getAllInsuredUsers_ByName(String fname, String sname) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String getProfile;
        if (sname == null) {//if no surname given, search only by the first name
            getProfile = "SELECT * FROM Users WHERE Role = 'Insured' AND fname LIKE '%" + fname + "%'";
        } else {//else, search by both names
            getProfile = "SELECT * FROM Users WHERE fname LIKE '%" + fname + "%' AND sname LIKE '%" + sname + "%' AND Role = 'Insured'";
        }

        String username, password, title, email, fName, sName, addressLine1, street, city, postcode, role;
        double rating;
        int ratingCount;
        ArrayList<User> users = new ArrayList<>();

        ResultSet resultSet = stm.executeQuery(getProfile);
        while (resultSet.next()) {
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            title = resultSet.getString("title");
            email = resultSet.getString("email");
            fName = resultSet.getString("fName");
            sName = resultSet.getString("sName");
            addressLine1 = resultSet.getString("address1");
            street = resultSet.getString("street");
            city = resultSet.getString("city");
            postcode = resultSet.getString("postcode");
            role = resultSet.getString("role");
            rating = Double.parseDouble(resultSet.getString("rating"));
            ratingCount = Integer.parseInt(resultSet.getString("RatingCount"));

            //create user with retrieved attributes
            User u = new User(username, password, title, email, fName, sName, addressLine1, street, city, postcode, role);
            //set rating & rating count as they are not passed to the constructor
            u.setRating(rating);
            u.setRatingCount(ratingCount);
            users.add(u);
        }
        return users;
    }

    /**
     * Updates the Notification attribute for the required user to either 'Y' or
     * 'N' depending on what is passed in val parameter. The correct user to
     * update the value for is found by using the username parameter to find
     * their role.
     *
     * @param val : given char value to update ('Y' or 'N')
     * @param username : username of user to update the notification
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updateNotif(String val, String username) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String role = getRole(username);
        String updateNotif = "UPDATE " + role + " SET Notification = '" + val + "' where Username = '" + username + "';";
        stm.executeUpdate(updateNotif);
    }

    /**
     * Adds a single request to the Request table in the database
     *
     * @param req : given Request to add
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ServletException
     */
    public void addRequest(Request req) throws ClassNotFoundException, SQLException, ServletException {
        Connection con = DatabaseController.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Request VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        try {
            //retrieving the Request attributes
            ps.setString(1, req.getAgreement_id());
            ps.setString(2, req.getInsured().getUsername());
            ps.setString(3, req.getInsurer().getUsername());
            ps.setInt(4, req.getThisDay());
            ps.setInt(5, req.getThisMonth());
            ps.setInt(6, req.getThisYear());
            ps.setString(7, req.getInsReq().getInsReq_id());
            ps.setString(8, req.getRiskT().getRiskT_id());
            ps.setString(9, req.getStatus().toString());
            ps.setBoolean(10, req.isPerfect_match());
            ps.setString(11, req.getComment());
            ps.setBoolean(12, req.isInsuredRated());
            ps.setBoolean(13, req.isInsurerRated());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Persist Problem into Request Table", e);
        }
    }

    /**
     * Retrieves a list of requests for a particular user which has the username
     * passed in the parameter username.
     *
     * @param username :given username of user to retrieve the Requests list for
     * @return : list of Requests.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Request> getRequests(String username) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        DatabaseController db = new DatabaseController();
        String getReq = null;

        String role = db.getRole(username);//get the user role
        //based on the role, contruct the correct query to get the requests
        if (role.contains("Insured")) {
            getReq = "SELECT * FROM Request WHERE Insured_Username ='" + username + "'";
        } else if (role.contains("Insurer")) {
            getReq = "SELECT * FROM Request WHERE Insurer_Username ='" + username + "'";
        }

        //Request attributes
        String req_id, insured_username, insurer_username, isPMatch, comment;
        int day, month, year;
        String ir_id, rt_id, status;
        boolean isPerfectMatch = false;
        boolean insuredRated = false;
        boolean insurerRated = false;

        ArrayList<Request> requests = new ArrayList<>();

        ResultSet resultSet = stm.executeQuery(getReq);
        while (resultSet.next()) {
            req_id = resultSet.getString("A_ID");
            insured_username = resultSet.getString("Insured_Username");
            insurer_username = resultSet.getString("Insurer_Username");
            day = resultSet.getInt("ThisDay");
            month = resultSet.getInt("ThisMonth");
            year = resultSet.getInt("ThisYear");
            ir_id = resultSet.getString("IR_ID");
            rt_id = resultSet.getString("RT_ID");
            status = resultSet.getString("Status");
            isPMatch = resultSet.getString("Perfect_Match");
            comment = resultSet.getString("Comment");
            insuredRated = resultSet.getBoolean("InsuredRated");
            insurerRated = resultSet.getBoolean("InsurerRated");
            //checking what value isPMatch is, true or false
            if (isPMatch.contains("t")) {
                isPerfectMatch = true;
            }

            //get Insured user involved in the Request
            Insured insured = db.getInsured(insured_username);

            //get Insurance Requirement of the Insured involved in the Request
            InsuranceRequirement insReq = null;
            for (InsuranceRequirement ir : insured.getInsReq()) {
                if (ir.getInsReq_id().contains(ir_id)) {
                    insReq = ir;
                }
            }

            //get Insurer user involved in the Request
            Insurer insurer = db.getInsurer(insurer_username);
            //get RiskThreshold of the Insurer involved in the Request
            RiskThreshold rt = null;
            for (RiskThreshold r : insurer.getRiskThresholds()) {
                if (r.getRiskT_id().contains(rt_id)) {
                    rt = r;
                }
            }
            //contruct the Request object
            Request req = new Request(insured, insurer, insReq, rt, isPerfectMatch, comment, insuredRated, insurerRated);
            req.setThisDay(day);
            req.setThisMonth(month);
            req.setThisYear(year);
            req.setAgreement_id(req_id);
            //check status of request & asign it
            if (status.contains("PENDING")) {
                req.setStatus(Request.Status.PENDING);
            } else if (status.contains("AGREED")) {
                req.setStatus(Request.Status.AGREED);
            } else if (status.contains("CANCELLED")) {
                req.setStatus(Request.Status.CANCELLED);
            }
            requests.add(req);//add to the Request list
        }
        connection.close();
        return requests;
    }

    /**
     * Retrieves a particular Request with the given Request ID from the
     * database.
     *
     * @param reqID : given request ID of wanted Request.
     * @return : a Request object
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Request getRequest(String reqID) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        DatabaseController db = new DatabaseController();

        String getReq = "SELECT * FROM Request WHERE A_ID ='" + reqID + "'";

        //Request attributes
        String req_id, insured_username, insurer_username, isPMatch, comment;
        int day, month, year;
        String ir_id, rt_id, status;
        boolean isPerfectMatch = false;
        boolean insuredRated = false;
        boolean insurerRated = false;
        Request req = null;

        ResultSet resultSet = stm.executeQuery(getReq);
        while (resultSet.next()) {
            req_id = resultSet.getString("A_ID");
            insured_username = resultSet.getString("Insured_Username");
            insurer_username = resultSet.getString("Insurer_Username");
            day = resultSet.getInt("ThisDay");
            month = resultSet.getInt("ThisMonth");
            year = resultSet.getInt("ThisYear");
            ir_id = resultSet.getString("IR_ID");
            rt_id = resultSet.getString("RT_ID");
            status = resultSet.getString("Status");
            isPMatch = resultSet.getString("Perfect_Match");
            comment = resultSet.getString("Comment");
            insuredRated = resultSet.getBoolean("InsuredRated");
            insurerRated = resultSet.getBoolean("InsurerRated");
            //checking if this request was a perfect match, true or false
            if (isPMatch.contains("true")) {
                isPerfectMatch = true;
            }

            //get Insured user involved in this Request
            Insured insured = db.getInsured(insured_username);

            //get Insurance Requirement of Insured involved in this Request
            InsuranceRequirement insReq = null;
            for (InsuranceRequirement ir : insured.getInsReq()) {
                if (ir.getInsReq_id().contains(ir_id)) {
                    insReq = ir;
                }
            }
            //get Insurer user involved in this Request
            Insurer insurer = db.getInsurer(insurer_username);
            //get RiskThreshold of Insurer involved in this request
            RiskThreshold rt = null;
            for (RiskThreshold r : insurer.getRiskThresholds()) {
                if (r.getRiskT_id().contains(rt_id)) {
                    rt = r;
                }
            }
            //Create Request object & set required values
            req = new Request(insured, insurer, insReq, rt, isPerfectMatch, comment, insuredRated, insurerRated);
            req.setThisDay(day);
            req.setThisMonth(month);
            req.setThisYear(year);
            req.setAgreement_id(req_id);

            if (status.contains("PENDING")) {
                req.setStatus(Request.Status.PENDING);
            } else if (status.contains("AGREED")) {
                req.setStatus(Request.Status.AGREED);
            } else if (status.contains("CANCELLED")) {
                req.setStatus(Request.Status.CANCELLED);
            }
        }
        connection.close();
        return req;
    }

    /**
     * Updates the Status attribute of a request in the Request table of the
     * database.
     *
     * @param req : Request for which the Status needs to be updated for.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateRequest_Status(Request req) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement updateReq = connection.createStatement();

        String updateReqStatus = "UPDATE Request SET Status = '" + req.getStatus().toString() + "'";
        updateReq.executeUpdate(updateReqStatus);
    }

    /**
     * Updates the Status attribute of a agreement in the Agreement table of the
     * database.
     *
     * @param agreement : Agreement for which the Status needs to be updated
     * for.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void updateAgreement_Status(Agreement agreement) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement updateAgreement = connection.createStatement();

        String updateAgreementStatus = "UPDATE Agreement SET Status = '" + agreement.getStatus().toString() + "'";
        updateAgreement.executeUpdate(updateAgreementStatus);
    }

    /**
     * Adds an agreement to the Agreement table in the database.
     *
     * @param agreement : Agreement to be added to the database
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws ServletException
     */
    public void addAgreement(Agreement agreement) throws SQLException, ClassNotFoundException, ServletException {
        Connection con = DatabaseController.connectDB();
        PreparedStatement ps = con.prepareStatement("INSERT INTO Agreement VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        try {
            //getting attributes of given agreement to be added
            ps.setString(1, agreement.getAgreement_id());
            ps.setString(2, agreement.getInsured().getUsername());
            ps.setString(3, agreement.getInsurer().getUsername());
            ps.setInt(4, agreement.getThisDay());
            ps.setInt(5, agreement.getThisMonth());
            ps.setInt(6, agreement.getThisYear());
            ps.setString(7, agreement.getInsReqID());
            ps.setString(8, agreement.getGadgetType());
            ps.setDouble(9, agreement.getAgreedVal());
            ps.setString(10, agreement.getTypeOfCover());
            ps.setInt(11, agreement.getCoverPeriod());
            ps.setString(12, agreement.getGadgetCondition());
            ps.setString(13, agreement.getStatus().toString());
            ps.setBoolean(14, agreement.isInsuredRated());
            ps.setBoolean(15, agreement.isInsurerRated());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Persist Problem into Agreement Table", e);
        }
    }

    /**
     * Retrieves the list of Agreements for a particular user with the given
     * username.
     *
     * @param username : username of the user to get the agreements for.
     * @return : a list of Agreements for a user
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<Agreement> getAgreements(String username) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        DatabaseController db = new DatabaseController();
        String getAgreements = null;

        String role = db.getRole(username);//get the role of the user
        //based on the role, contruct the correct query
        if (role.contains("Insured")) {
            getAgreements = "SELECT * FROM Agreement WHERE Insured_Username ='" + username + "'";
        } else if (role.contains("Insurer")) {
            getAgreements = "SELECT * FROM Agreement WHERE Insurer_Username ='" + username + "'";
        }

        //attributes of an Agreement
        String a_id, insured_username, insurer_username, ir_id, gadgetType, typeOfCover,
                gadgetCondition, status;
        int day, month, year, coverPeriod;
        double agreedValue;
        boolean insuredRated = false;
        boolean insurerRated = false;
        ArrayList<Agreement> agreements = new ArrayList<>();

        ResultSet resultSet = stm.executeQuery(getAgreements);
        while (resultSet.next()) {
            a_id = resultSet.getString("A_ID");
            insured_username = resultSet.getString("Insured_Username");
            insurer_username = resultSet.getString("Insurer_Username");
            day = resultSet.getInt("ThisDay");
            month = resultSet.getInt("ThisMonth");
            year = resultSet.getInt("ThisYear");
            ir_id = resultSet.getString("IR_ID");
            gadgetType = resultSet.getString("GadgetType_RT");
            agreedValue = resultSet.getDouble("AgreedValue");
            typeOfCover = resultSet.getString("TypeOfCover_RT");
            coverPeriod = resultSet.getInt("CoverPeriod_RT");
            gadgetCondition = resultSet.getString("GadgetCondition");
            status = resultSet.getString("Status");
            insuredRated = resultSet.getBoolean("InsuredRated");
            insurerRated = resultSet.getBoolean("InsurerRated");

            Insured insured = db.getInsured(insured_username);//Insured user involved in Agreement
            Insurer insurer = db.getInsurer(insurer_username);//Insurer user involced in Agreement
            //create Agreement object & set appropriate fields
            Agreement agreement = new Agreement(a_id, insured, insurer, ir_id, gadgetType, agreedValue, typeOfCover, coverPeriod,
                    gadgetCondition, Agreement.Status.AGREED, insuredRated, insurerRated);

            agreement.setThisDay(day);
            agreement.setThisMonth(month);
            agreement.setThisYear(year);

            if (status.contains("AGREED")) {
                agreement.setStatus(Agreement.Status.AGREED);
            } else if (status.contains("CANCELLED")) {
                agreement.setStatus(Agreement.Status.CANCELLED);
            }
            agreements.add(agreement);//add agreement to list
        }
        connection.close();
        return agreements;
    }

    public void updateRating(Request req, String role) throws SQLException, ClassNotFoundException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String updateRating = "";

        if (role.contains("Insured")) {
            updateRating = "UPDATE Users SET Rating = " + req.getInsured().getRating() + " "
                    + ", RatingCount = " + req.getInsured().getRatingCount() + " WHERE Username = '" + req.getInsured().getUsername() + "'";
        } else if (role.contains("Insurer")) {
            updateRating = "UPDATE Users SET Rating = " + req.getInsurer().getRating() + " "
                    + ", RatingCount = " + req.getInsurer().getRatingCount() + " WHERE Username ='" + req.getInsurer().getUsername() + "'";

        }
        stm.executeUpdate(updateRating);
    }

    public void updateRating_Feedback(Request req, String role) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String updateRatingStatus = "";

        if (role.contains("Insured")) {
            updateRatingStatus = "UPDATE Request SET InsuredRated = 't'";
        } else if (role.contains("Insurer")) {
            updateRatingStatus = "UPDATE Request SET InsurerRated = 't'";

        }
        stm.executeUpdate(updateRatingStatus);
    }

    public void updateAgreement_Feedback(Request req, String role) throws ClassNotFoundException, SQLException {
        Connection connection = DatabaseController.connectDB();
        Statement stm = connection.createStatement();
        String updateRatingStatus = "";

        if (role.contains("Insured")) {
            updateRatingStatus = "UPDATE Agreement SET InsuredRated = 't'";
        } else if (role.contains("Insurer")) {
            updateRatingStatus = "UPDATE Agreement SET InsurerRated = 't'";

        }
        stm.executeUpdate(updateRatingStatus);
    }

}

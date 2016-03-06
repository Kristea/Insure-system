/**
 * Insurer.java; Models the an Insurer user. Contains fields, 2 sets of
 * constructors and accessor & mutator methods for each field.
 */
package Model;

import java.util.ArrayList;

public class Insurer extends User {
    
    private String notification;
    private ArrayList<RiskThreshold> riskThresholds;
    
    public Insurer(){}

    public Insurer(String notification, ArrayList<RiskThreshold> riskThresholds, String username, String password, 
            String email, String title, String fName, String sName, String addressLine1, String street, String city, 
            String postcode, String role) {
        super(username, password, email, title, fName, sName, addressLine1, street, city, postcode, role);
        this.notification = notification;
        this.riskThresholds = riskThresholds;
    }

    /**
     * @return riskThresholds; ArrayList of RiskThreshold objects belonging to
     * this Insurer user
     */
    public ArrayList<RiskThreshold> getRiskThresholds() {
        return riskThresholds;
    }

    /**
     * Sets riskThresholds list to a given list of RiskThreshold objects
     * @param riskThresholds : given ArrayList of RiskThreshold objects
     */
    public void setRiskThresholds(ArrayList<RiskThreshold> riskThresholds) {
        this.riskThresholds = riskThresholds;
    }

    /**
     * @return true if Insurer has a notifications, false otherwise
     */
    public String getNotification() {
        return notification;
    }

    /**
     * Sets the notifications to either true or false, depending on given value
     * @param notification : given boolean
     */
    public void setNotification(String notification) {
        this.notification = notification;
    }
    
    
    
}

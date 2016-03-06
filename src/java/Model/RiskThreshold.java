/**
 * RiskTheshold.java; Models the a RiskTheshold belonging to an Insurer user.
 * Contains fields, 2 sets of constructors and accessor & mutator methods for
 * each field and a method to generate an ID.
 */
package Model;

import java.util.ArrayList;

public class RiskThreshold {

    private String riskT_id;
    private String gadgetType;
    private double minPrice;
    private double maxPrice;
    private String typeOfCover;
    private int coverPeriod;
    private ArrayList<String> gadgetConditions;
    private String userName;

    public RiskThreshold() {
    }

    public RiskThreshold(String gadgetType, double minPrice, double maxPrice, String typeOfCover, int coverPeriod,
            ArrayList<String> gadgetConditions, String userName) {

        this.gadgetType = gadgetType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.typeOfCover = typeOfCover;
        this.coverPeriod = coverPeriod;
        this.userName = userName;
        this.gadgetConditions = new ArrayList<String>();
        for (String s : gadgetConditions) {
            this.gadgetConditions.add(s);
        }
        generateRiskID();
    }

    /**
     * @return riskT_id ; RiskThreshold ID
     */
    public String getRiskT_id() {
        return riskT_id;
    }

    /**
     * Sets riskT_id to a given ID
     *
     * @param riskT_id : given ID
     */
    public void setRiskT_id(String riskT_id) {
        this.riskT_id = riskT_id;
    }

    /**
     * @return gadgetType; type of gadget accepted by Insurer
     */
    public String getGadgetType() {
        return gadgetType;
    }

    /**
     * Sets gadgetType to a given type
     *
     * @param gadgetType : given type
     */
    public void setGadgetType(String gadgetType) {
        this.gadgetType = gadgetType;
    }

    /**
     * @return minPrice; min price/value of gadget accepted
     */
    public double getMinPrice() {
        return minPrice;
    }

    /**
     * Sets minPrice to given min price
     *
     * @param minPrice : given minPrice
     */
    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * @return maxPrice; max price/value of gadget accepted
     */
    public double getMaxPrice() {
        return maxPrice;
    }

    /**
     * Sets maPrice to a given max price
     *
     * @param maxPrice : given max price
     */
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * Retrieves an ArrayList of conditions. One RiskThreshold can contains more
     * than one gadget condition
     *
     * @return ArrayList of String containing conditions accepted by the Insurer
     * for this RiskThreshold
     */
    public ArrayList<String> getGadgetConditions() {
        return gadgetConditions;
    }

    /**
     * Sets gadgetConditions list to a given ArrayList of conditions
     *
     * @param gadgetConditions : given ArrayList of conditions
     */
    public void setGadgetConditions(ArrayList<String> gadgetConditions) {
        this.gadgetConditions = gadgetConditions;
    }

    /**
     * @return typeOfCover
     */
    public String getTypeOfCover() {
        return typeOfCover;
    }

    /**
     * Sets typeOfCover to a given type of cover
     *
     * @param typeOfCover : type of cover given
     */
    public void setTypeOfCover(String typeOfCover) {
        this.typeOfCover = typeOfCover;
    }

    /**
     * @return coverPeriod
     */
    public int getCoverPeriod() {
        return coverPeriod;
    }

    /**
     * Sets coverPeriod to a given cover period
     *
     * @param coverPeriod : given cover period
     */
    public void setCoverPeriod(int coverPeriod) {
        this.coverPeriod = coverPeriod;
    }

    /**
     * Generates an ID for this RiskThreshold, set to riskT_id. ID is
     * compromised of an abbreviation of the gadgetType and typeOfCover together
     * with the username of the Insurer it belongs to.
     */
    public final void generateRiskID() {
        String strGT = " ";
        switch (this.gadgetType) {
            case "Smartphone":
                strGT = "ST";
                break;
            case "Tablet":
                strGT = "TB";
                break;
            case "Laptop":
                strGT = "LT";
                break;
        }

        String strCT = "";
        switch (this.typeOfCover) {
            case "Accidental Damage":
                strCT = "AD";
                break;
            case ("Theft"):
                strCT = "TF";
                break;
            case ("Breakdown"):
                strCT = "BD";
                break;
            case ("Loss"):
                strCT = "LS";
                break;
        }
        this.riskT_id = userName + strGT + strCT;
    }

}

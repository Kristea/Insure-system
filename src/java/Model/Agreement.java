/**
 * Agreement.java; Models the an Insurance Agreement created upon both parties
 * agree on the terms of insurance. Contains fields, 2 sets of constructors and
 * accessor & mutator methods for each field.
 */
package Model;

import java.util.Calendar;

public class Agreement {

    private String agreement_id;
    private Insured insured;
    private Insurer insurer;
    private int thisYear;
    private int thisMonth;
    private int thisDay;
    private String insReqID;
    private String gadgetType;
    private double agreedVal;
    private String typeOfCover;
    private int coverPeriod;
    private String gadgetCondition;
    private boolean insuredRated;
    private boolean insurerRated;

    public enum Status {

        AGREED, CANCELLED
    }
    private Agreement.Status status;

    public Agreement() {
    }

    public Agreement(String agreement_id, Insured insured, Insurer insurer, String insReqID, String gadgetType, double agreedVal, String typeOfCover, int coverPeriod, 
            String gadgetCondition, Status status, boolean insuredRated, boolean insurerRated) {
        this.agreement_id = agreement_id;
        this.insured = insured;
        this.insurer = insurer;
        this.insReqID = insReqID;
        this.gadgetType = gadgetType;
        this.agreedVal = agreedVal;
        this.typeOfCover = typeOfCover;
        this.coverPeriod = coverPeriod;
        this.gadgetCondition = gadgetCondition;
        this.status = status;
        Calendar today = Calendar.getInstance();
        thisYear = today.get(Calendar.YEAR);
        thisMonth = today.get(Calendar.MONTH) + 1;
        thisDay = today.get(Calendar.DAY_OF_MONTH);
        this.insuredRated = insuredRated;
        this.insurerRated = insurerRated;
    }

    /**
     * @return Gets Agreement ID
     */
    public String getAgreement_id() {
        return agreement_id;
    }

    /**
     * Sets agreement_id to a given agreement id
     *
     * @param agreement_id : given agreement ID
     */
    public void setAgreement_id(String agreement_id) {
        this.agreement_id = agreement_id;
    }

    /**
     * @return an Insured user
     */
    public Insured getInsured() {
        return insured;
    }

    /**
     * Sets insured to a given Insured user object
     *
     * @param insured :given Insured user object
     */
    public void setInsured(Insured insured) {
        this.insured = insured;
    }

    /**
     * @return an Insurer
     */
    public Insurer getInsurer() {
        return insurer;
    }

    /**
     * Sets insurer to a given Insurer user object
     *
     * @param insurer
     */
    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
    }

    /**
     * @return this year
     */
    public int getThisYear() {
        return thisYear;
    }

    /**
     * Sets thisYear to a given year
     *
     * @param thisYear : given year
     */
    public void setThisYear(int thisYear) {
        this.thisYear = thisYear;
    }

    /**
     * @return this month
     */
    public int getThisMonth() {
        return thisMonth;
    }

    /**
     * Sets thisMonth to a given month
     *
     * @param thisMonth : given month
     */
    public void setThisMonth(int thisMonth) {
        this.thisMonth = thisMonth;
    }

    /**
     * @return this day
     */
    public int getThisDay() {
        return thisDay;
    }

    /**
     * Sets thisDay to a given day
     *
     * @param thisDay : given day
     */
    public void setThisDay(int thisDay) {
        this.thisDay = thisDay;
    }

    /**
     * @return insReqID
     */
    public String getInsReqID() {
        return insReqID;
    }

    /**
     * Sets insReqID to a given id
     *
     * @param insReqID : given id
     */
    public void setInsReqID(String insReqID) {
        this.insReqID = insReqID;
    }

    /**
     * @return gadgetType
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
     * @return agreedVal
     */
    public double getAgreedVal() {
        return agreedVal;
    }

    /**
     * Sets agreedVal to a given value
     *
     * @param agreedVal : given value
     */
    public void setAgreedVal(double agreedVal) {
        this.agreedVal = agreedVal;
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
     * @param typeOfCover : given type of cover
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
     * @return gadgetCondition
     */
    public String getGadgetCondition() {
        return gadgetCondition;
    }

    /**
     * Sets gadgetCondition to a given condition
     *
     * @param gadgetCondition : given condition
     */
    public void setGadgetCondition(String gadgetCondition) {
        this.gadgetCondition = gadgetCondition;
    }

    /**
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status to a given status
     *
     * @param status : given status
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
    
        /**
     * @return insuredRated
     */
    public boolean isInsuredRated() {
        return insuredRated;
    }

    /**
     * Sets insuredRated to true if insurer has given feedback, false otherwise
     *
     * @param insuredRated : true if insurer has given feedback, false otherwise
     */
    public void setInsuredRated(boolean insuredRated) {
        this.insuredRated = insuredRated;
    }

    /**
     * @return insurerRated
     */
    public boolean isInsurerRated() {
        return insurerRated;
    }

    /**
     * Sets insurerRated to true if insured has given feedback, false otherwise
     *
     * @param insurerRated : true if insured has given feedback, false otherwise
     */
    public void setInsurerRated(boolean insurerRated) {
        this.insurerRated = insurerRated;
    }
}

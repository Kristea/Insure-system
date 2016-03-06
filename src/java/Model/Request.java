/**
 * Request.java; Models a request made by an insured user to an insurer.
 * Contains fields, 2 sets of constructors and accessor & mutator methods for
 * each field and a method to aid the functionality of a Request object.
 */
package Model;

import java.util.Calendar;

public class Request {

    public enum Status {

        AGREED, CANCELLED, PENDING
    }

    private String agreement_id;
    private Insured insured;
    private Insurer insurer;
    private InsuranceRequirement insReq;
    private RiskThreshold riskT;
    private int thisYear;
    private int thisMonth;
    private int thisDay;
    private Status status;
    private boolean perfect_match;
    private String comment;
    private boolean insuredRated;
    private boolean insurerRated;

    public Request() {
    }

    public Request(Insured insured, Insurer insurer, InsuranceRequirement ins_req, RiskThreshold riskT, boolean perfect_match, 
            String comment, boolean insuredRated, boolean insurerRated) {

        this.insured = insured;
        this.insurer = insurer;
        this.insReq = ins_req;
        this.riskT = riskT;
        Calendar today = Calendar.getInstance();
        thisYear = today.get(Calendar.YEAR);
        thisMonth = today.get(Calendar.MONTH) + 1;
        thisDay = today.get(Calendar.DAY_OF_MONTH);
        status = status.PENDING;
        this.perfect_match = perfect_match;
        this.comment = comment;
        this.insuredRated = insuredRated;
        this.insurerRated = insurerRated;
        generateID();
    }

    /**
     * @return agreement_id
     */
    public String getAgreement_id() {
        return agreement_id;
    }

    /**
     * Sets agreement_id to a given id
     *
     * @param agreement_id : given id
     */
    public void setAgreement_id(String agreement_id) {
        this.agreement_id = agreement_id;
    }

    /**
     * @return Insured object
     */
    public Insured getInsured() {
        return insured;
    }

    /**
     * Sets insured to a given Insured
     *
     * @param insured : given Insured
     */
    public void setInsured(Insured insured) {
        this.insured = insured;
    }

    /**
     * @return an Insurer object
     */
    public Insurer getInsurer() {
        return insurer;
    }

    /**
     * Sets insurer to a given Insurer
     *
     * @param insurer : given Insurer
     */
    public void setInsurer(Insurer insurer) {
        this.insurer = insurer;
    }

    /**
     * @return InsuranceRequirement
     */
    public InsuranceRequirement getInsReq() {
        return insReq;
    }

    /**
     * Sets insReq to a given InsuranceRequirement
     *
     * @param insReq : given InsuranceRequirement
     */
    public void setInsReq(InsuranceRequirement insReq) {
        this.insReq = insReq;
    }

    /**
     * @return thisYear
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
     * @return thisMonth
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
     * @return thisDay
     */
    public int getThisDay() {
        return thisDay;
    }

    /**
     * Sets agreement_id to a given id
     *
     * @param thisDay : given day
     */
    public void setThisDay(int thisDay) {
        this.thisDay = thisDay;
    }

    /**
     * @return a Risk Threshold
     */
    public RiskThreshold getRiskT() {
        return riskT;
    }

    /**
     * Sets riskT to a given RiskThreshold
     *
     * @param riskT : given RiskThreshold
     */
    public void setRiskT(RiskThreshold riskT) {
        this.riskT = riskT;
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
     * Generates the agreement if by merging the InsuranceRequirement ID & the
     * RiskThreshold ID
     */
    public final void generateID() {
        agreement_id = insReq.getInsReq_id() + riskT.getRiskT_id();
    }

    /**
     * @return true if request is perfect match, false otherwise
     */
    public boolean isPerfect_match() {
        return perfect_match;
    }

    /**
     * Sets perfect_match to a given boolean
     *
     * @param perfect_match : given boolean
     */
    public void setPerfect_match(boolean perfect_match) {
        this.perfect_match = perfect_match;
    }

    /**
     * @return comment on request
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment to a given comment
     *
     * @param comment : given comment
     */
    public void setComment(String comment) {
        this.comment = comment;
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

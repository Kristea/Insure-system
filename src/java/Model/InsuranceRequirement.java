/**
 * InsuranceRequirement.java; Models the an InsuranceRequirement belonging to an
 * Insured user. Contains fields, 3 sets of constructors and accessor & mutator
 * methods for each field and a couple of method to aid the functionality of
 * InsuranceRequirement.
 */
package Model;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class InsuranceRequirement {

    private String insReq_id;
    private Gadget gadget;
    private String typeOfCover;
    private int coverPeriod;
    private boolean isSelected;
    private int gadget_age;
    private String condition;
    private double value;
    private String environment;
    private String username;
    private boolean show;

    public InsuranceRequirement() {
    }

    /**
     * Copy Constructor
     *
     * @param ir : InsuranceRequirement to be copied from
     */
    public InsuranceRequirement(InsuranceRequirement ir) {
        this.gadget = ir.gadget;
        this.typeOfCover = ir.typeOfCover;
        this.coverPeriod = ir.coverPeriod;
        this.isSelected = ir.isSelected;
        this.gadget_age = ir.gadget_age;
        this.condition = ir.condition;
        this.environment = ir.environment;
        this.username = ir.username;
        this.insReq_id = ir.insReq_id;
        this.value = ir.value;
        this.show = ir.show;

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.format(value);
    }

    //Contructor
    public InsuranceRequirement(Gadget gadget, String typeOfCover, int coverPeriod,
            boolean isSelected, int gadget_age, String condition, String environment, String username, boolean show) {

        this.gadget = gadget;
        this.typeOfCover = typeOfCover;
        this.coverPeriod = coverPeriod;
        this.isSelected = isSelected;
        this.gadget_age = gadget_age;
        this.condition = condition;
        this.environment = environment;
        this.username = username;
        this.show = show;
        calcValue(); //calculate value of gadget
        generateInsID();//generate an ID for this InsuranceRequirement
    }

    /**
     * @return insReq_id; InsuranceRequirement ID
     */
    public String getInsReq_id() {
        return insReq_id;
    }

    /**
     * Sets insReq_id to a given ID
     *
     * @param insReq_id : given ID
     */
    public void setInsReq_id(String insReq_id) {
        this.insReq_id = insReq_id;
    }

    /**
     * @return a Gadget object
     */
    public Gadget getGadget() {
        return gadget;
    }

    /**
     * Sets gadget (object) to a given Gadget object
     *
     * @param gadget
     */
    public void setGadget(Gadget gadget) {
        this.gadget = gadget;
    }

    /**
     * @return typeOfCover
     */
    public String getTypeOfCover() {
        return typeOfCover;
    }

    /**
     * Sets typeOfCOver to a given type of cover
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
     * @return true if InsuranceRequirement is selected, false otherwise
     */
    public boolean isIsSelected() {
        return isSelected;
    }

    /**
     * Sets isSelected to a given boolean
     *
     * @param isSelected : given boolean, true or false
     */
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * @return gadget_age
     */
    public int getGadget_age() {
        return gadget_age;
    }

    /**
     * Sets gadget_age to a given ageRating
     *
     * @param gadget_age : given ageRating
     */
    public void setGadget_age(int gadget_age) {
        this.gadget_age = gadget_age;
    }

    /**
     * @return condition of the gadget to be insured
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Sets condition (of gadget to be insured) to a give condition).
     *
     * @param condition : given condition
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return value of the gadget to be insured
     */
    public double getValue() {
        
        return value;
    }

    /**
     * Sets the value of a gadget to a given value
     *
     * @param value : given value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * @return environment; environment which the gadget will be used
     */
    public String getEnvironment() {
        return environment;
    }

    /**
     * Sets environment to a given environment
     *
     * @param environment : given environment
     */
    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    /**
     * @return username of Insured user which the this InsuranceRequirement
     * belongs to
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets this username to a given username
     *
     * @param username : given username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return show; true if the Insured user has chosen the
     * InsuranceRequirement to be public, false otherwise
     */
    public boolean isShow() {
        return show;
    }

    /**
     * Sets show to a given boolean, either true or false
     *
     * @param show : given boolean
     */
    public void setShow(boolean show) {
        this.show = show;
    }

    /**
     * Calculates the value of the gadget by giving a rating to the attributes
     * environment, condition and gadget_age and performing a calculation to
     * obtain an adjusted percentage which is multiplied by the gadget price
     */
    public final void calcValue() {
        int envRating = 0;
        int conditionRating = 0;
        int ageRating = 0;

        switch (environment) {
            case "Outdoor":
                envRating = 5;
                break;
            case "Indoor":
                envRating = 1;
                break;
        }

        switch (condition) {
            case "New":
                conditionRating = 1;
                break;
            case "Refurbished":
                conditionRating = 2;
                break;
            case "Used - Like New":
                conditionRating = 3;
                break;
            case "Used - Good":
                conditionRating = 4;
                break;
            case "Used - Fair":
                conditionRating = 5;
                break;
        }

        switch (gadget_age) {
            case 3:
                ageRating = 1;
                break;
            case 6:
                ageRating = 2;
                break;
            case 12:
                ageRating = 3;
                break;
            case 18:
                ageRating = 4;
                break;
            case 19:
                ageRating = 5;
                break;
        }

        //weighting for ageRating=10, weighting for condition=5, weighting for environment = 5
        /*double adjustedPercentage = (1 - ((ageRating * (2 + conditionRating + envRating)) / 20));
         value = gadget.getPrice() * 2; //adjustedPercentage;*/
        double adjustedValue = ((ageRating * 2) + conditionRating + envRating);
        double discount = 1 - (adjustedValue / 20);
        value = gadget.getPrice() * discount;
    }

    /**
     * Generates an ID for InsuranceRequirement, set to insReq_id. ID is
     * compromised of an abbreviation of the type of cover together with the
     * value of the gadget and the username of the Insured user it belongs to
     */
    public final void generateInsID() {
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
        int v = (int) value;
        String val = Integer.toString(v);
        insReq_id = username + val + strCT;
    }

}

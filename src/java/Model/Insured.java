/**
 * Insured.java; Models the an Insured user. Contains fields, 2 sets of
 * constructors and accessor & mutator methods for each field and a method to
 * calculate the users age.
 */
package Model;

import java.util.ArrayList;
import java.util.Calendar;

public class Insured extends User {

    private int dayDOB;
    private int monthDOB;
    private int yearDOB;
    private ArrayList<InsuranceRequirement> insReq;

    public Insured() {
    }

    public Insured(int day, int month, int year, ArrayList<InsuranceRequirement> insReq, String username,
            String password, String email, String title, String fName, String sName, String addressLine1, String street,
            String city, String postcode, String role) {
        super(username, password, email, title, fName, sName, addressLine1, street, city, postcode, role);
        this.dayDOB = day;
        this.monthDOB = month;
        this.yearDOB = year;
        this.insReq = insReq;
    }

    /**
     * @return an ArrayList of the Insured users InsuranceRequirements
     */
    public ArrayList<InsuranceRequirement> getInsReq() {
        return insReq;
    }

    /**
     * Sets insReq list of InsuranceRequirements to a given list
     *
     * @param insReq : given ArrayList
     */
    public void setInsReq(ArrayList<InsuranceRequirement> insReq) {
        this.insReq = insReq;
    }

    /**
     * @return dayDOB; day of birth of the user
     */
    public int getDayDOB() {
        return dayDOB;
    }

    /**
     * Sets dayDOB to a given day
     * @param dayDOB : given day
     */
    public void setDayDOB(int dayDOB) {
        this.dayDOB = dayDOB;
    }

    /**
     * @return monthDOB; month of birth of the user
     */
    public int getMonthDOB() {
        return monthDOB;
    }

    /**
     * Sets monthDOB to a given month
     * @param monthDOB : given month
     */
    public void setMonthDOB(int monthDOB) {
        this.monthDOB = monthDOB;
    }

    /**
     * @return yearDOB; year of birth of user
     */
    public int getYearDOB() {
        return yearDOB;
    }

    /**
     * Sets yearDOB to a given year
     * @param yearDOB : given year
     */
    public void setYearDOB(int yearDOB) {
        this.yearDOB = yearDOB;
    }

    /**
     * Checks if the Insured user if over 18 years old, as that is the minimum
     * age to be able to register.
     * @return : true if user is 18 or over, false otherwise
     */
    public boolean calcAge() {
        int age;
        boolean oldEnough = false;
        Calendar c = Calendar.getInstance();
        
        //getting todays date
        int thisYear = c.get(Calendar.YEAR);
        int thisMonth = c.get(Calendar.MONTH) + 1;
        int thisDay = c.get(Calendar.DAY_OF_MONTH);

        age = thisYear - yearDOB;
        if (age == 18 && thisMonth == monthDOB && thisDay < dayDOB) {
            age = 17;
            oldEnough = true;
        } else if (age < 18) {
            oldEnough = false;
        }
        return oldEnough;
    }

}
